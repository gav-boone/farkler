package farkler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int ROUNDS_TO_PLAY = 100000;

        runStrategy("Naive", new NaiveStrategy(), ROUNDS_TO_PLAY);
    }

    private static void runStrategy(String name, Strategy strat, int rounds) throws IOException {
        int[] scores = new int[rounds];
        for (int i = 0; i < rounds; i++) {
            scores[i] = new Farkle().play(strat);
        }
        Stats stats = new Stats(scores);
        System.out.printf("%s Strategy:\n  Mean: %.1f | Q1: %d | Median: %d | Q3: %d | Max: %d | Farkle Rate: %.2f%%\n",
                name, stats.mean(), stats.quartile(1), stats.quartile(2), stats.quartile(3), stats.max(),
                stats.farkleRate() * 100);

        stats.saveCdf(name);
        System.out.printf("  Saved %s_cdf.png\n", name);
    }
}
