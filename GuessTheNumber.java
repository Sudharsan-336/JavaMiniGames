import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain = true;             
        int highScore = Integer.MAX_VALUE; // store least attempts

        System.out.println("Welcome to Guess the Number!");

        while (playAgain) {
            System.out.println("\nChoose a level to play: ");
            System.out.println("1. Easy (1-100)\n2. Medium (1-250)\n3. Hard (1-500)");
            System.out.print("Enter your choice (1-3): ");
            
            // NEW: Basic validation for difficulty
            while (!scan.hasNextInt()) {
                System.out.println("Please enter a number!");
                scan.next(); 
            }
            int difficulty = scan.nextInt();
            int maxNumber = (difficulty == 1) ? 100 : (difficulty == 2) ? 250 : 500;

            int numberToGuess = rand.nextInt(maxNumber) + 1;
            int userGuess = 0;
            int attempts = 0;

            System.out.println("I'm thinking of a number between 1 and " + maxNumber + ".");

            while (userGuess != numberToGuess) {
                System.out.print("Enter your guess: ");
                
                // NEW: Validation for guess input
                if (!scan.hasNextInt()) {
                    System.out.println("Invalid input! Enter a number.");
                    scan.next();
                    continue;
                }
                
                userGuess = scan.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! Total attempts: " + attempts);
                    if (attempts < highScore) {
                        highScore = attempts;
                        System.out.println("NEW HIGH SCORE!");
                    }
                }

                // NEW: Enhanced Hints
                if (attempts == 3 && userGuess != numberToGuess) {
                    String parity = (numberToGuess % 2 == 0) ? "even" : "odd";
                    System.out.println("Hint 1: The number is " + parity + ".");
                }
                if (attempts == 6 && userGuess != numberToGuess) {
                    // NEW: Tells the user if the number is a multiple of 10
                    String multiple = (numberToGuess % 10 == 0) ? "is" : "is not";
                    System.out.println("Hint 2: The number " + multiple + " a multiple of 10.");
                }
            }

            System.out.println("\nPlay again? (1 = Yes, 0 = No)");
            playAgain = (scan.nextInt() == 1);
        }

        System.out.println("Final High Score: " + (highScore == Integer.MAX_VALUE ? "N/A" : highScore));
        scan.close();
    }
}
