package edu.isistan.solutions;

import edu.isistan.IProblemSolver;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SolutionDevelopmentPrimitive implements IProblemSolver {

    private Int2IntOpenHashMap ocurrences = new Int2IntOpenHashMap();

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        ArrayList<Pair> result = new ArrayList<>();
        fullfillOcurrences(data);
        IntArrayList elements = obtainValuesNoDuplicated(data);
        elements.forEach(number -> calculatePairs(number, target, result));
        return result;
    }

    private void calculatePairs(int number, int target, List<Pair> result) {
        if (number > target) return;
        int objective = target - number;
        if (!ocurrences.containsKey(objective)) return;
        int frequency = ocurrences.get(objective);
        IntStream.range(0, frequency)
                .mapToObj(i -> new Pair(number, objective))
                .forEach(result::add);
    }

    private IntArrayList obtainValuesNoDuplicated(int[] data) {
        IntArrayList integers = new IntArrayList();
        IntOpenHashSet uniqueValues = new IntOpenHashSet();
        Arrays.stream(data)
                .filter(uniqueValues::add)
                .forEachOrdered(integers::add);
        return integers;
    }

    /**
     * Reccorre el arreglo y por cada elemento lo mete al map contando la cantidad de ocurrencias del elemento
     *
     * @param data el arreglo de enteros a analizar
     */
    private void fullfillOcurrences(int[] data) {
        Arrays.stream(data, 0, data.length - 1).forEach(this::addOcurrence);
    }

    /**
     * Suma un ocurrencia en el mapa, al numero pasado por parametro
     *
     * @param number el elemento a buscar en el mapa
     */
    private void addOcurrence(int number) {
        ocurrences.put(number, ocurrences.getOrDefault(number, 0) + 1);
    }
}
