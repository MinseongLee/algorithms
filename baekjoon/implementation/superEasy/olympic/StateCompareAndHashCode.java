package baekjoon.implementation.superEasy.olympic;

public class StateCompareAndHashCode implements Comparable<StateCompareAndHashCode>{
    int number;
    int gold;
    int sliver;
    int bronze;
    int level;

    public StateCompareAndHashCode(int number, int gold, int sliver, int bronze) {
        this.number = number;
        this.gold = gold;
        this.sliver = sliver;
        this.bronze = bronze;
    }

    @Override
    public int compareTo(StateCompareAndHashCode o) {
        int result = Integer.compare(o.gold,gold);
        if (result==0){
            result = Integer.compare(o.sliver,sliver);
            if (result==0){
                result = Integer.compare(o.bronze,bronze);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateCompareAndHashCode stateCompareAndHashCode = (StateCompareAndHashCode) o;
        return gold == stateCompareAndHashCode.gold &&
                sliver == stateCompareAndHashCode.sliver &&
                bronze == stateCompareAndHashCode.bronze;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(gold);
        result = 31*result+Integer.hashCode(sliver);
        result = 31*result+Integer.hashCode(bronze);
        return result;
    }
}
