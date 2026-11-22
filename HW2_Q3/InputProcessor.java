import java.util.Scanner;

public class InputProcessor {
    private Scanner scanner = new Scanner(System.in);
    private Manager manager;
    public InputProcessor(Manager manager){
        this.manager = manager;
    }

    private void processAddCar(String[] split){
//        Car addCar = new Car(split[1],split[2],split[3]);
//        manager.addCar(addCar);
        manager.addCar(new Car(split[1],split[2],split[3]));
    }

    private void processAddMotor(String[] split){
        manager.addMotor(new Motorcycle(split[1],split[2],split[3]));
    }

    private void processAddBike(String[] split){
        manager.addBike(new Bike(split[1],split[2],split[3],split[4]));
    }
    private void processSetCarColor(String[] split){
        manager.setCarColor(split[1],split[2]);
    }
    private void processSetMotorColor(String[] split){
        manager.setMotorColor(split[1],split[2]);
    }
    private void processSetBikeColor(String[] split){
        manager.setBikeColor(split[1],split[2]);
    }
    private void processSetCarOrigin(String[] split){
        manager.setCarOrigin(split[1],split[2]);
    }
    private void processSetMotorOrigin(String[] split){
        manager.setMotorOrigin(split[1],split[2]);
    }
    private void processSetBikeOrigin(String[] split){
        manager.setBikeOrigin(split[1],split[2]);
    }
    private void processSetCarStrength(String[] split){
        manager.setCarStrength(split[1],split[2]);
    }
    private void processSetMotorStrength(String[] split){
        manager.setMotorStrength(split[1],split[2]);
    }
    private void processSetCarSpeed(String[] split){
        manager.setCarSpeed(split[1],split[2]);
    }
    private void processSetMotorSpeed(String[] split){
        manager.setMotorSpeed(split[1],split[2]);
    }
    private void processSetBikeWeight(String[] split){
        manager.setBikeWeight(split[1],split[2]);
    }
    private void processSetWheelSize(String[] split){
        manager.setWheelSize(split[1],split[2]);
    }
    private void processSetCarPrice(String[] split){
        manager.setCarPrice(split[1],split[2]);
    }

    private void processSetMotorPrice(String[] split){
        manager.setMotorPrice(split[1],split[2]);
    }

    private void processSetBikePrice(String[] split){
        manager.setBikePrice(split[1],split[2]);
    }





    private void processSetCarState(String[] split){
        manager.setCarState(split[1],split[2]);
    }
    private void processSetMotorState(String[] split){
        manager.setMotorState(split[1],split[2]);
    }
    private void processSetBikeState(String[] split){
        manager.setBikeState(split[1],split[2]);
    }


    private void processCompareCars(String[] split){
        manager.compareCars(split[1],split[2]);
    }

    private void processCompareMotors(String[] split){
        manager.compareMotors(split[1],split[2]);
    }

    private void processCompareBikes(String[] split){
        manager.compareBikes(split[1],split[2]);
    }
    private void processSetCarDiscount(String[] split){
        manager.setCarDiscount(split[1],split[2]);
    }

    private void processSetMotorDiscount(String[] split){
        manager.setMotorDiscount(split[1],split[2]);
    }

    private void processSetBikeDiscount(String[] split){
        manager.setBikeDiscount(split[1],split[2]);
    }

    private void processSearchCarsColor(String[] split){
        manager.searchCarsColor(split[2]);
    }

    private void processSearchMotorsColor(String[] split){
        manager.searchMotorsColor(split[2]);
    }

    private void processSearchBikesType(String[] split){
        manager.searchBikesType(split[2]);
    }

    private void processSearchCarsOrigin(String[] split){
        manager.searchCarsOrigin(split[2]);
    }

    private void processSearchMotorsOrigin(String[] split){
        manager.searchMotorsOrigin(split[2]);
    }

    private void processSearchBikesOrigin(String[] split){
        manager.searchBikesOrigin(split[2]);
    }

    private void processLeastCarPrice(){
        manager.leastcarPrice();
    }

    private void processLeastMotorPrice(){
        manager.leastmotorPrice();
    }

    private void processLeastBikePrice(){
        manager.leastbikePrice();
    }

    private void processShowCarProperties(String[] split){
        manager.showCarProperties(split[2]);
    }

