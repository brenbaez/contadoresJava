package edu.isistan;

import java.util.List;

public interface IProblemSolver {

    /**
     * Look in the data two integers that sums to target.
     *
     * @param data Arreglo de enteros a analizar
     * @param target objetivo que deben cumplir cada uno de los pares
     * @return Lista de pares de enteros, la cual sumados entre si da como resultado $sum
     */
    List<Pair> isSumIn(int[] data, int target);

    static class Pair {
        private int i;
        private int j;

        public Pair(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }

        public Pair reverse() {
            return new Pair(getJ(), getI());
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + i;
            result = prime * result + j;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (i != other.i)
                return false;
            return j == other.j;
        }

        @Override
        public String toString() {
            return String.format("Pair [i=%d, j=%d]", i, j);
        }

    }
}
