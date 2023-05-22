public class Edge {
    private int flow, capacity, destination;

    public Edge(int destination, int capacity) {
        this.flow = 0;
        this.capacity = capacity;
        this.destination = destination;
    }

    public int getFlow() {
        return flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDestination() {
        return destination;
    }

    public void incrementFlow(int value) {
        this.flow += value;
    }


}
