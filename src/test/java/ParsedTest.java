import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ParsedTest {
    @ParameterizedTest
    @ValueSource(strings = {"Chapter", "PARTY"})
    void should_find_single_pattern(String token) {
        Parsed parsed = new Parsed(singletonList(new Couple(token, "NOUN")));
        assertThat(parsed.search(new Pattern("NOUN"))).isEqualTo(token);
    }

    @Test
    void should_not_find_unmatched_pattern() {
        Parsed parsed = new Parsed(singletonList(new Couple("Chapter", "NOUN")));
        assertThat(parsed.search(new Pattern("VERB"))).isEmpty();
    }

    @Test
    void should_find_pattern_subset() {
        List<Couple> couples = Arrays.asList(
                new Couple("eat", "VERB"),
                new Couple("pizza", "NOUN"));
        Parsed parsed = new Parsed(couples);
        assertThat(parsed.search(new Pattern("VERB"))).isEqualTo("eat");
    }
}
