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
        List<Couple> couples = givenCouples();
        Parsed parsed = new Parsed(couples);
        Pattern pattern = new Pattern("NOUN", "CCONJ", "NOUN");
        List<String> result = parsed.search(pattern);
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

    private static List<Couple> givenCouples() throws IOException {
        try (InputStream jsonStream = TolkienIT.class.getResourceAsStream("/json_parsing.json")) {
            JSONArray array = new JSONArray(new JSONTokener(requireNonNull(jsonStream)));
            return StreamSupport.stream(array.spliterator(), false)
                    .map(JSONArray.class::cast)
                    .map(rawCouple -> new Couple(rawCouple.getString(0), rawCouple.getString(1)))
                    .collect(toList());
        }
    }
}
