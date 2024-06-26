import java.util.Scanner;
import java.util.Random;
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int min = 1;
        int max = 100;
        int targetNumber = r.nextInt(max - min + 1) + min;
        int attempts = 0;
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("picked a number between " + min + " and " + max + ". Try to guess it!")
        while (true) {
            System.out.print("Enter guess: ");
            int userGuess = sc.nextInt();
            attempts++;
            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
        }
        sc.close();
}
