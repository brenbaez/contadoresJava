package edu.isistan;

import edu.isistan.IProblemSolver.Pair;
import edu.isistan.solutions.SolutionHashMap;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    private IProblemSolver naive;

    @Before
    public void setup() {
        naive = new SolutionHashMap();
    }

    private void compareMaps(Map<Pair, Integer> pairsMap, Map<Pair, Integer> mapTest) {
        pairsMap.forEach((pair, freq) -> {
            if (pair.getI() != pair.getJ()) {
                freq += pairsMap.getOrDefault(pair.reverse(), 0);
            }
            Integer freqTest = mapTest.getOrDefault(pair, mapTest.getOrDefault(pair.reverse(), 0));
            assertEquals(freqTest, freq);
        });
    }

    private Map<Pair, Integer> loadMap(List<Pair> pairs) {
        Map<Pair, Integer> pairsMap = new HashMap<>();
        pairs.forEach(pair -> pairsMap.put(pair, pairsMap.getOrDefault(pair, 0) + 1));
        return pairsMap;
    }

    @Test
    public void solutionTest_1() {
        int[] data = {10, 12, 10, 15, 11, -1, 7, 6, 5, 4, 2, 1, 1, 1};
        int target = 11;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 9;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(-1, 12), 1);
        mapTest.put(new Pair(1, 10), 6);
        mapTest.put(new Pair(4, 7), 1);
        mapTest.put(new Pair(5, 6), 1);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_2() {
        int[] data = {1, 1, 1, 1};
        int target = 2;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 6;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(1, 1), 6);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_3() {
        int[] data = {1, 5, 7, 6, -1, 5};
        int target = 6;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 3;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(-1, 7), 1);
        mapTest.put(new Pair(1, 5), 2);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_4() {
        int[] data = {1, 5, 7, -1};
        int target = 6;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 2;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(-1, 7), 1);
        mapTest.put(new Pair(1, 5), 1);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_5() {
        int[] data = {1, 5, 7, 3};
        int target = 8;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 2;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(1, 7), 1);
        mapTest.put(new Pair(3, 5), 1);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_6() {
        int[] data = {1, 1, 1, 1, 1};
        int target = 2;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 10;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(1, 1), 10);

        compareMaps(loadMap(pairs), mapTest);
    }

    @Test
    public void solutionTest_7() {
        int[] data = {1, 1, 1, 1, 1, 1};
        int target = 2;
        List<Pair> pairs = naive.isSumIn(data, target);

        int size = 15;
        assertEquals(pairs.size(), size);

        Map<Pair, Integer> mapTest = new HashMap<>();
        mapTest.put(new Pair(1, 1), 15);

        compareMaps(loadMap(pairs), mapTest);
    }
}
