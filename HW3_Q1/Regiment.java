abstract class Regiment {
    int number;
    int score;

    public Regiment(int number) {
        this.number = number;

    }
    public Regiment(){
    }

    public int getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
