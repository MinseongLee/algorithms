package baekjoon.bruteForce.insertOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 내일 마무리하자.. 더이상 시간 안된다..
 * 아 근데.. 왜 실패지.. 너무 쉽게 보지 말자.
 *
 * 문제 원인은 best값의 최소값을 0으로 해놓아서 그렇다.
 * 최대값이 -가 될 수 도있는데,,, 여기서 문제가 터짐.
 */
public class InsertOpCheckAlwaysMaxMinValue {
    //내생각에는 Min value 때문인거같다.. best값의 최소값이 -가 될 수도 있는데,
    //그걸 최소값으로 0을 넣어줬으므로 당연히 틀리지.
    private int best = Integer.MIN_VALUE;
    private int worst = Integer.MAX_VALUE;

    public void sol(int n, int[] num, int[] operation) {
        char[] op = createOp(operation, n);
        for (int i = 0; i < op.length; i++) {
            System.out.print(op[i] + " ");
        }
        System.out.println();
        boolean[] visited = new boolean[n - 1];
        List<Integer> list = new ArrayList<>();
        getResult(n - 1, list, num, op, visited);
        System.out.println(best);
        System.out.println(worst);
        Arrays.fill(visited, false);
        dfs(1, num, operation, num[0]);
        System.out.println(best);
        System.out.println(worst);
    }

    private void dfs(int steps, int[] num, int[] op, int result) {
//        System.out.println("ste="+steps+" "+result);
        if (steps == num.length) {
//            System.out.println("rest="+result);
            best = Math.max(best, result);
            worst = Math.min(worst, result);
            return;
        }
        if (op[0]>0) {
            op[0]--;
            dfs(steps + 1, num, op, result + num[steps]);
            op[0]++;
        }
        if (op[1] >0) {
            op[1]--;
            dfs(steps + 1, num, op, result - num[steps]);
            op[1]++;
        }
        if (op[2]>0) {
            op[2]--;
            dfs(steps + 1, num, op, result * num[steps]);
            op[2]++;
        }
        if (op[3]>0) {
            op[3]--;
            dfs(steps + 1, num, op, result / num[steps]);
            op[3]++;
        }
    }

    private void getResult(int n, List<Integer> list, int[] num, char[] op, boolean[] visited) {
        if (n == 0) {
                int result = num[0];
//            System.out.println("list=" + list);
            //여기서 list에 들어있는 index를 가지고 op에 있는 값들을 차례로 놓기.
            for (int i = 0; i < op.length; i++) {
                if (op[list.get(i)] == '+') {
                    result += num[i + 1];
                } else if (op[list.get(i)] == '-') {
                    result -= num[i + 1];
                } else if (op[list.get(i)] == '*') {
                    result *= num[i + 1];
                } else if (op[list.get(i)] == '/') {
                    if (num[i + 1] > 0) {
                        result /= num[i + 1];
                    }
                }
            }
            best = Math.max(best, result);
            worst = Math.min(worst, result);
            return;
        }
        for (int i = 0; i < op.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(i);
                getResult(n - 1, list, num, op, visited);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private char[] createOp(int[] operation, int n) {
        int[] oper = operation.clone();
        char[] op = new char[n - 1];
        int k = 0;
        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                char ch = ' ';
                if (i == 0) ch = '+';
                else if (i == 1) ch = '-';
                else if (i == 2) ch = '*';
                else ch = '/';
                while (oper[i] != 0) {
                    op[k++] = ch;
                    oper[i]--;
                }
            }
        }
        return op;
    }

    public static void main(String[] args) {
        /*int n = 6;
        int[] num = {1, 2, 3, 4, 5, 6};
        int[] op = {2, 1, 1, 1};*/
//        int n = 2;
//        int[] num = {5, 6};
//        int[] op = {0, 0, 1, 0};
        int n = 3;
        int[] num = {3,4,5};
        int[] op = {1, 0, 1, 0};
        InsertOpCheckAlwaysMaxMinValue insertOpCheckAlwaysMaxMinValue = new InsertOpCheckAlwaysMaxMinValue();
        insertOpCheckAlwaysMaxMinValue.sol(n, num, op);
    }
}
