package farkler;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Hello world!
 */
public class Farkle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nNumber of dice (0 to quit): ");
            int numDie = scanner.nextInt();

            if (numDie <= 0)
                break;

            if (numDie > 6) {
                System.out.print("Must be less than 6.");
                continue;
            }

            Die[] dice = new Die[numDie];
            Die die = new Die(6); // farkle uses d6s
            Arrays.fill(dice, die);

            System.out.printf("%s \n", Arrays.toString(Roll.roll(dice)));
        }
        scanner.close();
    }
}
