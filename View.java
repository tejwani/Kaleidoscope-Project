package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The View "observes" and displays what is going on in the Model.
 * Each model represents a different figure (either a polygon, a circle, or a square).
 * 
 * @author David Matuszek
 * @author Lucas Tejwani
 * @author Yue Chen
 */
public class View extends JPanel implements Observer {

	private ArrayList<Model> models;
    /**
     * Constructor.
     * @param models The Model whose working is to be displayed.
     */
    View() {}
    
    public void addFigures(ArrayList<Model> models) {
    	this.models = models;
    }

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK); // Sets background to black
        g.fillRect(0, 0, getWidth(), getHeight());
    	for (int i = 0; i < models.size(); i++){ // Iterates through list of models and draws them on screen
    		g.setColor(models.get(i).getColor()); // Sets color of model to be drawn based on randomly assigned color.
    		if (models.get(i).getShape() == Shape.POLYGON){ // Draws polygon objects.
    			createPolygon(g, models.get(i));
    		}
    		else if (models.get(i).getShape() == Shape.CIRCLE){ // Draws circle objects.
    			drawOval(g, models.get(i));
    		}
    		else{ // Draws square objects.
    			drawSquare(g, models.get(i));
    		}
    	}
    }
    
    /**
     * Draws an oval and its 8 reflections.
     * Position of each model is based on offset from origin. 
     * 
     * @param g The Graphics on which to paint things.
     * @param model The model figure that is currently being drawn.
     */
    public void drawOval(Graphics g, Model model){ // Draws Circle objects and its reflections.
    	g.fillOval(model.getOriginXPlusX() + model.getOffset(), 
    			model.getOriginYPlusY() + model.getOffset(),
    			model.getSize(), model.getSize());
        g.fillOval(model.getOriginXMinusX() - model.getOffset(),
        		model.getOriginYMinusY() - model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillOval(model.getOriginXMinusX() - model.getOffset(), 
        		model.getOriginYPlusY() + model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillOval(model.getOriginXPlusX() + model.getOffset(), 
        		model.getOriginYMinusY() - model.getOffset(),
        		model.getSize(), model.getSize());
        
    	g.fillOval(model.getOriginXPlusY() + model.getOffset(), 
    			model.getOriginYPlusX() + model.getOffset(),
    			model.getSize(), model.getSize());
        g.fillOval(model.getOriginXMinusY() - model.getOffset(),
        		model.getOriginYMinusX() - model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillOval(model.getOriginXMinusY() - model.getOffset(), 
        		model.getOriginYPlusX() + model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillOval(model.getOriginXPlusY() + model.getOffset(), 
        		model.getOriginYMinusX() - model.getOffset(),
        		model.getSize(), model.getSize());
    }
    
    /**
     * Draws an square and its 8 reflections.
     * Position of each model is based on offset from origin. 
     * 
     * @param g The Graphics on which to paint things.
     * @param model The model figure that is currently being drawn.
     */
    public void drawSquare(Graphics g, Model model){ // Draws Square objects and its reflections.
    	g.fillRect(model.getOriginXPlusX() + model.getOffset(), 
    			model.getOriginYPlusY() + model.getOffset(),
    			model.getSize(), model.getSize());
        g.fillRect(model.getOriginXMinusX() - model.getOffset(),
        		model.getOriginYMinusY() - model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillRect(model.getOriginXMinusX() - model.getOffset(), 
        		model.getOriginYPlusY() + model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillRect(model.getOriginXPlusX() + model.getOffset(), 
        		model.getOriginYMinusY() - model.getOffset(),
        		model.getSize(), model.getSize());
        
    	g.fillRect(model.getOriginXPlusY() + model.getOffset(), 
    			model.getOriginYPlusX() + model.getOffset(),
    			model.getSize(), model.getSize());
        g.fillRect(model.getOriginXMinusY() - model.getOffset(),
        		model.getOriginYMinusX() - model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillRect(model.getOriginXMinusY() - model.getOffset(), 
        		model.getOriginYPlusX() + model.getOffset(),
        		model.getSize(), model.getSize());
        g.fillRect(model.getOriginXPlusY() + model.getOffset(), 
        		model.getOriginYMinusX() - model.getOffset(),
        		model.getSize(), model.getSize());
    }

    /**
     * Draws an polygon (triangle) and its 8 reflections.
     * Position of each model is based on offset from origin. 
     * 
     * @param g The Graphics on which to paint things.
     * @param model The model figure that is currently being drawn.
     */
    public void createPolygon(Graphics g, Model model){ // Draws Polygon objects and its reflections.
    	g.fillPolygon(new int[]{model.getOriginXPlusX(),
    			model.getOriginXPlusX() + model.getOffset() / 4, 
    			model.getOriginXPlusX() + model.getOffset() / 2},
    			new int[]{model.getOriginYPlusY(),
    			model.getOriginYPlusY() + model.getOffset() / 2,
    			model.getOriginYPlusY() + model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXMinusX(),
    			model.getOriginXMinusX() - model.getOffset() / 4, 
    			model.getOriginXMinusX() - model.getOffset() / 2},
    			new int[]{model.getOriginYMinusY(),
    			model.getOriginYMinusY() - model.getOffset() / 2,
    			model.getOriginYMinusY() - model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXMinusX(),
    			model.getOriginXMinusX() - model.getOffset() / 4, 
    			model.getOriginXMinusX() - model.getOffset() / 2},
    			new int[]{model.getOriginYPlusY(),
    			model.getOriginYPlusY() + model.getOffset() / 2,
    			model.getOriginYPlusY() + model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXPlusX(),
    			model.getOriginXPlusX() + model.getOffset() / 4, 
    			model.getOriginXPlusX() + model.getOffset() / 2},
    			new int[]{model.getOriginYMinusY(),
    			model.getOriginYMinusY() - model.getOffset() / 2,
    			model.getOriginYMinusY() - model.getOffset() / 4}, 3);
    	
    	g.fillPolygon(new int[]{model.getOriginXPlusY(),
    			model.getOriginXPlusY() + model.getOffset() / 4, 
    			model.getOriginXPlusY() + model.getOffset() / 2},
    			new int[]{model.getOriginYPlusX(),
    			model.getOriginYPlusX() + model.getOffset() / 2,
    			model.getOriginYPlusX() + model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXMinusY(),
    			model.getOriginXMinusY() - model.getOffset() / 4, 
    			model.getOriginXMinusY() - model.getOffset() / 2},
    			new int[]{model.getOriginYMinusX(),
    			model.getOriginYMinusX() - model.getOffset() / 2,
    			model.getOriginYMinusX() - model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXMinusY(),
    			model.getOriginXMinusY() - model.getOffset() / 4, 
    			model.getOriginXMinusY() - model.getOffset() / 2},
    			new int[]{model.getOriginYPlusX(),
    			model.getOriginYPlusX() + model.getOffset() / 2,
    			model.getOriginYPlusX() + model.getOffset() / 4}, 3);
    	g.fillPolygon(new int[]{model.getOriginXPlusY(),
    			model.getOriginXPlusY() + model.getOffset() / 4, 
    			model.getOriginXPlusY() + model.getOffset() / 2},
    			new int[]{model.getOriginYMinusX(),
    			model.getOriginYMinusX() - model.getOffset() / 2,
    			model.getOriginYMinusX() - model.getOffset() / 4}, 3);
    }

    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
    

}