import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentences) {
        try {
            //split the WordCount string with 1 to n pieces of spaces
            String[] words = sentences.split(SPACE);
            List<WordFrequency> wordFrequencies = new ArrayList<>();
            for (String word : words) {
                WordFrequency wordFrequency = new WordFrequency(word, 1);
                wordFrequencies.add(wordFrequency);
            }
            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);
            List<WordFrequency> wordFrequenciesAgg = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
                WordFrequency WordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                wordFrequenciesAgg.add(WordFrequency);
            }
            wordFrequencies = wordFrequenciesAgg;
            wordFrequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());
            StringJoiner WordFrequencyJoiner = new StringJoiner(LINE_BREAK);
            for (WordFrequency wordFrequency : wordFrequencies) {
                String word = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                WordFrequencyJoiner.add(word);
            }
            return WordFrequencyJoiner.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> WordFrequencyMap = new HashMap<>();
        for (WordFrequency WordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(WordCount.getValue(), k -> new ArrayList<>()).add(WordCount);
            if (!WordFrequencyMap.containsKey(WordFrequency.getWord())) {
                ArrayList words = new ArrayList<>();
                words.add(WordFrequency);
                WordFrequencyMap.put(WordFrequency.getWord(), words);
            } else {
                WordFrequencyMap.get(WordFrequency.getWord()).add(WordFrequency);
            }
        }
        return WordFrequencyMap;
    }

}
