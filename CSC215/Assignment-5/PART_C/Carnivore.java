public class Carnivore extends Animal {
    Carnivore(String speciesName, String commonName, String individualName, int hungerLevel, double healthLevel, int nutritionLevel, double maxHealth, double maxHunger) {
        super(speciesName, commonName, individualName, hungerLevel, healthLevel, nutritionLevel, maxHealth, maxHunger);
    }

    void eat(Animal prey) {
        if (prey.getHealthLevel() > 0) {
            System.out.printf("%s cannot eat %s because %s is not dead\n", this.individualName, prey.getIndividualName(), prey.getIndividualName());
            return;
        }

        double oldHungerLevel = this.hungerLevel;

        if (this.hungerLevel + prey.nutritionLevel > this.maxHunger) {
            this.hungerLevel = this.maxHunger;
        } else {
            this.hungerLevel += prey.nutritionLevel;
        }

        System.out.printf("%s ate %s. Hunger level restored from %.1f/%.1f to %.1f/%.1f\n",
                this.individualName, prey.getIndividualName(),
                oldHungerLevel, this.maxHunger,
                this.hungerLevel, this.maxHunger);
    }
}
