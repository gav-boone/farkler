package farkler;

public class Main {
    public static void main(String[] args) {
        Strategy strat = new HumanStrategy();
        for (int i = 0; i < 1; i++) {
            Farkle game = new Farkle();
            game.play(strat);
        }
    }
}
