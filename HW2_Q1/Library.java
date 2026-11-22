import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Magazine> magazines;
    private ArrayList<Mem> members;
    public Library() {
        this.books= new ArrayList<>();
        this.magazines= new ArrayList<>();
        this.members= new ArrayList<>();
    }

    public void addMember (Mem member){
        members.add(member);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void addMagazine(Magazine magazine){
        magazines.add(magazine);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Magazine> getMagazines() {
        return magazines;
    }

    public Mem getMemberById(int ID){
        for (Mem member : members)
            if(member.getId()==ID)
                return member;
        return null;
    }

    public Book getBook(int ISBN){
        for (Book book : books)
            if(book.getISBN()==ISBN)
                return book;
        return null;
    }

    public Magazine getMagazine(int ISSN , int number){
        for (Magazine magazine : magazines)
            if(magazine.getISSN()==ISSN && magazine.getNumber()==number)
                return magazine;
        return null;

    }

    public void removeBook(Book book){
        getBooks().remove(book);
    }

    public void removeMagazine(Magazine magazine){
        getMagazines().remove(magazine);
    }

    public void sortBook(){

        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                String name1 = books.get(i).getName();
                String name2 = books.get(j).getName();
                String nameAuthor1 = books.get(i).getAuthor();
                String nameAuthor2 = books.get(j).getAuthor();
                String language1 = books.get(i).getLanguage();
                String language2 = books.get(j).getLanguage();
                int result = name1.compareTo(name2);
                int result2=nameAuthor1.compareTo(nameAuthor2);
                int result3= language1.compareTo(language2);

                if (result > 0) {
                    Book temp = books.get(i);
                    books.set(i, books.get(j));
                    books.set(j, temp);
                }
//                else if(result < 0){
//                    Book temp = books.get(j);
//                    books.set(j, books.get(i));
//                    books.set(i, temp);
//                }
                else if(result==0){

                    if(result2 > 0){
                        Book temp = books.get(i);
                        books.set(i, books.get(j));
                        books.set(j, temp);
                    }
//                    else if(result2 <0){
//                        Book temp = books.get(j);
//                        books.set(j, books.get(i));
//                        books.set(i, temp);
//                    }
                    else if(result2==0){

                        if(result3 > 0){
                            Book temp = books.get(i);
                            books.set(i, books.get(j));
                            books.set(j, temp);
                        }
//                        else if (result3 <0){
//                            Book temp = books.get(j);
//                            books.set(j, books.get(i));
//                            books.set(i, temp);
//                        }
                    }
                }
            }
        }
        for (Book book : books) {
            System.out.println(book.getISBN() + ": " + book.getName());
        }





    }
    public void sortMagazine(){
        for (int i = 0; i < magazines.size() - 1; i++) {
            for (int j = i + 1; j < magazines.size(); j++) {
                String name1 = magazines.get(i).getName();
                String name2 = magazines.get(j).getName();
                int number1=magazines.get(i).getNumber();
                int number2= magazines.get(j).getNumber();
                String nameAuthor1 = magazines.get(i).getAuthor();
                String nameAuthor2 = magazines.get(j).getAuthor();
                String language1 = magazines.get(i).getLanguage();
                String language2 = magazines.get(j).getLanguage();
                int result = name1.compareTo(name2);
                int result3 = nameAuthor1.compareTo(nameAuthor2);
                int result4 = language1.compareTo(language2);

                if (result > 0) {
                    Magazine temp = magazines.get(i);
                    magazines.set(i, magazines.get(j));
                    magazines.set(j, temp);

                }
//                } else if (result < 0) {
//                    Magazine temp = magazines.get(j);
//                    magazines.set(j, magazines.get(i));
//                    magazines.set(i, temp);
//                    System.out.println("2");
//                }
                else if (result == 0) {

                    if(number1>number2){
                        Magazine temp = magazines.get(i);
                        magazines.set(i, magazines.get(j));
                        magazines.set(j, temp);
                    }
//                    else if(number1 <number2){
//                        Magazine temp = magazines.get(j);
//                        magazines.set(j, magazines.get(i));
//                        magazines.set(i, temp);
//                        System.out.println("4");
//                    }
                    else if(number1==number2){
                        if (result3 > 0) {
                            Magazine temp = magazines.get(i);
                            magazines.set(i, magazines.get(j));
                            magazines.set(j, temp);
                        }
//                        } else if (result3 < 0) {
//                            Magazine temp = magazines.get(j);
//                            magazines.set(j, magazines.get(i));
//                            magazines.set(i, temp);
//                            System.out.println("6");
                      //  }
                    else if (result3 == 0) {

                            if (result4 > 0) {
                                Magazine temp = magazines.get(i);
                                magazines.set(i, magazines.get(j));
                                magazines.set(j, temp);

                            }
//                            else if (result4 < 0) {
//                                Magazine temp = magazines.get(j);
//                                magazines.set(j, magazines.get(i));
//                                magazines.set(i, temp);
//                                System.out.println("8");
//                            }
                    }
                    }
                }
            }
        }


        for (Magazine magazine : magazines) {
            System.out.println(magazine.getISSN() + ": " +magazine.getName() + " " + magazine.getNumber());
        }


    }


}
