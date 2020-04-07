package baekjoon.graph.bfs.breakWall;

public class Pair{
    int x;
    int y;
    int broken;
    public Pair(int x, int y,int broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y &&
                broken == pair.broken;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31*result+Integer.hashCode(y);
        result = 31*result+Integer.hashCode(broken);
        return result;
    }
}
