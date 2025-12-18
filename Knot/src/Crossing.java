public class Crossing {
    private int id;
   
    private Arc overStrand;
   
    private Arc underStrand1;
    private Arc underStrand2;

    public Crossing(int id, Arc over, Arc under1, Arc under2) {
        this.id = id;
        this.overStrand = over;
        this.underStrand1 = under1;
        this.underStrand2 = under2;
    }
   
    public int[] getEquationRow(int totalArcs) { //returns the linear relation (2x-y-z=0)
            int[] row = new int[totalArcs];

    row[this.overStrand.getMatrixIndex()] += 2; //x in our eq.
    row[this.underStrand1.getMatrixIndex()] -= 1; //y
    row[this.underStrand2.getMatrixIndex()] -= 1; //z

    //have to do += to handle loops, things that may appear to be knots
    //but can actually easily be untied

    //if a strand goes over, but twists under the same crossing,
    //you can imagine we don't actually have a knot there

    return row;
    }
   
    public int getId() { return id; }
    public Arc getOverStrand() { return overStrand; }
    public Arc getUnderStrand1() { return underStrand1; }
    public Arc getUnderStrand2() { return underStrand2; }
   
    public String toString() {
        return "Crossing " + id + " [Over=" + overStrand.getId() +", Unders=" + underStrand1.getId() + "," + underStrand2.getId() + "]";
    }
}