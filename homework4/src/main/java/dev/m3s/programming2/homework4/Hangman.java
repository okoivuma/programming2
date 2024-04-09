package dev.m3s.programming2.homework4;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    private int numOfGuesses;
    private String revealedWord;
    private String maskedWord;
    private List<Character> guessedChars = new ArrayList<>();

    public Hangman(WordList list, int guesses){
        List<String> wordList = list.giveWords();
        this.numOfGuesses = guesses;
        revealedWord = getRandomWord(wordList);
        maskedWord = MaskWord(revealedWord);
    }

    public boolean guess(Character c) {
        char guessedChar = Character.toLowerCase(c);


        // if guessedChar is not found in the word, it's index is -1
        if (revealedWord.indexOf(guessedChar) != -1){
            if (!guessedChars.contains(guessedChar)){
                guessedChars.add(guessedChar);
                unMask(guessedChar);
            }
            return true;
        } else {
            if (numOfGuesses > 0){
                numOfGuesses--;
            }
            if (!guessedChars.contains(guessedChar)){
                guessedChars.add(guessedChar);
            }
            return false;
        }
    }

    public List<Character> guesses() {
        return guessedChars;
    }

    public String word() {
        return revealedWord;
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    public int guessesLeft() {
        return numOfGuesses;
    }

    public boolean theEnd() {
        if (victory() || defeat()){
            return true;
        } else {
            return false;
        }
    }

    public boolean victory() {
        return maskedWord.indexOf('*') == -1 && numOfGuesses > 0;
    }

    public boolean defeat() {
        return numOfGuesses < 1;
    }


    private void unMask(char c) {
        List<Integer> indexes = new ArrayList<Integer>();

        for (int i = 0; i < revealedWord.length(); i++){
            if (revealedWord.charAt(i) == c){
                indexes.add(i);
            }
        }

        for (Integer index : indexes) {
            maskedWord = maskedWord.substring(0, index) + c + maskedWord.substring(index + 1);
        }
    }

    private String getRandomWord(List<String> words) {

        Random random = new Random();
        int randIndex = random.nextInt(words.size());

        return words.get(randIndex);

    }

    private String MaskWord(String word) {
        String masked = "";
        for (int i = 0; i < word.length(); i++){
            masked += '*';
        }
        return masked;
    }


}
