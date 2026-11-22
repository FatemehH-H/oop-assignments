import java.util.ArrayList;

public class User {

    private String userName;
    private String password;
    private int gold;
    private int experience;
    private int level;
    private int rank;
    private User opponent;
    private String currentMenu;
    private ArrayList<User> users;
    private ArrayList<User> users1;
    private ArrayList<Card> battleDeck;
    private ArrayList<Card> cards;
    private ArrayList<Castle> castles;
    private ArrayList<Card> gameCards;
    boolean useTroop=false;
    int moves=3;



//    private ArrayList<Card> opponentCards;
//    private ArrayList<Card> opponentBattleDeck;

//    public ArrayList<Card> getOpponentBattleDeck() {
//        return opponentBattleDeck;
//    }
//
//    public void setOpponentBattleDeck(ArrayList<Card> opponentBattleDeck) {
//        this.opponentBattleDeck = opponentBattleDeck;
//    }
//
//    public ArrayList<Card> getOpponentCards() {
//        return opponentCards;
//    }
//
//    public void setOpponentCards(ArrayList<Card> opponentCards) {
//        this.opponentCards = opponentCards;
//    }

    public ArrayList<Castle> getCastles() {
        return castles;
    }

    public void setCastles(ArrayList<Castle> castles) {
        this.castles = castles;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public int getRank() {
        return rank;
    }


    public String getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public User(){
        this.users=new ArrayList<>();
        this.battleDeck=new ArrayList<>();
        this.cards=new ArrayList<>();
        this.gameCards=new ArrayList<>();
    }

    public void setBattleDeck(ArrayList<Card> battleDeck) {
        this.battleDeck = battleDeck;
    }

    public ArrayList<Card> getGameCards() {
        return gameCards;
    }

    public void setGameCards(ArrayList<Card> gameCards) {
        this.gameCards = gameCards;
    }

    public User(String userName, String password, int gold, int experience, int level) {
        this.userName = userName;
        this.password = password;
        this.gold = gold;
        this.experience = experience;
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public User getUser(String userName){
        for (User user : users)
            if(user.getUserName().equals(userName))
                return user;
        return null;
    }

    public Card getCardFromBattle(String cardName){
        if(battleDeck!=null)
            for (Card card : battleDeck)
                if(card.getName().equals(cardName))
                    return card;
        return null;
    }

    public Card getCardByLocation(Location location) {
        if(gameCards!=null)
            for (Card gameCard : gameCards)
                if(gameCard.getLocation().equals(location))
                    return gameCard;
        return null;

    }

//    public User getCardOwner(Card card) {
//        if(gameCards!=null)
//            for (Card gameCard : gameCards)
//                if(gameCard.getLocation().equals(location))
//                    return gameCard;
//        return null;
//
//    }

    public Card getCard(String cardName){
        if(cards!=null)
            for (Card card : cards)
                if(card.getName().equals(cardName))
                    return card;
        return null;
    }

//    public Card getCardFromOpponent(String cardName){
//        if(opponentCards!=null)
//            for (Card card : opponentCards)
//                if(card.getName().equals(cardName))
//                    return card;
//        return null;
//    }
//
//    public Card getCardFromOpponentBattle(String cardName){
//        if(opponentBattleDeck!=null)
//            for (Card card : opponentBattleDeck)
//                if(card.getName().equals(cardName))
//                    return card;
//        return null;
//    }

    public Castle getCastle(String castleName){
        if(castles!=null)
            for (Castle castle : castles)
                if(castle.getName().equals(castleName))
                    return castle;
        return null;
    }

//    public Card getCastle(String cardName){
//        if(castles!=null)
//            for (Castle castle : castles)
//                if(castle.getName().equals(cardName))
//                    return card;
//        return null;
//    }


    public void addUser(User user){
        if(users==null){
            users=new ArrayList<>();
        }
        if(users1==null){
            users1=new ArrayList<>();
        }
        users.add(user);
        users1.add(user);
    }

    public void addCard(Card card){
        if(battleDeck==null){
            battleDeck=new ArrayList<>();
        }
        battleDeck.add(card);
    }

//    public void addOpponentCard(Card card){
//        if(battleDeck==null){
//            battleDeck=new ArrayList<>();
//        }
//        battleDeck.add(card);
//    }

    public void addCastle(Castle castle){
        if(castles==null){
            castles=new ArrayList<>();
        }
        castles.add(castle);
    }

    public void addCardToGame(Card card){
        if(gameCards==null){
            gameCards=new ArrayList<>();
        }
        gameCards.add(card);
    }

    public void sortUser() {


        for (int i = 0; i < users1.size() - 1; i++) {
            for (int j = i + 1; j < users1.size(); j++) {

                int level1 = users1.get(i).getLevel();
                int level2 = users1.get(j).getLevel();
                int experience1 = users1.get(i).getExperience();
                int experience2 = users1.get(j).getExperience();
                String userName1 = users1.get(i).getUserName();
                String userName2 = users1.get(j).getUserName();

                int result4 = userName1.compareTo(userName2);



                if (level1 < level2) {
                    User temp = users1.get(i);
                    users1.set(i, users1.get(j));
                    users1.set(j, temp);
                }

                else if (level1 == level2) {
                    if (experience1 < experience2) {
                        User temp = users1.get(i);
                        users1.set(i, users1.get(j));
                        users1.set(j, temp);
                    }


                    else if (experience1 == experience2) {

                        if (result4 > 0) {
                            User temp = users1.get(i);
                            users1.set(i, users1.get(j));
                            users1.set(j, temp);

                        }
                    }
                }
            }
        }
    }

    public ArrayList<User> getUsers1() {
        return users1;
    }

    public void setUsers1(ArrayList<User> users1) {
        this.users1 = users1;
    }

    public void sortBattleDeck() {
        for (int i = 0; i < battleDeck.size() - 1; i++) {
            for (int j = i + 1; j < battleDeck.size(); j++) {
                String name1 = battleDeck.get(i).getName();
                String name2 = battleDeck.get(j).getName();

                int result4 = name1.compareTo(name2);

                if (result4 > 0) {
                    Card temp = battleDeck.get(i);
                    battleDeck.set(i, battleDeck.get(j));
                    battleDeck.set(j, temp);

                }
            }
        }
    }

    public ArrayList<Card> getBattleDeck() {
        return battleDeck;
    }

//    public void setBattleDeck(ArrayList<Card> battleDeck) {
//        this.battleDeck = battleDeck;
//    }







}

