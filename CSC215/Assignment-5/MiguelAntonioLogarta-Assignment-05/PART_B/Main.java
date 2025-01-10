public class Main {
    public static Car makeANiceCar() {
        Car fordShelbyGT350 = new Car("Ford", "Shelby GT350", 2020);

        Engine engine = new Engine(5.2, 8, "Premium Unleaded", 526);

        Wheels frontWheels = new Wheels("P295/35ZR19", "Michelin Pilot Sport Cup 2", "Summer");
        Wheels rearWheels = new Wheels("P305/35ZR19", "Michelin Pilot Sport Cup 2", "Summer");

        Suspension frontSuspension = new Suspension("MagneRide Suspension", "Adaptive", "Strut", "RWD");
        Suspension rearSuspension = new Suspension("MagneRide Suspension", "Adaptive", "Multi-Link", "RWD");

        fordShelbyGT350.setEngine(engine);
        fordShelbyGT350.setWheels(frontWheels, rearWheels);
        fordShelbyGT350.setSuspension(frontSuspension, rearSuspension);

        return fordShelbyGT350;
    }

    public static void main(String[] args) {
        Car myNiceCar = makeANiceCar();

        System.out.printf("Check out my nice car! It's a %s! Pretty sweet right?\n", myNiceCar.getName());
        myNiceCar.printSpecs();
        myNiceCar.startCar();
    }
}