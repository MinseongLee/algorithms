package samsungSW.d4.graph.connectIsland;

public class Pair implements Comparable<Pair>{
    double weight;
    int vertex;
    Pair pair;

    public Pair(double weight, Pair pair) {
        this.weight = weight;
        this.pair = pair;
    }

    public Pair(double weight, int vertex) {
        this.weight = weight;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Pair o) {
        return Double.compare(weight,o.weight);
    }
}
