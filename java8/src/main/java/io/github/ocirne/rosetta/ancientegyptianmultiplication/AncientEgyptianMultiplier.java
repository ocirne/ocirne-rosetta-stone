package io.github.ocirne.rosetta.ancientegyptianmultiplication;

public class AncientEgyptianMultiplier {

    public static int mul(int x, int y) {
        int result = 0;
        while (x > 0) {
            if (x % 2 != 0) {
                result += y;
            }
            x /= 2;
            y *= 2;
        }
        return result;
    }
}
