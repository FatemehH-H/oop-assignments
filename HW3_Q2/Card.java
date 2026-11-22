public class Card {
    private String name;
    private int decreaseHitPoint;
    private int row;
    private String direction;
    private Location location;
    private User owner;
    private int hitPoint;



    public Card(String name, int decreaseHitPoint) {
        this.name = name;
        this.decreaseHitPoint = decreaseHitPoint;
    }

    public Card(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Card() {}


    public Card getCardByName(String cardName){
        Fireball fireball=new Fireball();
        Archer archer=new Archer();
        Wizard wizard=new Wizard();
        Dragon dragon=new Dragon();
        if(cardName.equals("Fireball")){
            return fireball;
        }
        else if(cardName.equals("Archer")){
            return archer;
        }
        else if(cardName.equals("Wizard")){
            return wizard;
        }
        else
            return dragon;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDecreaseHitPoint() {
        return decreaseHitPoint;
    }

    public void setDecreaseHitPoint(int decreaseHitPoint) {
        this.decreaseHitPoint = decreaseHitPoint;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Card getCardByLocation(String direction, int row){
        Card card=new Card();
        if(card.getDirection().equals(direction) && card.getRow()==row)
            return card;
        else
            return null;
    }



}
