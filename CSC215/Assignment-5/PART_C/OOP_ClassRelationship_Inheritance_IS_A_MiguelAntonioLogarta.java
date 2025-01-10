public class OOP_ClassRelationship_Inheritance_IS_A_MiguelAntonioLogarta {
    public static void main(String[] args) {
        // 3 rabbits, 1 wolf, 2 humans, 1 plant
        Plant carrot = new Plant("Daucus carota", "Carrot", "Patch of Carrots", 10);
        Herbivore rabbit1 = new Herbivore("Oryctolagus cuniculus", "Wild European Rabbit", "rabbit1", 2, 5, 3, 5, 3);
        Herbivore rabbit2 = new Herbivore("Oryctolagus cuniculus", "Wild European Rabbit", "rabbit2", 2, 5, 3, 5, 3);
        Herbivore rabbit3 = new Herbivore("Oryctolagus cuniculus", "Wild European Rabbit", "rabbit3", 0, 5, 3, 5, 3);
        Carnivore wolf1 = new Carnivore("Canis lupus", "Grey Wolf", "wolf1", 2, 20, 10, 20, 10);
        Omnivore human1 = new Omnivore("Homo Sapiens", "Human", "Jean", 2, 20, 10, 20, 10);
        Omnivore human2 = new Omnivore("Homo Sapiens", "Human", "Elizabeth", 2, 20, 10, 20, 10);

        System.out.println("SIMULATION START");
        rabbit3.eat(carrot);

        wolf1.attack(rabbit1, 5);
        wolf1.eat(rabbit1);

        wolf1.attack(rabbit2, 3);
        wolf1.eat(rabbit2);
        wolf1.attack(rabbit2, 3);
        wolf1.eat(rabbit2);

        human1.attack(wolf1, 10);
        human1.attack(wolf1, 10);

        human2.attack(rabbit3, 10);
        human2.eat(rabbit3);

        System.out.println();
        System.out.println("Here is the status of all the organisms:");
        System.out.println(rabbit1.getStatus());
        System.out.println(rabbit2.getStatus());
        System.out.println(rabbit3.getStatus());
        System.out.println(wolf1.getStatus());
        System.out.println(human1.getStatus());
        System.out.println(human2.getStatus());
    }
}
