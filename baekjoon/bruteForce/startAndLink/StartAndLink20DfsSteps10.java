package baekjoon.bruteForce.startAndLink;

/**
 * 20P10 을 steps 10으로 dfs로 돌리면 정말 금방 끝낼 수 있다.
 * dfs,, dfs,,, 정말 중요하다.
 * brute force에서 정통적인 순열 조합 로직을 그대로 사용하는 것이 아니라,
 * dfs로 적절히 변형해서 사용하면, 많은 경우의 수를 줄일 수 있다.
 *
 * 이 문제에서는 i(next)가 0부터 시작하는 것이 아니라, here부터 시작해,
 * 순열로직에서 중복처리되는 부분들을 없애는 것이 핵심이다.
 */

public class StartAndLink20DfsSteps10 {
    private int best = Integer.MAX_VALUE;
    //count로 경우의수를 세어보았다.
    private int count = 0;
    //20명중 10명만 선택하고 나머지는 그냥 더하면 된다.
    public int sol(int n, int[][] s){
        int[] taken = new int[n];
//        arePair(0,n,0,s,taken);
        boolean[] visited = new boolean[n];
        dfs(0,0,s,visited);
//        System.out.println(count);
        return best;
    }

    private void dfs(int here, int steps, int[][] s, boolean[] visited) {
        if (steps==s.length/2){
            count++;
            int size = s.length/2;
            int sum = 0,sum2=0;
            int[] pair = new int[size];
            int[] pair2 = new int[size];
            //taken에 true인 애들 합. taken에 false인 애들합.
            for (int i = 0,k=0,g=0; i < size*2; i++) {
                if (visited[i]){
//                    System.out.println("pair="+i);
                    pair[k++] = i;
                }else{
//                    System.out.println("pair2="+i);
                    pair2[g++] = i;
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sum += s[pair[i]][pair[j]];
                    sum2+= s[pair2[i]][pair2[j]];
                }
            }
            best = Math.min(best,Math.abs(sum-sum2));
            return;
        }
        for (int next = here; next < s.length; next++) {
            if (!visited[next]){
                visited[next] = true;
                dfs(next+1,steps+1,s,visited);
                visited[next] =false;
            }
        }
    }
/*
    //2^n으로 접근해보자.
    private void arePair(int here,int n, int steps, int[][] s, int[] taken) {
//        System.out.println(steps+ " "+n);
        if (steps==n/2||steps==n/2+1){
            // 만약 steps가 n/2+1 로 들어왔다면, 뒤에 2명을 버려야한다.
            // 왜냐하면 n/2로 나눈 값이 홀수이므로, 뒤에 1명은 0이므로,
            //2는 sii로 값이 0이되어야할 값.
            int size = steps,index=0;
            if (steps==n/2+1){
                for (int i = 0; i < taken.length; i++) {
                    if (taken[i]==1){
                        taken[i] = 0;
                        index = i;
                        break;
                    }
                }
                size--;
            }
            //pick한 애들 sum
            int sum = 0,sum2=0;
            //linear하게 처리를 해줄 것.
            //여기서 짝을 지어줘야한다.
            int[] pair = new int[size];
            int[] pair2 = new int[size];
            //taken에 true인 애들 합. taken에 false인 애들합.
            for (int i = 0,k=0,g=0; i < n; i++) {
                if (taken[i]==1){
//                    System.out.println("pair="+i);
                    pair[k++] = i;
                }else if (taken[i]==0){
//                    System.out.println("pair2="+i);
                    pair2[g++] = i;
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sum += s[pair[i]][pair[j]];
                    sum2+= s[pair2[i]][pair2[j]];
                }
            }
//            System.out.println(sum+" "+sum2);
            best = Math.min(best,Math.abs(sum-sum2));
            taken[index] = 1;
            return;
        }
        //here의 맨 마지막은 처리할 이유가 없다. 2명씩 선택.
        for (int next = here+1; next < n; next++) {
            if (taken[next]==0&&taken[here]==0){
                taken[here] = 1;
                taken[next] = 1;
                arePair(next+1,n,steps+2,s,taken);
                taken[here] = 0;
                taken[next] = 0;
            }
        }
    }*/

