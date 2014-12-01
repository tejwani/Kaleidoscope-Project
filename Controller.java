package kaleidoscope;

import kaleidoscope.Shape;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author Lucas Tejwani	
 * @author Yue Chen
 */
public class Controller extends JFrame {
    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton increaseSpeedButton = new JButton("Increase Speed");
    JButton decreaseSpeedButton = new JButton("Decrease Speed");
    JButton addFigureButton = new JButton("Add Figure");
    JButton removeFigureButton = new JButton("Remove Figure");
    JButton flashColorsButton = new JButton("Flash Colors");
    JButton exitButton = new JButton("Exit");
    Timer timer;

    private ArrayList<Model> models;  // initializes ArrayList to control Model objects
    private int timerSpeed = 50;
    private boolean flashOn = false;
    
    /** The View object displays what is happening in the Model. */
    View view;
    
    /**
     * Runs the Kaleidoscope program.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        ArrayList<Model> listOfModels = new ArrayList<Model>();
        c.init(listOfModels);
        c.display();
    }

    /**
     * Sets up communication between the components.
     */
    private void init(ArrayList<Model> listOfModels) {
    	this.models = listOfModels;
        view = new View();  // The view needs to know what model to look at 
    	for (int i = 0; i < 10; i++) { // Creates initial drawing of 10 model objects on screen
    		if (i % 5 == 0) {  // Every 5th item will be a polygon type (triangle-shaped).
    			models.add(new Model(1000, 800, Shape.POLYGON));  // initialize with default X and Y limits (changes when window resized)
    		}
    		else if (i % 2 == 0) { // Every even-numbered item that is not a multiple of 5 will be a circle.
    			models.add(new Model(1000, 800, Shape.CIRCLE)); 
    		}
    		else { // Every odd-numbered item that is not a multiple of 5 will be a square.
    			models.add(new Model(1000, 800, Shape.SQUARE)); 
    		}
    	}
    	view.addFigures(models);
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(1000, 800); // Sets default window size to 1000 x 800.
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     * Sets the all action buttons except Run and Quit to "Disabled".
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        
        buttonPanel.add(stopButton);
        stopButton.setEnabled(false);
        
        buttonPanel.add(increaseSpeedButton);
        increaseSpeedButton.setEnabled(false);
        
        buttonPanel.add(decreaseSpeedButton);
        decreaseSpeedButton.setEnabled(false);
        
        buttonPanel.add(addFigureButton);
        addFigureButton.setEnabled(false);
        
        buttonPanel.add(removeFigureButton);
        removeFigureButton.setEnabled(false);
        
        buttonPanel.add(flashColorsButton);
        flashColorsButton.setEnabled(false);
        
        buttonPanel.add(exitButton);
        
        this.add(BorderLayout.CENTER, view);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
    	
        // The Run button tells the Model to start and enables all action buttons.
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                start();
            	
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                increaseSpeedButton.setEnabled(true);
                decreaseSpeedButton.setEnabled(true);
                addFigureButton.setEnabled(true);
                removeFigureButton.setEnabled(true);
                flashColorsButton.setEnabled(true);
            }
        });
        // The Stop button tells the Model to pause and disables all action buttons.
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                increaseSpeedButton.setEnabled(false);
                decreaseSpeedButton.setEnabled(false);
                addFigureButton.setEnabled(false);
                removeFigureButton.setEnabled(false);
                flashColorsButton.setEnabled(false);
                pause();
            }
        });
        // When the window is resized, the Model is given the new limits and the origin is recalculated.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
            	for (int i = 0;  i < models.size(); i++){
            		models.get(i).setLimits(view.getWidth(), view.getHeight());
            		models.get(i).setOrigin();
            	}
            }
        });
        
        //Increases the speed of the entire Kaleidoscope animation by calling increaseSpeed().
        increaseSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	if (timerSpeed > 5) { // Prevents the user from decreasing animation's period to 0.
            		increaseSpeed();
            		pause();
            		start();
            	}
            	if (timerSpeed <= 5) {  // Disables the increaseSpeed button if timerSpeed approaches 0.
            		increaseSpeedButton.setEnabled(false);
            	}
            	decreaseSpeedButton.setEnabled(true); // Enables the decreaseSpeedButton.
            }
        });
        
        //Decreases the speed of the entire Kaleidoscope animation by calling decreaseSpeed().
        decreaseSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) { // Disables the increaseSpeed button if timerSpeed approaches 100.
            	if (timerSpeed < 100){ // Prevents the user from increasing animation's period beyond 100.
            		decreaseSpeed();
                	pause();
                	start();
            	}
            	if (timerSpeed >= 100) {
            		decreaseSpeedButton.setEnabled(false);
            	}
            	increaseSpeedButton.setEnabled(true); // Enables the increaseSpeed button.
            }
        });
        
        // Adds a figure to the screen by calling addFigure().
        addFigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	addFigure();
            }
        });
        
        // Removes a figure from the screen by calling removeFigure().
        removeFigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	removeFigure();
            }
        });
        
        // Activates the color flash of each figure.
        flashColorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	flashOn = !flashOn;
            }
        });
        
        // Allows the user to exit the program by pressing the Exit button.
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	exit();
            }
        });
    }
    
    /**
     * Tells the ball to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the ball to make one "step."
     */

    // Starts a timer whose period is determined by a variable timerSpeed.
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 5, timerSpeed);      
    }
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * Exits the program. 
     */
    public void exit() {
    	System.exit(0);
    }
    
    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            for (int i = 0; i < models.size(); i++){
            	models.get(i).makeOneStep();
            }
            if (flashOn) changeColor();
            view.repaint();
        }
    }
    
    /**
     * Increases the speed of animation by reducing the period by 5.
     */
    public void increaseSpeed(){
    	timerSpeed -= 5;
    }
    
    /**
     * Decreases the speed of animation by increasing the period by 5.
     */
    public void decreaseSpeed(){
    	timerSpeed += 5;
    }
    
    /**
     * Adds a figure to the screen.
     * Every 5th figure will be a polygon (triangle).
     * Every even-numbered figure that is not a multiple of 5 will be a circle.
     * Every odd-numbered figure that is not a multiple of 5 will be a square.
     */
    public void addFigure(){
    	if (models.size() % 5 == 0) { // Adds new Polygon figure when size of list of models is a multiple of 5.
    		models.add(new Model(view.getWidth(), view.getHeight(), Shape.POLYGON));
    	}
    	else if (models.size() % 2 == 0){ // Adds new Circle figure when size of list of models is even.
    		models.add(new Model(view.getWidth(), view.getHeight(), Shape.CIRCLE));
    	}
    	else { // Adds new Square figure when size of list of models is not even and not a multiple of 5.
    		models.add(new Model(view.getWidth(), view.getHeight(), Shape.SQUARE));
    	}
    	removeFigureButton.setEnabled(true);
    }
    
    /**
     * Pops the last-added figure from the ArrayList models and removes it from the screen.
     */
    public void removeFigure(){
    	if(models.size() > 1){
    		models.remove(models.size() - 1); // pops last item in models ArrayList and removes it from the screen
    	}
    	if (models.size() <= 1) removeFigureButton.setEnabled(false); // prevents user from removing all figures
    }
    
    /**
     * Randomly changes the color of each figure on the screen.
     */
    public void changeColor(){
	    for (int i = 0; i < models.size(); i++){
	    	models.get(i).randomizeColor(); // randomly generates new color for each figure
    	}
    }
}
