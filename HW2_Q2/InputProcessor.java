import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class InputProcessor {
    private Scanner scanner = new Scanner(System.in);
    private Manager manager;
    public InputProcessor(Manager manager){
        this.manager = manager;
    }
    private void processGetCourse(String[] split){
        if(split.length==6) {
            manager.getCourse(new Course(split[0], split[1], split[2], split[3], split[4], split[5]));
        }
        else{
            System.out.println("Invalid command !");
        }
    }

    private void processSignIn(String[] split){
        if (split.length == 4) {
            manager.signIn(new Student(split[1],split[2],split[3]));
        }
        else{
            System.out.println("Invalid command !");
        }

    }

    private void processLogIn(String[] split){
        if(split.length==4) {
            manager.logIn(split[1], split[2], split[3]);
        }
        else{
            System.out.println("Invalid command !");
        }
    }

    private void processLogOut(){
        manager.logOut();
    }

    private void processEditProfile(String[] split){
        if(split.length==5) {
            manager.editProfile(split[2], split[3], split[4]);
        }
        else{
            System.out.println("Invalid command !");
        }
    }

    private void processShowDepartmentList(){
        manager.showDepartmentList();
    }

    private void processShowMyList(){
        manager.showMyList();
    }

    private void processSelectCourse(String [] split){
        if(split.length==2) {
            manager.selectCourse(split[1]);
        }
        else{
            System.out.println("Invalid command !");
        }
    }

    private void processDeleteCourse(String[] split){
        if(split.length==3) {
            manager.deleteCourse(split[2]);
        }
        else{
            System.out.println("Invalid command !");
        }
    }

    private void processShowProfile(){
        manager.showProfile();
    }

//   private void processSelectAll(String[]... split){
//
//            String[] selectAll = new String[split.length - 2];
//            System.arraycopy(split, 2, selectAll, 0, split.length - 2);
//
//            String selectAllFinal=Arrays.toString(selectAll);
//            manager.selectAll(selectAllFinal);
//
//    }

//    private void processSelectAll(String... split){
//        manager.selectAll(split);
//    }

    private void processSelectAll(String[] split) {
        ArrayList<String> input=new ArrayList<>();
        manager.selectAllError();

        for (String array : split) {
            input.add(array);
        }
        for(int i=0; i<input.size(); i++){

            manager.selectAll(input.get(i));
        }
        manager.selectAllPrint();

    }
//    private void processSelectAll(String[] split) {
//        ArrayList<String> input=new ArrayList<>();
//        for (String array : split) {
//            input.add(array);
//        }
//        manager.selectAll(input);
//    }




    public String numberOfCourses;


    public void run() {
        String input;

        numberOfCourses = scanner.nextLine();
        // int courseNumber=Integer.parseInt(numberOfCourses);


        while (!(input = scanner.nextLine()).trim().equals("-end")) {

            String[] words= input.split("\\s+");



            if (!input.trim().startsWith("-" ) && !input.trim().startsWith(".")) {
                processGetCourse(input.trim().split(","));
            }

            else if(input.trim().startsWith("-signin")){
                processSignIn(input.split("\\s+"));

            }


            else if(input.trim().startsWith("-login")){
                processLogIn(input.split("\\s+"));
            }

            else if(input.trim().startsWith("-logout")){
                processLogOut();
            }

//            else if(words[0].trim().equals("-edit") && words[1].trim().equals("profile")){
//                processEditProfile(input.split("\\s+"));
//            }

            else if (words[0].trim().equals(("-edit"))) {
                if (words.length < 2) {
                    System.out.println("Invalid command !");
                } else if (words[1].trim().equals("profile")) {
                    processEditProfile(input.split("\\s+"));
                } else {
                    System.out.println("Invalid command !");
                }
            }




//            else if(words[0].trim().equals("-show") && words[1].trim().equals("department") && words[2].trim().equals("list")){
//                if(words.length !=3){
//                    System.out.println("Invalid command !");
//                }else {
//                    processShowDepartmentList();
//                }
//            }






//            else if(words[0].trim().equals("-show") && words[1].trim().equals("my") && words[2].trim().equals("list")){
//                if(words.length !=3){
//                    System.out.println("Invalid command !");
//                }
//                else {
//                    processShowMyList();
//                }
//            }

//            else if(words[0].trim().equals("-delete") && words[1].trim().equals("course")){
//                processDeleteCourse(input.split("\\s+"));
//            }

            else if (words[0].trim().equals("-delete")) {
                if (words.length < 2) {
                    System.out.println("Invalid command !");
                } else if (words[1].trim().equals("course")) {
                    processDeleteCourse(input.split("\\s+"));
                } else {
                    System.out.println("Invalid command !");
                }
            }

//            else if(words[0].equals("-show") && words[1].trim().equals("profile")){
//                if(words.length !=2){
//                    System.out.println("Invalid command !");
//                }
//                else {
//                    processShowProfile();
//                }
//            }

            else if(words[0].trim().equals("-show")){
                if(words.length <2){
                    System.out.println("Invalid command !");
                }
                else if(words.length ==2){
                    if(words[1].trim().equals("profile")){
                        processShowProfile();
                    }
                    else{
                        System.out.println("Invalid command !");
                    }
                }
                else if (words[1].trim().equals("my") && words[2].trim().equals("list")) {
                    processShowMyList();
                } else if (words[1].trim().equals("department") && words[2].trim().equals("list")) {
                    processShowDepartmentList();
                }

                else {
                    System.out.println("Invalid command !");
                }
            }





//            else if(words[0].trim().equals("-select") && words[1].trim().matches("\\d+")){
//                processSelectCourse(input.split("\\s+"));
//            }
//
//            else if(words[0].trim().equals("-select") && words[1].trim().equals("all")) {
//                String[] codes = Arrays.copyOfRange(words, 2, words.length);
//                processSelectAll(codes);
//            }

            else if(words[0].trim().equals("-select")){
                if(words.length<2){
                    System.out.println("Invalid command !");
                }
                if(words.length==2) {
                 if (words[1].trim().matches("\\d+")) {
                        processSelectCourse(input.split("\\s+"));
                    }
                }
                else if(words[1].trim().equals("all")){
                    String[] codes = Arrays.copyOfRange(words, 2, words.length);
                    processSelectAll(codes);
                }
                else{
                    System.out.println("Invalid command !");
                }
            }





            else{
                System.out.println("Invalid command !");
            }



        }


    }


}






