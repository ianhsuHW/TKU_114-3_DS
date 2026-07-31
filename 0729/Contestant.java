public class Contestant {
    private String id;
    private String name;
    private int score;
    private int seconds;

    public Contestant(
        String id,
        String name,
        int score,
        int seconds
    ) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }

        if (name == null) {
            this.name = "";
        } else {
            this.name = name.trim();
        }

        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }

        if (seconds < 0) {
            this.seconds = 0;
        } else {
            this.seconds = seconds;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return id + " " + name
            + " 分數=" + score
            + " 秒數=" + seconds;
    }
}
