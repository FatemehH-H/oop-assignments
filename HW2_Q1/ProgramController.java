import java.lang.reflect.Member;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {

    private Scanner scanner = new Scanner(System.in);
    private Library library = new Library();
    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    private void addAccount(Matcher matcher) {
        int tempID;
        tempID = Integer.valueOf(matcher.group("ID"));

       // if(library !=null) {
            if (library.getMemberById(tempID) != null) {
                System.out.println("A member with this ID already exists");
                return;
            }
       // }
            library.addMember(new Mem(matcher.group("Name"), Integer.valueOf(matcher.group("ID"))));
            System.out.println("Account created successfully");

    }

    private void increaseBalance(Matcher matcher){
        int tempID;
        int balanceAmount;
        tempID = Integer.valueOf(matcher.group("ID"));
        balanceAmount = Integer.valueOf(matcher.group("Amount"));

      //  if (library != null) {


            if (library.getMemberById(tempID) == null) {
                System.out.println("No member with this ID exists");
                return;
            } else {
                int balance=library.getMemberById(tempID).getBalance();
                library.getMemberById(tempID).setBalance(balanceAmount+balance);

                System.out.println("Balance increased successfully");
            }
      //  }

    }

    private void cashout(Matcher matcher){
        int tempID;
        tempID = Integer.valueOf(matcher.group("ID"));
        if(library.getMemberById(tempID)==null){
            System.out.println("No member with this ID exists");
        }
        else{
            int lastAmount=library.getMemberById(tempID).getBalance();
            library.getMemberById(tempID).setBalance(0);
            String accountName= library.getMemberById(tempID).getName();
            System.out.println(accountName + " cashed out successfully. Amount: " + lastAmount);
        }

    }

    private void addBook(Matcher matcher){
        int amountBook;


            amountBook = Integer.valueOf(matcher.group("AmountBook"));
            if(amountBook!=0) {


                for (int i = 0; i < amountBook; i++) {
                    library.addBook(new Book(matcher.group("BookName"), Integer.valueOf(matcher.group("ISBN")), matcher.group("Author"), matcher.group("Language"), Integer.valueOf(matcher.group("Price"))));
                }

            }
        System.out.println(amountBook + " books were added to the library successfully");
    }

    private void addMagazine(Matcher matcher){
        int amountMagazine;

            amountMagazine = Integer.valueOf(matcher.group("AmountMagazine"));
            if(amountMagazine!=0) {
                for (int i = 0; i < amountMagazine; i++) {
                    library.addMagazine(new Magazine(matcher.group("MagazineName"), Integer.valueOf(matcher.group("ISSN")), matcher.group("Author"), matcher.group("Language"), Integer.valueOf(matcher.group("Price")), Integer.valueOf(matcher.group("NumberMagazine"))));
                }

            }
        System.out.println(amountMagazine + " magazines were added to the library successfully");
    }

    private void borrowBook(Matcher matcher){
        int tempID;
        int ISBN;
        tempID = Integer.valueOf(matcher.group("ID"));
        ISBN = Integer.valueOf(matcher.group("ISBN"));


        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }
        else if(library.getBook(ISBN) == null){
            System.out.println("No book with this ISBN was found in the library");
        }
        else if(library.getBook(ISBN).getPrice()> library.getMemberById(tempID).getBalance()){
            System.out.println("The member's balance is not enough");
        }
        else{
            String accountName= library.getMemberById(tempID).getName();
            String bookName= library.getBook(ISBN).getName();
            int lastBalance= library.getMemberById(tempID).getBalance();
            int bookPrice= library.getBook(ISBN).getPrice();
            library.getMemberById(tempID).setBalance(lastBalance-bookPrice);
            library.getMemberById(tempID).borrowBook(library.getBook(ISBN));
            library.removeBook(library.getBook(ISBN));
            System.out.println(bookName + " was borrowed by " + accountName + " successfully");

        }

    }

    private void borrowMagazine(Matcher matcher){
        int tempID;
        int ISSN;
        int numberMagazine;
        tempID = Integer.valueOf(matcher.group("ID"));
        ISSN = Integer.valueOf(matcher.group("ISSN"));
        numberMagazine = Integer.valueOf(matcher.group("NumberMagazine"));
        Magazine magazine=library.getMagazine(ISSN,numberMagazine);
        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }
        else if(magazine == null){
            System.out.println("No magazine with this ISSN and number was found in the library");
        }
        else if(library.getMagazine(ISSN,numberMagazine).getPrice()> library.getMemberById(tempID).getBalance()){
            System.out.println("The member's balance is not enough");
        }
        else{

            String accountName= library.getMemberById(tempID).getName();
            String magazineName= magazine.getName();
            int lastBalance= library.getMemberById(tempID).getBalance();
            int magazinePrice= magazine.getPrice();
            library.getMemberById(tempID).setBalance(lastBalance-magazinePrice);
            library.getMemberById(tempID).borrowMagazine(magazine);
            library.removeMagazine(magazine);

            System.out.println(magazineName + " was borrowed by " +accountName + " successfully");

        }

    }

    private void returnBook(Matcher matcher){
        int tempID;
        int ISBN;
        tempID = Integer.valueOf(matcher.group("ID"));
        ISBN = Integer.valueOf(matcher.group("ISBN"));

        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }
