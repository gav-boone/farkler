package farkler;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Hello world!
 */
public class Farkle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        int numDice = 6;
        int numDiceToTake = -1;
        while (true) {
            if (numDiceToTake == 0) {
                System.out.printf("SCORE: %d \n", score);
                break;
            }

            if (numDiceToTake > 6) {
                System.out.print("Must be less than 6.");
                continue;
            }

            Die[] dice = new Die[numDice];
            Die die = new Die(6); // farkle uses d6s
            Arrays.fill(dice, die);

            System.out.printf("%s \n", Arrays.toString(Roll.roll(dice)));

            System.out.print("Which dice to take? (how many, then indexes): ");
            numDiceToTake = scanner.nextInt();
            for (int i = 0; i < numDiceToTake; i++) {
                int idx = scanner.nextInt();
                if (dice[idx].getValue() == 1)
                    score += 100;
                if (dice[idx].getValue() == 5)
                    score += 50;
            }
            numDice -= numDiceToTake;
        }
        scanner.close();
    }
}
