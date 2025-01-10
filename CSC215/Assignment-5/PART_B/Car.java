public class Car {
    private String make;
    private String model;
    private int year;

    private Engine engine;
    private Wheels frontWheels;
    private Wheels rearWheels;
    private Suspension frontSuspension;
    private Suspension rearSuspension;

    Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getName() {
        return year + " " + make + " " + model;
    }

    public void startCar() {
        System.out.printf("%s %s %d started. It makes a noise of a V%d\n", make, model, year, engine.getCylinders());
    }

    public void printSpecs() {
        System.out.printf("%s %s %d Specs:\n", this.make, this.model, this.year);
        System.out.println(this.engine.getEngineSpecs());
        System.out.println("Front " + this.frontWheels.getWheelsSpecs());
        System.out.println("Rear " + this.rearWheels.getWheelsSpecs());
        System.out.println("Front " + this.frontSuspension.getSuspensionSpecs());
        System.out.println("Rear " + this.rearSuspension.getSuspensionSpecs());
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheels[] getWheels() {
        return new Wheels[]{this.frontWheels, this.rearWheels};
    }

    public void setWheels(Wheels front, Wheels rear) {
        this.frontWheels = front;
        this.rearWheels = rear;
    }

    public Suspension[] getSuspension() {
        return new Suspension[]{this.frontSuspension, this.rearSuspension};
    }

    public void setSuspension(Suspension front, Suspension rear) {
        this.frontSuspension = front;
        this.rearSuspension = rear;
    }

}
