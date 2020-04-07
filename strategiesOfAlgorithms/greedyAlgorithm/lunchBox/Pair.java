package strategiesOfAlgorithms.greedyAlgorithm.lunchBox;

public class Pair implements Comparable<Pair>{
    int wait;
    int eat;

    public Pair(int wait, int eat) {
        this.wait = wait;
        this.eat = eat;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(eat,o.eat);
//        return Integer.compare(wait,o.wait);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "wait=" + wait +
                ", eat=" + eat +
                '}';
    }
}
