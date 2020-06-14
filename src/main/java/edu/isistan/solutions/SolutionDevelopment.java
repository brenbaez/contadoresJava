package edu.isistan.solutions;

import edu.isistan.IProblemSolver;
import edu.isistan.IProblemSolver.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolutionDevelopment implements IProblemSolver {
    private Map<Integer, Integer> ocurrences = new HashMap<>();

    @Override
    public List<Pair> isSumIn(int [] data ,int target) {
        List<Pair> result = new ArrayList<>();
        fullfillOcurrences(data);
        List<Integer> elements = obtainValuesNoDuplicated(data);

        for (Integer number: elements) {
            calculatePairs(number, target, result);
        }
        return result;
    }

    private void calculatePairs(Integer number, Integer target, List<Pair> result) {
        if (number > target) return; // TODO: 6/14/2020 si number es mayor a target, no debe calcular naranj
        Integer objective = calculateObjetive(number, target);
        if(!ocurrences.containsKey(objective)) return;
        int frequency = ocurrences.get(objective);
        for (int i = 0; i < frequency; i++) {
            result.add(new Pair(number, objective));
        }
    }

    private Integer calculateObjetive(Integer number, Integer target) {
        return target - number;
    }

    private List<Integer> obtainValuesNoDuplicated(int[] data) {
        return IntStream.of(data)
                .boxed()
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private void fullfillOcurrences(int[] data) {
        for (int i = 0; i < data.length -1; i++) { // TODO: 6/14/2020 chequear el length
            Integer number = data[i];
            if (!ocurrences.containsKey(number)) {
                ocurrences.put(number, 1);
            } else {
                ocurrences.put(number, ocurrences.get(number) + 1);
            }
        }
    }
}
