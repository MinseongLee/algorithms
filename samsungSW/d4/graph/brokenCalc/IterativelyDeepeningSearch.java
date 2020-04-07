package samsungSW.d4.graph.brokenCalc;

import java.util.ArrayList;
import java.util.List;

/**
 * 점점 깊어지는 탐색은 조합 탐색과 관계가 깊다.
 * -많은 경우, 조합 탐색에 사용하는 여러 최적화 기법들을
 * 점점 깊어지는 탐색에 적용할 수 있다.
 */

/**
 * 1808. 지희의 고장난 계산기
 *
 * 임의의 깊이 제한 l을 정한 후 이 제한보다 짧은 경로가 존재하는지
 * 깊이 우선 탐색으로 확인. 답 찾음 -> return best;
 * else l ++;
 * <p>
 * 오.. 속도는 확실히 빨라졌다. 그렇다면.. -1인경우를 어떻게 처리..
 * : -1인 경우는 깊이 한도를 높이고, x보다 큰 수가 들어올 시 바로 리턴.
 * //그렇다면 결국 나중에는 전부 x보다 큰 수일 것이므로, 금방 끝낼 수 있다.
 * ids는
 * 속도 빨라지고, 메모리 적게먹고 이건 아주좋다.
 * 그런데.. exception 처리를 어떻게 해줘야할까..
 * 그래도..12개에 대한 처리가 너무 늦어.. 음.. -
 * 조합탐색 공부하고 다시 정리.
 * 내 로직은 일단 완벽하다.
 * 그런데. 시간안에 못풀어. 즉, 최적화하는 방법을 배워야해.
 * 어떻게하는지 정확하게 배우고, 다시 풀어보자.
 *
 * 해냈다. 가지치기로 해냈어.- 최적화 코드 엄청나지만..
 * 이렇게 dfs, bfs 로 pruning을 잘 하면 처리가 가능하다.
 * pruning 과 heuristic 의 매력이란.. 이 두가지를 잘 활용하면,
 * 정말 엄청나게 큰 효율을 보일 수 있다.
 */
public class IterativelyDeepeningSearch {
    // 이렇게 전역 변수로 최단거리를 저장해놓으면,
    // 현재 탐색 깊이가 이 거리보다 커질 때 탐색을 조기 종료할 수 있음.
    private static int best;
    //number에는 사용할 수 있는 숫자들이 저장되어 있다.
    private static int[] number;
    //state에는 number[] + "*" +"=" 이렇게 들어있다.
    //만들 수 있는 모든 상태를 저장해놓았다.
    private static String[] state;

