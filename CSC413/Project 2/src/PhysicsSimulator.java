import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

// This class will use the observer pattern.
// When the simulation runs the next step, it will alert all objects subscribed to it
public class PhysicsSimulator implements Subject {
	private double initialHeight = 0;
	private double height = 0;
	private double time = 0;
	private double velocity = 0;
	private double gravity = 9.81;
	private double timeStep = 0;
	
	private List<Observer> observers;
	
	public PhysicsSimulator() {
		this.observers = new ArrayList<>();
	}
	
	public void GoToNextTimeStep() {
		// Calculates the result of the simulation after timeStep seconds
		// y(t) = y_i - (1/2)*gt^2
		this.time += this.timeStep;
		this.height = this.height - (1.0/2.0)*this.gravity*Math.pow(time, 2);
	}
	
	public void runSimulation() {
		this.height = this.initialHeight;
		
		// Notify observers that simulation is starting
		notifyObservers();
		
		while (this.height > 0) {
			GoToNextTimeStep();
			
			// If height results in zero, calculate the time when height was zero
			// Set it to zero for nice numbers
			if (this.height < 0) {
				this.height = 0;
				this.time = Math.sqrt(2*this.initialHeight/this.gravity);
			}
			
			// Send new data to observers
			notifyObservers();
		}
	}
	
	@Override
	public void register(Observer obj) {
		if(obj != null && !this.observers.contains(obj)) {
			this.observers.add(obj);
		}
	}
	
	@Override
	public void unregister(Observer obj) {
		this.observers.remove(obj);
	}
	
	@Override 
	public void notifyObservers() {
		for (Observer obj : this.observers) {
			obj.update();
		}
	}
	
	@Override
	public Object getUpdate(Observer obj) {
		// Return a message to observers that contains updated data
		SimulationData data = new SimulationData();
		data.height = this.height;
		data.time = this.time;
		data.velocity = this.velocity;
		data.gravity = this.gravity;
		data.timeStep = this.timeStep;
		return data;
	}
	
	// Getters
	double getInitialHeight() {
		return initialHeight;
	}
	
	double getHeight() {
		return height;
	}
	
	double getTime() {
		return time;
	}
	
	double getGravity() {
		return gravity;
	}
	
	double getTimeStep() {
		return timeStep;
	}
	
	double getVelocity() {
		return velocity;
	}
	
	// Setters
	void setInitialHeight(double initialHeight) {
		this.initialHeight = initialHeight;
	}
	
	void setHeight(double newHeight) {
		this.height = newHeight;
	}
	
	void setTime(double newTime) {
		this.time = newTime;
	}
	
	void setGravity(double newGravity) {
		this.gravity = newGravity;
	}
	
	void setTimeStep(double newTimeStep) {
		this.timeStep = newTimeStep;
	}
	
	void setVelocity(double newVelocity) {
		this.velocity = newVelocity;
	}
}
