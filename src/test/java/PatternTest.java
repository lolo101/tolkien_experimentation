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
    void should_match_same_partOfSpeech(String[] partsOfSpeech, List<Word> words) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.head(words).matches()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("unmatchingPartsOfSpeech")
    void should_not_match_different_partOfSpeech(String[] partsOfSpeech, List<Word> words) {
        Pattern pattern = new Pattern(partsOfSpeech);
        assertThat(pattern.head(words).matches()).isFalse();
    }

    private static Stream<Arguments> matchingPartsOfSpeech() {
        return Stream.of(
                Arguments.of(
                        new String[]{"NOUN", "NOUN"},
                        Arrays.asList(
                                new Word("toto", "NOUN"),
                                new Word("titi", "NOUN")
                        )
                ),
                Arguments.of(
                        new String[]{"NOUN", "VERB"},
                        Arrays.asList(
                                new Word("toto", "NOUN"),
                                new Word("tata", "VERB")
                        )
                )
        );
    }

    private static Stream<Arguments> unmatchingPartsOfSpeech() {
        return Stream.of(
                Arguments.of(
                        new String[]{"NOUN", "VERB"},
                        Arrays.asList(
                                new Word("tata", "VERB"),
                                new Word("titi", "NOUN")
                        )
                ),
                Arguments.of(
                        new String[]{"VERB", "NOUN"},
                        Arrays.asList(
                                new Word("tata", "VERB"),
                                new Word("tutu", "VERB")
                        )
                )
        );
    }
}