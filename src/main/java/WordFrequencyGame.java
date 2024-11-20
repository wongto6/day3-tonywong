import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentences) {
        try {
            //split the WordCount string with 1 to n pieces of spaces
            List<WordFrequency> wordFrequencies = mapSentenceToWordFrequencies(sentences);
            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordToWordFrequencies = getListMap(wordFrequencies);
            List<WordFrequency> wordFrequenciesAggregated = aggregateWordFrequencies(wordToWordFrequencies);
            return mapWordFrequenciesToString(wordFrequenciesAggregated);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordFrequency> mapSentenceToWordFrequencies(String sentences) {
        return Arrays
                .stream(sentences.split(SPACE))
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.toList());
    }

    private List<WordFrequency> aggregateWordFrequencies(Map<String, List<WordFrequency>> wordToWordFrequencies) {
        return wordToWordFrequencies
                .entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted(Comparator.comparing(WordFrequency::getWord))
                .collect(Collectors.toList());
    }

    private String mapWordFrequenciesToString(List<WordFrequency> wordFrequencies) {
        return wordFrequencies
                .stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }

}
