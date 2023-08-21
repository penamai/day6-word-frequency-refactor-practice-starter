import java.util.List;
import java.util.Map;

public class WordFrequencyInfo {
    private final String word;
    private final int count;

    public WordFrequencyInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public WordFrequencyInfo(Map.Entry<String, List<String>> entry) {
        this(entry.getKey(), entry.getValue().size());
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

}
