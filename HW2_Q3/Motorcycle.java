public class Motorcycle extends Vehicle {

    private String motorStrength;

    private String motorSpeed;
    public Motorcycle(String motorName,String motorBrand,String motorCode) {
        this.Name = motorName;
        this.Brand = motorBrand;
        this.Code = motorCode;

    }
    public void setMotorStrength(String motorStrength) {
        this.motorStrength = motorStrength;
    }
    public String getMotorStrength(){
        return motorStrength;
    }

    public void setMotorSpeed(String motorSpeed) {
        this.motorSpeed = motorSpeed;
    }

    public String getMotorSpeed() {
        return motorSpeed;
    }

}
