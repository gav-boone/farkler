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

    public Integer[] roll(int numRolls) {
        // roll number 1-num sides
        Integer[] result = new Integer[numRolls];
        for (int i = 0; i < numRolls; i++) {
            Random random = new Random();
            result[i] = random.nextInt(sides) + 1;
        }
        return result;
    }
}
