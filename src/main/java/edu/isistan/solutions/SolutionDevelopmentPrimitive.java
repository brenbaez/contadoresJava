package edu.isistan.solutions;

import edu.isistan.IProblemSolver;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SolutionDevelopmentPrimitive implements IProblemSolver {

    private Int2IntOpenHashMap ocurrences;
    private ArrayList<Pair> result;

    public SolutionDevelopmentPrimitive() {
        ocurrences = new Int2IntOpenHashMap();
        result = new ArrayList<>();
    }

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        fullfillOcurrences(data);
        IntArrayList elements = obtainValuesNoDuplicated(data);
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
    private IntArrayList obtainValuesNoDuplicated(int[] data) {
        IntArrayList integers = new IntArrayList();
        IntOpenHashSet uniqueValues = new IntOpenHashSet();
        Arrays.stream(data)
                .filter(uniqueValues::add)
                .forEach(integers::add);
        integers.sort(null);
        return integers;
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