//        else if(library.getMemberById(tempID).getBookFromBorrowedBooks(ISBN)==null){
//            System.out.println("This member has never borrowed this book or has returned it");
//        }
//        else{
//            String accountName= library.getMemberById(tempID).getName();
//            String bookName= library.getBook(ISBN).getName();
//            library.addBook(library.getBook(ISBN));
//            int lastBalance= library.getMemberById(tempID).getBalance();
//            int bookPrice= library.getBook(ISBN).getPrice();
//            library.getMemberById(tempID).returnBook(library.getBook(ISBN));
//            library.getMemberById(tempID).setBalance(lastBalance + ((9*bookPrice)/10));
//            System.out.println(accountName + " returned " + bookName+" successfully");
//
//        }
        else {
            Book book = library.getMemberById(tempID).getBookFromBorrowedBooks(ISBN);
            if (book == null ) {
                System.out.println("This member has never borrowed this book or has returned it");
            } else {
                String accountName = library.getMemberById(tempID).getName();
              //  String bookName = library.getBook(ISBN).getName();
                String bookName=book.getName();
             //   library.addBook(library.getBook(ISBN));
                library.addBook(book);
                int lastBalance = library.getMemberById(tempID).getBalance();
               // int bookPrice = library.getBook(ISBN).getPrice();
                int bookPrice=book.getPrice();
                library.getMemberById(tempID).returnBook(book);
                library.getMemberById(tempID).setBalance(lastBalance + ((9 * bookPrice) / 10));
                System.out.println(accountName + " returned " + bookName + " successfully");
            }
        }


    }

    private void returnMagazine(Matcher matcher){
        int tempID;
        int ISSN;
        int numberMagazine;
        tempID = Integer.valueOf(matcher.group("ID"));
        ISSN = Integer.valueOf(matcher.group("ISSN"));
        numberMagazine = Integer.valueOf(matcher.group("NumberMagazine"));

        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }

        else {
            Magazine magazine = library.getMemberById(tempID).getMagazineFromBorrowedMagazines(ISSN,numberMagazine);

            if (magazine == null || magazine.getName()==null ) {
                System.out.println("This member has never borrowed this magazine or has returned it");
            } else {
                String accountName = library.getMemberById(tempID).getName();
              //  String magazineName = library.getMagazine(ISSN, numberMagazine).getName();
                String magazineName=magazine.getName();
               // library.addMagazine(library.getMagazine(ISSN, numberMagazine));
                library.addMagazine(magazine);
                int lastBalance = library.getMemberById(tempID).getBalance();
              //  int magazinePrice = library.getMagazine(ISSN, numberMagazine).getPrice();
                int magazinePrice =magazine.getPrice();
                library.getMemberById(tempID).returnMagazine(magazine);
                library.getMemberById(tempID).setBalance(lastBalance + ((8 * magazinePrice) / 10));
                System.out.println(accountName + " returned " + magazineName + " successfully");
            }
        }
    }

    private void donateBook(Matcher matcher){
        int tempID;
        int amountBook;
        tempID = Integer.valueOf(matcher.group("ID"));
        amountBook = Integer.valueOf(matcher.group("AmountBook"));
        int bookPrice= Integer.valueOf(matcher.group("Price")) ;
        int newAccount= amountBook* bookPrice;

        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }
        else{

                String accountName = library.getMemberById(tempID).getName();
                int lastBalance = library.getMemberById(tempID).getBalance();
                if(amountBook!=0) {
                    for (int i = 0; i < amountBook; i++) {
                        library.addBook(new Book(matcher.group("BookName"), Integer.valueOf(matcher.group("ISBN")), matcher.group("Author"), matcher.group("Language"), Integer.valueOf(matcher.group("Price"))));
                    }
                }
                library.getMemberById(tempID).setBalance(lastBalance + ((4 * newAccount) / 10));

                System.out.println(accountName + " donated " + amountBook + " books successfully");
        }

    }

    private void returnMagazineAndBurrowNext(Matcher matcher){
        int tempID;
        int ISSN;
        int numberMagazine;
        tempID = Integer.valueOf(matcher.group("ID"));
        ISSN = Integer.valueOf(matcher.group("ISSN"));
        numberMagazine = Integer.valueOf(matcher.group("NumberMagazine"));

        if(library.getMemberById(tempID) == null){
            System.out.println("No member with this ID exists");
        }

        else {
            Magazine magazine = library.getMemberById(tempID).getMagazineFromBorrowedMagazines(ISSN,numberMagazine);
            if (magazine == null) {
                System.out.println("This member has never borrowed this magazine or has returned it");
            }
            else if(library.getMagazine(ISSN,numberMagazine+1)==null){
            System.out.println("The library does not have the next issue of this magazine");
        }
            else {
                String accountName= library.getMemberById(tempID).getName();
                String magazineName= magazine.getName();
                library.getMagazines().add(magazine);
                library.getMemberById(tempID).borrowMagazine(library.getMagazine(ISSN,numberMagazine+1));
                library.getMemberById(tempID).returnMagazine(magazine);
                library.removeMagazine(library.getMagazine(ISSN,numberMagazine+1));
                System.out.println(accountName+" returned "+magazineName+" and borrowed the next issue");
            }
        }
    }

    private void printBooks(Matcher matcher){
        System.out.println("List of all books:");
        library.sortBook();
    }

    private void printMagazines(Matcher matcher){
        System.out.println("List of all magazines:");
        library.sortMagazine();
    }


    public void run() {
        String command;
        boolean libraryCreated = false;

        do {
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("end")) {
                break;  // Exit the loop if input is "end"
            }
            if (command.matches("Create Library")) {
                libraryCreated = true;
                System.out.println("Library created successfully");
            }
            else if (!libraryCreated) {
                System.out.println("You have to create the library first");
            }

           else if (command.matches("Add account Account-Name (?<Name>[a-zA-Z\\s]+) Account-Number (?<ID>\\d{5})")) {
                Matcher matcher = getCommandMatcher(command, "Add account Account-Name (?<Name>[a-zA-Z\\s]+) Account-Number (?<ID>\\d{5})");
                matcher.find();
                addAccount(matcher);
                continue;
            }
            else if(command.matches("Increase balance Account-Number (?<ID>\\d{5}) Amount (?<Amount>\\d+)")){
                Matcher matcher = getCommandMatcher(command, "Increase balance Account-Number (?<ID>\\d{5}) Amount (?<Amount>\\d+)");
                matcher.find();
                increaseBalance(matcher);
                continue;
            }
            else if(command.matches("Cashout Account-Number (?<ID>\\d{5})")){
                Matcher matcher = getCommandMatcher(command, "Cashout Account-Number (?<ID>\\d{5})");
                matcher.find();
                cashout(matcher);
                continue;
            }
            else if(command.matches("Add book Book-Name (?<BookName>[a-zA-Z\\s\\d]+) ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountBook>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Add book Book-Name (?<BookName>[a-zA-Z\\s\\d]+) ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountBook>\\d+)");
                matcher.find();
                addBook(matcher);
                continue;
            }
            else if(command.matches("Add magazine Magazine-Name (?<MagazineName>[a-zA-Z\\s\\d]+) ISSN (?<ISSN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountMagazine>\\d+) Number (?<NumberMagazine>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Add magazine Magazine-Name (?<MagazineName>[a-zA-Z\\s\\d]+) ISSN (?<ISSN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountMagazine>\\d+) Number (?<NumberMagazine>\\d+)");
                matcher.find();
                addMagazine(matcher);
                continue;
            }
            else if(command.matches("Borrow book ISBN (?<ISBN>\\d+) Account-Number (?<ID>\\d{5})")){
                Matcher matcher = getCommandMatcher(command, "Borrow book ISBN (?<ISBN>\\d+) Account-Number (?<ID>\\d{5})");
                matcher.find();
                borrowBook(matcher);
                continue;
            }
            else if(command.matches("Borrow magazine ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Borrow magazine ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)" );
                matcher.find();
                borrowMagazine(matcher);
                continue;
            }
            else if(command.matches("Return book ISBN (?<ISBN>\\d+) Account-Number (?<ID>\\d{5})")){
                Matcher matcher = getCommandMatcher(command, "Return book ISBN (?<ISBN>\\d+) Account-Number (?<ID>\\d{5})");
                matcher.find();
                returnBook(matcher);
                continue;
            }
            else if(command.matches("Return magazine ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)")){
                Matcher matcher = getCommandMatcher(command,"Return magazine ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)");
                matcher.find();
                returnMagazine(matcher);
                continue;
            }
            else if(command.matches("Donate book Account-Number (?<ID>\\d{5}) Book-Name (?<BookName>[a-zA-Z\\s\\d]+) ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountBook>\\d+)")){
                Matcher matcher = getCommandMatcher(command, "Donate book Account-Number (?<ID>\\d{5}) Book-Name (?<BookName>[a-zA-Z\\s\\d]+) ISBN (?<ISBN>\\d+) Author (?<Author>[a-zA-Z\\s]+) Language (?<Language>[a-zA-Z]+) Price (?<Price>\\d+) Amount (?<AmountBook>\\d+)");
                matcher.find();
                donateBook(matcher);
                continue;
            }
            else if(command.matches("Return magazine and borrow next ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)")){
                Matcher matcher = getCommandMatcher(command, "Return magazine and borrow next ISSN (?<ISSN>\\d+) Account-Number (?<ID>\\d{5}) Number (?<NumberMagazine>\\d+)");
                matcher.find();
                returnMagazineAndBurrowNext(matcher);
            }


            else if(command.matches("Print books")){
                Matcher matcher =getCommandMatcher(command,"Print books");
                matcher.find();
                printBooks(matcher);
            }
            else if(command.matches("Print magazines")){
                Matcher matcher =getCommandMatcher(command,"Print magazines");
                matcher.find();
                printMagazines(matcher);
            }
            else{
                System.out.println("invalid command");
            }


        }while (true);
    }



}
