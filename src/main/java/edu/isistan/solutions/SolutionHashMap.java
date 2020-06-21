package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SolutionHashMap implements IProblemSolver {

    private Map<Integer, Integer> ocurrences;
    private ArrayList<Pair> result;

    public SolutionHashMap() {
        ocurrences = new HashMap<>();
        result = new ArrayList<>();
    }

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        fullfillOcurrences(data);
        Arrays.stream(data)
                .filter(number -> number <= target / 2)
                .forEach(number -> calculatePairs(number, target - number));
        return result;
    }

    private void calculatePairs(int number, int objective) {
        if (!ocurrences.containsKey(objective) || ocurrences.get(objective) == 0) return;
        int quantityPairs = getFrequency(number, objective);
        IntStream.range(0, quantityPairs)
                .mapToObj(i -> new Pair(number, objective))
                .forEach(result::add);
    }

    @SuppressWarnings("ConstantConditions")
    private int getFrequency(int number, int objective) {
        int freqObj = ocurrences.replace(objective, 0);
        int freqNum = ocurrences.get(number);
        return objective != number ? freqObj * freqNum : IntStream.range(0, freqObj).sum();
    }

    /**
     * Por cada elemento del arreglo, lo inserta/incrementa su ocurrencia en el mapa
     *
     * @param data el arreglo de enteros a analizar
     */
    private void fullfillOcurrences(int[] data) {
        Arrays.stream(data)
                .forEach(number -> ocurrences.put(number,
                        ocurrences.getOrDefault(number, 0) + 1));
    }
}
