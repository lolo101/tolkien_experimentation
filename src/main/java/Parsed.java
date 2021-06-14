import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Parsed {
    private final List<Couple> couples;

    public Parsed(List<Couple> couples) {
        this.couples = couples;
    }

    public List<String> search(Pattern pattern) {
        return IntStream.range(0, couples.size())
                .mapToObj(this::sublist)
                .map(pattern::head)
                .filter(Pattern.Head::matches)
                .map(Pattern.Head::tokens)
                .collect(toList());
    }

    private List<Couple> sublist(int index) {
        return couples.subList(index, couples.size());
    }
}
