public class Parsed {
    private final Couple couple;

    public Parsed(Couple couple) {
        this.couple = couple;
    }

    public String search(Pattern pattern) {
        return couple.token;
    }
}
