package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolutionDevelopment implements IProblemSolver {

    private Map<Integer, Integer> ocurrences;
    private ArrayList<Pair> result;

    public SolutionDevelopment() {
        ocurrences = new HashMap<>();
        result = new ArrayList<>();
    }

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        fullfillOcurrences(data);
        ArrayList<Integer> elements = obtainValuesNoDuplicated(data);
        elements.stream()
                .filter(number -> number <= target/2)
                .forEach(number -> calculatePairs(number, target - number));
        return result;
    }

    private void calculatePairs(int number, int objective) {
        if (!ocurrences.containsKey(objective)) return;
        int freqObj = ocurrences.get(objective);
        int freqNum = ocurrences.get(number);
        int frequency = objective != number ? freqObj * freqNum : IntStream.range(0, freqObj).sum();
        IntStream.range(0, frequency)
                .mapToObj(i -> new Pair(number, objective))
                .forEach(result::add);
    }

    /**
     * Obtiene los elementos ordenados y no duplicados del arreglo
     *
     * @param data el arreglo a analizar
     * @return un arreglo con elementos no duplicados
     */
    private ArrayList<Integer> obtainValuesNoDuplicated(int[] data) {
        return IntStream.of(data)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
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
