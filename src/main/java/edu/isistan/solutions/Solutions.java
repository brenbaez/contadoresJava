package edu.isistan.solutions;

import edu.isistan.IProblemSolver;
import edu.isistan.ProblemGen;
import edu.isistan.IProblemSolver.Pair;

import java.util.List;

public class Solutions {

    public static void main(String[] args) {

        ProblemGen problemGen = new ProblemGen();

        IProblemSolver naive = new SolutionDevelopment();

        for (int i = 0; i < 20; i++) {
            problemGen.genRandomProblem(1000000);

            long start = System.currentTimeMillis(); //acá no está haciendo el warm up para empezar con el benchmarking!!

            int[] data = problemGen.getData();
            int target = (int) (Math.random() * 2 * Integer.MAX_VALUE + Integer.MIN_VALUE / 2);
            List<Pair> pairs = naive.isSumIn(data, target);

            System.out.printf(" -- Pairs: %d%n", pairs.size());

            start = System.currentTimeMillis() - start;
            System.out.printf("Tiempo total: %d%n", start);
        }
    }
}
