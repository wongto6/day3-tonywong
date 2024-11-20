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
            List<WordCount> WordCountList = new ArrayList<>();
            for (String word : words) {
                WordCount WordCount = new WordCount(word, 1);
                WordCountList.add(WordCount);
            }
            //get the map for the next step of sizing the same word
            Map<String, List<WordCount>> map = getListMap(WordCountList);
            List<WordCount> list = new ArrayList<>();
            for (Map.Entry<String, List<WordCount>> entry : map.entrySet()) {
                WordCount WordCount = new WordCount(entry.getKey(), entry.getValue().size());
                list.add(WordCount);
            }
            WordCountList = list;
            WordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
            StringJoiner joiner = new StringJoiner("\n");
            for (WordCount w : WordCountList) {
                String word = w.getWord() + " " + w.getWordCount();
                joiner.add(word);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private Map<String, List<WordCount>> getListMap(List<WordCount> WordCountList) {
        Map<String, List<WordCount>> map = new HashMap<>();
        for (WordCount WordCount : WordCountList) {
//       map.computeIfAbsent(WordCount.getValue(), k -> new ArrayList<>()).add(WordCount);
            if (!map.containsKey(WordCount.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(WordCount);
                map.put(WordCount.getWord(), arr);
            } else {
                map.get(WordCount.getWord()).add(WordCount);
            }
        }
        return map;
    }

}
