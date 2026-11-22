public class Castle {

    private String name;
    private int hitPoint;

    public int getDECREASE_HIT_POINT() {
        return DECREASE_HIT_POINT;
    }

    public void setDECREASE_HIT_POINT(int DECREASE_HIT_POINT) {
        this.DECREASE_HIT_POINT = DECREASE_HIT_POINT;
    }

    private  int DECREASE_HIT_POINT ;

    public Castle(String name,int hitPoint) {
        this.hitPoint = hitPoint;
        this.name = name;
    }
    public Castle(){}

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
