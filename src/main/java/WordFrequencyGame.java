import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String MULTIPLE_SPACES_DELIMITER = "\\s+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final int ONE = 1;
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordsWithFrequencies(String inputString) {
        try {
            //split the input string with 1 to n pieces of spaces
            List<String> wordsArray = List.of(inputString.split(MULTIPLE_SPACES_DELIMITER));

            List<WordFrequencyInfo> wordFrequencyInfoList = wordsArray.stream()
                    .map(word -> new WordFrequencyInfo(word, ONE))
                    .collect(Collectors.toList());

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequencyInfo>> stringWordFrequencyInfoMap = getListMap(wordFrequencyInfoList);

            List<WordFrequencyInfo> wordFrequencyInfos = stringWordFrequencyInfoMap.entrySet().stream()
                    .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                    .collect(Collectors.toList());

            wordFrequencyInfoList = wordFrequencyInfos;

            wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
            wordFrequencyInfoList.stream()
                    .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + " " + wordFrequencyInfo.getWordCount())
                    .forEach(joiner::add);

            return joiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        wordFrequencyInfoList.forEach(wordFrequencyInfo -> {
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList<WordFrequencyInfo> arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        });
        return map;
    }

}
