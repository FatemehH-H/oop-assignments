public class Vehicle {

    protected String Name;
    protected String Price;
    protected String Brand;
    protected String Color;
    protected String Origin;
//    protected String carStrength;
//    protected String carSpeed;
    protected String State;
    protected String Code;


        public Vehicle(String Name,String Brand,String Code) {
            this.Name = Name;
            this.Brand = Brand;
            this.Code = Code;

        }

    public Vehicle() {

    }

    public String getCode() {
            return Code;
        }

        public void setColor(String carColor) {
            this.Color = carColor;
        }
        public String getColor(){
            return Color;
        }

        public void setOrigin(String carOrigin) {
            this.Origin = carOrigin;
        }
        public String getOrigin(){
            return Origin;
        }

//        public void setCarStrength(String carStrength) {
//            this.carStrength = carStrength;
//        }
//        public String getCarStrength(){
//            return carStrength;
//        }
//
//        public void setCarSpeed(String carSpeed) {
//            this.carSpeed = carSpeed;
//        }
//
//        public String getCarSpeed() {
//            return carSpeed;
//        }

        public void setPrice(String carPrice) {
            this.Price = carPrice;
        }

        public String getPrice() {
            return Price;
        }

        public void setState(String carState) {
            this.State = carState;
        }

        public String getState() {
            return State;
        }




}
