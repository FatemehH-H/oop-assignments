public class Wizard extends Card{

//    private int hitPoint=3300;
    private static final int DECREASE_HIT_POINT = 1400;

    public Wizard() {
        super("Wizard", DECREASE_HIT_POINT);
    }
    public Wizard(String direction,int row) {
        super(direction, row);
    }

    public Wizard(int hitPoint) {
        super(hitPoint);
    }
}