    public static int sol(int[] calc, int x) {
        //static list에 저장하기 위해 num에 미리 저장.
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < calc.length; i++) {
            if (calc[i] == 1) {
                num.add(i);
            }
        }
        //first optimal
        //처음에 숫자조합으로 끝낼 수 있는지 체크.
        String finish = String.valueOf(x);
        String ans = "";
        for (int i = 0; i < finish.length(); i++) {
            int fini = finish.charAt(i) - '0';
            for (int j = 0; j < num.size(); j++) {
                if (fini == num.get(j)) {
                    ans += fini;
                    break;
                }
            }
        }
        if (finish.equals(ans)) {
            //맨 마지막에 무조건 "="이 포함되어야만 한다.
            return ans.length() + 1;
        }
        //make static Array
        number = new int[num.size()];
        for (int i = 0; i < num.size(); i++) {
            number[i] = num.get(i);
//            System.out.println(number[i]);
        }
        //make state[]
        makeState();
        // 첫 시작점이 하나여야하므로, 여러 개인 경우에는
        // 이렇게 null값으로 넘김. bfs를 dfs로 처리하므로 가능하다.
        State start = new State("", 0);
        //x는 finish이고, start는 첫번째 상태값,
        // 1은 서서히 증가하는 값의 크기이다.
        return ids(start, x, 1);
    }
    //dfs
    //bfs의 dfs 버전이다. steps로 bfs에서 Map이나 int[][] 로 표현해
    //dist값을 +1씩 늘려간 것처럼. steps를 이용해 깊이를 하나씩 늘려가며
    //모든 상태를 검색해 나간다.
    private static void dfs(State here, int finish, int steps) {
        if (steps >= 17) {
            best = steps;
            return;
        }
        //pruning,
        if (pruningHere(here.formula, finish)) {
            return;
        }
        // heuristic은 (steps+here.estimate() >=best) return;
        //이렇게 예상값을 steps에 적용해줘야한다. 즉, 하한 값.
        //지금까지 구한 최적해보다 더 좋을 가능성이 없으면 버린다.
        if (steps >= best) return;
        //목표 상태에 도달한 경우
        if (here.result == finish) {
            best = steps;
            return;
        }
        //인접한 상태들을 깊이 우선탐색으로
        // 당연히 여기에서 인접 상태들은 계속적으로 변화해야만 한다.
        // bfs 처럼**즉, q와 Map dist 가 없는 bfs인 것이다.
        //상태가 깊어질 수록 새로운 값들을
        // 계속 더해가며 값을 검증해 나가야하는 것이다.
        for (int i = 0; i < state.length; i++) {
//            System.out.println("here="+(here.formula+state[i]));
//            System.out.println(here.formula+" "+state[i]);
            State there = new State(here.formula + state[i], 0);
//            System.out.println("there.formula="+there.formula);
            //there 검증부터하고 calc로 넘기자.
            if (validateThere(there.formula)) {
                //true라면 calc, 두경우 존재,
                //그냥 num, or num*num*num=
                there.result = calc(there.formula);
            }
            dfs(there, finish, steps + 1);
        }
    }

    // 점점 깊어지는 탐색
    public static int ids(State start, int finish, int growthStep) {
        // limit은 best가 가지고 있는 최소 깊이이다.
        // 즉, 더 낮아야한다면 더 낮출수있다.
        //limit은 heuristic 최적화를 생각한다면, 아주 잘 설정해야한다.
        for (int limit = 4; ; limit += growthStep) {
            // dfs로 돌리고 만약 못찾았으면 growthStep만큼 증가시킨 후
            // 다시 dfs 돌릴것.
            //best를 깊이 한계(limit)에 1 더한 것으로 설정하고,
            //탐색 후에 이 값이 깊이 한계 이하로 내려왔는지 확인만 하면 된다.
            best = limit + 1;
            //steps는 0부터 시작하므로,
            //무조건 첫 시작이 steps보단 커야한다.**이것이 중요하다.
            dfs(start, finish, 0);
            //17보다 더 깊어지면 타임오버가 뜬다.
            if (best == 17) return -1;
            // 만약 best가 steps값을 받는 다면, 상태가 finish에 도달했다는 것이므로,
            // best를 리턴.
            if (best <= limit) return best;
        }
    }
    //state[] : 나올 수 있는 모든 상태를 저장.
    private static void makeState() {
        int n = number.length;
        state = new String[n + 2];
        for (int i = 0; i < n; i++) {
            state[i] = String.valueOf(number[i]);
        }
        state[n] = "*";
        state[n + 1] = "=";
    }

    //true이면 탐색 종료, 아니면 탐색 계속.
    private static boolean pruningHere(String s, int x) {
        if (s.length() == 0) {
            return false;
        } else {
            if (s.charAt(0) == '=' || s.charAt(0) == '*' || s.charAt(0) == '0') {
                return true;
            }
        }
        //count num,*,= 를 하면 될거같다.
        //number,*,= 개수를 세서 그 개수에 맞춰 값처리를 하였다.
        int[] counting = new int[3];
//        System.out.println("s="+s);
        //addCounting에서 =이 맨 마지막에 하나만 없다면 false이므로,
        //num의 개수가 *의 개수보다 같거나 큰 경우에만 탐색을 허용.
        if (addCounting(counting, s)) {
            //true라면, counting[2]==1 or 0 이다.
            //계산해서 x값보다 더 크다면 false 계속 리턴.
            //num or num=
            if (counting[1] == 0 && counting[0] == 1) {
                //num이 x보다 더 크다면 return;
                String tmp = s;
                if (counting[2] == 1) {
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
                int num = (int) Long.parseLong(tmp);
//                System.out.println("1 s " + s + " num=" + num + " x=" + x + " best=" + best);
                //여기서 구한 num은 x에 나누어 떨어져야만 유효한 값이 될 수 있다.
                //즉, 배수여야만 해. - 즉, 숫자만 있는 경우는 배수일 필요가 없어.
                //붙이는 단계이므로,
                if (num > x || num <= 0) {
                    //답은 배수여야만 하는데.. 그 과정에 있는 값은..?
                    // ex) 66은 답이 아니고 배수가 아니다. 하지만 666은 답이야.
                    // 이런경우는? - 즉, 배수든 아니든 모든 값을 허용해줘야한다.
//                    System.out.println("1 s "+s+" num="+num+" x="+x+" best="+best);
                    return true;
                }
                return false;
            }
            //num* or num*num or num*num= ,,etc
            if (counting[0] == counting[1] || counting[0] == counting[1] + 1) {

                //여기 들어온 친구들은 그냥 num이 있는 만큼 곱하면 된다.
                //하나라면 하나만 리턴.
                int[] numList = new int[counting[0]];
                StringBuilder sb = new StringBuilder();
                int j = 0;
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if (ch != '*' && ch != '=') {
                        sb.append(ch);
                    } else if (sb.length() > 0) {
                        numList[j++] = (int) Long.parseLong(sb.toString());
                        sb.setLength(0);
                    }
                }
                if (sb.length() > 0) numList[j] = (int) Long.parseLong(sb.toString());
                int mul = 1;
                int checkOne = 0;
                //이제 여기 넣은 숫자들을 곱할 것.
                for (int i = 0; i < counting[0]; i++) {
                    // 만약 numList 안에 1이 포함되어 있다면?
                    // 당연히 더 깊이 들어갈 이유가 없다.
                    // 하지만 1이후 추가할 수 있는 값이 존재한다.
                    //ex) 1->11->111, 1->12->132 ,,etc
                    // 그러므로 1이 2번 이상 나올 때 의미가 있다.
                    if (numList[i]==1) {
                        checkOne++;
                        if (checkOne>=2) return true;
                    }
                    mul *= numList[i];
                }
//                System.out.println("2 s " + s + " mul=" + mul + " x=" + x + " best=" + best);
                //계산 값이 x보다 크거나, 1보다 작거나 같거나. 하면 true를 리턴.
                if (mul > x || mul <= 1) return true;
                //여기서 구한 mul은 x에 나누어 떨어져야만 유효한 값이 될 수 있다.
                //즉, 배수여야만 해.
                if (x % mul != 0) {
                    //너무 깊어지면 시간초과가 나므로.
                    // 제한을 14초과로 두었다.
                    if (best > 14) return true;

                    //즉, 배수가 아닐때, 뒤가 숫자라면,
                    //숫자는 더 붙을 수 있어야만한다.
                    //그래야지만이 제대로된 값을 계산할 수 있어.
                    char ch = s.charAt(s.length() - 1);
                    if (ch != '*' && ch != '=') {
                        return false;
                    }
//                    System.out.println("in s "+s+" mul="+mul+" x="+x+" best="+best);
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    //이렇게 두 개의 구조에 대해서만.
    private static boolean validateThere(String s) {
        //count num,*,= 를 하면 될거같다.
        int[] counting = new int[3];
        if (addCounting(counting, s)) {
            //*는 하나도 없고, = 하나, 숫자하나 인경우
            if (counting[1] == 0 && counting[2] == 1 && counting[0] == 1) {
                return true;
            }
            //=은 여러개면 최적해가 될수없다. 무조건 마지막에 하나가있어야해.
            // num의 개수=*의 개수+1
            if (counting[0] >= 2 && counting[1] > 0 && counting[2] == 1 && counting[0] == counting[1] + 1) {
                return true;
            }
        }
        //num도 무조건 옆에= 이 있어야만 한다.
        //num
        //num*num*num=
        //num*num=num*num*num*num= - 이런경우는 최적해 x
        return false;
    }

    private static boolean addCounting(int[] counting, String s) {
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (ch == '*') {
                counting[1]++;
            }
            //=은 무조건 마지막에 하나.
            else if (ch == '=') {
                if (i != s.length() - 1) return false;
                counting[2]++;
            }
            //num인경우. index범위 가져올 것.
            else {
                i = findLast(s, i);
                counting[0]++;
            }
        }
        return true;
    }

    private static int findLast(String s, int i) {
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j) == '*' || s.charAt(j) == '=') {
                return j - 1;
            }
        }
        //그냥 숫자하나란 의미. 혹은 마지막 숫자.
        return s.length() - 1;
    }

    // num*num,,*num= or num= 인 경우만 들어온다.
    private static int calc(String s) {
//        System.out.println("s="+s);
        int first = findLast(s, 0);
        long mul = Long.parseLong(s.substring(0, first + 1));
        //숫자 하나인 경우. 맨 마지막에 =이 있으므로,
        if (first == s.length() - 2) {
//            System.out.println(first+" "+s);
            return (int) mul;
        }
        //숫자*숫자 인경우.
        long num = 0;
        //= 인 경우는 제외하고 검색.
        int len = s.length() - 1;
        //*개수와 num 개수가 같다.
        for (int i = first + 1; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                // 이렇게 index를 찾아서 넣고 num으로 곱하고..
                // 이렇게 세분화하는 습관을 들이면 너무 편하다.
                int last = findLast(s, i + 1);
                num = Long.parseLong(s.substring(i + 1, last + 1));
                mul *= num;
                i = last;
            }
        }
        return (int) mul;
    }

    public static void main(String[] args) {
//        int[] calc = {0 ,0, 0, 1, 1, 0, 1, 0, 0, 1};
//        int x = 34396;
//        int[] calc = {0 ,1, 1, 0, 0, 1, 0, 0, 0, 0};
//        int x = 60;
        /*int[] calc = {1, 1, 0, 0, 0, 0, 0, 1, 1, 0};
        int x = 1000000;*/
        /*int[] calc = {1, 1, 0, 0, 0, 1, 1, 0, 0, 1};
        int x = 658395;*/
        int[] calc = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        int x = 793881;
//        int[] calc = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
//        int x = 23976;
        //14번 틀림.
//        int[] calc = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
//        int x = 123321;
        //61번 틀림.
        //92번
//        int[] calc = {0, 0, 0, 0, 0, 1, 0, 1, 0, 0};
//        int x = 290625;

        long start = System.currentTimeMillis();
        System.out.println(sol(calc, x));
        long end = System.currentTimeMillis();
        System.out.println("time=" + (end - start));
        //x%num=18
        //1 s 66 num=66 x=23976 best=2
        int a = 23976;
        int n = 66;
        int qu = a % n;
        double dv = (double) a / n;
        System.out.println("qu=" + qu + " " + dv);
    }
}
