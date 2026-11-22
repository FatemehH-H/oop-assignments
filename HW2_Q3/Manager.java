import java.util.ArrayList;

public class Manager {

    private ArrayList<Car> addCars;
    private ArrayList<Motorcycle> addMotors;
    private ArrayList<Bike> addBikes;
    public Manager() {
        this.addCars= new ArrayList<>();
        this.addMotors= new ArrayList<>();
        this.addBikes= new ArrayList<>();
    }

    private Car getCar(String carCode) {
        for (Car addCar : addCars)
            if (addCar.getCode().equals(carCode))
                return addCar;
        return null;
    }
    private Motorcycle getMotor(String motorCode) {
        for (Motorcycle addMotor : addMotors)
            if (addMotor.getCode().equals(motorCode))
                return addMotor;
        return null;
    }
    private Bike getBike(String bikeCode) {
        for (Bike addBike : addBikes)
            if (addBike.getCode().equals(bikeCode))
                return addBike;
        return null;
    }

    public void addCar(Car addcar){
        boolean code=false;
        for (Car car : addCars) {
            if(car.getCode().equals(addcar.getCode())){
                code=true;
            }
        }
        if(code){
            System.out.println("Car with this code already exists!");
        }
        if(!code){
            addCars.add(addcar);
            System.out.println("Car was added successfully!");
        }
    }
    public void addMotor(Motorcycle addmotor){
        boolean code1=false;
        for (Motorcycle motor : addMotors) {
            if(motor.getCode().equals(addmotor.getCode())){
                code1=true;
            }
        }
        if(code1){
            System.out.println("Motorcycle with this code already exists!");
        }
        if(!code1){
            addMotors.add(addmotor);
            System.out.println("Motorcycle was added successfully!");
        }
    }

