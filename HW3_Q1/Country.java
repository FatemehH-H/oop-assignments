import java.util.ArrayList;

public class Country {
    private String name;
    private String nationality;
    private ArrayList<Army> armies;
    private ArrayList<Corps> corps;
    private ArrayList<Country> countries;
    private int score;

    private ArrayList<Country> unions;
    private ArrayList<Country> enemies;
    public Country(){
        this.countries=new ArrayList<>();
        this.corps=new ArrayList<>();
    }

    public Country(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

//    public String getArmy() {
//        return army;
//    }

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public ArrayList<Corps> getCorps() {
        return corps;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public ArrayList<Country> getUnions() {
        return unions;
    }

    public ArrayList<Country> getEnemies() {
        return enemies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArmies(ArrayList<Army> armies) {
        this.armies = armies;
    }

//    public void setCorps(ArrayList<Corps> corps) {
//        this.corps = corps;
//    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

//    public void setArmy(String army) {
//        this.army = army;
//    }

    public void addCountry(Country country){
        if(countries==null){
            countries=new ArrayList<>();
        }
        countries.add(country);
    }
    public void addCorps(Corps corp){
        if(corps==null){
            corps=new ArrayList<>();
        }
        corps.add(corp);
    }

    public void addArmy(Army army) {
        if(armies==null){
            armies=new ArrayList<>();
        }
        armies.add(army);
    }

    public void addUnions(Country country){
        if(unions==null){
            unions= new ArrayList<>();
        }
        unions.add(country);
    }

    public void addEnemies(Country country){
        if(enemies==null){
            enemies=new ArrayList<>();
        }
        enemies.add(country);
    }

    public Country getCountry(String countryName){
        for (Country country : countries)
            if(country.getName().equals(countryName))
                return country;
        return null;
    }

    public Army getArmy(int armyNumber){
        for (Army army : armies)
            if(army.getNumber()==armyNumber)
                return army;
        return null;


    }

    public Corps getCorps(String corpsNumber){
        for (Corps corps1 : corps)
            if(corps1.getNumber().equals(corpsNumber))
                return corps1;
        return null;
    }

    public Country getUnions(String countryName){
        for (Country union : unions)
            if(union.getName().equals(countryName))
                return union;
        return null;
    }


    public Country getEnemies(String countryName){
        for (Country enemy : enemies)
            if(enemy.getName().equals(countryName))
                return enemy;
        return null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Country getCountryWithScoreUnions(int score){
        for (Country country : unions)
            if(country.getScore()==score)
                return country;
        return null;
    }


    public Country getCountryWithScoreEnemies(int score){
        for (Country country : enemies)
            if(country.getScore()==score)
                return country;
        return null;
    }


}
