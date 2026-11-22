public class Car extends Vehicle {
//    private String carName;
//    private String carPrice;
//    private String carBrand;
//    private String carColor;
//    private String carOrigin;
    private String carStrength;
    private String carSpeed;
//    private String carState;
//    private String carCode;


    public Car(String carName,String carBrand,String carCode) {
        this.Name = carName;
        this.Brand = carBrand;
        this.Code = carCode;

    }


//    public String getCarCode() {
//        return carCode;
//    }
//
//    public void setCarColor(String carColor) {
//        this.carColor = carColor;
//    }
//    public String getCarColor(){
//        return carColor;
//    }
//
//    public void setCarOrigin(String carOrigin) {
//        this.carOrigin = carOrigin;
//    }
//    public String getCarOrigin(){
//        return carOrigin;
//    }
//
    public void setCarStrength(String carStrength) {
        this.carStrength = carStrength;
    }
    public String getCarStrength(){
        return carStrength;
    }

    public void setCarSpeed(String carSpeed) {
        this.carSpeed = carSpeed;
    }

    public String getCarSpeed() {
        return carSpeed;
    }
//
//    public void setCarPrice(String carPrice) {
//        this.carPrice = carPrice;
//    }
//
//    public String getCarPrice() {
//        return carPrice;
//    }
//
//    public void setCarState(String carState) {
//        this.carState = carState;
//    }
//
//    public String getCarState() {
//        return carState;
//    }


}
