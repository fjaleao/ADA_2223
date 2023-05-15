public class Region {
    private int population, departure;

    public Region(int population, int departure) {
        this.population = population;
        this.departure = departure;
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
}
