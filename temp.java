import java.util.Scanner;

public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the temp value: ");
        double temp = sc.nextDouble();
        System.out.print("Enter your value either in Celsius, Fahrenheit, or Kelvin: ");
        String oUnit = sc.next().toLowerCase();
        double convertedToFahrenheit;
        double convertedToKelvin;
        switch (oUnit) {
            case "celsius":
                convertedToFahrenheit = (temp * 9 / 5) + 32;
                convertedToKelvin = temp + 273.15;
                break;
            case "fahrenheit":
                convertedToFahrenheit = temp;
                convertedToKelvin = (temp + 459.67) * 5 / 9;
                break;
            case "kelvin":
                convertedToFahrenheit = (temp * 9 / 5) - 459.67;
                convertedToKelvin = temp;
                break;
            default:
                System.out.println("Invalid value , Sorry!");
                return;
        }
        System.out.println("Converted to Fahrenheit: " + convertedToFahrenheit);
        System.out.println("Converted to Kelvin: " + convertedToKelvin);
    }
