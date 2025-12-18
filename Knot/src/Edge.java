public class Edge {
    int dest; 
    int weight;

    public Edge(int d, int w) {
        dest = d;
        weight = w;
    }

    public int getWeight() {
        return this.weight;
    }
    public int getDest() {
        return this.dest;
    }
}
