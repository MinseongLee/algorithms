package samsungSW.d4.graph.brokenCalc;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class State {
    String formula;
    int result;
    //상태를 미리 만들어놓으려면,
    // 쓰인 숫자들도 저장해놓고 나중에 비교할때 사용해야한다.
    Set<Integer> number = new HashSet<>();

    public State(String formula, int result) {
        this.formula = formula;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(formula, state.formula);
    }

    @Override
    public int hashCode() {
        // 이 코드는 느리다고하였다..
        // 하지만 String에 관련해서는 이렇게 정의.
        // - String은 내부적으로 hash가 재정의되어져있다.
        return Objects.hash(formula);
    }
}
