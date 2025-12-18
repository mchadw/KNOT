public class Runner {
    public static void main(String[] args) {

        KnotGraph trefoil = new KnotGraph();
        trefoil.addCrossing(1, 1, 3, 2);
        trefoil.addCrossing(2, 3, 2, 1);
        trefoil.addCrossing(3, 2, 1, 3);
       
        performAnalysis("Trefoil Test", trefoil);

        KnotGraph fig8 = new KnotGraph();
        fig8.addCrossing(1, 3, 1, 2);
        fig8.addCrossing(2, 4, 2, 3);
        fig8.addCrossing(3, 1, 3, 4);
        fig8.addCrossing(4, 2, 4, 1);

        performAnalysis("Figure-Eight Test", fig8);
    }

    public static void performAnalysis(String testName, KnotGraph knot) {
        System.out.println(">>> RUNNING: " + testName + " <<<");

        String p1Status = knot.processGraph();
        System.out.println("[Phase I] " + p1Status);

        //math + logic:
        // Calculates the Matrix and Determinant
        KnotCalculator calc = new KnotCalculator();
        String mathResults = calc.analyze(knot);
        System.out.println("[Phase II] Math Results:");
        System.out.println(mathResults);

        // identification:
        //extract the det from the list of other info
        long det = extractDeterminant(mathResults);
       
        //get the order of the knot
        int order = knot.getCrossings().size();

        //check the library
        String identity = KnotLibrary.identify(order, det);
       
        System.out.println("[Phase III] Identification:");
        System.out.println("Matches: " + identity);
        
        System.out.println("========================================== \n");
    }

    private static long extractDeterminant(String result) {
        try { //try catch in case a determinant wasn't calculated correctly / at all
            String[] lines = result.split("\n");
            for (String line : lines) {
                if (line.trim().startsWith("Knot Determinant:")) {
                    // split by : as delimeter, just extract the part after
                    String val = line.split(":")[1].trim();
                    return Long.parseLong(val);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't find determinant");
        }
        return -1;
    }
}