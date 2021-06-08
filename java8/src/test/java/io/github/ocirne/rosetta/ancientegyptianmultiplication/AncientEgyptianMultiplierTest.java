package io.github.ocirne.rosetta.ancientegyptianmultiplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AncientEgyptianMultiplierTest {

    @Test
    public void canMultiply() {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                int actual = AncientEgyptianMultiplier.mul(x, y);
                assertEquals(x*y, actual);
            }
        }
    }
}