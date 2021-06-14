import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ParsedTest {
    @Test
    void should_not_find_unmatched_pattern() {
        Parsed parsed = new Parsed(singletonList(new Couple("Chapter", "NOUN")));
        assertThat(parsed.search(new Pattern("VERB"))).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("couples")
    void should_find_pattern_subset(List<Couple> couples, Pattern pattern, List<String> expected) {
        Parsed parsed = new Parsed(couples);
        assertThat(parsed.search(pattern)).isEqualTo(expected);
    }

    private static Stream<Arguments> couples() {
        return Stream.of(
                Arguments.of(
                        singletonList(new Couple("Chapter", "NOUN")),
                        new Pattern("NOUN"),
                        singletonList("Chapter")
                ),
                Arguments.of(
                        singletonList(new Couple("PARTY", "NOUN")),
                        new Pattern("NOUN"),
                        singletonList("PARTY")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Couple("eat", "VERB"),
                                new Couple("pizza", "NOUN")
                        ),
                        new Pattern("VERB"),
                        singletonList("eat")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Couple("eat", "VERB"),
                                new Couple("pizza", "NOUN")
                        ),
                        new Pattern("VERB", "NOUN"),
                        singletonList("eat pizza")
                ),
                Arguments.of(
                        Arrays.asList(
                                new Couple("eat", "VERB"),
                                new Couple("pizza", "NOUN"),
                                new Couple("drink", "VERB"),
                                new Couple("beer", "NOUN"),
                                new Couple("burp", "VERB")
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
