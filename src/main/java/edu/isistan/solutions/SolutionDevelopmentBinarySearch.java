package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SolutionDevelopmentBinarySearch implements IProblemSolver {

    private ArrayList<Pair> result;

    public SolutionDevelopmentBinarySearch() {
        result = new ArrayList<>();
    }

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        Arrays.sort(data);
        Arrays.stream(data)
                .filter(value -> value <= target / 2 && value != target - value)
                .forEach(number -> calculatePairs(number, target - number, data));
        calculatePairs(target / 2, target / 2, data);
        return result;
    }

    private void calculatePairs(int number, int objective, int[] data) {
        int idx = Arrays.binarySearch(data, objective);
        if (idx < 0) return;
        int freq = 0;
        freq = getFrequency(objective, data, freq, idx, -1);
        freq = getFrequency(objective, data, freq, idx + 1, 1);
        if (number == objective) {
            freq = IntStream.range(0, freq).sum();
        }
        addPairs(number, objective, freq);
    }

    private int getFrequency(int objective, int[] data, int freq, int idx, int k) {
        int idxAux = idx;
        while (inRange(data, idxAux) && data[idxAux] == objective) {
            idxAux += k;
            freq++;
        }
        return freq;
    }

    private boolean inRange(int[] data, int idx) {
        return idx >= 0 && idx < data.length;
    }

    private void addPairs(int number, int objective, int freq) {
        IntStream.range(0, freq)
                .mapToObj(i -> new Pair(number, objective))
                .forEach(result::add);
    }

}
