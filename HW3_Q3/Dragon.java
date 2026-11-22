public class Dragon extends Card{


//    private int hitPoint=3200;
    private static final int DECREASE_HIT_POINT = 1100;

    public Dragon() {
        super("Dragon", DECREASE_HIT_POINT);
    }
    public Dragon(String direction,int row) {
        super(direction, row);
    }

    public Dragon(int hitPoint) {
        super(hitPoint);
    }
    //    public Dragon(int hitPoint, int hitPoint1) {
//        super(hitPoint);
//        this.hitPoint = hitPoint1;
//    }
}
