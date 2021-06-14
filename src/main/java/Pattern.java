import java.util.Arrays;

public class Pattern {
    private final String[] partsOfSpeech;

    public Pattern(String... partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public boolean match(String... somePartsOfSpeech) {
        return Arrays.equals(this.partsOfSpeech, somePartsOfSpeech);
    }
}
