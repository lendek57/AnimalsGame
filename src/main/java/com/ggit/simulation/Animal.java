package com.ggit.simulation;

public class Animal implements Comparable<Animal> {
    private final int id = counter++;
    private int energy;
    private int age = 0;
    private int noOfChildren = 0;
    private final Genome genome;
    private Vector2D position;
    private static int counter = 0;
    private final int DAY_ENERGY = 5;

    public Animal(Vector2D position, int baseEnergy) {
        this.position = position;
        this.energy = baseEnergy;
        genome = new Genome();
    }

    public Animal(Animal mather, Animal father) {
        energy = (mather.energy + father.energy) / 4;
        genome = new Genome(mather.genome, father.genome);
        position = mather.position.add(MapDirection.random().getUnitVector());
    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public Genome getGenome() {
        return genome;
    }

    public void eat(int plantEnergy) {
        energy += plantEnergy;
    }

    public Animal ageOneDay() {
        age++;
        energy -= DAY_ENERGY;
        return this;
    }

    public Animal reproduce(Animal father) {
        energy = energy * 3 / 4;
        father.energy = father.energy * 3 / 4;
        noOfChildren++;
        father.noOfChildren++;
        Animal child = new Animal(this, father);
        System.out.printf("New child at %s, id = %d\n", child.getPosition(), child.getId());
        return child;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void move(MapDirection direction, WorldMap map) {
        position = pbc(position.add(direction.getUnitVector()), map);
        System.out.printf("Animal %d moved to %s\n", id, position);
    }

    public void moveBasedOnGenome(WorldMap map) {
        move(genome.randomGene(), map);
    }

    @Override
    public int compareTo(Animal animal) {
        return energy == animal.energy ? id - animal.id : energy - animal.energy;
    }

    private Vector2D pbc(Vector2D newPosition, WorldMap map) {
        int x = newPosition.x();
        int y = newPosition.y();

        if (x < 0) return new Vector2D(x + map.getWidth(), y);
        if (y < 0) return new Vector2D(x, y + map.getHeight());
        if (x >= map.getWidth()) return new Vector2D(x - map.getWidth(), y);
        if (y >= map.getHeight()) return new Vector2D(x, y - map.getHeight());
        return newPosition;
    }
}
