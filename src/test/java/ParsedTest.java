import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ParsedTest {
    @ParameterizedTest
    @ValueSource(strings = {"Chapter", "PARTY"})
    void should_find_single_pattern(String token) {
        Parsed parsed = new Parsed(new Couple(token, "NOUN"));
        assertThat(parsed.search(Pattern.of("NOUN"))).isEqualTo(token);
    }
}
