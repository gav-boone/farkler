package farkler;

public interface Strategy {
    Integer[] chooseDice(Integer[] roll, int current_score);

    boolean rollAgain(int current_score, int diceRemaining);
}
