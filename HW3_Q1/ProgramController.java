import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {

    private Scanner scanner = new Scanner(System.in);
    private Country country = new Country();
    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public void createCountry(Matcher matcher){
        String name=matcher.group("Name");
        boolean countryExist= false;
        if(country.getName() !=null){
            System.out.println("country was created");
            countryExist=true;
        }
        for (Country countryCountry : country.getCountries()) {
           if(countryCountry.getName().equalsIgnoreCase(name)){
               System.out.println("country was created");
               countryExist=true;
           }
        }
        if(!countryExist){
           // String name=matcher.group("Name");
            country.addCountry(new Country(matcher.group("Name"), matcher.group("Nationality")));
            System.out.println("country "+name+" created");
        }
    }

    public void createCorps(Matcher matcher){

        int infantry= Integer.parseInt(matcher.group("infantry"));
        int cavalry= Integer.parseInt(matcher.group("cavalry"));
        int artillery= Integer.parseInt(matcher.group("artillery"));
        int members= 1000*infantry + 400*cavalry + 10* artillery;
        Infantry infantry1=new Infantry(infantry);
        Cavalry cavalry1= new Cavalry(cavalry);
        Artillery artillery1=new Artillery(artillery);
        String corpsNumber= matcher.group("Number");
        String countryName= matcher.group("Country");
        String rankOfficer= matcher.group("Officer");
        Country country1= country.getCountry(countryName);
        boolean addCorps=true;

        if(!(rankOfficer.equals("corporal") || rankOfficer.equals("sergent") || rankOfficer.equals("lieutenant") || rankOfficer.equals("capitan") || rankOfficer.equals("colonel") || rankOfficer.equals("general") || rankOfficer.equals("marshal"))){
            System.out.println("invalid input!");
        }
        else if (members>30000){
            System.out.println("cannot have more than 30k in a corps!");
        }
        else if(country1 ==null){
            System.out.println("country was not found!");
        }
        else{
            if(country1.getCorps()!=null) {
                for (Corps corps : Objects.requireNonNull(country1).getCorps()) {
                    if (corps.getNumber().equals(corpsNumber)) {
                        System.out.println("this country already has this corps!");

                        addCorps = false;
                        break;
                    }
                }
            }

        if(addCorps){
            System.out.println("corps "+corpsNumber+" created successfully!");
            country1.addCorps(new Corps(infantry1,cavalry1,artillery1,matcher.group("Officer"),matcher.group("Number")));

            }
        }
    }

    public void createArmyWithPlace(Matcher matcher){
        int armyNumber=Integer.parseInt(matcher.group("Number"));
        String countryName= matcher.group("Country");
        Country country1= country.getCountry(countryName);
        boolean addArmy=true;

        if(country1 ==null){
            System.out.println("country was not found!");
        }
        else{
            if(country1.getArmies()!=null){
                for (Army army : country1.getArmies()) {
                    if(army.getNumber()==armyNumber){
                        addArmy=false;
                        System.out.println("this country already has this army!");
                    }

                }
            }
            if(addArmy){
                Army army= new Army (armyNumber,matcher.group("Leader"));
                army.setPlace(matcher.group("Place"));
                country1.addArmy(army);
                System.out.println("army created successfully!");
            }
        }
    }

    public void createArmyWithoutPlace(Matcher matcher){
        int armyNumber=Integer.parseInt(matcher.group("Number"));
        String countryName= matcher.group("Country");
        Country country1= country.getCountry(countryName);
        boolean addArmy=true;

        if(country1 ==null){
            System.out.println("country was not found!");
        }
        else{
            if(country1.getArmies()!=null){
                for (Army army : country1.getArmies()) {
                    if(army.getNumber()==armyNumber){
                        addArmy=false;
                        System.out.println("this country already has this army!");
                    }

                }
            }
            if(addArmy){
                Army army= new Army (armyNumber,matcher.group("Leader"));
                country1.addArmy(army);
                System.out.println("army created successfully!");
            }
        }
    }

    public void setPlace(Matcher matcher){
        int armyNumber=Integer.parseInt(matcher.group("ArmyNumber"));
        String countryName= matcher.group("Country");
        String armyPlace = matcher.group("Place");
        Country country1=country.getCountry(countryName);

        if(country1 ==null){
            System.out.println("country was not found!");
        }
        else if(country1.getArmy(armyNumber)==null){
            System.out.println("army was not found!");
        }
        else{
            Army army= country1.getArmy(armyNumber);
            army.setPlace(armyPlace);
            System.out.println("set successfully!");
        }
    }

    public void addCorps(Matcher matcher){
        String corpsNumber=matcher.group("CorpsNumber");
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);


        if(country1 == null){
            System.out.println("country was not found!");
        }

        else if( !matcher.group("ArmyNumber").matches("\\d+")){
            System.out.println("army was not found!");
        }
        else {
            int armyNumber = Integer.parseInt(matcher.group("ArmyNumber"));
            if (country1.getArmy(armyNumber) == null) {
                System.out.println("army was not found!");
            } else if (country1.getCorps(corpsNumber) == null) {
                System.out.println("corps was not found!");
            } else {
                boolean corpsb = true;

                Army army = country1.getArmy(armyNumber);
                Corps corps = country1.getCorps(corpsNumber);
                if (country1.getCorps() != null && army.getCorps() != null) {
                    for (Corps armyCorp : army.getCorps()) {
                        if (armyCorp.equals(corps)) {
                            System.out.println("this corps is in an army!");
                            corpsb = false;
                            break;
                        }
                    }
                }
                if (corpsb) {
                    System.out.println("corps added to army successfully!");
                    army.addCorps(corps);
                }

            }
        }
    }

    public void printArmy(Matcher matcher){

        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);

        if(country1 == null){
            System.out.println("country was not found!");
        }
        else if( !matcher.group("Number").matches("\\d+")){
            System.out.println("army was not found!");
        }
        else {
            int armyNumber = Integer.parseInt(matcher.group("Number"));
            if (country1.getArmy(armyNumber) == null) {
                System.out.println("army was not found!");
            }
            else{
                String leader=country1.getArmy(armyNumber).getLeader();
                int corpsNumber=country1.getArmy(armyNumber).getCorps().size();
                System.out.println(leader +" " + corpsNumber);
            }

        }

    }

    public void printArmyWithDetails(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        ArrayList<Integer> output1= new ArrayList<>();

        if(country1 == null){
            System.out.println("country was not found!");
        }
        else if( !matcher.group("Number").matches("\\d+")){
            System.out.println("army was not found!");
        }
        else {
            int armyNumber = Integer.parseInt(matcher.group("Number"));
            if (country1.getArmy(armyNumber) == null) {
                System.out.println("army was not found!");
            }
            else{
                String leader=country1.getArmy(armyNumber).getLeader();
                int corpsNumber=country1.getArmy(armyNumber).getCorps().size();
                String output=new String();
                int round=0;
                System.out.println(leader +" " + corpsNumber);
                country1.getArmy(armyNumber).getCorps().sort(new Corps.CorpsComparator());
                if(country1.getArmy(armyNumber).getCorps().size()<=3){
                    for (Corps corps : country1.getArmy(armyNumber).getCorps()) {

                        if(corps.getNumber().equals("I")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if(corps.getNumber().equals("II")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }
                        else if(corps.getNumber().equals("III")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if(corps.getNumber().equals("IV")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if(corps.getNumber().equals("V")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if (corps.getNumber().equals("VI")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if(corps.getNumber().equals("VII")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }
                        else if(corps.getNumber().equals("VIII")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }
                        else if(corps.getNumber().equals("IX")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }

                        else if(corps.getNumber().equals("X")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                        }









//                        Infantry infantry=corps.getInfantry();
//                        int infantryNumber= infantry.getNumber();
//                        Cavalry cavalry=corps.getCavalry();
//                        int cavalryNumber= cavalry.getNumber();
//                        Artillery artillery=corps.getArtillery();
//                        int artilleryNumber= artillery.getNumber();
//                        String rankedOfficer=corps.getRankedOfficer();
//                        int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
//                        String corpsNumber1= String.valueOf(number);
//                        output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
//                        System.out.println(output);
                    }
                }
                else{
                    for (Corps corps : country1.getArmy(armyNumber).getCorps()) {

                        if(round==3)
                            break;

                        if(corps.getNumber().equals("I")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if(corps.getNumber().equals("II")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }
                        else if(corps.getNumber().equals("III")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if(corps.getNumber().equals("IV")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if(corps.getNumber().equals("V")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if (corps.getNumber().equals("VI")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if(corps.getNumber().equals("VII")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }
                        else if(corps.getNumber().equals("VIII")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }
                        else if(corps.getNumber().equals("IX")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

                        else if(corps.getNumber().equals("X")){
                            Infantry infantry=corps.getInfantry();
                            int infantryNumber= infantry.getNumber();
                            Cavalry cavalry=corps.getCavalry();
                            int cavalryNumber= cavalry.getNumber();
                            Artillery artillery=corps.getArtillery();
                            int artilleryNumber= artillery.getNumber();
                            String rankedOfficer=corps.getRankedOfficer();
                            int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
                            String corpsNumber1= String.valueOf(number);
                            output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
                            System.out.println(output);
                            round++;
                        }

//                        Infantry infantry=corps.getInfantry();
//                        int infantryNumber= infantry.getNumber();
//                        Cavalry cavalry=corps.getCavalry();
//                        int cavalryNumber= cavalry.getNumber();
//                        Artillery artillery=corps.getArtillery();
//                        int artilleryNumber= artillery.getNumber();
//                        String rankedOfficer=corps.getRankedOfficer();
//                        int number=infantryNumber*1000+cavalryNumber*400+artilleryNumber*10;
//                        String corpsNumber1= String.valueOf(number);
//                        output="    "+infantryNumber+" "+cavalryNumber+" "+artilleryNumber+" "+rankedOfficer+" " +corpsNumber1;
//                        round++;
//                        System.out.println(output);
//                        if(round==3)
//                            break;
                    }
                }


            }
        }
    }

    public void printCountry(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        ArrayList<Integer> armyCorps1=new ArrayList<>();


        if(country1 == null){
            System.out.println("country was not found!");
        }
        else{
            String nationality=country1.getNationality();
            int armyNumber= country1.getArmies().size();

            for (Army army : country1.getArmies()) {
                int armyCorps=army.getCorps().size();
                armyCorps1.add(armyCorps);
            }
            for(int i=0; i<armyCorps1.size(); i++){
                for(int j= i+1; j<armyCorps1.size(); j++){
                    if(armyCorps1.get(i)>armyCorps1.get(j)){
                        int temp=armyCorps1.get(i);
                        armyCorps1.set(i,armyCorps1.get(j));
                        armyCorps1.set(j,temp);
                    }
                }
            }
            StringBuilder stringBuilder= new StringBuilder();
            for (Integer i : armyCorps1) {
                stringBuilder.append(i).append(" ");

            }


            System.out.println(nationality+" "+ armyNumber+" "+ stringBuilder);

        }
    }

    public void printCountryWithDetails(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        ArrayList<Integer> armyCorps1=new ArrayList<>();
        ArrayList<String> leaders=new ArrayList<>();
        ArrayList<Integer> armyNumbers=new ArrayList<>();
        StringBuilder stringBuilder1= new StringBuilder();

        if(country1 == null){
            System.out.println("country was not found!");
        }
        else{
            String nationality=country1.getNationality();
            int armyNumber= country1.getArmies().size();

            for (Army army : country1.getArmies()) {
                int armyCorps=army.getCorps().size();
                armyCorps1.add(armyCorps);
            }
            for(int i=0; i<armyCorps1.size(); i++){
                for(int j= i+1; j<armyCorps1.size(); j++){
                    if(armyCorps1.get(i)>armyCorps1.get(j)){
                        int temp=armyCorps1.get(i);
                        armyCorps1.set(i,armyCorps1.get(j));
                        armyCorps1.set(j,temp);
                    }
                }
            }
            StringBuilder stringBuilder= new StringBuilder();
            for (Integer i : armyCorps1) {
                stringBuilder.append(i).append(" ");
            }

            for (Army army : country1.getArmies()) {
                int number= army.getNumber();
                armyNumbers.add(number);
            }
            for(int i=0; i<armyNumbers.size(); i++){
                for(int j= i+1; j<armyNumbers.size(); j++){
                    if(armyNumbers.get(i)>armyNumbers.get(j)){
                        int temp=armyNumbers.get(i);
                        armyNumbers.set(i,armyNumbers.get(j));
                        armyNumbers.set(j,temp);
                    }
                }
            }
            for (Integer number : armyNumbers) {
                String leader= country1.getArmy(number).getLeader();
                leaders.add(leader);
            }

            for (String i : leaders) {
                stringBuilder1.append(i).append(" ");
            }

            System.out.println(nationality+" "+ armyNumber+" "+ stringBuilder);
            System.out.println(stringBuilder1);

        }
    }

    public int calculateCorpsScore(Corps corps, Army army){
                int score=0;
//        String countryName = matcher.group("Country");
//        String corpsNumber = matcher.group("CorpsNumber");
//        Country country1 = country.getCountry(countryName);
//        int armyNumber = Integer.parseInt(matcher.group("ArmyNumber"));

                String place=army.getPlace();
                String rankedOfficer= corps.getRankedOfficer();
                int rankedOfficerScore=0;
                int cavalryScore=0;
                int infantryScore=0;
                int artilleryScore=0;

                int infantryNumber=corps.getInfantry().getNumber()*1000;
                int cavalryNumber= corps.getCavalry().getNumber()*400;
                int artilleryNumber=corps.getArtillery().getNumber()*10;
                int totalNumber= infantryNumber+cavalryNumber+artilleryNumber;

                if(rankedOfficer.equals("corporal")){
                    rankedOfficerScore=1;

                }
                else if(rankedOfficer.equals("sergent")){
                    rankedOfficerScore=4;
                }
                else if(rankedOfficer.equals("lieutenant")){
                    rankedOfficerScore=7;
                }
                else if(rankedOfficer.equals("capitan")){
                    rankedOfficerScore=12;
                }
                else if(rankedOfficer.equals("colonel")){
                    rankedOfficerScore=18;
                }
                else if(rankedOfficer.equals("general")){
                    rankedOfficerScore=22;
                }
                else if(rankedOfficer.equals("marshal")){
                    rankedOfficerScore=25;
                }


                if(place.equals("Forest")){
                    corps.getInfantry().setScore(2* infantryNumber);
                    corps.getCavalry().setScore(5* cavalryNumber);
                    corps.getArtillery().setScore(2* artilleryNumber);

                }
                else if(place.equals("Plain")){
                    corps.getInfantry().setScore(2* infantryNumber);
                    corps.getCavalry().setScore(0* cavalryNumber);
                    corps.getArtillery().setScore( artilleryNumber);


                }
                else if(place.equals("Hill")){
                    corps.getInfantry().setScore(3* infantryNumber);
                    corps.getCavalry().setScore(-1* cavalryNumber);
                    corps.getArtillery().setScore(artilleryNumber);

                }
                else if(place.equals("Mountain")){
                    corps.getInfantry().setScore(infantryNumber);
                    corps.getCavalry().setScore(-2* cavalryNumber);
                    corps.getArtillery().setScore(artilleryNumber);

                }
                infantryScore=corps.getInfantry().getScore();
                cavalryScore=corps.getCavalry().getScore();
                artilleryScore=corps.getArtillery().getScore();
                score= infantryScore +cavalryScore+ artilleryScore + rankedOfficerScore*1000 + totalNumber;
               // System.out.println(score);
        return score;

            }






    public void printCorpsScore(Matcher matcher) {

        String countryName = matcher.group("Country");
        String corpsNumber = matcher.group("CorpsNumber");
        Country country1 = country.getCountry(countryName);

        if (country1 == null) {
            System.out.println("country was not found!");
        } else if (!matcher.group("ArmyNumber").matches("\\d+")) {
            System.out.println("army was not found!");
        } else {
            int armyNumber = Integer.parseInt(matcher.group("ArmyNumber"));
            if (country1.getArmy(armyNumber) == null) {
                System.out.println("army was not found!");
            } else if (country1.getCorps(corpsNumber) == null) {
                System.out.println("corps was not found!");
            }
            else if(country1.getArmy(armyNumber).getCorps(corpsNumber)==null){
                System.out.println("this corps is not in this army!");
            }
            else{
                String place=country1.getArmy(armyNumber).getPlace();
                String rankedOfficer= country1.getCorps(corpsNumber).getRankedOfficer();
                int rankedOfficerScore=0;
                int cavalryScore=0;
                int infantryScore=0;
                int artilleryScore=0;
                int score=0;
                int infantryNumber=country1.getCorps(corpsNumber).getInfantry().getNumber()*1000;
                int cavalryNumber= country1.getCorps(corpsNumber).getCavalry().getNumber()*400;
                int artilleryNumber=country1.getCorps(corpsNumber).getArtillery().getNumber()*10;
                int totalNumber= infantryNumber+cavalryNumber+artilleryNumber;

//                if(rankedOfficer.equals("corporal")){
//                    rankedOfficerScore=1;
//
//                }
//                else if(rankedOfficer.equals("sergent")){
//                    rankedOfficerScore=4;
//                }
//                else if(rankedOfficer.equals("lieutenant")){
//                    rankedOfficerScore=7;
//                }
//                else if(rankedOfficer.equals("capitan")){
//                    rankedOfficerScore=12;
//                }
//                else if(rankedOfficer.equals("colonel")){
//                    rankedOfficerScore=18;
//                }
//                else if(rankedOfficer.equals("general")){
//                    rankedOfficerScore=22;
//                }
//                else if(rankedOfficer.equals("marshal")){
//                    rankedOfficerScore=25;
//                }
//
//
//                if(place.equals("Forest")){
//                    country1.getCorps(corpsNumber).getInfantry().setScore(2* infantryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(5* cavalryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(2* artilleryNumber);
//
//                }
//                else if(place.equals("Plain")){
//                    country1.getCorps(corpsNumber).getInfantry().setScore(2* infantryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(0* cavalryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore( artilleryNumber);
//
//
//                }
//                else if(place.equals("Hill")){
//                    country1.getCorps(corpsNumber).getInfantry().setScore(3* infantryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(-1* cavalryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(artilleryNumber);
//
//                }
//                else if(place.equals("Mountain")){
//                    country1.getCorps(corpsNumber).getInfantry().setScore(infantryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(-2* cavalryNumber);
//                    country1.getCorps(corpsNumber).getCavalry().setScore(artilleryNumber);
//
//                }
//                infantryScore=country1.getCorps(corpsNumber).getInfantry().getScore();
//                cavalryScore=country1.getCorps(corpsNumber).getCavalry().getScore();
//                artilleryScore=country1.getCorps(corpsNumber).getArtillery().getScore();
//                score= infantryScore +cavalryScore+ artilleryScore + rankedOfficerScore*1000 + totalNumber;
//                System.out.println(score);
          int result=calculateCorpsScore(country1.getCorps(corpsNumber),country1.getArmy(armyNumber));
                System.out.println(result);
            }


        }
    }

    public int calculateArmyScore(Army army){
        int score=0;
        for (Corps corps : army.getCorps()) {

            score+=calculateCorpsScore(corps,army);
//                    infantryScore+=corps.getInfantry().getScore();
//                    cavalryScore+=corps.getCavalry().getScore();
//                    artilleryScore+=corps.getArtillery().getScore();
        }
        return score;
    }

    public void printArmyScore(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        int armyNumber=Integer.parseInt(matcher.group("ArmyNumber"));


        if(country1 == null){
            System.out.println("country was not found!");
        }
        else if( !matcher.group("ArmyNumber").matches("\\d+")){
            System.out.println("army was not found!");
        }
        else{
            int infantryScore=0;
            int cavalryScore=0;
            int artilleryScore=0;
            int score=0;

            if (country1.getArmy(armyNumber) == null) {
                System.out.println("army was not found!");
            }
            else{
          //  System.out.println(country1.getArmy(armyNumber).getCorps());

                for (Corps corps : country1.getArmy(armyNumber).getCorps()) {


                    score+=calculateCorpsScore(corps,country1.getArmy(armyNumber));
//                    infantryScore+=corps.getInfantry().getScore();
//                    cavalryScore+=corps.getCavalry().getScore();
//                    artilleryScore+=corps.getArtillery().getScore();
                }

                //score=infantryScore+cavalryScore+artilleryScore;
                 score=calculateArmyScore(country1.getArmy(armyNumber));

                System.out.println(score);

            }
        }
    }

    public int calculateCountryScore(Country country){
        int score=0;
        if(country!=null)
        for (Army army : country.getArmies())
            score+=calculateArmyScore(army);
        return score;
    }

    public void printCountryScore(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        int score=0;
        if(country1 == null){
            System.out.println("country was not found!");
        }
        else{

           score+= calculateCountryScore(country1);

//            for (Army army : country1.getArmies()) {
//                score+=calculateArmyScore(army);
//            }
            System.out.println(score);
        }
    }

    public void unionWithOne(Matcher matcher){
        String countryName1= matcher.group("Country1");
        String countryName2= matcher.group("Country2");
        Country country1=country.getCountry(countryName1);
        Country country2=country.getCountry(countryName2);
        boolean oops= false;
        boolean end= true;



        if(country1 == null || country2==null){
            System.out.println("country was not found!");
            end=false;
        }
//        else if(country1.getUnions()!=null && country2.getUnions()!=null) {

        else {

            if (country1.getUnions() != null && country2.getEnemies() != null) {
                for (Country union : country1.getUnions()) {
                    for (Country enemy : country2.getEnemies()) {

                        if (union.equals(enemy)) {
                            oops = true;
                            break;
                        }
                    }
                }
            }

            if (country1.getEnemies() != null && country2.getUnions() != null) {

                for (Country enemy : country1.getEnemies()) {
                    for (Country union : country2.getUnions()) {
                        System.out.println(union);
                        System.out.println(enemy);
                        if (union.equals(enemy)) {
                            oops = true;
                            break;
                        }
                    }
                }
            }
            if (country1.getUnions() != null) {
                if (country1.getUnions(countryName2) != null) {
                    System.out.println("something went wrong!");
                    end = false;
                }
            }
            if (country1.getEnemies() != null) {
                if (country1.getEnemies(countryName2) != null) {
                    System.out.println("something went wrong!");
                    end = false;
                }
            }
            if (oops) {
                System.out.println("something went wrong!");
                end = false;

            }
            if (end) {
                country1.addUnions(country2);
                country2.addUnions(country1);
                System.out.println("unionized successfully!");
            }
        }
    }

    public void makeEnemy(Matcher matcher){
        String countryName1= matcher.group("Country1");
        String countryName2= matcher.group("Country2");
        Country country1=country.getCountry(countryName1);
        Country country2=country.getCountry(countryName2);
        boolean oops= false;

        if(country1 == null || country2==null){
            System.out.println("country was not found!");
        }
        else if(country1.getEnemies()!=null && country2.getEnemies()!=null) {

            if(country1.getUnions()!=null && country2.getEnemies()!=null) {


                for (Country union : country1.getUnions()) {
                    for (Country enemy : country2.getEnemies()) {
                        if (union.equals(enemy)) {
                            oops = true;
                            break;
                        }
                    }
                }
            }
            if( country1.getEnemies()!=null && country2.getUnions()!=null){

                for (Country enemy : country1.getEnemies()) {
                    for (Country union : country2.getUnions()) {
                        if (union.equals(enemy)) {
                            oops = true;
                            break;
                        }
                    }
                }
            }

            if (country1.getUnions(countryName2) != null || country1.getEnemies(countryName2) != null || oops ) {
                System.out.println("something went wrong!");
            }
        }

        else{

            country1.addEnemies(country2);
            country2.addEnemies(country1);
            System.out.println("enemy made successfully!");
        }
    }

    public void unionWithSome(Matcher matcher){
        String countryName1= matcher.group("Country1");
        String[] countries= matcher.group("ListOfCountries").split("\\s+");
        String[] countries2= Arrays.copyOfRange(countries, 0,countries.length);
        ArrayList<String> countries3= new ArrayList<>();

        for(String countries4 : countries2){
            countries3.add(countries4);
        }

//        for(int i=0; i<input.size(); i++){
//
//            manager.selectAll(input.get(i));
//        }

    }

    public void showFriends(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        ArrayList<Country> unions= new ArrayList<>();
        ArrayList<Integer> unionScore= new ArrayList<>();


        if(country1 == null){
            System.out.println("country was not found!");
        }
        else{



            for (Country union : country1.getUnions()) {
                union.setScore(calculateCountryScore(union));

            }
            for (Country union : country1.getUnions()) {
                unionScore.add(union.getScore());
            }


            for(int i=0; i<unionScore.size(); i++){
                for(int j= i+1; j<unionScore.size(); j++){
                    if(unionScore.get(i)>unionScore.get(j)){
                        int temp=unionScore.get(i);
                        unionScore.set(i,unionScore.get(j));
                        unionScore.set(j,temp);
                    }
                }
            }
            for (Integer number : unionScore) {
                Country country= country1.getCountryWithScoreUnions(number);
                unions.add(country);
            }
            StringBuilder stringBuilder= new StringBuilder();
            for (Country i : unions) {

                stringBuilder.append(i.getName()).append(" ");
            }
            System.out.println(stringBuilder);
        }

    }

    public void showEnemies(Matcher matcher){
        String countryName= matcher.group("Country");
        Country country1=country.getCountry(countryName);
        ArrayList<Country> enemies= new ArrayList<>();
        ArrayList<Integer> enemiesScore= new ArrayList<>();


        if(country1 == null){
            System.out.println("country was not found!");
        }
        else{

            for (Country enemy : country1.getEnemies()) {
                enemy.setScore(calculateCountryScore(enemy));
            }
            for (Country enemy : country1.getEnemies()) {
                enemiesScore.add(enemy.getScore());
            }


            for(int i=0; i<enemiesScore.size(); i++){
                for(int j= i+1; j<enemiesScore.size(); j++){
                    if(enemiesScore.get(i)>enemiesScore.get(j)){
                        int temp=enemiesScore.get(i);
                        enemiesScore.set(i,enemiesScore.get(j));
                        enemiesScore.set(j,temp);
                    }
                }
            }
            for (Integer number : enemiesScore) {
                Country country= country1.getCountryWithScoreEnemies(number);
                enemies.add(country);
            }
            StringBuilder stringBuilder= new StringBuilder();
            for (Country i : enemies) {

                stringBuilder.append(i.getName()).append(" ");
            }
            System.out.println(stringBuilder);
        }
    }

    public void warWithPlace(Matcher matcher){
        String countryName1= matcher.group("Country1");
        String countryName2= matcher.group("Country2");
        String place=matcher.group("Place");
        Country country1=country.getCountry(countryName1);
        Country country2=country.getCountry(countryName2);

        if(country1 == null || country2==null){
            System.out.println("country was not found!");
        }

        else{
            int score1=0;
            int score2=0;

            for (Army army : country1.getArmies()) {
                if(army.getPlace().equals(place)){
                    score1+=calculateArmyScore(army);
                }
            }
            for (Army army : country2.getArmies()) {
                if(army.getPlace().equals(place)){
                    score2+=calculateArmyScore(army);
                }
            }

            if(score1>score2){
                System.out.println(country1.getName());
                for (Army army : country2.getArmies()) {
                    for (Corps corps : army.getCorps()) {
                        int infantry=corps.getInfantry().getNumber();
                        int cavalry= corps.getCavalry().getNumber();
                        if(infantry%2==0 && cavalry%2==0){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber(infantry/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber(cavalry/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==1 && cavalry%2==0){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber((infantry+1)/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber(cavalry/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==0 && cavalry%2==1){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber(infantry/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber((cavalry+1)/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==1 && cavalry%2==1){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber((infantry+1)/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber((cavalry+1)/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                    }
                }
            }
            else if(score2>score1){
                System.out.println(country2.getName());
                for (Army army : country1.getArmies()) {
                    for (Corps corps : army.getCorps()) {
                        int infantry=corps.getInfantry().getNumber();
                        int cavalry= corps.getCavalry().getNumber();
                        if(infantry%2==0 && cavalry%2==0){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber(infantry/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber(cavalry/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==1 && cavalry%2==0){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber((infantry+1)/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber(cavalry/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==0 && cavalry%2==1){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber(infantry/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber((cavalry+1)/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                        else if(infantry%2==1 && cavalry%2==1){
                            Infantry infantry2=corps.getInfantry();
                            infantry2.setNumber((infantry+1)/2);
                            Cavalry cavalry2= corps.getCavalry();
                            cavalry2.setNumber((cavalry+1)/2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                    }
                }
            }
        }
    }

    public void warWithoutPlace(Matcher matcher) {
        String countryName1 = matcher.group("Country1");
        String countryName2 = matcher.group("Country2");
        Country country1 = country.getCountry(countryName1);
        Country country2 = country.getCountry(countryName2);

        int score1 =0;
        int score2 =0;

        if (country1 == null || country2 == null) {
            System.out.println("country was not found!");
        } else {
             score1 = calculateCountryScore(country1);
             score2 = calculateCountryScore(country2);

            if (score1 > score2) {
                System.out.println(country1.getName());

                for (Army army : country2.getArmies()) {
                    for (Corps corps : army.getCorps()) {
                        int infantry = corps.getInfantry().getNumber();
                        int cavalry = corps.getCavalry().getNumber();
                        if (infantry % 2 == 0 && cavalry % 2 == 0) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber(infantry / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber(cavalry / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 1 && cavalry % 2 == 0) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber((infantry + 1) / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber(cavalry / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 0 && cavalry % 2 == 1) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber(infantry / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber((cavalry + 1) / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 1 && cavalry % 2 == 1) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber((infantry + 1) / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber((cavalry + 1) / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                    }
                }
            } else if (score2 > score1) {
                System.out.println(country2.getName());
                for (Army army : country1.getArmies()) {
                    for (Corps corps : army.getCorps()) {
                        int infantry = corps.getInfantry().getNumber();
                        int cavalry = corps.getCavalry().getNumber();
                        if (infantry % 2 == 0 && cavalry % 2 == 0) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber(infantry / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber(cavalry / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 1 && cavalry % 2 == 0) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber((infantry + 1) / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber(cavalry / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 0 && cavalry % 2 == 1) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber(infantry / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber((cavalry + 1) / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        } else if (infantry % 2 == 1 && cavalry % 2 == 1) {
                            Infantry infantry2 = corps.getInfantry();
                            infantry2.setNumber((infantry + 1) / 2);
                            Cavalry cavalry2 = corps.getCavalry();
                            cavalry2.setNumber((cavalry + 1) / 2);
                            corps.setInfantry(infantry2);
                            corps.setCavalry(cavalry2);
                        }
                    }
                }
            }
        }
    }







    public void run() {
    String command;

        do {
        command = scanner.nextLine();

        if (command.equalsIgnoreCase("end")) {
            break;
        }
        else if (command.matches("create country (?<Name>[a-zA-Z\\s]+) (?<Nationality>[a-zA-Z\\s]+)")) {
            Matcher matcher = getCommandMatcher(command, "create country (?<Name>[a-zA-Z\\s]+) (?<Nationality>[a-zA-Z\\s]+)");
            matcher.find();
            createCountry(matcher);
            continue;
        }
        else if(command.matches("create corps (?<infantry>\\d+) (?<cavalry>\\d+) (?<artillery>\\d+) (?<Officer>[a-z\\s]+) for (?<Country>[a-zA-Z\\s]+) (?<Number>.*)")){
            Matcher matcher = getCommandMatcher(command, "create corps (?<infantry>\\d+) (?<cavalry>\\d+) (?<artillery>\\d+) (?<Officer>[a-z\\s]+) for (?<Country>[a-zA-Z\\s]+) (?<Number>.*)");
            matcher.find();
            createCorps(matcher);
            continue;
        }
        else if(command.matches("create army (?<Number>\\d+) (?<Leader>[a-zA-Z\\s]+) for (?<Country>[a-zA-Z\\s]+) in (?<Place>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command, "create army (?<Number>\\d+) (?<Leader>[a-zA-Z\\s]+) for (?<Country>[a-zA-Z\\s]+) in (?<Place>[a-zA-Z\\s]+)");
            matcher.find();
            createArmyWithPlace(matcher);
            continue;
        }
        else if(command.matches("create army (?<Number>\\d+) (?<Leader>[a-zA-Z\\s]+) for (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command,"create army (?<Number>\\d+) (?<Leader>[a-zA-Z\\s]+) for (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            createArmyWithoutPlace(matcher);
            continue;
        }
        else if(command.matches("set place for (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>\\d+) in (?<Place>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command,"set place for (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>\\d+) in (?<Place>[a-zA-Z\\s]+)");
            matcher.find();
            setPlace(matcher);
            continue;
        }
        else if(command.matches("add corps (?<CorpsNumber>.*) to army (?<ArmyNumber>.*) of (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command, "add corps (?<CorpsNumber>.*) to army (?<ArmyNumber>.*) of (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            addCorps(matcher);
            continue;
        }
        else if(command.matches("print army (?<Number>\\d+) (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command,"print army (?<Number>\\d+) (?<Country>[a-zA-Z\\s]+)" );
            matcher.find();
            printArmy(matcher);
            continue;
        }
        else if(command.matches("print army with details (?<Number>\\d+) (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command, "print army with details (?<Number>\\d+) (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            printArmyWithDetails(matcher);
            continue;
        }
        else if(command.matches("print country (?<Country>[a-zA-Z]+)")){
            Matcher matcher = getCommandMatcher(command,"print country (?<Country>[a-zA-Z]+)");
            matcher.find();
            printCountry(matcher);
            continue;
        }
        else if(command.matches("print country with details (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher = getCommandMatcher(command, "print country with details (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            printCountryWithDetails(matcher);
            continue;
        }
        else if(command.matches("print score of (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>.*) (?<CorpsNumber>.*)")){
            Matcher matcher = getCommandMatcher(command, "print score of (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>.*) (?<CorpsNumber>.*)");
            matcher.find();
            printCorpsScore(matcher);
            //calculateCorpsScore(matcher);
        }


        else if(command.matches("print score of (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>\\d+)")){
            Matcher matcher =getCommandMatcher(command,"print score of (?<Country>[a-zA-Z\\s]+) (?<ArmyNumber>\\d+)");
            matcher.find();
            printArmyScore(matcher);
        }
        else if(command.matches("print score of (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"print score of (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            printCountryScore(matcher);
        }
        else if(command.matches("(?<Country1>[a-zA-Z\\s]+) join union with (?<Country2>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"(?<Country1>[a-zA-Z\\s]+) join union with (?<Country2>[a-zA-Z\\s]+)");
            matcher.find();
            unionWithOne(matcher);
        }
        else if(command.matches("(?<Country1>[a-zA-Z\\s]+) made enemy of (?<Country2>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"(?<Country1>[a-zA-Z\\s]+) made enemy of (?<Country2>[a-zA-Z\\s]+)");
            matcher.find();
            makeEnemy(matcher);
        }
        else if(command.matches("(?<Country1>[a-zA-Z\\s]+) join union with (?<ListOfCountries>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"(?<Country1>[a-zA-Z\\s]+) made enemy of (?<Country2>[a-zA-Z\\s]+)");
            matcher.find();
            unionWithSome(matcher);
        }
        else if(command.matches("show friends of (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"show friends of (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            showFriends(matcher);
        }
        else if(command.matches("show enemies of (?<Country>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"show enemies of (?<Country>[a-zA-Z\\s]+)");
            matcher.find();
            showEnemies(matcher);
        }
        else if(command.matches("war between (?<Country1>[a-zA-Z\\s]+) and (?<Country2>[a-zA-Z\\s]+) in (?<Place>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"war between (?<Country1>[a-zA-Z\\s]+) and (?<Country2>[a-zA-Z\\s]+) in (?<Place>[a-zA-Z\\s]+)");
            matcher.find();
            warWithPlace(matcher);
        }
        else if(command.matches("war between (?<Country1>[a-zA-Z\\s]+) and (?<Country2>[a-zA-Z\\s]+)")){
            Matcher matcher =getCommandMatcher(command,"war between (?<Country1>[a-zA-Z\\s]+) and (?<Country2>[a-zA-Z\\s]+)");
            matcher.find();
            warWithoutPlace(matcher);
        }
        else{
            System.out.println("invalid input!");
        }

    }while (true);
}

}
