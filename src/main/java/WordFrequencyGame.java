import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String MULTIPLE_SPACES_DELIMITER = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String NEWLINE_DELIMITER = "\n";

    public String getWordsWithFrequencies(String inputString) {
        try {
            List<String> wordsList = getListOfWords(inputString);
            Map<String, List<String>> stringListOfStringMap = getStringListOfStringMap(wordsList);
            List<WordFrequencyInfo> wordFrequencyInfoList = extractWordFrequencyInfoList(stringListOfStringMap);
            wordFrequencyInfoList.sort(Comparator.comparingInt(WordFrequencyInfo::getWordCount).reversed());

            return generatePrintLines(wordFrequencyInfoList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private static List<String> getListOfWords(String inputString) {
        return List.of(inputString.split(MULTIPLE_SPACES_DELIMITER));
    }

    private static List<WordFrequencyInfo> extractWordFrequencyInfoList(Map<String, List<String>> stringListOfStringMap) {
        return stringListOfStringMap.entrySet().stream()
                .map(WordFrequencyInfo::new)
                .collect(Collectors.toList());
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(WordFrequencyGame::generateLineContent)
                .collect(Collectors.joining(NEWLINE_DELIMITER));
    }

    private static String generateLineContent(WordFrequencyInfo wordFrequencyInfo) {
        return wordFrequencyInfo.getWord() + " " + wordFrequencyInfo.getWordCount();
    }

    private Map<String, List<String>> getStringListOfStringMap(List<String> words) {
        Map<String, List<String>> stringListOfStringMap = new HashMap<>();
        words.forEach(word -> {
            if (!stringListOfStringMap.containsKey(word)) {
                stringListOfStringMap.put(word, new ArrayList<>(List.of(word)));
            } else {
                stringListOfStringMap.get(word).add(word);
            }
        });
        return stringListOfStringMap;
    }

}