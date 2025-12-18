public class Arc {
    private int id;
    private int matrixIndex; 

    private Crossing startNode;
    private Crossing endNode;

    public Arc(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Crossing getStartNode(){
        return this.startNode;
    }
    public Crossing getEndNode(){
        return this.endNode;
    }

    public void setStartNode(Crossing c) {
        this.startNode = c;
    }

    public void setEndNode(Crossing c) {
        this.endNode = c;
    }
   
    public String toString() {
        return "Arc " + id;
    }

    public void setMatrixIndex(int i) { this.matrixIndex = i; }
    public int getMatrixIndex() { return matrixIndex; }
}