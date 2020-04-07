package baekjoon.implementation.superEasy.olympic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * O(n) // 정렬을 한번 하므로, O(NlogN)
 */
public class Olympic {
    public int sol(int n,int k,int[][] countries){
        List<StateCompareAndHashCode> country = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            country.add(new StateCompareAndHashCode(countries[i][0],countries[i][1],countries[i][2],countries[i][3]));
        }
        Collections.sort(country);
        int order = 1;
        country.get(0).level = 1;
        for (int i = 1; i < country.size(); i++) {
            order++;
            if (country.get(i-1).equals(country.get(i))){
                country.get(i).level = country.get(i-1).level;
            }else{
                country.get(i).level = order;
            }
        }
        for (int i = 0; i < country.size(); i++) {
            if (country.get(i).number==k){
                return country.get(i).level;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 3;

        int[][] countries = {
                {1,1,2,0},
                {2,0,1,0},
                {3,0,1,0},
                {4,0,0,1},
        };
        Olympic olympic = new Olympic();
        System.out.println(olympic.sol(n,k,countries));

    }
}
