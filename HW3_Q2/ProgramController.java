import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.ArrayList;

public class ProgramController {
    private Scanner scanner = new Scanner(System.in);

    private User loggedInUser ;
    private User turnUser;

    // private boolean gameTurn=false;

    //    private Card gameGround[][][]= new Card[15][15][15];
    private Card[][][] gameGround2= new Card[15][3][];

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    User user = new User();
    Card card = new Card();
    Fireball fireball = new Fireball();
    Archer archer = new Archer();
    Wizard wizard = new Wizard();
    Dragon dragon = new Dragon();
    MidCastle midCastle = new MidCastle(3400);
    RightCastle rightCastle = new RightCastle(2200);
    LeftCastle leftCastle = new LeftCastle(2200);
    private int turns;
    private int moves=3;
    private int cardsToPlay=1;

    public void signIn(Matcher matcher) {
        String userName = matcher.group("username");
        String password = matcher.group("password");
        boolean done = true;
        boolean check = true;

        if (!userName.matches("[a-zA-Z]+(?!.*\\s)")) {
            System.out.println("Incorrect format for username!");
            done = false;
            check = false;
        } else if (!password.matches("(?!\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])(?!.*\\s).{8,20}")) {
            System.out.println("Incorrect format for password!");
            done = false;
            check = false;
        }
        if (check) {
            for (User userUser : user.getUsers()) {
                if (userUser.getUserName().equals(userName)) {
                    System.out.println("Username already exists!");
                    done = false;
                    break;
                }
            }
        }
        if (done) {
            System.out.println("User " + userName + " created successfully!");
            User user1=new User(userName,password,80,0,1);
            user.addUser(user1);
            Fireball fireball1 = new Fireball();
            user1.addCard(fireball1);
            fireball1.setOwner(user1);
            Archer archer1 = new Archer();
            user1.addCard(archer1);
            archer1.setOwner(user1);
            user1.addCastle(new MidCastle(3400));
            user1.addCastle(new RightCastle(2200));
            user1.addCastle(new LeftCastle(2200));
            user1.getCastle("MidCastle").setHitPoint(user1.getLevel()*3400);
            user1.getCastle("RightCastle").setHitPoint(user1.getLevel()*2200);
            user1.getCastle("LeftCastle").setHitPoint(user1.getLevel()*2200);


        }
    }

    public void logIn(Matcher matcher) {

        String userName = matcher.group("username");
        String password = matcher.group("password");
        boolean check = true;
        boolean userExists = false;
        User user1 = user.getUser(userName);

        for (User userUser : user.getUsers()) {
            if (userUser.getUserName().equals(userName)) {
                userExists = true;
                break;
            }
        }

        if (!userName.matches("[a-zA-Z]+(?!.*\\s)")) {
            System.out.println("Incorrect format for username!");
            check = false;
        } else if (!password.matches("(?!\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])(?!.*\\s).{8,20}")) {
            System.out.println("Incorrect format for password!");
            check = false;
        }

//        if (check) {

        else if (user1 == null || !userExists ) {
            System.out.println("Username doesn't exist!");
        } else if (!user1.getPassword().equals(password)) {
            System.out.println("Password is incorrect!");
        } else {
            System.out.println("User " + userName + " logged in!");
            loggedInUser = user1;
            loggedInUser.setCurrentMenu("Main Menu");

        }
//            }
    }


