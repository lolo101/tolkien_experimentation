import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class TextTest {
    @Test
    void should_not_find_unmatched_pattern() {
        Text text = new Text(singletonList(new Word("Chapter", "NOUN")));
        assertThat(text.search(new Pattern("VERB"))).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("couples")
    void should_find_pattern_subset(List<Word> words, Pattern pattern, List<String> expected) {
        Text text = new Text(words);
        assertThat(text.search(pattern)).isEqualTo(expected);
    }

    private static Stream<Arguments> couples() {
        return Stream.of(
                Arguments.of(
                        singletonList(new Word("Chapter", "NOUN")),
                        new Pattern("NOUN"),
                        singletonList("Chapter")
                ),
                Arguments.of(
                        singletonList(new Word("PARTY", "NOUN")),
                        new Pattern("NOUN"),
                        singletonList("PARTY")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Word("eat", "VERB"),
                                new Word("pizza", "NOUN")
                        ),
                        new Pattern("VERB"),
                        singletonList("eat")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Word("eat", "VERB"),
                                new Word("pizza", "NOUN")
                        ),
                        new Pattern("VERB", "NOUN"),
                        singletonList("eat pizza")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Word("eat", "VERB"),
                                new Word("pizza", "NOUN"),
                                new Word("drink", "VERB"),
                                new Word("beer", "NOUN"),
                                new Word("burp", "VERB")
                        ),
                        new Pattern("VERB", "NOUN"),
                        Arrays.asList(
                                "eat pizza",
                                "drink beer"
                        )
                )
        );
    }
}
