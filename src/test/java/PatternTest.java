import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PatternTest {
    @ParameterizedTest
    @MethodSource("partsOfSpeech")
    void should_match_same_partOfSpeech(String[] partsOfSpeech) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.match(partsOfSpeech)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("differentPartsOfSpeech")
    void should_not_match_different_partOfSpeech(String[] partsOfSpeech, String[] otherPartsOfSpeech) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.match(otherPartsOfSpeech)).isFalse();
    }

    private static Stream<Arguments> partsOfSpeech() {
        return Stream.of(
                Arguments.of((Object) new String[] {"NOUN", "NOUN"}),
                Arguments.of((Object) new String[] {"NOUN", "VERB"}));
    }

    private static Stream<Arguments> differentPartsOfSpeech() {
        return Stream.of(
                Arguments.of(new String[] {"NOUN", "VERB"}, new String[]{"VERB", "NOUN"}));
    }
}