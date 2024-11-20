public class WordCount {
    private String word;
    private int count;

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }


    public String getValue() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }


}
