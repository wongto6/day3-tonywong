import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final int INITIAL_COUNT = 1;

    public String getWordFrequency(String sentences) {
        try {
            List<WordFrequency> wordFrequencies = mapSentenceToWordFrequencies(sentences);
            Map<String, List<WordFrequency>> wordToWordFrequencies = getWordToWordFrequencies(wordFrequencies);
            List<WordFrequency> wordFrequenciesAggregated = aggregateWordFrequencies(wordToWordFrequencies);
            return mapWordFrequenciesToString(wordFrequenciesAggregated);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordFrequency> mapSentenceToWordFrequencies(String sentences) {
        return Arrays
                .stream(sentences.split(SPACE))
                .map(word -> new WordFrequency(word, INITIAL_COUNT))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> aggregateWordFrequencies(Map<String, List<WordFrequency>> wordToWordFrequencies) {
        return wordToWordFrequencies
                .entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparing(WordFrequency::getWordCount).reversed())
                .collect(Collectors.toList());
    }

    private String mapWordFrequenciesToString(List<WordFrequency> wordFrequencies) {
        return wordFrequencies
                .stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private Map<String, List<WordFrequency>> getWordToWordFrequencies(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }

}
