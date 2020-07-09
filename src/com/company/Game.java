package com.company;

import java.util.Scanner;

public class Game {
    private int wrongGuesses = 0;
    private int tries;
    private String movieName;
    private String secretMovieName;
    private String [] arrSecretMovieName;
    private String [] guessedChars = new String[26];

    Game(String movieName, int tries) {
        this.movieName = movieName;

        //making string which is fully secret movie name
        //we know just a length
        this.tries = tries;
        this.secretMovieName = "_".repeat(movieName.length());
        this.arrSecretMovieName = secretMovieName.split("");
        for (int i = 0; i < guessedChars.length; i++) {
            guessedChars[i] = "";
        }
    }

    //to count tries that user have made
    public void decrementTries() {
        tries--;
    }

    public int getTries() {
        return tries;
    }

    //shows current state of secret movie
    //guessed letters at their positions and underscores
    public String showSecretMovie() {
        return this.secretMovieName;
    }

    //checking if guess is in movie name
    public boolean checkGuess(CharSequence guess) {
        if (movieName.contains(guess)) {
            return true;
        }
        else {return false;}
    }

    //method to check whether char has already guessed and update array of guessed
    public boolean isGuessed(String guess) {
        for (int i = 0; i < guessedChars.length; i++) {
            if (guessedChars[i].equals(guess)) {
                return true;
            }
        }
        for (int i = 0; i < guessedChars.length; i++) {
            if (guessedChars[i] == "") {
                guessedChars[i] = guess;
                break;
            }
        }
        return false;
    }

    public void updateSecretMovie(String guess) {
        for (int i = 0; i < this.arrSecretMovieName.length; i++) {
            if (guess.toCharArray()[0] == movieName.charAt(i)) {
                this.arrSecretMovieName[i] = guess;
            }
        }
        this.secretMovieName = "";
        for (int i = 0; i < arrSecretMovieName.length ; i++) {
            this.secretMovieName += arrSecretMovieName[i];
        }
    }

    public void incrementWrongGuesses() {
        wrongGuesses++;
    }

    public int getNumberOfWrongGuesses() {
        return wrongGuesses;
    }
    //checking whether player has opened all the hidden letters
    public boolean hasWon()  {
        boolean win = true;

        for (int i = 0; i < arrSecretMovieName.length ; i++) {
            if (arrSecretMovieName[i].equals("_") && movieName.charAt(i) == " ".toCharArray()[0]) {
                continue;
            }
            if (!(arrSecretMovieName[i].toCharArray()[0] == movieName.charAt(i)) ) {
                win = false;
            }
        }
        if (win) {
            System.out.println("The movie is \"" + movieName + "\"!");
        }
        return win;
    }

//    public String getArr() {
//        return arrSecretMovieName.toString();
//    }

}

