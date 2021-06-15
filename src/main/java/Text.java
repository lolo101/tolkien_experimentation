import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Text {
    private final List<Word> words;

    public Text(List<Word> words) {
        this.words = words;
    }

    public List<String> search(Pattern pattern) {
        return IntStream.range(0, words.size())
                .mapToObj(this::sublist)
                .map(pattern::head)
                .filter(Pattern.Head::matches)
                .map(Pattern.Head::tokens)
                .collect(toList());
    }

    private List<Word> sublist(int index) {
        return words.subList(index, words.size());
    }
}
