package farkler;

import java.util.Random;

public class Die {
    private final int sides;
    private int value;

    public Die(int sides) {
        this.sides = sides;
        this.value = 0;
    }

    public int getSides() {
        return sides;
    }

    public int getValue() {
        return value;
    }

    public int roll() {
        // roll number 1-num sides
        Random random = new Random();
        int result = random.nextInt(sides) + 1;
        this.value = result;
        return result;
    }
}
