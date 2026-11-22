public class Bike extends Vehicle {

    private String bikeType;
    private String bikeWeight;
    private String wheelSize;

    public Bike(String bikeName,String bikeBrand,String bikeCode,String bikeType) {
        this.Name = bikeName;
        this.Brand = bikeBrand;
        this.Code = bikeCode;
        this.bikeType = bikeType;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBikeWeight() {
        return bikeWeight;
    }

    public void setBikeWeight(String bikeWeight) {
        this.bikeWeight = bikeWeight;
    }

    public String getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(String wheelSize) {
        this.wheelSize = wheelSize;
    }
}
