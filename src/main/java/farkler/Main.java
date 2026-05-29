package farkler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        int ROUNDS_TO_PLAY = 100000;
        Map<String, int[]> allScores = new LinkedHashMap<>();

        allScores.put("Naive", runStrategy("Naive", new NaiveStrategy(), ROUNDS_TO_PLAY));
        allScores.put("Naive2", runStrategy("Naive2", new Naive2Strategy(), ROUNDS_TO_PLAY));

        Stats.saveCdfComparison(allScores, "strategy_comparison_cdf");
        System.out.println("Saved strategy_comparison_cdf.png");
    }

    private static int[] runStrategy(String name, Strategy strat, int rounds) {
        int[] scores = new int[rounds];
        for (int i = 0; i < rounds; i++) {
            scores[i] = new Farkle().play(strat);
        }
        Stats stats = new Stats(scores);
        System.out.printf("%s Strategy | Mean: %.1f | Q1: %d | Median: %d | Q3: %d | Max: %d | Farkle Rate: %.2f%%\n",
                name, stats.mean(), stats.quartile(1), stats.quartile(2), stats.quartile(3), stats.max(),
                stats.farkleRate() * 100);
        return scores;
    }
}
