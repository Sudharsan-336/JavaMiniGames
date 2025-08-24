import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"rock", "paper", "scissors"};

        int userScore = 0;
        int computerScore = 0;

        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.print("How many rounds would you like to play? ");
        int rounds = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 1; i <= rounds; i++) {
            System.out.println("\nRound " + i);
            System.out.print("Enter your choice (rock, paper, or scissors): ");
            String userChoice = scanner.nextLine().toLowerCase();

            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice. You lose this round by default.");
                computerScore++;
                continue;
            }

            String computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);

            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("You win this round!");
                userScore++;
            } else {
                System.out.println("Computer wins this round!");
                computerScore++;
            }
        }


        System.out.println("\nFinal Score:");
        System.out.println("You: " + userScore);
        System.out.println("Computer: " + computerScore);

        if (userScore > computerScore) {
            System.out.println("Congratulations! You won the game!");
        } else if (userScore < computerScore) {
            System.out.println("Computer wins the game. Better luck next time!");
        } else {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}