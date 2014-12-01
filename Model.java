package kaleidoscope;
  
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.Color;
import kaleidoscope.Shape;
  
  
  
/**
 * This is the Model class for a bouncing ball. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author Lucas Tejwani
 * @author Yue Chen
 */
public class Model {
      
    private int size;
    public int originX;
    public int originY;
    private int xPosition;
    private int yPosition;
    private int distanceFromOriginX;
    private int distanceFromOriginY;
    private int xLimit, yLimit;
    private int xDelta;
    private int yDelta;
    private int offset;
    private Random rand;
    private Color color;
    private Shape shape;
    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     * @param shape The type of shape that each Model object is.
     */
      
    public Model(int xLimit, int yLimit, Shape shape) {
          
        setLimits(xLimit, yLimit); // sets limit of window
        setOrigin(); // sets origin of the window by calling setOrigin()
          
        this.rand = new Random();
        this.size = rand.nextInt(70); // randomly selects a size for the circle and square Model objects
        randomizeColor(); // randomly selects a color by calling randomizeColor()
          
        this.xDelta = 5 + rand.nextInt(10);  // randomly selects a 'speed' for horizontal movement between 5 and 15
        this.yDelta =  3 + rand.nextInt(10); // randomly selects a 'speed' for vertical movement between 3 and 13
  
        int maxOffset = Math.min(xLimit, yLimit) - size;
        this.offset = rand.nextInt(maxOffset / 2);
        this.shape = shape;
    }
      
    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - size;
        this.yLimit = yLimit - size;
        xPosition = Math.min(xPosition, xLimit);
        yPosition = Math.min(yPosition, yLimit);
    }
      
    /**
     * Sets the origin of the window based on the size of the window.
     */
    public void setOrigin() {
        this.originX = xLimit / 2;
        this.originY = yLimit / 2;
    }
      
    /**
     * Randomly generates a color and assigns it as an instance variable to each Model object.
     */
    public void randomizeColor() {
        float redValue = rand.nextFloat(); // Generate a random float for redValue
        float greenValue = rand.nextFloat(); // Generate a random float for greenValue
        float blueValue = rand.nextFloat(); // Generate a random float for blueValue
        color = new Color(redValue, greenValue, blueValue);
    }
      
    /**
     * For testing purposes only.
     * Color of actual object will be randomly generated in constructor.
     * @param color The color to set the Model object.
     */
    public void setColor(Color color) {
        this.color = color;
    }
      
    /**
     * @return The Model object's color.
     */
    public Color getColor() {
        return color;
    }
      
    /**
     * For testing purposes only.
     * Offset of actual object will be randomly generated in constructor.
     * @param offset The offset to set the Model object's offset from origin.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
      
    /**
     * @return The Model object's offset from the origin.
     */
    public int getOffset() {
        return offset;
    }
      
    /**
     * For testing purposes only. 
     * Size of actual object will be randomly generated in constructor.
     * @param size The size to set the Model object's size.
     */
    public void setSize(int size) {
        this.size = size;
    }
      
    /**
     * @return The Model object's size.
     */
    public int getSize() {
        return size;
    }
      
    /**
     * @return The Model object's shape.
     */
    public Shape getShape() {
        return shape;
    }
    
    /**
     * For testing purposes only. 
     * @param x The Model object's X position.
     */
    public void setX(int x) {
        this.xPosition = x;
    }
    
    /**
     * @return The Model object's X position.
     */
    public int getX() {
        return xPosition;
    }
  
    /**
     * For testing purposes only. 
     * @param y The Model object's Y position.
     */
    public void setY(int y) {
        this.yPosition = y;
    }
    /**
     * @return The Model object's Y position.
     */
    public int getY() {
        return yPosition;
    }
      
    /**
     * 
     * @return X location on screen - X origin.
     */
    public int getDistanceFromOriginX(){
        distanceFromOriginX = getX() - originX;
        return distanceFromOriginX;
    }
      
    /**
     * 
     * @return Y location on screen - Y origin.
     */
    public int getDistanceFromOriginY(){
        distanceFromOriginY = getY() - originY;
        return distanceFromOriginY;
    }
      
    /**
     * 
     * @return Origin X + X location relative to origin.
     */
    public int getOriginXPlusX(){
        return this.originX + this.getDistanceFromOriginX();
    }
      
    /**
     * 
     * @return Origin X - X location relative to origin.
     */
    public int getOriginXMinusX(){
        return this.originX - this.getDistanceFromOriginX();
    }
      
    /**
     * 
     * @return Origin Y + Y location relative to origin.
     */
    public int getOriginYPlusY(){
        return this.originY + this.getDistanceFromOriginY();
    }
      
    /**
     * 
     * @return Origin Y - Y location relative to origin.
     */
    public int getOriginYMinusY(){
        return this.originY - this.getDistanceFromOriginY();
    }
      
    /**
     * 
     * @return Origin X + Y location relative to origin.
     */
    public int getOriginXPlusY(){
        return this.originX + this.getDistanceFromOriginY();
    }
      
    /**
     * 
     * @return Origin X - Y location relative to origin.
     */
    public int getOriginXMinusY(){
        return this.originX - this.getDistanceFromOriginY();
    }
      
    /**
     * 
     * @return Origin Y + X location relative to origin.
     */
    public int getOriginYPlusX(){
        return this.originY + this.getDistanceFromOriginX();
    }
      
    /**
     * 
     * @return Origin Y - X location relative to origin.
     */
    public int getOriginYMinusX(){
        return this.originY - this.getDistanceFromOriginX();
    }
  
      
    /**
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xDelta = -xDelta;
            xPosition += xDelta;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition += yDelta;
        }
    }
  
}