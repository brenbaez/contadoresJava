package edu.isistan.solutions;

import edu.isistan.IProblemSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SolutionNaive2 implements IProblemSolver {

    public List<Pair> isSumIn(int[] data, int sum) {

        List<Pair> pairs = new ArrayList<>();
        Arrays.sort(data);

        IntStream.range(0, data.length)
                .forEach(i -> IntStream.range(i + 1, data.length)
                        .filter(j -> i != j && data[i] + data[j] == sum)
                        .forEach(j -> pairs.add(new Pair(data[i], data[j])))
                );


        return pairs;
    }

}