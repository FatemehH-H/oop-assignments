import java.util.ArrayList;
import java.util.Comparator;

public class Corps {

    private Infantry infantry;
    private Cavalry cavalry;
    private Artillery artillery;
//    private int infantry;
//    private int cavalry;
//    private int artillery;
    private String rankedOfficer;
    private String number;

//    public Corps(int infantry, int cavalry, int artillery, String rankedOfficer, String number) {
//        this.infantry = infantry;
//        this.cavalry = cavalry;
//        this.artillery = artillery;
//        this.rankedOfficer = rankedOfficer;
//        this.number = number;
//    }


    public Corps(Infantry infantry, Cavalry cavalry, Artillery artillery, String rankedOfficer, String number) {
        this.infantry = infantry;
        this.cavalry = cavalry;
        this.artillery = artillery;
        this.rankedOfficer = rankedOfficer;
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Infantry getInfantry() {
        return infantry;
    }

    public Cavalry getCavalry() {
        return cavalry;
    }

    public Artillery getArtillery() {
        return artillery;
    }

    public String getRankedOfficer() {
        return rankedOfficer;
    }

    public void setInfantry(Infantry infantry) {
        this.infantry = infantry;
    }

    public void setCavalry(Cavalry cavalry) {
        this.cavalry = cavalry;
    }

    public void setArtillery(Artillery artillery) {
        this.artillery = artillery;
    }
    public int romanToInt(String roman) {

        if(roman.equals("I"))
            return 1;
        else if(roman.equals("II"))
            return 2;
        else if(roman.equals("III"))
            return 3;
        else if(roman.equals("IV"))
            return 4;
        else if(roman.equals("V"))
            return 5;
        else if(roman.equals("VI"))
            return 6;
        else if(roman.equals("VII"))
            return 7;
        else if(roman.equals("VIII"))
            return 8;
        else if(roman.equals("IX"))
            return 9;
        else if(roman.equals("X"))
            return 10;
        else
            return -1;


    }



    public static class CorpsComparator implements Comparator<Corps> {
        @Override
        public int compare(Corps c1, Corps c2) {
            int num1 = c1.romanToInt(c1.getNumber());
            int num2 = c2.romanToInt(c2.getNumber());
            return Integer.compare(num1, num2);
        }
    }

}
