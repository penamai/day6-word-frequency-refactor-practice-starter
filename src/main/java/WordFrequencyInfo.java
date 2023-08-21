import java.util.List;
import java.util.Map;

public class WordFrequencyInfo {
    public static final int DEFAULT_COUNT = 1;
    private final String word;
    private final int count;

    public WordFrequencyInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public WordFrequencyInfo(String word) {
        this.word = word;
        this.count = DEFAULT_COUNT;
    }

    public WordFrequencyInfo(Map.Entry<String, List<WordFrequencyInfo>> entry) {
        this.word = entry.getKey();
        this.count = entry.getValue().size();
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

}
