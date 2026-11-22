public class Location {

    String direction;
    int row;

    public Location(String direction, int row) {
        this.direction = direction;
        this.row = row;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
