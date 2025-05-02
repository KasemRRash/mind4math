

public class EEA {
    public static EEAResult berechneEEA(int a, int b) {
        int x0 = 1, y0 = 0;
        int x1 = 0, y1 = 1;

        while (b != 0) {
            int q = a / b;
            int temp = a % b;
            a = b;
            b = temp;

            int tempX = x1;
            x1 = x0 - q * x1;
            x0 = tempX;

            int tempY = y1;
            y1 = y0 - q * y1;
            y0 = tempY;
        }
        return new EEAResult(a, x0, y0);
    }
}
