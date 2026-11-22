import java.util.ArrayList;

public class Army{

    private ArrayList<Corps> corps;
    private int number;
    private String leader;
    private String place;
    public Army(){
        this.corps=new ArrayList<>();
    }

    public Army(int number, String leader) {
        this.number = number;
        this.leader = leader;
    }

    public int getNumber() {
        return number;
    }

    public String getPlace() {
        return place;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCorps(ArrayList<Corps> corps) {
        this.corps = corps;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ArrayList<Corps> getCorps() {
        return corps;
    }
    public void addCorps(Corps corp){
        if(corps==null){
            corps=new ArrayList<>();
        }
        corps.add(corp);
    }

    public String getLeader() {
        return leader;
    }
    public Corps getCorps(String corpsNumber){
        for (Corps corps1 : corps)
            if(corps1.getNumber().equals(corpsNumber))
                return corps1;
        return null;
    }

}





