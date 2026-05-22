package farkler;

public class Roll {

    public Roll() {
    }

    public static Integer[] roll(Die[] dice) {
        Integer[] results = new Integer[dice.length];
        for (int i = 0; i < dice.length; i++) {
            Die die = dice[i];
            results[i] = die.roll();
        }
        return results;
    }
}
