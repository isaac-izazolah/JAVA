import java.util.Scanner;

class Shapes {
    // Method to calculate the circumference of a circle
    public double calculateCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

// Method to calculate the volume of a sphere
    public double calculateVolume(double radius) {
return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an object of the Shapes class
Shapes shape = new Shapes();

        // Circle calculations
        System.out.print("Enter the radius of the circle: ");
        double circleRadius = scanner.nextDouble();
        double circleCircumference = shape.calculateCircumference(circleRadius);
System.out.println("The circumference of the circle is: " + circleCircumference);

        // Sphere-related calculations
        System.out.print("Enter the radius of the sphere: ");
        double sphereRadius = scanner.nextDouble();
        double sphereVolume = shape.calculateVolume(sphereRadius);
System.out.println("The volume of the sphere is: " + sphereVolume);

        scanner.close();
    }
}

