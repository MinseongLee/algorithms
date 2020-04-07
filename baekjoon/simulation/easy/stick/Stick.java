package baekjoon.simulation.easy.stick;

import java.util.ArrayList;
import java.util.List;

/**
 * 한방에 성공했지만.. 처음 딱 보았을 때, base case가 명시되지 않아서
 * 해깔렸었다. 내가 직접 base case를 만들어야했던 문제.
 * if (div>0){ 이렇게 0이상일 때만 추가하게끔.
 */

public class Stick {
    public int sol(int x){
        //작은 숫자를 맨뒤에 추가, 그리고 맨 뒤에 숫자 remove
        List<Integer> sticks = new ArrayList<>();
        sticks.add(64);
        return getStickCnt(sticks,x);
    }

    private int getStickCnt(List<Integer> sticks, int x) {
        if (sumSticks(sticks)==x) return sticks.size();
        if (sumSticks(sticks)>x){
            int minElement = sticks.remove(sticks.size() - 1);
            int div = minElement/2;
            if (div>0) sticks.add(div);
            div /=2;
            if (sumSticks(sticks)+div>=x){
                if (div>0) {
                    sticks.add(div);
                }
                return getStickCnt(sticks,x);
            }else{
                if (div>0){
                    sticks.add(div);
                    sticks.add(div);
                }
                return getStickCnt(sticks,x);
            }
        }
        return 0;
    }

    private int sumSticks(List<Integer> sticks) {
        int size = sticks.size(),sum=0;
        for (int i = 0; i < size; i++) {
            sum+= sticks.get(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int x = 23;
        Stick stick = new Stick();
        System.out.println(stick.sol(x));
    }
}
