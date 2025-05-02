
public class KGV {
    public static int berechneKGV(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int ggt = GGT.berechneGGT(a, b);
        return Math.abs(a * b) / ggt;
    }
}