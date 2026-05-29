package farkler;

import java.util.HashMap;
import java.util.Map;

public class Farkle {
    private static int SCORE;
    private static Integer[] roll;
    private static Integer[] indexes;

    public int play(Strategy strat) {
        SCORE = 0;
        int numDice = 6;
        int numDiceToTake = 0;
        Die d6 = new Die(6); // farkle uses d6s
        boolean roll_again = true;
        while (true) {
            roll = d6.roll(numDice);
            indexes = strat.chooseDice(roll, SCORE);
            if (indexes == null || indexes.length == 0)
                break;
            handleScore();
            numDiceToTake = indexes.length;
            numDice -= numDiceToTake;
            if (numDice <= 0)
                numDice = 6;
            roll_again = strat.rollAgain(SCORE, numDice);
            if (!roll_again)
                break;
        }
        return SCORE;
    }

    private static void handleScore() {
        if (indexes.length >= 4) {
            handleBigCombos();
        }

        if (indexes.length >= 3) {
            handleTriples();
        }

        for (int idx : indexes) {
            if (roll[idx] == 1)
                SCORE += 100;
            if (roll[idx] == 5)
                SCORE += 50;
        }
    }

    private static void handleBigCombos() {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int val : roll) {
            counts.merge(val, 1, Integer::sum);
        }

        Map<Integer, Integer> straight = Map.of(
                1, 1,
                2, 1,
                3, 1,
                4, 1,
                5, 1,
                6, 1);
        if (counts.equals(straight)) {
            SCORE += 1500;
            roll = new Integer[] { 0, 0, 0, 0, 0, 0 };
            return;
        }

        int triplets = 0;
        for (int v : counts.values()) {
            if (v == 3)
                triplets++;
        }
        if (triplets == 2) {
            SCORE += 2500;
            roll = new Integer[] { 0, 0, 0, 0, 0, 0 };
            return;
        }

        int pairs = 0;
        for (int v : counts.values()) {
            if (v == 2)
                pairs++;
        }
        if (pairs == 3) {
            SCORE += 1500;
            roll = new Integer[] { 0, 0, 0, 0, 0, 0 };
            return;
        }

        if (counts.values().contains(4) && counts.values().contains(2)) {
            SCORE += 1500;
            roll = new Integer[] { 0, 0, 0, 0, 0, 0 };
            return;
        }

        if (counts.containsValue(6)) {
            SCORE += 3000;
            roll = new Integer[] { 0, 0, 0, 0, 0, 0 };
            return;
        }

        if (counts.containsValue(5)) {
            SCORE += 2000;
            int key = 0;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue().equals(5)) {
                    key = entry.getKey();
                    break;
                }
            }
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == key)
                    roll[indexes[i]] = 0;
            }
            return;
        }

        if (counts.containsValue(4)) {
            SCORE += 1000;
            int key = 0;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue().equals(4)) {
                    key = entry.getKey();
                    break;
                }
            }
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == key)
                    roll[indexes[i]] = 0;
            }
            return;
        }
    }

    private static void handleTriples() {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int val : roll) {
            counts.merge(val, 1, Integer::sum);
        }

        if (counts.getOrDefault(1, 0) == 3) {
            SCORE += 300;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 1)
                    roll[indexes[i]] = 0;
            }
        }
        if (counts.getOrDefault(2, 0) == 3) {
            SCORE += 200;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 2)
                    roll[indexes[i]] = 0;
            }
        }
        if (counts.getOrDefault(3, 0) == 3) {
            SCORE += 300;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 3)
                    roll[indexes[i]] = 0;
            }
        }
        if (counts.getOrDefault(4, 0) == 3) {
            SCORE += 400;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 4)
                    roll[indexes[i]] = 0;
            }
        }
        if (counts.getOrDefault(5, 0) == 3) {
            SCORE += 500;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 5)
                    roll[indexes[i]] = 0;
            }
        }
        if (counts.getOrDefault(6, 0) == 3) {
            SCORE += 600;
            for (int i = 0; i < indexes.length; i++) {
                if (roll[indexes[i]] == 6)
                    roll[indexes[i]] = 0;
            }
        }
    }
}
