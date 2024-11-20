import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentences) {
        try {
            //split the WordCount string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencies = Arrays
                    .stream(sentences.split(SPACE))
                    .map(word -> new WordFrequency(word, 1))
                    .collect(Collectors.toList());
            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);

            wordFrequencies = wordToWordFrequencies
                    .entrySet()
                    .stream()
                    .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                    .collect(Collectors.toList());
            wordFrequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());

            return  wordFrequencies.stream().map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount()).collect(Collectors.joining(LINE_BREAK));
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
