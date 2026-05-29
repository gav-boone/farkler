package farkler;

import java.util.Arrays;
import java.io.IOException;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

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

    public void saveCdf(String strategyName) throws IOException {
        double[] xData = new double[data.length];
        double[] yData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            xData[i] = data[i];
            yData[i] = (i + 1.0) / data.length * 100;
        }


        XYChart chart = new XYChartBuilder()
                .width(800).height(600)
                .title(strategyName + " CDF")
                .xAxisTitle("Score").yAxisTitle("% of Games ≤ Score")
                .build();
        chart.addSeries(strategyName, xData, yData).setMarker(org.knowm.xchart.style.markers.SeriesMarkers.NONE);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(100.0);
        chart.getStyler().setyAxisTickLabelsFormattingFunction(y -> {
            int val = (int) Math.round(y);
            return val % 10 == 0 ? val + "%" : " ";
        });
        BitmapEncoder.saveBitmap(chart, "./" + strategyName + "_cdf", BitmapFormat.PNG);
    }
}