    public void addBike(Bike addbike){
        boolean code2=false;
        for (Bike bike : addBikes) {
            if(bike.getCode().equals(addbike.getCode())){
                code2=true;
            }
        }
        if(code2){
            System.out.println("Bike with this code already exists!");
        }
        if(!code2){
            addBikes.add(addbike);
            System.out.println("Bike was added successfully!");
        }
    }
    public void setCarColor(String code, String color){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            car.setColor(color);
            System.out.println("CarColor is "+ color +"!");
        }
    }
    public void setMotorColor(String code, String color){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            motor.setColor(color);
            System.out.println("MotorcycleColor is "+ color +"!");
        }
    }

    public void setBikeColor(String code, String color){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            bike.setColor(color);
            System.out.println("BikeColor is "+ color +"!");
        }
    }

    public void setCarOrigin(String code, String origin){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            car.setOrigin(origin);
            System.out.println("CarOrigin is "+ origin +"!");
        }
    }

    public void setMotorOrigin(String code, String origin){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            motor.setOrigin(origin);
            System.out.println("MotorcycleOrigin is "+ origin +"!");
        }
    }
    public void setBikeOrigin(String code, String origin){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            bike.setOrigin(origin);
            System.out.println("BikeOrigin is "+ origin +"!");
        }
    }





    public void setCarStrength(String code, String strength){
        Car car=getCar(code);
        double strengthdbl= Double.parseDouble(strength);

        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else if (strengthdbl>500 || strengthdbl<100){
            System.out.println("CarStrength is invalid!");
        }
        else{
            car.setCarStrength(strength);
            System.out.println("CarStrength is "+ strength +" hp!");
        }
    }

    public void setMotorStrength(String code, String strength){
        Motorcycle motor=getMotor(code);
        double strengthdbl= Double.parseDouble(strength);

        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else if (strengthdbl>100 || strengthdbl<50){
            System.out.println("MotorcycleStrength is invalid!");
        }
        else{
            motor.setMotorStrength(strength);
            System.out.println("MotorcycleStrength is "+ strength +" hp!");
        }
    }

    public void setCarSpeed(String code, String speed){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            car.setCarSpeed(speed);
            System.out.println("CarSpeed is "+ speed +"!");
        }
    }

    public void setMotorSpeed(String code, String speed){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            motor.setMotorSpeed(speed);
            System.out.println("MotorcycleSpeed is "+ speed +"!");
        }
    }

    public void setBikeWeight(String code, String weight){
        Bike bike= getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");
        }
        else{
            bike.setBikeWeight(weight);
            System.out.println("BikeWeight is "+weight+"!");
        }
    }

    public void setWheelSize(String code,String size){
        Bike bike= getBike(code);
        double sizedbl= Double.parseDouble(size);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");
        }
        else if(sizedbl>29 || sizedbl<12){
            System.out.println("WheelSize is invalid!");
        }
        else{
            bike.setWheelSize(size);
            System.out.println("WheelSize is "+size+" inches!");
        }
    }

    public void setCarPrice(String code, String price){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            car.setPrice(price);
            double money=Double.parseDouble(price);
          //  int money2=(int)money;
            System.out.println("CarPrice is "+ (int)money +"$!");
        }
    }

    public void setMotorPrice(String code, String price){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            motor.setPrice(price);
            double money=Double.parseDouble(price);
           // int money2=(int)money;
            System.out.println("MotorcyclePrice is "+ (int)money +"$!");
        }
    }

    public void setBikePrice(String code, String price){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            bike.setPrice(price);
            double money=Double.parseDouble(price);
            //int money2=(int)money;
            System.out.println("BikePrice is "+ (int)money +"$!");
        }
    }
    public void setCarState(String code, String state){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            car.setState(state);
            if(state.equals("sold")){
                addCars.remove(car);
            }

            System.out.println("CarState is "+ state +"!");

        }
    }

    public void setMotorState(String code, String state){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            motor.setState(state);
            if(state.equals("sold")){
                addMotors.remove(motor);
            }

            System.out.println("MotorcycleState is "+ state +"!");

        }
    }

    public void setBikeState(String code, String state){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            bike.setState(state);
            if(state.equals("sold")){
                addBikes.remove(bike);
            }

            System.out.println("BikeState is "+ state +"!");

        }
    }
    public void compareCars(String code1, String code2){
        Car car=getCar(code1);
        Car car1=getCar(code2);
        if(car==null || car1==null){
            System.out.println("Input is invalid!");
        }
        else{
            double speed1=Double.parseDouble(car.getCarSpeed());
            double speed2=Double.parseDouble(car1.getCarSpeed());
            double strength1=Double.parseDouble(car.getCarStrength());
            double strength2=Double.parseDouble(car1.getCarStrength());
            double price1=Double.parseDouble(car.getPrice());
            double price2=Double.parseDouble(car1.getPrice());

            if(speed1>speed2){
                System.out.println("CarSpeed: "+ code1 );
            }
            else if(speed2>speed1){
                System.out.println("CarSpeed: "+ code2 );
            }
            if(strength1>strength2){
                System.out.println("CarStrength: "+ code1 );
            }
            else if(strength2>strength1){
                System.out.println("CarStrength: "+ code2 );
            }
            if(price1>price2){
                System.out.println("CarPrice: "+ code2 );
            }
            else if(price2>price1){
                System.out.println("CarPrice: "+ code1 );
            }

        }

    }

    public void compareMotors(String code1, String code2){
        Motorcycle motor=getMotor(code1);
        Motorcycle motor1=getMotor(code2);
        if(motor==null || motor1==null){
            System.out.println("Input is invalid!");
        }
        else{
            double speed1=Double.parseDouble(motor.getMotorSpeed());
            double speed2=Double.parseDouble(motor1.getMotorSpeed());
            double strength1=Double.parseDouble(motor.getMotorStrength());
            double strength2=Double.parseDouble(motor1.getMotorStrength());
            double price1=Double.parseDouble(motor.getPrice());
            double price2=Double.parseDouble(motor1.getPrice());

            if(speed1>speed2){
                System.out.println("MotorcycleSpeed: "+ code1 );
            }
            else if(speed2>speed1){
                System.out.println("MotorcycleSpeed: "+ code2 );
            }
            if(strength1>strength2){
                System.out.println("MotorcycleStrength: "+ code1 );
            }
            else if(strength2>strength1){
                System.out.println("MotorcycleStrength: "+ code2 );
            }
            if(price1>price2){
                System.out.println("MotorcyclePrice: "+ code2 );
            }
            else if(price2>price1){
                System.out.println("MotorcyclePrice: "+ code1 );
            }

        }

    }

    public void compareBikes(String code1, String code2){
        Bike bike=getBike(code1);
        Bike bike1=getBike(code2);
        if(bike==null || bike1==null){
            System.out.println("Input is invalid!");
        }
        else if(!bike.getBikeType().equals(bike1.getBikeType())){
            System.out.println("Types should be the same!");
        }
        else{
            double price1=Double.parseDouble(bike.getPrice());
            double price2=Double.parseDouble(bike1.getPrice());
            double weight1=Double.parseDouble(bike.getBikeWeight());
            double weight2=Double.parseDouble(bike1.getBikeWeight());


            if(weight1>weight2){
                System.out.println("BikeWeight: "+ code1 );
            }
            else if(weight2>weight1){
                System.out.println("BikeWeight: "+ code2 );
            }
            if(price1>price2){
                System.out.println("BikePrice: "+ code2 );
            }
            else if(price2>price1){
                System.out.println("BikePrice: "+ code1 );
            }

        }

    }
    public void setCarDiscount(String code, String discountpercent){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            double discount= Double.parseDouble(discountpercent);
            double price =Double.parseDouble(car.getPrice());

            if(discount>15 || discount<5){
                System.out.println("Input for discount is invalid!");
            }
            else{
                double newPrice=((100-discount)/100)*price;
                int newPriceInt= (int) newPrice;
                String newPriceStr=String.valueOf(newPrice);
                car.setPrice(newPriceStr);
                System.out.println("New carPrice is "+ (int)newPrice+"$!");

            }

        }
    }

    public void setMotorDiscount(String code, String discountpercent){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            double discount= Double.parseDouble(discountpercent);
            double price =Double.parseDouble(motor.getPrice());

            if(discount>15 || discount<5){
                System.out.println("Input for discount is invalid!");
            }
            else{
                double newPrice=((100-discount)/100)*price;
                int newPriceInt= (int) newPrice;
                String newPriceStr=String.valueOf(newPrice);
                motor.setPrice(newPriceStr);
                System.out.println("New motorcyclePrice is "+ (int)newPrice+"$!");
            }

        }
    }

    public void setBikeDiscount(String code, String discountpercent){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            double discount= Double.parseDouble(discountpercent);
            double price =Double.parseDouble(bike.getPrice());

            if(discount>15 || discount<5){
                System.out.println("Input for discount is invalid!");
            }
            else{
                double newPrice=((100-discount)/100)*price;
                int newPriceInt= (int) newPrice;
                String newPriceStr=String.valueOf(newPrice);
                bike.setPrice(newPriceStr);
                System.out.println("New bikePrice is "+ (int)newPrice+"$!");
            }

        }
    }


    public void searchCarsColor(String color){
        boolean flag=false;
        for (Car car : addCars) {
            String carColor=car.getColor();
            if(color!=null && carColor.equals(color)){
                System.out.println(car.Name+" "+car.Code);
                flag=true;
            }

        }
        if(!flag)
            System.out.println("No cars found with the given color!");

    }

    public void searchMotorsColor(String color){
        boolean flag=false;
        for (Motorcycle motor : addMotors) {
            String motorColor=motor.getColor();
            if(color!=null && motorColor.equals(color)){
                System.out.println(motor.Name+" "+motor.Code);
                flag=true;
            }

        }
        if(!flag)
            System.out.println("No motorcycles found with the given color!");

    }
    public void searchBikesType(String type){
        boolean flag=false;
        for (Bike bike : addBikes) {
            String bikeColor=bike.getBikeType();
            if(type!=null && bikeColor.equals(type)){
                System.out.println(bike.Name+" "+bike.Code);
                flag=true;
            }

        }
        if(!flag)
            System.out.println("No bikes found with the given type!");

    }

    public void searchCarsOrigin(String origin){
        boolean flag2=false;
        for (Car car : addCars) {
            String carColor=car.getOrigin();
            if(origin!=null && carColor.equals(origin)){
                System.out.println(car.Name+" "+car.Code);
                flag2=true;
            }

        }
        if(!flag2)
            System.out.println("No cars found with the given origin!");

    }

    public void searchMotorsOrigin(String origin){
        boolean flag2=false;
        for (Motorcycle motor : addMotors) {
            String motorColor=motor.getOrigin();
            if(origin!=null && motorColor.equals(origin)){
                System.out.println(motor.Name+" "+motor.Code);
                flag2=true;
            }

        }
        if(!flag2)
            System.out.println("No motorcycles found with the given origin!");

    }


    public void searchBikesOrigin(String origin){
        boolean flag2=false;
        for (Bike bike : addBikes) {
            String bikeColor=bike.getOrigin();
            if(origin!=null && bikeColor.equals(origin)){
                System.out.println(bike.Name+" "+bike.Code);
                flag2=true;
            }

        }
        if(!flag2)
            System.out.println("No bikes found with the given origin!");

    }


    public void leastcarPrice(){

        Car leastCar =addCars.get(0);
        String x=leastCar.getPrice();
        double y=Double.parseDouble(x);
        for(Car car : addCars){
            double z=Double.parseDouble(car.getPrice());
            if(z<y) {
                y = Double.parseDouble(car.getPrice());
                leastCar = car;
            }
        }
        double g=Double.parseDouble(leastCar.getPrice());
        System.out.println(leastCar.Name+" "+leastCar.Code+" "+(int)g);
    }

    public void leastmotorPrice(){

        Motorcycle leastMotor =addMotors.get(0);
        String x=leastMotor.getPrice();
        double y=Double.parseDouble(x);
        for(Motorcycle motor : addMotors){
            double z=Double.parseDouble(motor.getPrice());
            if(z<y) {
                y = Double.parseDouble(motor.getPrice());
                leastMotor = motor;
            }
        }
        double g=Double.parseDouble(leastMotor.getPrice());
        System.out.println(leastMotor.Name+" "+leastMotor.Code+" "+(int)g);
    }

    public void leastbikePrice(){

        Bike leastBike =addBikes.get(0);
        String x=leastBike.getPrice();
        double y=Double.parseDouble(x);
        for(Bike bike : addBikes){
            double z=Double.parseDouble(bike.getPrice());
            if(z<y) {
                y = Double.parseDouble(bike.getPrice());
                leastBike = bike;
            }
        }
        double g=Double.parseDouble(leastBike.getPrice());
        System.out.println(leastBike.Name+" "+leastBike.Code+" "+(int)g);
    }

    public void showCarProperties(String code){
        Car car=getCar(code);
        if(car==null){
            System.out.println("No cars exist with the given code!");

        }
        else{
            String name= car.Name;
            String brand= car.Brand;
            String origin=car.getOrigin();
            String color=car.getColor();
            System.out.println("CarBrand: "+ brand );
            System.out.println("CarName: " + name);
            System.out.println("CarColor: "+color);
            System.out.println("CarOrigin: "+origin);
        }
    }

    public void showMotorProperties(String code){
        Motorcycle motor=getMotor(code);
        if(motor==null){
            System.out.println("No motorcycles exist with the given code!");

        }
        else{
            String name= motor.Name;
            String brand= motor.Brand;
            String origin=motor.getOrigin();
            String color=motor.getColor();
            System.out.println("MotorcycleBrand: "+ brand );
            System.out.println("MotorcycleName: " + name);
            System.out.println("MotorcycleColor: "+color);
            System.out.println("MotorcycleOrigin: "+origin);
        }
    }


    public void showBikeProperties(String code){
        Bike bike=getBike(code);
        if(bike==null){
            System.out.println("No bikes exist with the given code!");

        }
        else{
            String name= bike.Name;
            String brand= bike.Brand;
            String origin=bike.getOrigin();
            String color=bike.getColor();
            System.out.println("BikeBrand: "+ brand );
            System.out.println("BikeName: " + name);
            System.out.println("BikeColor: "+color);
            System.out.println("BikeOrigin: "+origin);
        }
    }

}




