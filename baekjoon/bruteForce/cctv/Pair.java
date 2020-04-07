package baekjoon.bruteForce.cctv;

public class Pair {
    int x;
    int y;
    int number;
    Pair pair;
    char direction;

    public Pair(Pair pair, char direction) {
        this.pair = pair;
        this.direction = direction;
    }

    public Pair(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                ", number=" + number +
                ", pair=" + pair +
                ", direction=" + direction +
                '}';
    }
}
