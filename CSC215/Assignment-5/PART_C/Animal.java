class Animal extends Organism {

    protected double hungerLevel;
    protected double healthLevel;
    protected double maxHealth;
    protected double maxHunger;

    Animal(String speciesName, String commonName, String individualName, int hungerLevel, double healthLevel, int nutritionLevel, double maxHealth, double maxHunger) {
        super(speciesName, commonName, individualName, nutritionLevel);
        this.hungerLevel = hungerLevel;
        this.healthLevel = healthLevel;
        this.maxHealth = maxHealth;
        this.maxHunger = maxHunger;
    }

    public String getStatus() {
        return "Status of " + individualName + ":\n" +
                "Species: " + speciesName + " (" + commonName + ")\n" +
                "Health: " + healthLevel + "/" + maxHealth + " (" + getHealthStatus() + ")\n" +
                "Hunger: " + hungerLevel + "/" + maxHunger + " (" + getHungerStatus() + ")\n";
    }

    void attack(Animal target, double damage) {
        double oldHealth = target.getHealthLevel();
        target.setHealthLevel(oldHealth - damage);
        if (target.getHealthLevel() <= 0) {
            target.setHealthLevel(0);
            System.out.printf("%s attacked %s. %s is dead\n", this.individualName, target.getIndividualName(), target.getIndividualName());
        } else {
            System.out.printf("%s attacked %s. %s's health went from %.1f to %.1f\n", this.individualName, target.getIndividualName(), target.getIndividualName(), oldHealth, target.healthLevel);

        }
    }

    public String getHealthStatus() {
        if (this.healthLevel >= 0.8 * this.maxHealth) {
            return "Healthy";
        } else if (this.healthLevel >= 0.5 * this.maxHealth) {
            return "Injured";
        } else if (this.healthLevel > 0.0) {
            return "Severely Injured";
        } else {
            return "Dead";
        }
    }

    public String getHungerStatus() {
        if (this.hungerLevel >= 0.8 * this.maxHunger) {
            return "Full";
        } else if (this.hungerLevel >= 0.5 * this.maxHunger) {
            return "Slightly Hungry";
        } else if (this.hungerLevel >= 0.0) {
            return "Hungry";
        } else {
            return "Starving";
        }
    }

    public double getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(double hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public double getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(double healthLevel) {
        this.healthLevel = healthLevel;
    }
}
