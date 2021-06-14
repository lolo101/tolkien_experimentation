public class Parsed {
    private final Couple couple;

    public Parsed(Couple couple) {
        this.couple = couple;
    }

    public String search(Pattern pattern) {
        if (pattern.match(couple.partOfSpeech)) {
            return couple.token;
        }
        return "";
    }
}
