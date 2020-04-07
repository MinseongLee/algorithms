package samsungSW.d4.justRule.turnDirection;

import java.util.Objects;

public class Pair {
    private int x;
    private int y;
    //x쪽으로 움직였다는 체크를 해줘야한다.
    private Move move;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(int x, int y, Move move){
        this.x = x;
        this.y =y;
        this.move = move;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;

    }
    public Move getMove(){
        return move;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x &&
                y == pair.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
