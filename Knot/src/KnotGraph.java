import java.util.ArrayList;
import java.util.List;

public class KnotGraph {
    private List<Arc> arcs;
    private List<Crossing> crossings;

    public KnotGraph() {
        this.arcs = new ArrayList<>();
        this.crossings = new ArrayList<>();
    }

    public Arc getOrCreateArc(int id) {
        for (Arc a : arcs) { //if an arc with the same id is already in knot
            if (a.getId() == id) return a;
        }
        Arc newArc = new Arc(id);
        arcs.add(newArc);
        return newArc;
    }

    public void addCrossing(int id, int overID, int under1ID, int under2ID) {
        Arc over = getOrCreateArc(overID);
        Arc u1 = getOrCreateArc(under1ID);
        Arc u2 = getOrCreateArc(under2ID);

        Crossing c = new Crossing(id, over, u1, u2);
        crossings.add(c);
       
        //is this actually a knot
        if (u1.getStartNode() == null) u1.setStartNode(c);
        else u1.setEndNode(c);
       
        if (u2.getStartNode() == null) u2.setStartNode(c);
        else u2.setEndNode(c);
    }

    public String processGraph() {
        // normalization, in case someone puts in indexes arbitrarily instead of 0 --> n-1 where n = size
        for (int i = 0; i < arcs.size(); i++) {
            arcs.get(i).setMatrixIndex(i);
        }

        //get the order
        int order = crossings.size();
        return "Valid Knot detected. Order: " + order;
    }

    public List<Crossing> getCrossings() {
        return crossings;
    }

    public int getNumArcs() {
        return arcs.size();
    }
}