import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PatternTest {
    @Test
    void should_match_same_partOfSpeech() {
        Pattern pattern = new Pattern("NOUN");
        assertThat(pattern.match("NOUN")).isTrue();
    }

    @Test
    void should_not_match_different_partOfSpeech() {
        Pattern pattern = new Pattern("NOUN");
        assertThat(pattern.match("VERB")).isFalse();
    }
}