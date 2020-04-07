package baekjoon.implementation.superEasy.wedding;

import java.util.HashSet;
import java.util.Set;

/**
 * 결혼식
 */

public class Wedding {
    public int sol(int n,int m,int[][] list){
        //먼저 주인공의 친구들을 저장.
        Set<Integer> friends = new HashSet<>();
        for (int i = 0; i < m; i++) {
            //ai 만 1번이 될 수있다.
            if (list[i][0]==1){
                friends.add(list[i][1]);
            }
        }
        //주인공 친구의 친구들을 저장.
        Set<Integer> friendsOfFriends = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (friends.contains(list[i][0])||friends.contains(list[i][1])){
                friendsOfFriends.add(list[i][0]);
                friendsOfFriends.add(list[i][1]);
            }
        }
        //주인공 친구들과 주인공 친구의 친구들을 저장.
        Set<Integer> lastFriends = new HashSet<>();
        for (int f :friends) {
            lastFriends.add(f);
        }
        for (int f :friendsOfFriends) {
            lastFriends.add(f);
        }
        //만약 주인공이 포함되어있다면 제거.
        if (lastFriends.contains(1)) lastFriends.remove(1);
        return lastFriends.size();
    }
    public static void main(String[] args) {
        Wedding wedding= new Wedding();
//        System.out.println(wedding.sol(n,m,list));
    }
}
