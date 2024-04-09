import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            WordList words = new WordList("words.txt");
            Hangman newgame = new Hangman(words, 5);


            System.out.println("Welcome to play Hangman!");

            while (!newgame.theEnd()) {

                System.out.println("The hidden word...");
                System.out.println(newgame.getMaskedWord());

                System.out.println("Guesses left: " + newgame.guessesLeft());
                System.out.println("Guessed letters: " + newgame.guesses() + "\n");

                System.out.println("Guess a letter: ");

                String input = scanner.next();
                if (input.length() > 1) {
                    System.out.println("You have to give only on character");
                } else {
                    Character c = Character.toLowerCase(input.charAt(0));
                    newgame.guess(c);
                }

                System.out.println("\n");
            }

            if (newgame.victory()) {
                System.out.println("Congratulations! You won!!!");
                System.out.println("The hidden word was: " + newgame.word());
            } else {
                System.out.println("Sorry, you lost!");
                System.out.println("The hidden word was: " + newgame.word());
            }
        } catch (IOException e) {
            System.out.println("Error while reading the wordlist");
        } finally {
            scanner.close();
        }
    }
}