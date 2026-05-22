package farkler;

import java.util.Random;

public class Die {
    private final int sides;

    public Die(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public int roll() {
        // roll number 1-num sides
        Random random = new Random();
        return random.nextInt(sides) + 1;
    }
}
