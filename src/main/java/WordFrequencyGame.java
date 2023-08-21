import java.util.*;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s";
    public static final String PLUS_SIGN = "+";
    public static final String NEWLINE_DELIMITER = "\n";
    public static final int ONE = 1;
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER + PLUS_SIGN).length == ONE) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split(SPACE_DELIMITER + PLUS_SIGN);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String s : arr) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(s, ONE);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> map = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : map.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = list;

                wordFrequencyInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
                for (WordFrequencyInfo w : wordFrequencyInfoList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }
        return map;
    }

}
