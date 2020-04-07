package samsungSW.d3.makeTeam;

import java.util.ArrayList;
import java.util.List;


public class MakeTeam {
    public static int[] sol(int n,int k){
        List<Integer>[] team = new List[k];
        for (int i = 0; i < k; i++) {
            team[i] = new ArrayList<>();
        }
        getMembers(team,n,k);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int sum= 0;
            for (int j = 0; j < team[i].size(); j++) {
                sum += team[i].get(j);
            }
            ans[i] = sum;
        }
        return ans;
    }

    private static void getMembers(List<Integer>[] team,int n,int k) {
        for (int i = 1; i <= n; i++) {
            //올바른 방향
            if (i%2==1){
                int g = 0;
                for (int member = (i-1)*k+1; member <= i*k; member++,g++) {
                    team[g].add(member);
                }
            }
            //역순
            else{
                int g = k-1;
                for (int member = (i-1)*k+1; member <=i*k; member++,g--) {
                    team[g].add(member);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 6;
        int[] sol = sol(n,k);
        for (int i = 0; i < k; i++) {
            System.out.println(sol[i]);
        }
    }
}
