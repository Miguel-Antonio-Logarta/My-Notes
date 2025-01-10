public class Omnivore extends Animal {
    Omnivore(String speciesName, String commonName, String individualName, int hungerLevel, double healthLevel, int nutritionLevel, double maxHealth, double maxHunger) {
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

    void eat(Plant plant) {
        double oldHungerLevel = this.hungerLevel;

        if (this.hungerLevel + plant.nutritionLevel > this.maxHunger) {
            this.hungerLevel = this.maxHunger;
        } else {
            this.hungerLevel += plant.nutritionLevel;
        }

        System.out.printf("%s ate %s. Hunger level restored from %.1f/%.1f to %.1f/%.1f\n",
                this.individualName, plant.getIndividualName(),
                oldHungerLevel, this.maxHunger,
                this.hungerLevel, this.maxHunger);
    }
}
