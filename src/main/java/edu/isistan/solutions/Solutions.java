package edu.isistan.solutions;

import edu.isistan.IProblemSolver;
import edu.isistan.ProblemGen;
import edu.isistan.IProblemSolver.Pair;

import java.util.Arrays;
import java.util.List;

public class Solutions {

    public static void main(String[] args) {

        ProblemGen problemGen = new ProblemGen();

        IProblemSolver naive = new SolutionDevelopmentPrimitive();

        int repetitions = 20;
        long[] times = new long[repetitions];

        for (int i = 0; i < repetitions; i++) {
            problemGen.genRandomProblem(1000000);

            long start = System.currentTimeMillis(); //acá no está haciendo el warm up para empezar con el benchmarking!!

            int[] data = problemGen.getData();
            int target = (int) (Math.random() * 2 * Integer.MAX_VALUE + Integer.MIN_VALUE / 2);
            List<Pair> pairs = naive.isSumIn(data, target);

            System.out.printf(" -- Pairs: %d%n", pairs.size());

            start = System.currentTimeMillis() - start;
            System.out.printf("Tiempo total: %d%n", start);
            times[i] = start;
        }

        long sum = Arrays.stream(times).reduce(0, Long::sum);

        System.out.printf("%nTiempo promedio: %d%n", sum / repetitions);
    }
}