    public static void main(String[] args) {
        int n = 20;
        int[][] list = {
                {0, 5, 4, 5, 4, 5, 4, 5,2,4,4, 5, 4, 5, 4, 5, 4, 5,2,4},
                {4, 0, 5, 1, 2, 3, 4, 5,6,3,4, 2, 5, 1, 2, 3, 4, 5,6,3},
                {9, 8, 0, 1, 2, 3, 1, 2,2,6,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {9, 9, 9, 0, 9, 9, 9, 9,3,6,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {1, 1, 1, 1, 0, 1, 1, 1,3,2,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {8, 7, 6, 5, 4, 0, 3, 2,1,7,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {9, 1, 9, 1, 9, 1, 0, 9,9,7,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {6, 5, 4, 3, 2, 1, 9, 0,1,2,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {6, 5, 4, 3, 2, 1, 9, 2,0,2,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {6, 5, 4, 3, 2, 1, 9, 4,1,0,9, 8, 3, 1, 2, 3, 1, 2,2,6},
                {4, 5, 4, 5, 4, 5, 4, 5,2,4,0, 5, 4, 5, 4, 5, 4, 5,2,4},
                {4, 4, 5, 1, 2, 3, 4, 5,6,3,4, 0, 5, 1, 2, 3, 4, 5,6,3},
                {9, 8, 3, 1, 2, 3, 1, 2,2,6,9, 8, 0, 1, 2, 3, 1, 2,2,6},
                {9, 9, 9, 2, 9, 9, 9, 9,3,6,9, 8, 3, 0, 2, 3, 1, 2,2,6},
                {1, 1, 1, 1, 6, 1, 1, 1,3,2,9, 8, 3, 1, 0, 3, 1, 2,2,6},
                {8, 7, 6, 5, 4, 1, 3, 2,1,7,9, 8, 3, 1, 2, 0, 1, 2,2,6},
                {9, 1, 9, 1, 9, 1, 3, 9,9,7,9, 8, 3, 1, 2, 3, 0, 2,2,6},
                {6, 5, 4, 3, 2, 1, 9, 6,1,2,9, 8, 3, 1, 2, 3, 1, 0,2,6},
                {6, 5, 4, 3, 2, 1, 9, 2,3,2,9, 8, 3, 1, 2, 3, 1, 2,0,6},
                {6, 5, 4, 3, 2, 1, 9, 4,1,2,9, 8, 3, 1, 2, 3, 1, 2,2,0},
        };
        /*int n = 8;
        int[][] list = {
                {0, 5, 4, 5, 4, 5, 4, 5},
                {4, 0, 5, 1, 2, 3, 4, 5},
                {9, 8, 0, 1, 2, 3, 1, 2},
                {9, 9, 9, 0, 9, 9, 9, 9},
                {1, 1, 1, 1, 0, 1, 1, 1},
                {8, 7, 6, 5, 4, 0, 3, 2},
                {9, 1, 9, 1, 9, 1, 0, 9},
                {6, 5, 4, 3, 2, 1, 9, 0},
        };*/
        /*int n = 6;
        int[][] list = {
                {0,1,2,3,4,5},
                {1,0,2,3,4,5},
                {1,2,0,3,4,5},
                {1,2,3,0,4,5},
                {1,2,3,4,0,5},
                {1,2,3,4,5,0},
        };*/
        /*int n = 4;
        int[][] list = {
                {0,1,2,3},
                {4,0,5,6},
                {7,1,0,2},
                {3,4,5,0},
        };*/
        StartAndLink20DfsSteps10 startAndLink20DfsSteps10 = new StartAndLink20DfsSteps10();
        System.out.println(startAndLink20DfsSteps10.sol(n,list));
    }
}
