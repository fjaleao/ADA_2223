import java.util.Objects;

public class Region {
    private int population, departure, id;

    public Region(int population, int departure, int id) {
        this.population = population;
        this.departure = departure;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public int getPopulation() {
        return population;
    }

    public int getDeparture() {
        return departure;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public boolean isEvacuated() {
        return this.population == 0;
    }

    // public boolean isFull

}
