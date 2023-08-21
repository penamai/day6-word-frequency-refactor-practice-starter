import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr){


        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split("\\s+");

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String s : arr) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(s, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> map =getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : map.entrySet()){
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = list;

                wordFrequencyInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequencyInfo w : wordFrequencyInfoList) {
                    String s = w.getWord() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            }

            else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }


        return map;
    }


}
