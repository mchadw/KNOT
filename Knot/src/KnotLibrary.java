public class KnotLibrary {
    public static String identify(int n, long det) {
        if (det == 1) return "Unknot (0_1)";
        if (det == 0) return "Link / Invalid";

        if (n == 3 && det == 3) return "Trefoil Knot (3_1)";

        if (n == 4 && det == 5) return "Figure-Eight Knot (4_1)";

        if (n == 5) {
            if (det == 5) return "Cinque Foil (5_1)";
            if (det == 7) return "Three-Twist Knot (5_2)";
        }

        if (n == 6) {
            if (det == 9)  return "Stevedore Knot (6_1)";
            if (det == 11) return "6_2 Knot";
            if (det == 13) return "6_3 Knot";
        }
       
        //for unknown nots with n>9 or just has no name
        return "Unknown Knot (Order " + n + ", Det " + det + ")";
    }
}