    public void changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if(loggedInUser!=null){
            if(!loggedInUser.getPassword().equals(oldPassword)) {
                System.out.println("Incorrect password!");
            }
            else if(!newPassword.matches("(?!\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])(?!.*\\s).{8,20}")){
                System.out.println("Incorrect format for new password!");
            }
            else{
                System.out.println("Password changed successfully!");
                loggedInUser.setPassword(newPassword);
            }
        }
    }

    public void backToMainMenu(Matcher matcher) {
        System.out.println("Entered main menu!");
        loggedInUser.setCurrentMenu("Main Menu");
    }

    public void info(Matcher matcher) {

        scoreboard();

        String userName=loggedInUser.getUserName();
        String password=loggedInUser.getPassword();
        int level=loggedInUser.getLevel();
        int experience=loggedInUser.getExperience();
        int gold=loggedInUser.getGold();
        int rank=loggedInUser.getRank();

        System.out.println("username: "+userName);
        System.out.println("password: "+password);
        System.out.println("level: "+level);
        System.out.println("experience: "+experience);
        System.out.println("gold: "+gold);
        System.out.println("rank: "+rank);
    }

    public void listOfUsers(Matcher matcher) {
        int i=1;
        for (User userUser : user.getUsers()) {
            System.out.println("user " + i + ": " + userUser.getUserName());
            i++;
        }
    }

    public void logOut(Matcher matcher) {
        System.out.println("User "+loggedInUser.getUserName()+" logged out successfully!");
        loggedInUser = null;
    }

    public void profileMenu(Matcher matcher) {
        System.out.println("Entered profile menu!");
        loggedInUser.setCurrentMenu("profile menu");
    }

    public void shopMenu(Matcher matcher) {
        System.out.println("Entered shop menu!");
        loggedInUser.setCurrentMenu("shop menu");
    }

    public void startGame(Matcher matcher) {
        String userName = matcher.group("username");
        int turnsCount = Integer.parseInt(matcher.group("turnsCount"));
        User user1= user.getUser(userName);
        boolean userExists= false;

        for (User userUser : user.getUsers()) {
            if (userUser.getUserName().equals(userName)) {
                userExists = true;
                break;
            }
        }

        if(turnsCount>30 || turnsCount<5){
            System.out.println("Invalid turns count!");
        }
        else if (!userName.matches("[a-zA-Z]+(?!.*\\s)")) {
            System.out.println("Incorrect format for username!");
        }
        else if (user1 == null || !userExists ) {
            System.out.println("Username doesn't exist!");
        }
        else{
            System.out.println("Battle started with user "+userName);
            loggedInUser.setOpponent(user1);
            user1.setOpponent(loggedInUser);
            loggedInUser.setCurrentMenu("Game Menu");
            turnUser=loggedInUser;
            turns=turnsCount;

        }
    }

    public void showCurrentMenu(Matcher matcher) {
        if(loggedInUser==null){
            System.out.println("Register/Login Menu");
        }
        if(loggedInUser!=null){
            String currentMenu=loggedInUser.getCurrentMenu();
            System.out.println(currentMenu);
        }

    }

    public void scoreboard() {
        int i=1;
        user.sortUser();
        if(user.getUsers1().size()<5){
            for (User userUser : user.getUsers1()) {
                userUser.setRank(i);
                i++;
            }
        }

        else if(user.getUsers1().size()>=5) {
            for (User userUser : user.getUsers1()) {
                userUser.setRank(i);
                i++;
                if (i == 5)
                    break;
            }
        }
    }

    public void printScoreBoard(){
        int i=1;
        user.sortUser();
        if(user.getUsers1().size()<5){
            for (User userUser : user.getUsers1()) {
                userUser.setRank(i);
                System.out.println(i + "-" + " username: " + userUser.getUserName() + " level: " + userUser.getLevel() + " experience: " + userUser.getExperience());
                i++;
            }
        }

        else if(user.getUsers1().size()>=5) {
            for (User userUser : user.getUsers1()) {
                userUser.setRank(i);
                System.out.println(i + "-" + " username: " + userUser.getUserName() + " level: " + userUser.getLevel() + " experience: " + userUser.getExperience());
                i++;
                if (i == 5)
                    break;
            }
        }


    }



    public void removeFromBattleDeck(Matcher matcher) {
        String cardName = matcher.group("cardName");
        Card card1 = card.getCardByName(cardName);
        boolean cardRExist = false;

        if (loggedInUser.getBattleDeck() != null) {
            for (Card card : loggedInUser.getBattleDeck()) {
                if (card.getName().equals(cardName)) {
                    cardRExist = true;
                    break;
                }
            }
        }

        if (!(cardName.equals("Fireball") || cardName.equals("Archer") || cardName.equals("Dragon") || cardName.equals("Wizard"))) {
            System.out.println("Invalid card name!");
        } else if (!cardRExist) {
            System.out.println("This card isn't in your battle deck!");
        } else if (loggedInUser.getBattleDeck().size() == 1 && loggedInUser.getBattleDeck().get(0).getName().equals(cardName)) {
            System.out.println("Invalid action: your battle deck will be empty!");
        } else {
            System.out.println("Card " + cardName + " removed successfully!");
            Iterator<Card> iterator = loggedInUser.getBattleDeck().iterator();
            while (iterator.hasNext()) {
                Card card2 = iterator.next();
                if (card2.getName().equals(cardName)) {
                    iterator.remove();
                    if (loggedInUser.getCards() == null) {
                        loggedInUser.setCards(new ArrayList<>());
                    }
                    loggedInUser.getCards().add(card2);

                    break;
                }
            }
        }
    }




    public void addToBattleDeck(Matcher matcher) {
        String cardName = matcher.group("cardName");
        Card card1= card.getCardByName(cardName);

        if(!(cardName.equals("Fireball") || cardName.equals("Archer") || cardName.equals("Dragon") || cardName.equals("Wizard"))){
            System.out.println("Invalid card name!");
        }else if(loggedInUser.getCardFromBattle(cardName)!=null){
            System.out.println("This card is already in your battle deck!");
        }
        else if(loggedInUser.getCard(cardName)==null){
            System.out.println("You don't have this card!");
        }

        else if(loggedInUser.getBattleDeck().size()==3){
            System.out.println("Invalid action: your battle deck is full!");
        }
        else{
            System.out.println("Card "+cardName+" added successfully!");
            loggedInUser.getBattleDeck().add(card1);
        }
    }

    public void showBattleDeck(Matcher matcher) {
        loggedInUser.sortBattleDeck();

        for (Card card : loggedInUser.getBattleDeck()) {
            System.out.println(card.getName());
        }
    }


    public void buyCard(Matcher matcher) {
        String cardName = matcher.group("cardName");
        Card card1 = card.getCardByName(cardName);

        if(!(cardName.equals("Fireball") || cardName.equals("Archer") || cardName.equals("Dragon") || cardName.equals("Wizard"))){
            System.out.println("Invalid card name!");
        }
        else if(cardName.equals("Fireball") && loggedInUser.getGold()<80){
            System.out.println("Not enough gold to buy Fireball!");
        }
        else if(cardName.equals("Archer") && loggedInUser.getGold()<80){
            System.out.println("Not enough gold to buy Archer!");
        }
        else if(cardName.equals("Dragon") && loggedInUser.getGold()<160){
            System.out.println("Not enough gold to buy Dragon!");
        }
        else if(cardName.equals("Wizard") && loggedInUser.getGold()<140){
            System.out.println("Not enough gold to buy Wizard!");
        }
        else if(loggedInUser.getCardFromBattle(cardName)!=null || loggedInUser.getCard(cardName)!=null){
            System.out.println("You have this card!");
        }
        else{
            System.out.println("Card "+cardName+" bought successfully!");
            loggedInUser.getCards().add(card1);
            card1.setOwner(loggedInUser);
            if(cardName.equals("Archer")){
                loggedInUser.setGold(loggedInUser.getGold()-80);
            }
            else if(cardName.equals("Dragon")){
                loggedInUser.setGold(loggedInUser.getGold()-160);
            }
            else if(cardName.equals("Wizard")){
                loggedInUser.setGold(loggedInUser.getGold()-140);
            }
            else {
                loggedInUser.setGold(loggedInUser.getGold()-80);
            }


        }


    }

    public void sellCard(Matcher matcher) {
        String cardName = matcher.group("cardName");
        Card card1 = card.getCardByName(cardName);
        if(!(cardName.equals("Fireball") || cardName.equals("Archer") || cardName.equals("Dragon") || cardName.equals("Wizard"))){
            System.out.println("Invalid card name!");
        }
        else if( loggedInUser.getCard(cardName)==null){
            System.out.println("You don't have this card!");
        }
        else if(loggedInUser.getCardFromBattle(cardName)!=null){
            System.out.println("You cannot sell a card from your battle deck!");
        }
        else{
            System.out.println("Card "+cardName+" sold successfully!");
            loggedInUser.getCards().remove(card1);
            card1.setOwner(null);
            if(cardName.equals("Archer")){
                loggedInUser.setGold(loggedInUser.getGold()+60);
            }
            else if(cardName.equals("Dragon")){
                loggedInUser.setGold(loggedInUser.getGold()+120);
            }
            else if(cardName.equals("Wizard")){
                loggedInUser.setGold(loggedInUser.getGold()+105);
            }
            else {
                loggedInUser.setGold(loggedInUser.getGold()+60);
            }
        }
    }

    public void showCastleHitpoint(Matcher matcher){
        int mid= turnUser.getOpponent().getCastle("MidCastle").getHitPoint();
        int right=turnUser.getOpponent().getCastle("RightCastle").getHitPoint();
        int left=turnUser.getOpponent().getCastle("LeftCastle").getHitPoint();

        if(turnUser.getOpponent().getCastle("LeftCastle").getHitPoint()<=0){
            left=-1;
        }
        if(turnUser.getOpponent().getCastle("MidCastle").getHitPoint()<=0){
            mid=-1;
        }
        if(turnUser.getOpponent().getCastle("RightCastle").getHitPoint()<=0){
            right=-1;
        }
        System.out.println("middle castle: " + mid);
        System.out.println("left castle: " + left);
        System.out.println("right castle: " + right);
    }

    public void showLineInfo(Matcher matcher){
        String line = matcher.group("lineDirection");
        List<String> print = new ArrayList<>();
        if(!(line.equals("left") || line.equals("right") || line.equals("middle"))){
            System.out.println("Incorrect line direction!");
        }
        else{
            System.out.println(line +" line:");


            if(line.equals("left")){
                for (int i = 0; i < 15; i++) {
                    if(gameGround2[i][0]!=null){
                        for (Card card1 : gameGround2[i][0]) {
                            print.add(card1.getName()+": "+card1.getOwner().getUserName());
                        }
                    }
                    if(!print.isEmpty())
                        System.out.println("row "+ (i+1)+": "+String.join("", print));
                    print.clear();
                }
            }
            else if(line.equals("right")){
                for (int i = 0; i < 15; i++) {
                    if(gameGround2[i][2]!=null){
                        for (Card card1 : gameGround2[i][2]) {
                            print.add(card1.getName()+": "+card1.getOwner().getUserName());
                        }
                    }
                    if(!print.isEmpty())
                        System.out.println("row "+ (i+1)+": "+String.join("", print));
                    print.clear();
                }
            }
            else{
                for (int i = 0; i < 15; i++) {
                    if(gameGround2[i][1]!=null){
                        for (Card card1 : gameGround2[i][1]) {
                            print.add(card1.getName()+": "+card1.getOwner().getUserName());
                        }
                    }
                    if(!print.isEmpty())
                        System.out.println("row "+ (i+1)+": "+String.join("", print));
                    print.clear();
                }
            }

        }
    }

    public void numberOfCardsToPlay(Matcher matcher){

        if(cardsToPlay==0){
            System.out.println("You can play "+0+" cards more!");
        }
        else{
            System.out.println("You can play "+1+" cards more!");
        }
    }

    public void numberOfMoves(Matcher matcher){

        System.out.println("You have "+moves+" moves left!");

    }


    public void moveTroop(Matcher matcher) {
        String lineDirection = matcher.group("lineDirection");
        String direction = matcher.group("direction");
        int row = Integer.parseInt(matcher.group("rowNumber"));

        // Validate inputs
        if (!(lineDirection.equals("left") || lineDirection.equals("right") || lineDirection.equals("middle"))) {
            System.out.println("Incorrect line direction!");
            return;
        }

        if (!(row <= 15 && row >= 1)) {
            System.out.println("Invalid row number!");
            return;
        }

        if (!(direction.equals("upward") || direction.equals("downward"))) {
            System.out.println("you can only move troops upward or downward!");
            return;
        }

        if (turnUser.moves == 0) {
            System.out.println("You are out of moves!");
            return;
        }

        int col = -1;
        switch (lineDirection) {
            case "left":
                col = 0;
                break;
            case "middle":
                col = 1;
                break;
            case "right":
                col = 2;
                break;
        }

        // Check if there are troops in the specified location and if they belong to the current user
        Card[] troopCards = gameGround2[row - 1][col];
        if (troopCards == null || troopCards.length == 0) {
            System.out.println("You don't have any troops in this place!");
            return;
        }

        Card cardToMove = null;
        int cardIndex = -1;
        for (int i = 0; i < troopCards.length; i++) {
            if (troopCards[i].getOwner().getUserName().equals(turnUser.getUserName())) {
                cardToMove = troopCards[i];
                cardIndex = i;
//                System.out.println(cardToMove.getName());
                break;
            }
        }

        if (cardToMove == null) {
            System.out.println("You don't have any troops in this place!");
            return;
        }

        // Determine the new row based on the direction
        int newRow = direction.equals("upward") ? row + 1 : row - 1;

        // Check for invalid moves at the boundaries
        if (newRow > 15 || newRow < 1) {
            System.out.println("Invalid move!");
            return;
        }

        // Move the card
        if (gameGround2[newRow - 1][col] == null) {
            gameGround2[newRow - 1][col] = new Card[0];
//            System.out.println("fuck");


        }
        gameGround2[newRow - 1][col] = addCardToArray(gameGround2[newRow - 1][col], cardToMove);
        gameGround2[row - 1][col] = removeCardFromArray(troopCards, cardIndex);
        System.out.println(cardToMove.getName() + " moved successfully to row " + newRow + " in line " + lineDirection);
        turnUser.moves -= 1;

    }


    private Card[] addCardToArray(Card[] array, Card card) {
        Card[] newArray = new Card[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = card;
        return newArray;
    }


    private Card[] removeCardFromArray(Card[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        Card[] newArray = new Card[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }




    public void deployTroop(Matcher matcher) {
        String lineDirection = matcher.group("lineDirection");
        int row = Integer.parseInt(matcher.group("rowNumber"));
        String troopName = matcher.group("troopName");
        Card card1 = card.getCardByName(troopName);
        boolean exist = false;

        // Check if the card exists in the user's battle deck
        for (Card card2 : turnUser.getBattleDeck()) {
            if (card1.getName().equals(card2.getName())) {
                exist = true;
                break;
            }
        }

        // Validate inputs
        if (!(troopName.equals("Dragon") || troopName.equals("Archer") || troopName.equals("Wizard"))) {
            System.out.println("Invalid troop name!");
        } else if (!exist) {
            System.out.println("You don't have " + troopName + " card in your battle deck!");
        } else if (!(lineDirection.equals("left") || lineDirection.equals("right") || lineDirection.equals("middle"))) {
            System.out.println("Incorrect line direction!");
        } else if (!(row <= 15 && row >= 1)) {
            System.out.println("Invalid row number!");
        } else if (turnUser.equals(loggedInUser) && row > 4) {
            System.out.println("Deploy your troops near your castles!");
        } else if (turnUser.equals(loggedInUser.getOpponent()) && row < 12) {
            System.out.println("Deploy your troops near your castles!");
        } else if (turnUser.useTroop) {
            System.out.println("You have deployed a troop or spell this turn!");
        } else {
            turnUser.useTroop = true;
            card1.setOwner(turnUser);

            int col = -1;
            switch (lineDirection) {
                case "left":
                    col = 0;
                    break;
                case "middle":
                    col = 1;
                    break;
                case "right":
                    col = 2;
                    break;
            }

            if (gameGround2[row - 1][col] == null) {
                gameGround2[row - 1][col] = new Card[0];
            }
            gameGround2[row - 1][col] = addCardToArray(gameGround2[row - 1][col], card1);

            System.out.println("You have deployed " + troopName + " successfully!");
        }
    }



    public void deploySpell(Matcher matcher){
        String lineDirection = matcher.group("lineDirection");
        String castleName;
        boolean exist=false;
        int destroyedCastles=0;
        int destroyedOpponents=0;
        int totalHitPoint=0;
        int totalHitPointOpponent=0;

        for (Card card1 : turnUser.getBattleDeck()) {
            if(card1.getName().equals("Fireball")){
                exist=true;
                break;
            }
        }
        if(!(lineDirection.equals("left") || lineDirection.equals("right") || lineDirection.equals("middle"))){
            System.out.println("Incorrect line direction!");
        }
        if(lineDirection.equals("left")){
            castleName = "LeftCastle";
        }
        else if(lineDirection.equals("right")){
            castleName = "RightCastle";
        }
        else{
            castleName = "MiddleCastle";
        }
        Castle castle=turnUser.getOpponent().getCastle(castleName);
        if(!exist){
            System.out.println("You don't have Fireball card in your battle deck!");
        }
        else if(turnUser.useTroop){
            System.out.println("You have deployed a troop or spell this turn!");
        }
        else if(castle.getHitPoint()<=0){
            System.out.println("This castle is already destroyed!");
        }
        else{
            System.out.println("You have deployed Fireball successfully!");
            turnUser.useTroop=true;
            if(turnUser.equals(loggedInUser)){
                loggedInUser.getOpponent().getCastle(castleName).setHitPoint(loggedInUser.getOpponent().getCastle(castleName).getHitPoint()-1400);

            }
            else{
                loggedInUser.getCastle(castleName).setHitPoint(loggedInUser.getCastle(castleName).getHitPoint()-1400);
            }
            for (Castle castle1 : loggedInUser.getCastles()) {
                if(castle1.getHitPoint()<=0) {
                    destroyedCastles++;
                }
            }
            for (Castle castle2 : loggedInUser.getOpponent().getCastles()) {
                if(castle2.getHitPoint()<=0) {
                    destroyedOpponents++;
                }
            }
            if(turns>0 && (destroyedOpponents==3 || destroyedCastles==3)){

                if(destroyedOpponents==3){
                    for (Castle castle3 : loggedInUser.getCastles()) {
                        if(castle3.getHitPoint()>=0){
                            totalHitPoint+=castle3.getHitPoint();
                        }
                    }
                    for (Castle castle4 : loggedInUser.getOpponent().getCastles()) {
                        if(castle4.getHitPoint()>=0) {
                            totalHitPointOpponent += castle4.getHitPoint();
                        }
                    }
                    loggedInUser.setExperience(loggedInUser.getExperience()+totalHitPoint);
                    loggedInUser.getOpponent().setExperience(loggedInUser.getOpponent().getExperience()+totalHitPointOpponent);
                    int loggedInUserExperience = loggedInUser.getExperience();
                    int opponentExperience = loggedInUser.getOpponent().getExperience();
                    int loggedInUserLevel= loggedInUser.getLevel();
                    int opponentLevel= loggedInUser.getOpponent().getLevel();
                    int needToUpgrade= loggedInUserLevel*loggedInUserLevel*150;
                    int needToUpgradeOpponent= opponentLevel*opponentLevel*150;
                    while(needToUpgrade <= loggedInUserExperience || needToUpgradeOpponent <= opponentExperience){
                        if (needToUpgrade <= loggedInUserExperience) {
                            loggedInUserLevel += 1;
                            loggedInUser.setLevel(loggedInUserLevel);
                            loggedInUser.setExperience(loggedInUserExperience - needToUpgrade);
                            loggedInUserExperience = loggedInUserExperience - needToUpgrade;
                            needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
                        }
                        if (needToUpgradeOpponent <= opponentExperience) {
                            opponentLevel += 1;
                            loggedInUser.getOpponent().setLevel(opponentLevel);
                            loggedInUser.getOpponent().setExperience(opponentExperience - needToUpgradeOpponent);
                            opponentExperience = opponentExperience - needToUpgradeOpponent;
                            needToUpgradeOpponent = opponentLevel * opponentLevel * 150;
                        }
                    }
                    loggedInUser.setGold(loggedInUser.getGold()+destroyedOpponents*20);
                    loggedInUser.getOpponent().setGold(loggedInUser.getOpponent().getGold()+destroyedCastles*20);
                    System.out.println("Game has ended. Winner: "+ loggedInUser.getOpponent().getUserName());
                    loggedInUser.setCurrentMenu("Main Menu");
                }
                else {
                    for (Castle castle1 : loggedInUser.getCastles()) {
                        if(castle1.getHitPoint()>=0){
                            totalHitPoint+=castle1.getHitPoint();
                        }
                    }
                    for (Castle castle2 : loggedInUser.getOpponent().getCastles()) {
                        if(castle2.getHitPoint()>=0) {
                            totalHitPointOpponent += castle2.getHitPoint();
                        }
                    }
                    loggedInUser.setExperience(loggedInUser.getExperience()+totalHitPoint);
                    loggedInUser.getOpponent().setExperience(loggedInUser.getOpponent().getExperience()+totalHitPointOpponent);
                    int loggedInUserExperience = loggedInUser.getExperience();
                    int opponentExperience = loggedInUser.getOpponent().getExperience();
                    int loggedInUserLevel= loggedInUser.getLevel();
                    int opponentLevel= loggedInUser.getOpponent().getLevel();
                    int needToUpgrade= loggedInUserLevel*loggedInUserLevel*150;
                    int needToUpgradeOpponent= opponentLevel*opponentLevel*150;
                    while(needToUpgrade <= loggedInUserExperience || needToUpgradeOpponent <= opponentExperience){
                        if (needToUpgrade <= loggedInUserExperience) {
                            loggedInUserLevel += 1;
                            loggedInUser.setLevel(loggedInUserLevel);
                            loggedInUser.setExperience(loggedInUserExperience - needToUpgrade);
                            loggedInUserExperience = loggedInUserExperience - needToUpgrade;
                            needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
                        }
                        if (needToUpgradeOpponent <= opponentExperience) {
                            opponentLevel += 1;
                            loggedInUser.getOpponent().setLevel(opponentLevel);
                            loggedInUser.getOpponent().setExperience(opponentExperience - needToUpgradeOpponent);
                            opponentExperience = opponentExperience - needToUpgradeOpponent;
                            needToUpgradeOpponent = opponentLevel * opponentLevel * 150;
                        }
                    }

                    loggedInUser.setGold(loggedInUser.getGold()+destroyedOpponents*20);
                    loggedInUser.getOpponent().setGold(loggedInUser.getOpponent().getGold()+destroyedCastles*20);
                    System.out.println("Game has ended. Winner: "+ loggedInUser.getUserName());
                    loggedInUser.setCurrentMenu("Main Menu");
                }
            }

        }
    }


    public void nextTurn(){
        int totalHitPoint=0;
        int totalHitPointOpponent=0;
        int destroyedCastles=0;
        int destroyedOpponents=0;
        if(turnUser.equals(loggedInUser)){
            turnUser= new User();
            loggedInUser.moves=3;
            turnUser = loggedInUser.getOpponent();
            System.out.println("Player "+loggedInUser.getOpponent().getUserName()+" is now playing!");
        }
        else if(turnUser.equals(loggedInUser.getOpponent())){
            turnUser = new User();
            loggedInUser.moves=3;
            loggedInUser.getOpponent().moves=3;
            turns-=1;
            turnUser = loggedInUser;
            if(turns>0)
                System.out.println("Player "+loggedInUser.getUserName()+" is now playing!");
            loggedInUser.useTroop=false;
            loggedInUser.getOpponent().useTroop=false;

            if(gameGround2[0][0]!=null && loggedInUser.getCastle("LeftCastle").getHitPoint()>0) {
                for (Card card1 : gameGround2[0][0]) {
                    if (card1.getOwner().equals(loggedInUser.getOpponent())) {
                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getCastle("LeftCastle").setHitPoint(loggedInUser.getCastle("LeftCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getCastle("LeftCastle").setHitPoint(loggedInUser.getCastle("LeftCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getCastle("LeftCastle").setHitPoint(loggedInUser.getCastle("LeftCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }

            if(gameGround2[0][1]!=null && loggedInUser.getCastle("MidCastle").getHitPoint()>0) {
                for (Card card1 : gameGround2[0][1]) {
                    if (card1.getOwner().equals(loggedInUser.getOpponent())) {
                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getCastle("MidCastle").setHitPoint(loggedInUser.getCastle("MidCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getCastle("MidCastle").setHitPoint(loggedInUser.getCastle("MidCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getCastle("MidCastle").setHitPoint(loggedInUser.getCastle("MidCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }

            if(gameGround2[0][2]!=null && loggedInUser.getCastle("RightCastle").getHitPoint()>0) {
                for (Card card1 : gameGround2[0][2]) {
                    if (card1.getOwner().equals(loggedInUser.getOpponent())) {
                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getCastle("RightCastle").setHitPoint(loggedInUser.getCastle("RightCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getCastle("RightCastle").setHitPoint(loggedInUser.getCastle("RightCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getCastle("RightCastle").setHitPoint(loggedInUser.getCastle("RightCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }

            if(gameGround2[14][0]!=null && loggedInUser.getOpponent().getCastle("LeftCastle").getHitPoint()>0) {

                for (Card card1 : gameGround2[14][0]) {
                    if (card1.getOwner().equals(loggedInUser)) {

                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getOpponent().getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getOpponent().getCastle("LeftCastle").setHitPoint(loggedInUser.getOpponent().getCastle("LeftCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getOpponent().getCastle("LeftCastle").setHitPoint(loggedInUser.getOpponent().getCastle("LeftCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getOpponent().getCastle("LeftCastle").setHitPoint(loggedInUser.getOpponent().getCastle("LeftCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }

            if(gameGround2[14][1]!=null && loggedInUser.getOpponent().getCastle("MidCastle").getHitPoint()>0) {

                for (Card card1 : gameGround2[14][1]) {
                    if (card1.getOwner().equals(loggedInUser)) {
                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getOpponent().getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getOpponent().getCastle("MidCastle").setHitPoint(loggedInUser.getOpponent().getCastle("MidCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getOpponent().getCastle("MidCastle").setHitPoint(loggedInUser.getOpponent().getCastle("MidCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getOpponent().getCastle("MidCastle").setHitPoint(loggedInUser.getOpponent().getCastle("MidCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }


            if(gameGround2[14][2]!=null && loggedInUser.getOpponent().getCastle("RightCastle").getHitPoint()>0) {
                for (Card card1 : gameGround2[14][2]) {
                    if (card1.getOwner().equals(loggedInUser)) {
                        card1.setHitPoint(card1.getHitPoint() - loggedInUser.getOpponent().getLevel() * 450);
                        if (card1.getName().equals("Archer")) {
                            loggedInUser.getOpponent().getCastle("RightCastle").setHitPoint(loggedInUser.getOpponent().getCastle("RightCastle").getHitPoint() - 800);
                        } else if (card1.getName().equals("Wizard")) {
                            loggedInUser.getOpponent().getCastle("RightCastle").setHitPoint(loggedInUser.getOpponent().getCastle("RightCastle").getHitPoint() - 1400);
                        } else if (card1.getName().equals("Dragon")) {
                            loggedInUser.getOpponent().getCastle("RightCastle").setHitPoint(loggedInUser.getOpponent().getCastle("RightCastle").getHitPoint() - 1100);
                        }
                    }
                }
            }


            for (int row = 0; row < gameGround2.length; row++) {
                for (int col = 0; col < gameGround2[row].length; col++) {
                    Card[] cardArray = gameGround2[row][col];
                    if (cardArray != null) {
                        for (int i = 0; i < cardArray.length; i++) {
                            Card card1 = cardArray[i];

                            for (int j = i + 1; j < cardArray.length; j++) {
                                Card card2 = cardArray[j];
                                if (!card1.getOwner().equals(card2.getOwner())) {
                                    if (card1.getName().equals("Archer")) {
                                        if (card2.getName().equals("Dragon")) {
                                            card1.setHitPoint(card1.getHitPoint() - 300);
                                        } else if (card2.getName().equals("Wizard")) {
                                            card1.setHitPoint(card1.getHitPoint() - 600);
                                        }
                                    } else if (card1.getName().equals("Dragon")) {
                                        if (card2.getName().equals("Archer")) {
                                            card2.setHitPoint(card2.getHitPoint() - 300);
                                        } else if (card2.getName().equals("Wizard")) {
                                            card1.setHitPoint(card1.getHitPoint() - 300);
                                        }
                                    } else if (card1.getName().equals("Wizard")) {
                                        if (card2.getName().equals("Archer")) {
                                            card2.setHitPoint(card2.getHitPoint() - 600);
                                        } else if (card2.getName().equals("Dragon")) {
                                            card2.setHitPoint(card2.getHitPoint() - 300);
                                        }
                                    }
                                }
                                if (card1.getHitPoint() <= 0) {
                                    cardArray = removeCardFromArray(cardArray, i);
                                    i--;
                                    break;
                                }
                                if (card2.getHitPoint() <= 0) {
                                    cardArray = removeCardFromArray(cardArray, j);
                                    j--;
                                }
                            }
                        }
                        gameGround2[row][col] = cardArray;
                    }
                }
            }
        }


        for (Castle castle : loggedInUser.getCastles()) {
            if(castle.getHitPoint()<=0) {
                destroyedCastles++;
            }
        }
        for (Castle castle : loggedInUser.getOpponent().getCastles()) {
            if(castle.getHitPoint()<=0) {
                destroyedOpponents++;
            }
        }
        if(turns>0 && (destroyedOpponents==3 || destroyedCastles==3)){

            if(destroyedOpponents==3){
                for (Castle castle : loggedInUser.getCastles()) {
                    if(castle.getHitPoint()>=0){
                        totalHitPoint+=castle.getHitPoint();
                    }
                }
                for (Castle castle : loggedInUser.getOpponent().getCastles()) {
                    if(castle.getHitPoint()>=0) {
                        totalHitPointOpponent += castle.getHitPoint();
                    }
                }
                loggedInUser.setExperience(loggedInUser.getExperience()+totalHitPoint);
                loggedInUser.getOpponent().setExperience(loggedInUser.getOpponent().getExperience()+totalHitPointOpponent);
                int loggedInUserExperience = loggedInUser.getExperience();
                int opponentExperience = loggedInUser.getOpponent().getExperience();
                int loggedInUserLevel= loggedInUser.getLevel();
                int opponentLevel= loggedInUser.getOpponent().getLevel();
                int needToUpgrade= loggedInUserLevel*loggedInUserLevel*150;
                int needToUpgradeOpponent= opponentLevel*opponentLevel*150;
                while(needToUpgrade <= loggedInUserExperience || needToUpgradeOpponent <= opponentExperience){
                    if (needToUpgrade <= loggedInUserExperience) {
                        loggedInUserLevel += 1;
                        loggedInUser.setLevel(loggedInUserLevel);
                        loggedInUser.setExperience(loggedInUserExperience - needToUpgrade);
                        loggedInUserExperience = loggedInUserExperience - needToUpgrade;
                        needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
                    }
                    if (needToUpgradeOpponent <= opponentExperience) {
                        opponentLevel += 1;
                        loggedInUser.getOpponent().setLevel(opponentLevel);
                        loggedInUser.getOpponent().setExperience(opponentExperience - needToUpgradeOpponent);
                        opponentExperience = opponentExperience - needToUpgradeOpponent;
                        needToUpgradeOpponent = opponentLevel * opponentLevel * 150;
                    }
                }
                loggedInUser.setGold(loggedInUser.getGold()+destroyedOpponents*20);
                loggedInUser.getOpponent().setGold(loggedInUser.getOpponent().getGold()+destroyedCastles*20);
                System.out.println("Game has ended. Winner: "+ loggedInUser.getOpponent().getUserName());
                loggedInUser.setCurrentMenu("Main Menu");
            }
            else {
                for (Castle castle : loggedInUser.getCastles()) {
                    if(castle.getHitPoint()>=0){
                        totalHitPoint+=castle.getHitPoint();
                    }
                }
                for (Castle castle : loggedInUser.getOpponent().getCastles()) {
                    if(castle.getHitPoint()>=0) {
                        totalHitPointOpponent += castle.getHitPoint();
                    }
                }
                loggedInUser.setExperience(loggedInUser.getExperience()+totalHitPoint);
                loggedInUser.getOpponent().setExperience(loggedInUser.getOpponent().getExperience()+totalHitPointOpponent);
                int loggedInUserExperience = loggedInUser.getExperience();
                int opponentExperience = loggedInUser.getOpponent().getExperience();
                int loggedInUserLevel= loggedInUser.getLevel();
                int opponentLevel= loggedInUser.getOpponent().getLevel();
                int needToUpgrade= loggedInUserLevel*loggedInUserLevel*150;
                int needToUpgradeOpponent= opponentLevel*opponentLevel*150;
                while(needToUpgrade <= loggedInUserExperience || needToUpgradeOpponent <= opponentExperience){
                    if (needToUpgrade <= loggedInUserExperience) {
                        loggedInUserLevel += 1;
                        loggedInUser.setLevel(loggedInUserLevel);
                        loggedInUser.setExperience(loggedInUserExperience - needToUpgrade);
                        loggedInUserExperience = loggedInUserExperience - needToUpgrade;
                        needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
                    }
                    if (needToUpgradeOpponent <= opponentExperience) {
                        opponentLevel += 1;
                        loggedInUser.getOpponent().setLevel(opponentLevel);
                        loggedInUser.getOpponent().setExperience(opponentExperience - needToUpgradeOpponent);
                        opponentExperience = opponentExperience - needToUpgradeOpponent;
                        needToUpgradeOpponent = opponentLevel * opponentLevel * 150;
                    }
                }

                loggedInUser.setGold(loggedInUser.getGold()+destroyedOpponents*20);
                loggedInUser.getOpponent().setGold(loggedInUser.getOpponent().getGold()+destroyedCastles*20);
                System.out.println("Game has ended. Winner: "+ loggedInUser.getUserName());
                loggedInUser.setCurrentMenu("Main Menu");
            }
        }
        if(turns==0) {


            for (Castle castle : loggedInUser.getCastles()) {
                if (castle.getHitPoint() >= 0) {
                    totalHitPoint += castle.getHitPoint();
                }
            }
            for (Castle castle : loggedInUser.getOpponent().getCastles()) {
                if (castle.getHitPoint() >= 0) {
                    totalHitPointOpponent += castle.getHitPoint();
                }
            }
            loggedInUser.setExperience(loggedInUser.getExperience() + totalHitPoint);
            loggedInUser.getOpponent().setExperience(loggedInUser.getOpponent().getExperience() + totalHitPointOpponent);
            int loggedInUserExperience = loggedInUser.getExperience();
            int opponentExperience = loggedInUser.getOpponent().getExperience();
            int loggedInUserLevel = loggedInUser.getLevel();
            int opponentLevel = loggedInUser.getOpponent().getLevel();
            int needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
            int needToUpgradeOpponent = opponentLevel * opponentLevel * 150;

            while(needToUpgrade <= loggedInUserExperience || needToUpgradeOpponent <= opponentExperience){
                if (needToUpgrade <= loggedInUserExperience) {
                    loggedInUserLevel += 1;
                    loggedInUser.setLevel(loggedInUserLevel);
                    loggedInUser.setExperience(loggedInUserExperience - needToUpgrade);
                    loggedInUserExperience = loggedInUserExperience - needToUpgrade;
                    needToUpgrade = loggedInUserLevel * loggedInUserLevel * 150;
                }
                if (needToUpgradeOpponent <= opponentExperience) {
                    opponentLevel += 1;
                    loggedInUser.getOpponent().setLevel(opponentLevel);
                    loggedInUser.getOpponent().setExperience(opponentExperience - needToUpgradeOpponent);
                    opponentExperience = opponentExperience - needToUpgradeOpponent;
                    needToUpgradeOpponent = opponentLevel * opponentLevel * 150;
                }
            }

            loggedInUser.setGold(loggedInUser.getGold()+destroyedOpponents*20);
            loggedInUser.getOpponent().setGold(loggedInUser.getOpponent().getGold()+destroyedCastles*20);
            if(totalHitPoint==totalHitPointOpponent ){
                loggedInUser.setCurrentMenu("Main Menu");
                System.out.println("Game has ended. Result: Tie");
            }
            else if(totalHitPoint>totalHitPointOpponent){
                loggedInUser.setCurrentMenu("Main Menu");
                System.out.println("Game has ended. Winner: "+ loggedInUser.getUserName());
            }
            else {
                loggedInUser.setCurrentMenu("Main Menu");
                System.out.println("Game has ended. Winner: "+ loggedInUser.getOpponent().getUserName());
            }
        }
    }

//    public void fight(){
//
//        for (Card[][] cards : gameGround2) {
//            for (Card[] card1 : cards) {
//
//            }
//        }
//    }


    public void run() {
        String command;

        do {
            command = scanner.nextLine();

            if (command.equals("Exit")) {
                break;
            }
            if(loggedInUser==null){
                if (command.matches("register username (?<username>.*) password (?<password>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "register username (?<username>.*) password (?<password>.*)");
                    matcher.find();
                    signIn(matcher);
                    continue;
                }

                else if (command.matches("login username (?<username>.*) password (?<password>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "login username (?<username>.*) password (?<password>.*)");
                    matcher.find();
                    logIn(matcher);
                    continue;
                }
                else if (command.matches("show current menu")) {
                    Matcher matcher = getCommandMatcher(command, "show current menu");
                    matcher.find();
                    showCurrentMenu(matcher);
                }
                else {
                    System.out.println("Invalid command!");
                }
            }


            else if(loggedInUser.getCurrentMenu().equals("Main Menu")){
                if (command.matches("list of users")) {
                    Matcher matcher = getCommandMatcher(command, "list of users");
                    matcher.find();
                    listOfUsers(matcher);
                    continue;
                }
                else if (command.matches("scoreboard")) {
                    Matcher matcher = getCommandMatcher(command, "scoreboard");
                    matcher.find();
                    printScoreBoard();
                }

                else if (command.matches("logout")) {
                    Matcher matcher = getCommandMatcher(command, "logout");
                    matcher.find();
                    logOut(matcher);
                    continue;
                }
                else if (command.matches("profile menu")) {
                    Matcher matcher = getCommandMatcher(command, "profile menu");
                    matcher.find();
                    profileMenu(matcher);
                    continue;
                }
                else if (command.matches("shop menu")) {
                    Matcher matcher = getCommandMatcher(command, "shop menu");
                    matcher.find();
                    shopMenu(matcher);
                    continue;
                }
                else if (command.matches("start game turns count (?<turnsCount>\\d+) username (?<username>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "start game turns count (?<turnsCount>\\d+) username (?<username>.*)");
                    matcher.find();
                    startGame(matcher);
                    continue;
                }
                else if (command.matches("show current menu")) {
                    Matcher matcher = getCommandMatcher(command, "show current menu");
                    matcher.find();
                    showCurrentMenu(matcher);
                }
                else {
                    System.out.println("Invalid command!");
                }
            }



            else if(loggedInUser.getCurrentMenu().equals("profile menu")){
                if (command.matches("back")) {
                    Matcher matcher = getCommandMatcher(command, "back");
                    matcher.find();
                    backToMainMenu(matcher);
                    continue;
                }
                else if (command.matches("change password old password (?<oldPassword>.*) new password (?<newPassword>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "change password old password (?<oldPassword>.*) new password (?<newPassword>.*)");
                    matcher.find();
                    changePassword(matcher);
                    continue;
                }
                else if (command.matches("Info")) {
                    Matcher matcher = getCommandMatcher(command, "Info");
                    matcher.find();
                    info(matcher);
                    continue;
                }
                else if (command.matches("remove from battle deck (?<cardName>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "remove from battle deck (?<cardName>.*)");
                    matcher.find();
                    removeFromBattleDeck(matcher);
                }
                else if (command.matches("add to battle deck (?<cardName>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "add to battle deck (?<cardName>.*)");
                    matcher.find();
                    addToBattleDeck(matcher);
                }
                else if (command.matches("show battle deck")) {
                    Matcher matcher = getCommandMatcher(command, "show battle deck");
                    matcher.find();
                    showBattleDeck(matcher);
                }
                else if (command.matches("show current menu")) {
                    Matcher matcher = getCommandMatcher(command, "show current menu");
                    matcher.find();
                    showCurrentMenu(matcher);
                }
                else {
                    System.out.println("Invalid command!");
                }

            }

            else if(loggedInUser.getCurrentMenu().equals("shop menu")){
                if (command.matches("back")) {
                    Matcher matcher = getCommandMatcher(command, "back");
                    matcher.find();
                    backToMainMenu(matcher);
                    continue;
                }
                else if (command.matches("buy card (?<cardName>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "buy card (?<cardName>.*)");
                    matcher.find();
                    buyCard(matcher);
                }
                else if (command.matches("sell card (?<cardName>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "sell card (?<cardName>.*)");
                    matcher.find();
                    sellCard(matcher);
                }
                else if (command.matches("show current menu")) {
                    Matcher matcher = getCommandMatcher(command, "show current menu");
                    matcher.find();
                    showCurrentMenu(matcher);
                }
                else {
                    System.out.println("Invalid command!");
                }
            }

            else if (loggedInUser.getCurrentMenu().equals("Game Menu")){
                if (command.matches("show the hitpoints left of my opponent")) {
                    Matcher matcher = getCommandMatcher(command, "show the hitpoints left of my opponent");
                    matcher.find();
                    showCastleHitpoint(matcher);
                }
                else if (command.matches("show line info (?<lineDirection>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "show line info (?<lineDirection>.*)");
                    matcher.find();
                    showLineInfo(matcher);
                }
                else if (command.matches("number of cards to play")) {
                    Matcher matcher = getCommandMatcher(command, "number of cards to play");
                    matcher.find();
                    numberOfCardsToPlay(matcher);
                }
                else if (command.matches("number of moves left")) {
                    Matcher matcher = getCommandMatcher(command, "number of moves left");
                    matcher.find();
                    numberOfMoves(matcher);
                }
                else if (command.matches("move troop in line (?<lineDirection>.*) and row (?<rowNumber>.*) (?<direction>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "move troop in line (?<lineDirection>.*) and row (?<rowNumber>.*) (?<direction>.*)");
                    matcher.find();
                    moveTroop(matcher);
                }
                else if (command.matches("deploy troop (?<troopName>.*) in line (?<lineDirection>.*) and row (?<rowNumber>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "deploy troop (?<troopName>.*) in line (?<lineDirection>.*) and row (?<rowNumber>.*)");
                    matcher.find();
                    deployTroop(matcher);
                }
                else if (command.matches("deploy spell Fireball in line (?<lineDirection>.*)")) {
                    Matcher matcher = getCommandMatcher(command, "deploy spell Fireball in line (?<lineDirection>.*)");
                    matcher.find();
                    deploySpell(matcher);
                }
                else if (command.matches("next turn")) {
                    Matcher matcher = getCommandMatcher(command, "next turn");
                    matcher.find();
                    nextTurn();
                }
                else if (command.matches("show current menu")) {
                    Matcher matcher = getCommandMatcher(command, "show current menu");
                    matcher.find();
                    showCurrentMenu(matcher);
                }
                else {
                    System.out.println("Invalid command!");
                }

            }

            else if (command.matches("show current menu")) {
                Matcher matcher = getCommandMatcher(command, "show current menu");
                matcher.find();
                showCurrentMenu(matcher);
            }
            else if(command.matches("exit")){
                System.out.println("Invalid command!");
            }

            else {
                System.out.println("Invalid command!");
            }

        } while (true);

    }
}

