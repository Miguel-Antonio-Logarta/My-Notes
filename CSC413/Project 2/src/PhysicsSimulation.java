
public class PhysicsSimulation {
	public static void main(String[] args) {		
		// Classes
		// UI, Simulator, CSVWriter, SimulationData
		
		// Interfaces
		// Observer, Subject
		
		// This Homework uses the Observer pattern to 
		// Record data for a physics project
		// The UI and CSVWriter observes the simulation in real time and 
		// records data into a CSV file. 
		// When the simulation goes forward in time by timeSteps, it notifies
		// Its observers to make an update and sends new data. This data is then
		// Recorded
		
		// Instantiate all the objects and variables
		double initialHeight;
		String dataOutputFile = "datasimulation.csv";
		UI simulationUI = new UI();
		CSVWriter dataWriter = new CSVWriter();
		PhysicsSimulator simulation = new PhysicsSimulator();
		
		
		// Make UI and CSV writer subscribe to the simulator
		simulationUI.setSubject(simulation);
		dataWriter.setSubject(simulation);
		
		// Register observers so simulation can alert observers when state changes
		simulation.register(simulationUI);
		simulation.register(dataWriter);
		
		// Take in input and initialize data writer
		initialHeight = simulationUI.promptUser();
		dataWriter.createFile(dataOutputFile);
		
		// Setup the simulator then start
		simulationUI.promptEnteringSimulation();
		simulation.setInitialHeight(initialHeight);
		simulation.setTimeStep(0.1);
		
		// Whenever the simulator does an update,
		// We output the current data to a file
		// And we output the variables through the UI
		simulation.runSimulation();
		
		// Tell user we saved results to a file
		simulationUI.promptWriteToFile(dataOutputFile);
		simulationUI.promptWriteToFileDone();
		
		// Close the program
	}
}
