import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PatternTest {
    @ParameterizedTest
    @MethodSource("matchingPartsOfSpeech")
    void should_match_same_partOfSpeech(String[] partsOfSpeech, List<Couple> couples) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.head(couples).matches()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("unmatchingPartsOfSpeech")
    void should_not_match_different_partOfSpeech(String[] partsOfSpeech, List<Couple> couples) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.head(couples).matches()).isFalse();
    }

    private static Stream<Arguments> matchingPartsOfSpeech() {
        return Stream.of(
                Arguments.of(
                        new String[]{"NOUN", "NOUN"},
                        Arrays.asList(
                                new Couple("toto", "NOUN"),
                                new Couple("titi", "NOUN")
                        )
                ),
                Arguments.of(
                        new String[]{"NOUN", "VERB"},
                        Arrays.asList(
                                new Couple("toto", "NOUN"),
                                new Couple("tata", "VERB")
                        )
                )
        );
    }

    private static Stream<Arguments> unmatchingPartsOfSpeech() {
        return Stream.of(
                Arguments.of(
                        new String[]{"NOUN", "VERB"},
                        Arrays.asList(
                                new Couple("tata", "VERB"),
                                new Couple("titi", "NOUN")
                        )
                ),
                Arguments.of(
                        new String[]{"VERB", "NOUN"},
                        Arrays.asList(
                                new Couple("tata", "VERB"),
                                new Couple("tutu", "VERB")
                        )
                )
        );
    }
}