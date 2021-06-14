import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ParsedTest {
    @ParameterizedTest
    @ValueSource(strings = {"Chapter", "PARTY"})
    void should_find_single_pattern(String token) {
        Parsed parsed = new Parsed(new Couple(token, "NOUN"));
        assertThat(parsed.search(new Pattern("NOUN"))).isEqualTo(token);
    }

    @Test
    void should_not_find_unmatched_pattern() {
        Parsed parsed = new Parsed(new Couple("Chapter", "NOUN"));
        assertThat(parsed.search(new Pattern("VERB"))).isEmpty();
    }
}