    private void processShowMotorProperties(String[] split){
        manager.showMotorProperties(split[2]);
    }

    private void processShowBikeProperties(String[] split){
        manager.showBikeProperties(split[2]);
    }



    public void run(){
        String input;

        while(!(input = scanner.nextLine()).trim().equals("end")){
            String[] words= input.split("\\s+");
            if(input.startsWith("addCar")){
            processAddCar(input.split("\\s+"));
            }
            else if(input.startsWith("addMotorcycle")){
            processAddMotor(input.split("\\s+"));
            }
            else if(input.startsWith("addBike")){
            processAddBike(input.split("\\s+"));
            }

            else if(input.startsWith("SetCarColor")){
                processSetCarColor(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleColor")){
                processSetMotorColor(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikeColor")){
                processSetBikeColor(input.split("\\s+"));
            }

            else if(input.startsWith("SetCarOrigin")){
                processSetCarOrigin(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleOrigin")){
                processSetMotorOrigin(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikeOrigin")){
                processSetBikeOrigin(input.split("\\s+"));
            }
            else if(input.startsWith("SetCarStrength")){
                processSetCarStrength(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleStrength")){
                processSetMotorStrength(input.split("\\s+"));
            }
            else if(input.startsWith("SetCarSpeed")){
                processSetCarSpeed(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleSpeed")){
                processSetMotorSpeed(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikeWeight")){
                processSetBikeWeight(input.split("\\s+"));
            }
            else if(input.startsWith("SetWheelSize")){
                processSetWheelSize(input.split("\\s+"));
            }

            else if(input.startsWith("SetCarPrice")){
                processSetCarPrice(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcyclePrice")){
                processSetMotorPrice(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikePrice")){
                processSetBikePrice(input.split("\\s+"));
            }
            else if(input.startsWith("SetCarDiscount")){
                processSetCarDiscount(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleDiscount")){
                processSetMotorDiscount(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikeDiscount")){
                processSetBikeDiscount(input.split("\\s+"));
            }
            else if(input.startsWith("CompareCars")){
                processCompareCars(input.split("\\s+"));
            }
            else if(input.startsWith("CompareMotorcycles")){
                processCompareMotors(input.split("\\s+"));
            }
            else if(input.startsWith("CompareBikes")){
                processCompareBikes(input.split("\\s+"));
            }
            else if(input.startsWith("SetCarState")){
                processSetCarState(input.split("\\s+"));
            }
            else if(input.startsWith("SetMotorcycleState")){
                processSetMotorState(input.split("\\s+"));
            }
            else if(input.startsWith("SetBikeState")){
                processSetBikeState(input.split("\\s+"));
            }
            else if(words[0].equals("SearchCars") && words[1].equals("Color")){
                processSearchCarsColor(input.split("\\s+"));
            }
            else if(words[0].equals("SearchMotorcycles") && words[1].equals("Color")){
                processSearchMotorsColor(input.split("\\s+"));
            }
            else if(words[0].equals("SearchBikes") && words[1].equals("Type")){
                processSearchBikesType(input.split("\\s+"));
            }
            else if(words[0].equals("SearchCars") && words[1].equals("Origin")){
                processSearchCarsOrigin(input.split("\\s+"));
            }
            else if(words[0].equals("SearchMotorcycles") && words[1].equals("Origin")){
                processSearchMotorsOrigin(input.split("\\s+"));
            }
            else if(words[0].equals("SearchBikes") && words[1].equals("Origin")){
                processSearchBikesOrigin(input.split("\\s+"));
            }
            else if(input.startsWith("LeastCarPrice")){
                processLeastCarPrice();
            }
            else if(input.startsWith("LeastMotorcyclePrice")){
                processLeastMotorPrice();
            }
            else if(input.startsWith("LeastBikePrice")){
                processLeastBikePrice();
            }
            else if(input.startsWith("Show CarProperties")){
                processShowCarProperties(input.split("\\s+"));
            }
            else if(input.startsWith("Show MotorcycleProperties")){
                processShowMotorProperties(input.split("\\s+"));
            }
            else if(input.startsWith("Show BikeProperties")){
                processShowBikeProperties(input.split("\\s+"));
            }
        }

    }
}
