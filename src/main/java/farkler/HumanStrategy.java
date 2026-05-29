package farkler;

import java.util.Arrays;
import java.util.Scanner;

public class HumanStrategy implements Strategy {
    private final Scanner scanner = new Scanner(System.in);

    public Integer[] chooseDice(Integer[] roll, int current_score) {
        int numDiceToTake;
        Integer[] indexes;

        while (true) {
            System.out.printf("%s \n", Arrays.toString(roll));
            System.out.printf("%d \n", current_score);
            System.out.print("How many dice to take (0 if farkle): ");
            numDiceToTake = scanner.nextInt();

            if (numDiceToTake == 0) {
                current_score = 0;
                System.out.printf("!!!!FARKLE!!!!\nSCORE: %d \n", current_score);
                indexes = new Integer[] {};
                break;
            }

            if (numDiceToTake > roll.length) {
                System.out.print("Must be less than dice in roll.");
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
            break;
        }

        return indexes;
    }

    public boolean rollAgain(int current_score, int diceRemaining) {
        boolean roll_again;
        System.out.printf("Dice Remaining: %d\nScore: %d\nRoll again?: ", diceRemaining, current_score);
        roll_again = scanner.next().equals("y");

        if (!roll_again)
            System.out.printf("YOU WIN!!!\nScore: %d\n", current_score);

        return roll_again;
    }
}
