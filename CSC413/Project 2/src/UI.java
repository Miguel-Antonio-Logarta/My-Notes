import java.util.Scanner;  // Import the Scanner class
import java.text.DecimalFormat;

public class UI implements Observer {
	
	private Subject simulator;
	
	public double promptUser() {
		// Prompt user to set an initial drop height for the object
		
		double initialHeight = 0;
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		
		System.out.println("Please enter simulation parameters");	// Prompt user
		System.out.println("Initial Height (in meters): ");
		
		initialHeight = input.nextDouble();  // Read user input. Decimals allowed
	    input.close();
	    
	    return initialHeight;
	}
	
	public void promptEnteringSimulation() {
		System.out.println("Simulating...");
	}
	
	public void promptWriteToFile(String filename) {
		System.out.println("Writing to " + filename);
	}
	
	public void promptWriteToFileDone() {
		System.out.println("Done.");
	}
	
	@Override
	public void update() {
		// When subject updates, get updated data and output it to the user
		
		SimulationData data = (SimulationData)simulator.getUpdate(this);
		DecimalFormat df = new DecimalFormat("#.##");
        
        // Format the number using the DecimalFormat object
        String formattedTime = df.format(data.time);
        String formattedHeight = df.format(data.height);
        
		System.out.println("t = " + formattedTime + " h = " + formattedHeight);		
	}
	
	@Override
	public void setSubject(Subject sub) {
		this.simulator = sub;
	}
}
