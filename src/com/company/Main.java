package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {

    private static int TRIES = 18;

    public static void main(String[] args) throws Exception {

        int i = 1;

        //opening file with movies to check how many are there (to create appropriate array)
        File file = new File("movies.txt");
        Scanner scannerZero = new Scanner(file);
        while (scannerZero.hasNextLine()) {
            String line = scannerZero.nextLine();
            i++;
        }

	    //making and initializing array of movies
        String movies[] = new String[i];
        Scanner scanner = new Scanner(file);
        i = 0;
	    while(scanner.hasNextLine()) {
	        String line  = scanner.nextLine();
	        movies[i] = line;
            i++;
        }

	    //random film selection
	    int filmPosition = (int)(Math.random() * 25) + 1;
        String film = movies[filmPosition];

        //game loop
        Game game = new Game(film, TRIES);
        Scanner scanner1 = new Scanner(System.in);
        while  (game.getTries() > 0) {
            System.out.println("You have " + game.getTries() + " tries");
            System.out.println("The number of wrong guesses: " + game.getNumberOfWrongGuesses());
            System.out.println("The film is:" + game.showSecretMovie());
            System.out.println("Guess a letter");
            String guess = scanner1.next();

            if (game.isGuessed(guess)) {
                System.out.println("You've already guessed this letter\n");
            }
            else {
                if (game.checkGuess(guess)) {
                    game.updateSecretMovie(guess);
                }
                else {
                    game.incrementWrongGuesses();
                }
                game.decrementTries();
            }

            if (game.hasWon()) {
                System.out.println("YOU WIN");
                return;
            }
            if (game.getTries() == 0) {
                System.out.println("0 tries left!\nYOU LOSE");
                return;
            }
        }
    }
}
