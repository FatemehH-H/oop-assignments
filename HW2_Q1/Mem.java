import java.util.ArrayList;

public class Mem {
    private String name;
    private int id;
    private int balance;
    private ArrayList<Book> borrowedBooks;
    private ArrayList<Magazine> borrowedMagazines;

    public Mem(){
        this.borrowedBooks= new ArrayList<>();
        this.borrowedMagazines= new ArrayList<>();
    }

    public Mem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void borrowBook(Book book){
        if (borrowedBooks == null) {
            borrowedBooks = new ArrayList<>();
        }
        borrowedBooks.add(book);
    }


    public void borrowMagazine(Magazine magazine){
        if(borrowedMagazines ==null){
            borrowedMagazines = new ArrayList<>();
        }
        borrowedMagazines.add(magazine);
    }

    public Book getBookFromBorrowedBooks(int ISBN){
        if(borrowedBooks!=null)
        for (Book borrowedBook : borrowedBooks)
            if(borrowedBook.getISBN()==ISBN)
                return borrowedBook;
        return null;

    }

    public Magazine getMagazineFromBorrowedMagazines(int ISSN , int number){
        if(borrowedMagazines!=null)
        for (Magazine borrowedMagazine : borrowedMagazines)
            if(borrowedMagazine.getISSN()==ISSN && borrowedMagazine.getNumber()==number)
                return borrowedMagazine;
        return null;

    }

    public void returnBook(Book book){
        if(borrowedBooks!=null)
            borrowedBooks.remove(book);
    }

    public void returnMagazine(Magazine magazine){
     //   if(borrowedMagazines!=null)
            borrowedMagazines.remove(magazine);

    }


}
