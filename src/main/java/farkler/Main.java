package farkler;

public class Main {
    public static void main(String[] args) {
        int ROUNDS_TO_PLAY = 3;
        Strategy strat = new HumanStrategy();
        int[] scores = new int[ROUNDS_TO_PLAY];
        for (int i = 0; i < ROUNDS_TO_PLAY; i++) {
            Farkle game = new Farkle();
            scores[i] = game.play(strat);
        }
        Stats stats = new Stats(scores);
        System.out.printf("Mean: %.1f\nQ1: %d\nMedian: %d\nQ3: %d\nMax: %d\nFarkle Rate: %.2f%%\n",
                stats.mean(), stats.quartile(1), stats.quartile(2), stats.quartile(3), stats.max(),
                stats.farkleRate() * 100);
    }
}
