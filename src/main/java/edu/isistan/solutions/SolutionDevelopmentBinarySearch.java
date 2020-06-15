package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

//TODO
public class SolutionDevelopmentBinarySearch implements IProblemSolver {

    private ArrayList<Pair> result;

    public SolutionDevelopmentBinarySearch() {
        result = new ArrayList<>();
    }

    @Override
    public ArrayList<Pair> isSumIn(int[] data, int target) {
        Arrays.stream(data)
                .sorted()
                .forEach(number -> calculatePairs(number, target - number, data.length));
        return result;
    }

    private void calculatePairs(int number, int objective, int size) {
        IntStream.range(0, size)
                .mapToObj(i -> new Pair(number, objective))
                .forEach(result::add);
    }

}
