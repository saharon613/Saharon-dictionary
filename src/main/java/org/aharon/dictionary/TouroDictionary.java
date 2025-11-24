package org.aharon.dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TouroDictionary {

    private Map<String, String> dictionary;

    public TouroDictionary() {
        dictionary = new HashMap<>();
        loadDictionary();
    }

    private void loadDictionary() {
        try {
            InputStream inputS = getClass().getClassLoader().getResourceAsStream("dictionary.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Find first space
                int firstSpace = line.indexOf(' ');

                if (firstSpace == -1) {
                    // No space - just the word, no definition
                    String word = line.trim();
                    dictionary.put(word, null);
                } else {
                    // Has space - word and definition
                    String word = line.substring(0, firstSpace);
                    String definition = line.substring(firstSpace + 1);
                    dictionary.put(word, definition);
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String lookup(String word) {
        String upperWord = word.toUpperCase();
        return dictionary.get(upperWord);
    }
}
