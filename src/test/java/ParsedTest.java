import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParsedTest {
    @Test
    void should_search_pattern() {
        Parsed parsed = new Parsed(Couple.of("Chapter", "NOUN"));
        assertThat(parsed.search(Pattern.of("NOUN"))).isEqualTo("Chapter");
    }
}
