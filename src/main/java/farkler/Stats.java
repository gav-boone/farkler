package farkler;

import java.util.Arrays;

public class Stats {
    private final int[] data;

    public Stats(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        Arrays.sort(this.data);
    }

    public double mean() {
        return Arrays.stream(data).average().orElse(0);
    }

    public int min() {
        return data[0];
    }

    public int max() {
        return data[data.length - 1];
    }

    public int quartile(int q) {
        // q = 1, 2, or 3
        double idx = q * (data.length + 1) / 4.0 - 1;
        int lower = (int) Math.floor(idx);
        int upper = (int) Math.ceil(idx);
        if (lower < 0)
            return data[0];
        if (upper >= data.length)
            return data[data.length - 1];
        return (data[lower] + data[upper]) / 2;
    }

    public double farkleRate() {
        long zeroes = Arrays.stream(data).filter(s -> s == 0).count();
        return (double) zeroes / data.length;
    }
}
