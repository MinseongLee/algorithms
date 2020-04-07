package baekjoon.bruteForce.printerQueue;

public class State {
    int index;
    int value;

    public State(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "State{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }
}
