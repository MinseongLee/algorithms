package strategiesOfAlgorithms.graph.breadthFirstSearch.hanoi;

import java.util.*;

/**
 * solve it! 3/4/2020
 * bfs 양방향 탐색으로 풀었다.
 * 각 하노이 탑 기둥은 Stack으로 표현해서 List<Stack<>>으로 저장해서
 * 각 다른 상태를 하나의 정점으로 사용하였다.
 * **항상 bfs 양방향 탐색이 가능하다면, 양방향으로 풀 것. - 엄청난 시간절약.
 *
 * **상태공간에서 최소값은 항상 bfs부터 생각.
 * 그 후, dfs, 등등 다른 알고리즘 생각.
 * 모든 문제를 바라볼 때,brute force,
 * bfs, dfs 적용부터 생각해보자. 도저히 이 세 가지 적용이 안되면,
 * 그 때, dp를 생각.
 */
public class HanoiBidirectionalSearch {
    // 상태 최소값은 모든 상태를 넣을 수 있는 bfs로 가능하다.
    // 하지만 일반적인 bfs로는 시간초과가 된다.
    // 끝 상태를 알고있으므로, 양방향 탐색을 할 수 있다.
    private static Map<List<Stack<Integer>>, Integer> dist;

    // 모든 상태를 만들어 넣기. - 정확하지 않다. 그러므로 현재상태로 구했다.
    private static int makeState(List<Stack<Integer>> start, List<Stack<Integer>> end) {
        dist = new HashMap<>();
        Queue<List<Stack<Integer>>> q = new LinkedList<>();
        dist.put(start, 1);
        dist.put(end, -1);
        q.add(start);
        q.add(end);
        while (!q.isEmpty()) {
            //here은 변경하지 않고, here의 stack에 있는 내용들을 가지고,
            //다른 상태들을 만들어, q에 넣을 것.
            List<Stack<Integer>> here = q.poll();
            /*if (dist.containsKey(end)) {
                return dist.get(end);
            }*/
//            System.out.println(" h="+here+" dist="+dist.get(here));
            for (int i = 0; i < here.size(); i++) {
                Stack<Integer> pillar = here.get(i);
                // stack 에 있는 원반을
                // 다른 곳에 옮길 수 있는 경우가 존재한다면, 다 옮길 것.
                if (!pillar.isEmpty()) {
                    int plate = pillar.peek();
                    //4군데 모두 확인.- 맨 위에 값
                    for (int j = 0; j < here.size(); j++) {
                        if (i == j) continue;
                        if (!here.get(j).isEmpty()) {
                            //plate가 작은 경우에만 옮길 수 있음.
                            if (plate < here.get(j).peek()) {
                                //여기에서 저장 그 값을. - j번째에 있는 스택에 plate를 넣기.
                                //i번째에 있는 스택에는 pop할 것.
                                List<Stack<Integer>> there = createThere(here, i, j);
                                //중복되는 data가 싫어서 checkThere로 따로 뺐다.
                                if (checkThere(q,there,here)){
                                    return Math.abs(dist.get(there)) + Math.abs(dist.get(here)) - 2 + 1;
                                }
                            }
                        }
                        //만약 empty라면, 그냥 처음보는데 넣기.
                        else if (here.get(j).isEmpty()) {
                            //여기에서 저장 그 값을. - j번째에 있는 스택에 plate를 넣기.
                            //i번째에 있는 스택에는 pop할 것.
                            List<Stack<Integer>> there = createThere(here, i, j);
                            if (checkThere(q,there,here)){
                                return Math.abs(dist.get(there)) + Math.abs(dist.get(here)) - 2 + 1;
                            }
                        }
                    }
                }
            }
        }
        //만약 모든 상태를 검사했는데 답이 없다면 -1
        return -1;
    }

    private static boolean checkThere(Queue<List<Stack<Integer>>> q, List<Stack<Integer>> there,List<Stack<Integer>> here) {
        if (dist.get(there) == null) {
//                                    System.out.println("if here="+here+" "+dist.get(here));
//                                    dist.put(there,dist.get(here)+1);
            dist.put(there, incr(dist.get(here)));
            q.add(there);
        }
        //부호가 다를 경우, 가운데서 만났단 의미. 이 때, 합친값이 답이 된다.
        else if (sgn(dist.get(there)) != sgn(dist.get(here))) {
            return true;
        }
        return false;
    }
    private static int sgn(int x) {
        if (x == 0) return 0;
        return x > 0 ? 1 : -1;
    }
    //부호에 따라 minus라면 -1, plus +1
    private static int incr(int x) {
        if (x > 0) return x + 1;
        return x - 1;
    }

    private static List<Stack<Integer>> createThere(List<Stack<Integer>> here, int remove, int add) {
        List<Stack<Integer>> there = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] tmp = new int[here.get(i).size()];
            Stack<Integer> stack = new Stack<>();
            int j = 0;
            //stack을 for문으로 꺼내면, FILO이 아니라,
            //FIFO이 된다.***
            for (int value :
                    here.get(i)) {
//                System.out.println("value="+value);
                tmp[j++] = value;
            }
            //stack에 넣기.
            for (int k = 0; k < tmp.length; k++) {
                stack.push(tmp[k]);
            }
            there.add(stack);
        }
        //안에 있는 stack은 같은 공간을 바라본다..
        // 흠.. 이거 그냥 state라는 객체를 만드는 게 편할 듯.
        int plate = there.get(remove).pop();
        there.get(add).push(plate);
     /*   System.out.println("here="+here);
        System.out.println("there="+there);*/
        return there;
    }

    public static int sol(int n, int[][] pillar) {
        //init
        /*List<Stack<Integer>> start = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int j = n; j >= 1; j--) {
            stack.push(j);
        }
        start.add(stack);
        for (int i = 1; i < 4; i++) {
            start.add(new Stack<>());
        }
        System.out.println(stack.peek());*/
        //last state
        List<Stack<Integer>> end = new ArrayList<>();
        Stack<Integer> endStack = new Stack<>();
        for (int i = 0; i < 3; i++) {
            end.add(new Stack<>());
        }
        for (int j = n; j >= 1; j--) {
            endStack.push(j);
        }
        end.add(endStack);
        //현재 상태 저장.
        List<Stack<Integer>> now = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Stack<Integer> stack1 = new Stack<>();
            //0개인경우도 존재하므로.
            for (int j = 0; j < pillar[i].length; j++) {
                stack1.push(pillar[i][j]);
            }
            now.add(stack1);
        }
        //start는 맨 왼쪽에 모든 원반이 있는 경우.
        //모든 상태를 구하는 상태그래프.
        return makeState(now, end);
//        return dist.get(now);
    }

    //내 로직 정확하게 작동한다.
    public static void main(String[] args) {
        /*int n = 5;
        int[][] pillar = {
                {1},
                {3},
                {5,4},
                {2},
        };*/
        int n = 10;
        int[][] pillar = {
                {8, 7},
                {5, 4},
                {6, 3, 2},
                {10, 9, 1},
        };
        long start = System.currentTimeMillis();
        System.out.println(sol(n, pillar));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
