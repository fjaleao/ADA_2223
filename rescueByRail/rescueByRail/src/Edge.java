public class Edge {
    private int capacity, destination;

    public Edge(int destination, int capacity) {
        this.capacity = capacity;
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDestination() {
        return destination;
    }


}
