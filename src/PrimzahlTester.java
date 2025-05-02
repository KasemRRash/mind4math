public class PrimzahlTester {
    public static boolean istPrimzahl(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int wurzel = (int) Math.sqrt(n);
        for (int i = 3; i <= wurzel; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}