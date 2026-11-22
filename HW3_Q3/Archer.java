public class Archer extends Card {

//    private int hitPoint = 1900;
    private static final int DECREASE_HIT_POINT = 800;

    public Archer() {
        super("Archer", DECREASE_HIT_POINT);
    }
    public Archer(String direction,int row) {
        super(direction, row);
    }

    public Archer(int hitPoint) {
        super(hitPoint);
    }

}
