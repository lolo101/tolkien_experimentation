public class Pattern {
    private String partOfSpeech;

    public Pattern(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public boolean match(String somePartOfSpeech) {
        return this.partOfSpeech.equals(somePartOfSpeech);
    }
}
