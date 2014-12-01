package kaleidoscope;
  
import static org.junit.Assert.*;
  
import java.awt.Color;
  
import org.junit.Before;
import org.junit.Test;
  
public class ModelTest {
          
    private Model model1;
    private Model model2;
    private Color color1;
  
    @Before
    public void setUp() throws Exception {
        model1 = new Model(1000, 800, Shape.CIRCLE);
        model2 = new Model(1000, 800, Shape.POLYGON);
        color1 = new Color(100, 100, 100);
    }
  
    @Test
    public void testModel() {
        try {
            Model constructModel1 = new Model(1000, 800, Shape.CIRCLE);
            Model constructModel2 = new Model(500, 400, Shape.SQUARE);
            Model constructModel3 = new Model(100, 100, Shape.POLYGON);
              
  
            assertTrue(constructModel1 != null); // test to ensure that constructor created non-null instance of Model
            assertTrue(constructModel2 != null); // test to ensure that constructor created non-null instance of Model
            assertTrue(constructModel3 != null); // test to ensure that constructor created non-null instance of Model
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
  
    @Test
    public void testRandomizeColor() { // Tests to ensure that randomizeColor() creates valid color
        model1.randomizeColor();
        assertTrue(model1.getColor() != null); 
    }
  
    @Test
    public void testGetColor() { // Sets color to color1 and tests to ensure that getColor() returns correct color.
        model1.setColor(color1); 
        assertEquals(new Color(100, 100, 100), model1.getColor());
    }
  
    @Test
    public void testGetOffset() { // Sets offset of model and makes sure that getOffset() returns correct offset.
        model1.setOffset(100);  
        model2.setOffset(0);
          
        assertEquals(100, model1.getOffset());
        assertEquals(0, model2.getOffset());
    }
  
    @Test
    public void testGetSize() { // Sets size of model and makes sure that getSize() returns correct size.
        model1.setSize(50);
        model2.setSize(100);
          
        assertEquals(50, model1.getSize());
        assertEquals(100, model2.getSize());
    }
  
    @Test
    public void testGetShape() { // Tests that shape of model matches shape when initialized. 
        assertEquals(Shape.CIRCLE, model1.getShape());
        assertEquals(Shape.POLYGON, model2.getShape());
    }
  
    @Test
    public void testGetX() { // Sets the x value of models and tests that getX() returns proper X value.
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(50, model1.getX());
        assertEquals(55, model2.getX());
    }
  
    @Test
    public void testGetY() { // Sets the Y value of models and tests that getY() returns proper Y value.
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(85, model1.getY());
        assertEquals(70, model2.getY());
    }
  
    @Test
    public void testGetDistanceFromOriginX() {
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(-450, model1.getDistanceFromOriginX());
        assertEquals(-445, model2.getDistanceFromOriginX());
    }
  
    @Test
    public void testGetDistanceFromOriginY() {
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(-315, model1.getDistanceFromOriginY());
        assertEquals(-330, model2.getDistanceFromOriginY());
    }
  
    @Test
    public void testGetOriginXPlusX() {
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(50, model1.getOriginXPlusX());
        assertEquals(55, model2.getOriginXPlusX());
    }
  
    @Test
    public void testGetOriginXMinusX() {
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(950, model1.getOriginXMinusX());
        assertEquals(945, model2.getOriginXMinusX());
    }
  
    @Test
    public void testGetOriginYPlusY() {
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(85, model1.getOriginYPlusY());
        assertEquals(70, model2.getOriginYPlusY());
    }
  
    @Test
    public void testGetOriginYMinusY() {
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(715, model1.getOriginYMinusY());
        assertEquals(730, model2.getOriginYMinusY());
    }
  
    @Test
    public void testGetOriginXPlusY() {
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(185, model1.getOriginXPlusY());
        assertEquals(170, model2.getOriginXPlusY());
    }
  
    @Test
    public void testGetOriginXMinusY() {
        model1.setY(85);
        model2.setY(70);
          
        assertEquals(815, model1.getOriginXMinusY());
        assertEquals(830, model2.getOriginXMinusY());
    }
  
    @Test
    public void testGetOriginYPlusX() {
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(-50, model1.getOriginYPlusX());
        assertEquals(-45, model2.getOriginYPlusX());
    }
  
    @Test
    public void testGetOriginYMinusX() {
        model1.setX(50);
        model2.setX(55);
          
        assertEquals(850, model1.getOriginYMinusX());
        assertEquals(845, model2.getOriginYMinusX());
    }
  
  
}