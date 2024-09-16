package codes.domino.constant;

public enum Dialogues {
    FELINOR_DIALOGUE("Me-wow, is that the latest Felinor fashion?"),
    SOME_WEATHER("Some weather we're having, huh?"),
    HEY_HIVEKIN("Hey hivekin, can I bug you for a moment?"),
    KEEPING_BUSY("So, what's keeping you busy these days?"),
    BREEZE("Wow, this breeze is great, right?"),
    DEEP_THOUGHTS("Sometimes I have really deep thoughts about life and stuff."),
    CANOR("You ever been to a Canor restaurant? The food's pretty howlright."),
    WORK("So, how's work?");

    private final String content;
    private final String[] words;

    Dialogues(String content) {
        this.content = content;
        words = content.split(" ");
    }

    public boolean isSimilar(String text) {
        System.out.println("text = " + text);
        int grade = 0;
        int maxGrade = words.length;

        for (String word : words) {
            if (text.contains(word)) {
                grade++;
            }
        }
        return ((double) grade / maxGrade) >= 0.3;
    }

    public String content() {
        return content;
    }
}
