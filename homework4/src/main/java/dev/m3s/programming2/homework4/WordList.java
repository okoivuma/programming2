package dev.m3s.programming2.homework4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WordList {
    private List<String> words = new ArrayList<>();

    public WordList(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String row;
            while ((row = reader.readLine()) != null) {
                words.add(row.toLowerCase());
            }
        }
    }

    public List<String> giveWords(){
        return words;
    }
}
