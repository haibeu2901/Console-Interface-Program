// BMICalculator class is a utility class that's used to calculate the BMI value in both Imperial and Metric systems
public class BMICalculator {

    // Please declare 4 attributes/properties to represent weight in pounds, height in inches, weight in kilos and height in meters
    private double weightInPounds;
    private double heightInInches;
    private double weightInKilos;
    private double heightInMeters;

    // Please use this default no arguments constructor to initialize the 4 properties to some initial values
    public BMICalculator() {
        this.weightInPounds = 154;
        this.heightInInches = 69;
        this.weightInKilos = 70;
        this.heightInMeters = 1.75;
    }

    // Please implement the calculateBmiImperial() method to take in weight and height to calculate and return the BMI value in the Imperial system
    public double calculateBmiImperial(double weightInPounds, double heightInInches) {
        // Setting the attributes with the values passed in as method arguments
        this.weightInPounds = weightInPounds;
        this.heightInInches = heightInInches;
        // Calculating and returning the BMI value in the Imperial system
        return (this.weightInPounds * 703) / (this.heightInInches * this.heightInInches);
    }

    // Please implement the calculateBmiMetric() method to take in weight and height to calculate and return the BMI value in the Metric system
    public double calculateBmiMetric(double weightInKilos, double heightInMeters) {
        // Setting the attributes with the values passed in as method arguments
        this.weightInKilos = weightInKilos;
        this.heightInMeters = heightInMeters;
        // Calculating and returning the BMI value in the Metric system
        return this.weightInKilos / (this.heightInMeters * this.heightInMeters);
    }

    // Please implement the getBMICategory() method so that it takes the BMI value and returns the BMI category based on it
    public String getBMICategory(double bmi) {
        if (bmi < 18.5) { // checking if the BMI value is less than 18.5
            return "Underweight"; // Returning that the BMI category is Underweight
        } else if (bmi < 25) { // checking if the BMI value is less than 25
            return "Normal weight"; // Returning that the BMI category is Normal weight
        } else if (bmi < 30) { // checking if the BMI value is less than 30
            return "Overweight"; // Returning that the BMI category is Normal Overweight
        } else {
            return "Obese"; // Returning that the BMI category is Obese when the bmi value is 30 or above
        }
    }
}
