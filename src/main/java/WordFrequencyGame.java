import java.util.*;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s";
    public static final String PLUS_SIGN = "+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final int ONE = 1;
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordsWithFrequencies(String inputString) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] wordsArray = inputString.split(SPACE_DELIMITER + PLUS_SIGN);

            List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
            for (String word : wordsArray) {
                WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, ONE);
                wordFrequencyInfoList.add(wordFrequencyInfo);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequencyInfo>> stringWordFrequencyInfoMap = getListMap(wordFrequencyInfoList);

            List<WordFrequencyInfo> wordFrequencyInfos = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequencyInfo>> entry : stringWordFrequencyInfoMap.entrySet()) {
                WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                wordFrequencyInfos.add(wordFrequencyInfo);
            }
            wordFrequencyInfoList = wordFrequencyInfos;

            wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

            StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
            for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
                String s = wordFrequencyInfo.getWord() + " " + wordFrequencyInfo.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList<WordFrequencyInfo> arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }
        return map;
    }

}
