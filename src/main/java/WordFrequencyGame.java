import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String MULTIPLE_SPACES_DELIMITER = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String NEWLINE_DELIMITER = "\n";

    public String getWordsWithFrequencies(String inputString) {
        try {
            List<String> wordsArray = getListOfWords(inputString);
            List<WordFrequencyInfo> wordFrequencyInfoList = convertToWordFrequencyInfoList(wordsArray);
            Map<String, List<WordFrequencyInfo>> stringWordFrequencyInfoMap = getStringWordFrequencyInfoMap(wordFrequencyInfoList);

            List<WordFrequencyInfo> wordFrequencyInfos = convertToWordFrequencyInfoList(stringWordFrequencyInfoMap);

            wordFrequencyInfoList = wordFrequencyInfos;

            wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            return generatePrintLines(wordFrequencyInfoList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private static List<String> getListOfWords(String inputString) {
        return List.of(inputString.split(MULTIPLE_SPACES_DELIMITER));
    }

    private static List<WordFrequencyInfo> convertToWordFrequencyInfoList(List<String> wordsArray) {
        return wordsArray.stream()
                .map(WordFrequencyInfo::new)
                .collect(Collectors.toList());
    }

    private static List<WordFrequencyInfo> convertToWordFrequencyInfoList(Map<String, List<WordFrequencyInfo>> stringWordFrequencyInfoMap) {
        return stringWordFrequencyInfoMap.entrySet().stream()
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

    private Map<String, List<WordFrequencyInfo>> getStringWordFrequencyInfoMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> stringWordFrequencyInfoMap = new HashMap<>();
        wordFrequencyInfoList.forEach(wordFrequencyInfo -> {
            String word = wordFrequencyInfo.getWord();
            if (!stringWordFrequencyInfoMap.containsKey(word)) {
                stringWordFrequencyInfoMap.put(word, new ArrayList<>(List.of(wordFrequencyInfo)));
            } else {
                stringWordFrequencyInfoMap.get(word).add(wordFrequencyInfo);
            }
        });
        return stringWordFrequencyInfoMap;
    }

}