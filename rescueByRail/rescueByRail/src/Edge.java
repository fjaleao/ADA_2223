public class Edge {
    private int capacity, origin, destination;

    public Edge(int origin, int destination, int capacity) {
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
