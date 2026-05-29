package farkler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Naive2Strategy implements Strategy {
    
    public Integer[] chooseDice(Integer[] roll, int current_score) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int val : roll) {
            counts.merge(val, 1, Integer::sum);
        }

        // Check for big combos that use all 6 dice
        if (roll.length == 6 && isSixDiceCombo(counts)) {
            return new Integer[] { 0, 1, 2, 3, 4, 5 };
        }

        List<Integer> taken = new ArrayList<>();

        // Take triples (and above)
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= 3) {
                int count = 0;
                for (int i = 0; i < roll.length; i++) {
                    if (roll[i] == entry.getKey() && count < entry.getValue()) {
                        taken.add(i);
                        count++;
                    }
                }
            }
        }

        // Take remaining 1s and 5s not already taken by triples
        for (int i = 0; i < roll.length; i++) {
            if (taken.contains(i)) continue;
            if (roll[i] == 1 || roll[i] == 5) {
                taken.add(i);
            }
        }

        return taken.toArray(new Integer[0]);
    }

    public boolean rollAgain(int current_score, int diceRemaining) {
        return diceRemaining > 2;
    }

    private boolean isSixDiceCombo(Map<Integer, Integer> counts) {
        // Straight
        if (counts.size() == 6) return true;
        // Three pairs
        int pairs = 0;
        int triplets = 0;
        for (int v : counts.values()) {
            if (v == 2) pairs++;
            if (v == 3) triplets++;
        }
        if (pairs == 3) return true;
        if (triplets == 2) return true;
        // Four + pair
        if (counts.containsValue(4) && counts.containsValue(2)) return true;
        // Six of a kind
        if (counts.containsValue(6)) return true;
        return false;
    }
}
