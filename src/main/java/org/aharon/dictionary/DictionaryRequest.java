package org.aharon.dictionary;

class DictionaryRequest {
    private String word;

    public DictionaryRequest(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}