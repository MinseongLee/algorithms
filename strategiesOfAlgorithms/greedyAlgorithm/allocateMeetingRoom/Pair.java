package strategiesOfAlgorithms.greedyAlgorithm.allocateMeetingRoom;

public class Pair implements Comparable<Pair>{
    int begin;
    int end;

    public Pair(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(end,o.end);
    }
}
