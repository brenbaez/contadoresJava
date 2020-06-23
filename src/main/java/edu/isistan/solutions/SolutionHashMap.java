package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        List<Integer> listValues = obtainValuesNoDuplicated(data);
        for (Integer number : listValues) {
            if (number <= target / 2) {
                calculatePairs(number, target - number);
            }
        }
        return result;
    }

    private List<Integer> obtainValuesNoDuplicated(int[] data) {
        return IntStream.of(data)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private void calculatePairs(int number, int objective) {
        if (!ocurrences.containsKey(objective)) return;
        int quantityPairs = getFrequency(number, objective);
        ArrayList<Pair> pairs = result;
        for (int i = 0; i < quantityPairs; i++) {
            pairs.add(new Pair(number, objective));
        }
    }

    private int getFrequency(int number, int objective) {
        int freqObj = ocurrences.get(objective);
        int freqNum = ocurrences.get(number);
        if (objective != number) return freqObj * freqNum;
        int sum = 0;
        for (int i = 0; i < freqObj; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * Por cada elemento del arreglo, lo inserta/incrementa su ocurrencia en el mapa
     *
     * @param data el arreglo de enteros a analizar
     */
    private void fullfillOcurrences(int[] data) {
        for (int number : data) {
            int frequency = ocurrences.getOrDefault(number, 0);
            ocurrences.put(number, frequency + 1);
        }
    }
}
