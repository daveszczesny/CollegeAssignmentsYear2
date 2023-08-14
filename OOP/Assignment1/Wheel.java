/*
 * Wheel class
 * calculates the circumference of the wheel
 * simple getters for external classes
 */

public class Wheel {
    // initilize and declare variables
    private String name = "";
    private float radius = 0;
    private float circumference = 0;

    // constructor, takes in name and radius
    public Wheel(String name, float radius){
        this.name = name;
        this.radius = radius;
        circumference = (float)(this.radius * 2 * Math.PI); // circumference of the wheel
    } 

    // getters
    public float getCircumference(){
        return circumference;
    }
    public float getRadius(){
        return radius;
    }
    public String getName(){
        return name;
    }

}
