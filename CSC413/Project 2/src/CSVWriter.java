import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class CSVWriter implements Observer {
	private Subject simulator;
	private String filename;
	
	public void createFile(String filename) {
		// Creates a file and inserts first row containing column titles
		this.filename = filename;
		try {
			File fileHandle = new File(filename);
	      	if (fileHandle.createNewFile()) {
	      		System.out.println("File created: " + fileHandle.getName());
	      		boolean append = true;
	            try(FileWriter writer = new FileWriter(this.filename, append)){
	                writer.append("time,height,velocity\n");
	            } catch (IOException e) {
	            	System.out.println("Something went wrong writing to the file");
	            }
		    } else {
		    	System.out.println("File already exists.");
		    }	
	    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	    }
	}
	
	@Override
	public void update() {
		// When simulation updates, record new data and store it into an output file
		
		SimulationData data = (SimulationData)simulator.getUpdate(this);
		DecimalFormat df = new DecimalFormat("#.##");
        
        // Format the number using the DecimalFormat object
        String formattedTime = df.format(data.time);
        String formattedHeight = df.format(data.height);
        String formattedVelocity = df.format(data.velocity);
        
        // Write the strings into the CSV file
        boolean append = true;
        try(FileWriter writer = new FileWriter(this.filename, append)){
            writer.append(formattedTime + "," + formattedHeight + "," + formattedVelocity + "\n");
        } catch (IOException e) {
        	System.out.println("Something went wrong writing to the file");
        }
	}
	
	@Override
	public void setSubject(Subject sub) {
		this.simulator = sub;
	}
	
	// Getters
	public String getFilename() {
		return filename;
	}
	
	// Setters
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
