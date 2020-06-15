package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolutionDevelopment implements IProblemSolver {

    private Map<Integer, Integer> ocurrences = new HashMap<>();

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        ArrayList<Pair> result = new ArrayList<>();
        fullfillOcurrences(data);
        ArrayList<Integer> elements = obtainValuesNoDuplicated(data);
        elements.forEach(number -> calculatePairs(number, target, result));
        return result;
    }

    private void calculatePairs(int number, int target, List<Pair> result) {
        if (number > target) return;
        int objective = target - number;
        if (!ocurrences.containsKey(objective)) return;
        int frequency = ocurrences.get(objective);
        for (int i = 0; i < frequency; i++) {
            result.add(new Pair(number, objective));
        }
    }

    private ArrayList<Integer> obtainValuesNoDuplicated(int[] data) {
        return IntStream.of(data)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Reccorre el arreglo y por cada elemento lo mete al map contando la cantidad de ocurrencias del elemento
     *
     * @param data el arreglo de enteros a analizar
     */
    private void fullfillOcurrences(int[] data) {
        for (int i = 0; i < data.length -1; i++) {
            int number = data[i];
            ocurrences.put(number, ocurrences.getOrDefault(number, 0) + 1);
        }
    }
}
