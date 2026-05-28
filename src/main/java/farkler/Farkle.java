package farkler;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class Farkle {
    private static int SCORE;
    private static Integer[] roll;
    private static Integer[] indexes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SCORE = 0;
        int numDice = 6;
        int numDiceToTake = 0;
        while (true) {
            Die d6 = new Die(6); // farkle uses d6s

            roll = d6.roll(numDice);

            roll = new Integer[] { 1, 1, 1, 1, 3, 3 };

            System.out.printf("%s \n", Arrays.toString(roll));
            System.out.printf("%d \n", SCORE);
            System.out.print("How many dice to take (0 if farkle): ");
            numDiceToTake = scanner.nextInt();

            if (numDiceToTake == 0) {
                System.out.printf("!!!!FARKLE!!!!\nSCORE: %d \n", SCORE);
                break;
            }

            if (numDiceToTake > 6) {
                System.out.print("Must be less than 6.");
                continue;
            }

            if (numDiceToTake == 6) {
                indexes = new Integer[] { 0, 1, 2, 3, 4, 5 };
            } else {
                indexes = new Integer[numDiceToTake];
                for (int i = 0; i < numDiceToTake; i++) {
                    System.out.print("Index to take (1-6): ");
                    indexes[i] = scanner.nextInt() - 1;
                }
            }

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

            numDice -= numDiceToTake;
        }
        scanner.close();
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
