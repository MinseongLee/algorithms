package samsungSW.swTest.mountainWay;

public class Pair implements Comparable<Pair>{
    int x;
    int y;
    int value;

    public Pair(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Pair o) {
        int result = Integer.compare(x,o.x);
        if (result==0){
            result = Integer.compare(y,o.y);
        }
        return result;
    }
}
