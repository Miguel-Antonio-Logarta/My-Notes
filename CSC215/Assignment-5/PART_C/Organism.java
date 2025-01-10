import java.util.ArrayList;

public class Organism {
    protected String speciesName;
    protected String commonName;
    protected String individualName;
    protected int nutritionLevel;

    ArrayList<String> log;

    public Organism(String speciesName, String commonName, String individualName, int nutritionLevel) {
        this.speciesName = speciesName;
        this.commonName = commonName;
        this.individualName = individualName;
        this.nutritionLevel = nutritionLevel;
        this.log = new ArrayList<>();
    }

    public void addToLog(String description) {
        this.log.add(description);
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getIndividualName() {
        return individualName;
    }

    public void setIndividualName(String individualName) {
        this.individualName = individualName;
    }
}
