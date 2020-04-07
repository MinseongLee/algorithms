package baekjoon.bruteForce.bigBody;

public class StateWrongSort implements Comparable<StateWrongSort>{
    int weight;
    int height;
    int index;
    int level;

    public StateWrongSort(int weight, int height, int index) {
        this.weight = weight;
        this.height = height;
        this.index = index;
    }

    @Override
    public int compareTo(StateWrongSort o) {
        //return (x < y) ? -1 : ((x == y) ? 0 : 1);
        //즉 큰값이 앞으로.
        if (weight>o.weight&&height>o.height){
            return -1;
        }
        //이렇게 복잡하게 sort를 하면 정확한 sort값이 떨어지지 않는다.**
        else if ((weight>o.weight&&height<o.height)||
                (weight<o.weight&&height>o.height)){
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "State{" +
                "weight=" + weight +
                ", height=" + height +
                ", index=" + index +
                '}';
    }
}
