public class Fireball extends Card{

    private static final int DECREASE_HIT_POINT = 1400;

    public Fireball() {
        super("Fireball", DECREASE_HIT_POINT);
    }
    public Fireball(String direction,int row) {
        super(direction, row);
    }


}
