import java.util.List;
import java.util.stream.Collectors;

public class Parsed {
    private final List<Couple> couples;

    public Parsed(List<Couple> couples) {
        this.couples = couples;
    }

    public String search(Pattern pattern) {
        return couples.stream()
                .filter(couple -> pattern.match(couple.partOfSpeech))
                .map(couple -> couple.token)
                .collect(Collectors.joining());
    }
}
