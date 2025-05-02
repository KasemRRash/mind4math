
public class MatheFunktionen {

    public static int fibonacciIterativ(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }

    public static int fibonacciRekursiv(int n) {
        if (n <= 1) return n;
        return fibonacciRekursiv(n - 1) + fibonacciRekursiv(n - 2);
    }

    public static long fakultaetIterativ(int n) {
        long ergebnis = 1;
        for (int i = 2; i <= n; i++) {
            ergebnis *= i;
        }
        return ergebnis;
    }

    public static long fakultaetRekursiv(int n) {
        if (n <= 1) return 1;
        return n * fakultaetRekursiv(n - 1);
    }
}
