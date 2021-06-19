import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class TolkienIT {
    @Test
    void search_pattern() throws IOException {
        List<Word> words = givenCouples();
        Text text = new Text(words);
        Pattern pattern = new Pattern("NOUN", "CCONJ", "NOUN");
        List<String> result = text.search(pattern);
        assertThat(result).contains(
                "talk and excitement",
                "childhood and coming",
                "history and character",
                "gold and silver",
                "Cabbages and potatoes",
                "sorts and shapes",
                "squib or cracker"
        );
    }

    @Test
    void search_word_pattern() throws IOException {
        List<Word> words = givenCouples();
        Text text = new Text(words);
        Pattern pattern = new Pattern(PatternElement.anyToken("NOUN"), PatternElement.of("and", "CCONJ"), PatternElement.anyToken("NOUN"));
        List<String> result = text.search(pattern);
        assertThat(result).contains(
                "talk and excitement",
                "childhood and coming",
                "history and character",
                "gold and silver",
                "Cabbages and potatoes",
                "sorts and shapes"
        ).doesNotContain(
                "squib or cracker"
        );
    }

    private static List<Word> givenCouples() throws IOException {
        try (InputStream jsonStream = TolkienIT.class.getResourceAsStream("/json_parsing.json")) {
            JSONArray array = new JSONArray(new JSONTokener(requireNonNull(jsonStream)));
            return StreamSupport.stream(array.spliterator(), false)
                    .map(JSONArray.class::cast)
                    .map(rawCouple -> new Word(rawCouple.getString(0), rawCouple.getString(1)))
                    .collect(toList());
        }
    }
}
