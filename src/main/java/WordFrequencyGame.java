import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


public class WordFrequencyGame {
    public String getWordFrequency(String sentences) {
        try {
            //split the WordCount string with 1 to n pieces of spaces
            String[] words = sentences.split("\\s+");
            List<WordFrequency> wordFrequencies = new ArrayList<>();
            for (String word : words) {
                WordFrequency wordFrequency = new WordFrequency(word, 1);
                wordFrequencies.add(wordFrequency);
            }
            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordFrequencyMap = getListMap(wordFrequencies);
            List<WordFrequency> wordFrequencyMaps = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
                WordFrequency WordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                wordFrequencyMaps.add(WordFrequency);
            }
            wordFrequencies = wordFrequencyMaps;
            wordFrequencies.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
            StringJoiner WordFrequencyJoiner = new StringJoiner("\n");
            for (WordFrequency wordFrequency : wordFrequencies) {
                String word = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                WordFrequencyJoiner.add(word);
            }
            return WordFrequencyJoiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
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
