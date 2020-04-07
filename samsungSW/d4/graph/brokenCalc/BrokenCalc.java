package samsungSW.d4.graph.brokenCalc;

import java.util.*;

/**
 * 1808. 지희의 고장난 계산기
 *
 * 이거.. 깊이가 깊어서 bfs, ids,로 시간안에 못푼다..
 * exception 처리도 어떻게 해줘야할지 음..
 * bfs -
 * exception 처리를 bfs 하기 전에 하였다. - but 정확하지 않다..
 * 음.. 다른 방법으로 접근을 해볼까? - 정확도는 아주 좋다.
 * 다른 방법이라면 dp가 떠오른다. 그런데.. 난 그래프부터 확실하게 풀고싶다.
 * 일단. 조합탐색부터 공부한 후, 문풀 들어가자.
 */
public class BrokenCalc {
    private static int[] number;
    private static String[] state;
    private static Map<State,Integer> dist;
    private static int count = 0;
    //1. x값 판별, 답이 존재하는지.
    public static int sol(int[] calc, int x) {
        /*List<Integer> num = new ArrayList<>();
        for (int i = 0; i < calc.length; i++) {
            if (calc[i] == 1) {
                num.add(i);
            }
        }
        number = new int[num.size()];
        for (int i = 0; i < num.size(); i++) {
            number[i] = num.get(i);
            System.out.println(number[i]);
        }*/
        //무조건 x는 0~9까지의 숫자들의 배수가 되어야한다.
        //존재하면 false
        //배수가 되야는건 맞는데.. 99*99*9 이런 식으로 변수가 너무많아.
        //그냥 깊이가 어느정도 깊어지면 -1을 리턴하는게 좋을거같다.
        /*if (checkX(x)) {
            System.out.println("not");
            return -1;
        }*/
        makeState();
        System.out.println("count="+count);
        for (State key :
                dist.keySet()) {
            if (check_key(key.number, calc)){
                if (x==key.result) return dist.get(key);
            }
        }
//        return bfs(x);
        return -1;
    }

    private static boolean check_key(Set<Integer> number, int[] calc) {
        Set<Integer> check = new HashSet<>();
        for (int i = 0; i < calc.length; i++) {
            if (calc[i]==1) check.add(i);
        }
        if (check.equals(number)) return true;
        return false;
    }
    //점점 깊어지는 탐색으로 방향을 잡자.
    //n이 6만 되도.. 2,714,520 이다.. 다 못돌아..
    //모든 상태를 저장. 0 start지점이 10개이다..
    private static void makeState() {
        number = new int[10];
        int n = number.length;
        for (int i = 0; i < n; i++) {
            number[i] = i;
        }
        state = new String[n + 2];
        for (int i = 0; i < n; i++) {
            state[i] = String.valueOf(number[i]);
        }
        state[n] = "*";
        state[n + 1] = "=";

        //여기다가 미리 상태를 저장해놓는다. 중복계산을 하지 않도록.
        dist = new HashMap<>();
        //객체에다가 String, shortest, 계산 결과까지 넣는게 좋아보인다.
        //그래야 중복계산을 안한다.
        Queue<State> q = new LinkedList<>();
        //start는 number에 존재하는 각 숫자들.
        for (int i = 0; i < number.length; i++) {
            String num = String.valueOf(number[i]);
            State state = new State(num,number[i]);
            q.add(state);
//            dist.put(num, 1);
            dist.put(state,1);
        }
        while (!q.isEmpty()) {
//            String here = q.poll();
            State here = q.poll();
            //너무 깊어지면 -1
            // - 이렇게 exception을 밖에서 처리하기 힘들때,
            //주어지는 숫자의 크기를 생각해 false를 리턴할 수 있다.
            if (dist.get(here)>=6) return;
            for (int i = 0; i < state.length; i++) {
//                String there = here.formula + state[i];
                State there = new State(here.formula + state[i],0);
                if (dist.get(there) == null) {
                    int ret = 0;
                    Set<Integer> number = new HashSet<>();
                    //there 검증부터하고 calc로 넘기자.
                    if (validateThere(there.formula)){
                        //true라면 calc, 두경우 존재,
                        //그냥 num, or num*num*num=
                        ret = calc(there.formula);
                        getNumber(number,there.formula);
                    }
                    count++;
                    there.number = number;
                    there.result = ret;
                    dist.put(there,dist.get(here)+1);
                    q.add(there);
                }
            }
        }
    }

    private static void getNumber(Set<Integer> number, String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch!='*'&&ch!='='){
                number.add(ch-'0');
            }
        }
    }

    private static int bfs(int x) {
//        Map<String, Integer> dist = new HashMap<>();
        //객체에다가 String, shortest, 계산 결과까지 넣는게 좋아보인다.
        //그래야 중복계산을 안한다.
        Map<State,Integer> dist = new HashMap<>();
        Queue<State> q = new LinkedList<>();
        //start는 number에 존재하는 각 숫자들.
        for (int i = 0; i < number.length; i++) {
            if (number[i]==0) continue;
            String num = String.valueOf(number[i]);
            State state = new State(num,number[i]);
            q.add(state);
//            dist.put(num, 1);
            dist.put(state,1);
            //만약 x가 0~9사이 존재한다면,
            if (number[i]==x)return 1;
        }
        while (!q.isEmpty()) {
//            String here = q.poll();
            State here = q.poll();
            //너무 깊어지면 -1
            // - 이렇게 exception을 밖에서 처리하기 힘들때,
            //주어지는 숫자의 크기를 생각해 false를 리턴할 수 있다.
            if (dist.get(here)>=15) return -1;
            for (int i = 0; i < state.length; i++) {
//                String there = here.formula + state[i];
                State there = new State(here.formula + state[i],0);
                if (dist.get(there) == null) {
                    int ret = 0;
                    //there 검증부터하고 calc로 넘기자.
                    if (validateThere(there.formula)){
                        //true라면 calc, 두경우 존재,
                        //그냥 num, or num*num*num=
                        ret = calc(there.formula);
                    }
                    there.result = ret;
                    dist.put(there,dist.get(here)+1);
                    q.add(there);
                    if (ret==x){
                        return dist.get(there);
                    }
                }
            }
        }
        return -1;
    }
    //이렇게 두 개의 구조에 대해서만.
    private static boolean validateThere(String s) {
        //count num,*,= 를 하면 될거같다.
        int[] counting = new int[3];
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (ch=='*'){
                counting[1]++;
            }
            //=은 무조건 마지막에 하나.
            else if (ch=='='){
                if (i!=s.length()-1) return false;
                counting[2]++;
            }
            //num인경우. index범위 가져올 것.
            else{
                i = findLast(s,i);
                counting[0]++;
            }
        }
        if (counting[1]==0&&counting[2]==1&&counting[0]==1){
            return true;
        }
        //=은 여러개면 최적해가 될수없다. 무조건 마지막에 하나가있어야해. num의 개수=*의 개수+1
        if (counting[0]>=2&&counting[1]>0&&counting[2]==1&&counting[0]==counting[1]+1){
            return true;
        }
        //num도 무조건 옆에= 이 있어야만 한다.
        //num
        //num*num*num=
        //num*num=num*num*num*num= - 이런경우는 최적해 x
        return false;
    }

    private static int findLast(String s, int i) {
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j)=='*'||s.charAt(j)=='='){
                return j-1;
            }
        }
        //그냥 숫자하나란 의미. 혹은 마지막 숫자.
        return s.length()-1;
    }

    //num*num,,*num= or num 인 경우만 들어온다.
    private static int calc(String s) {
//        System.out.println("s="+s);
        StringBuilder sb = new StringBuilder();
        int first = findLast(s,0);
        int mul = Integer.parseInt(s.substring(0,first+1));
        //숫자 하나인 경우.
        if (first==s.length()-1) return mul;
        //숫자*숫자 인경우.
        int num = 0;
        //= 인 경우는 제외하고 검색.
        int len = s.length()-1;
        //*개수와 num 개수가 같다.
        for (int i = first+1; i < len; i++) {
            char ch = s.charAt(i);
            if (ch=='*'){
                // 이렇게 index를 찾아서 넣고 num으로 곱하고..
                // 이렇게 세분화하는 습관을 들이면 너무 편하다.
                int last = findLast(s,i+1);
                num = Integer.parseInt(s.substring(i+1,last+1));
                mul *= num;
                i = last;
            }
        }
        return mul;
    }

    // 1~9까지의 각 숫자로 나눈 몫이 존재하는지 확인.
    private static boolean checkX(int x) {
        //일단 x에 있는 각 숫자들을 number가 가지고 있는지 확인.
        String toX = String.valueOf(x);
        Set<Integer> checkNum = new HashSet<>();
        for (int i = 0; i < number.length; i++) {
            checkNum.add(number[i]);
        }
        boolean ok = true;
        for (int i = 0; i < toX.length(); i++) {
            if (!checkNum.contains(toX.charAt(i)-'0')){
                ok = false;
                break;
            }
        }
        if (ok) return false;
        for (int i = 0; i < number.length; i++) {
            //위에서 그 단어에 대한 체크를 했으므로, 1-x 의값은 pass해도 된다.
            if (number[i]==0||number[i]==1) continue;
            // 2부터 체크. - 모든 경우 나머지가 0이어야만한다.
            // 배수이므로,
            if (x%number[i]==0){
                //2번째 경우
                //이 수가 number[i]에 있는 수의 pow로 나타낼 수 있는경우.
                //ex) x=16 : 2,8 : 즉, 2만 있어도 나타낼 수 있음.
                if (pow(x,number[i])){//true이면 나타낼수있다는 것.
                    return false;
                }
                //ex) x=60, 60/5=12, 라면, 1,2가 존재해야 답이 존재.
                //여기부턴 몫이 무조건 있어야만 한다. 그래야 답이 나올 수 있다.
                String qu = String.valueOf(x / number[i]);
                String same = "";
                //x를 나눈 몫의 숫자들이 존재하는지 확인.(모두 존재해야 답이 존재.)
                for (int j = 0; j < qu.length(); j++) {
                    int num = qu.charAt(j) - '0';
                    //여기서는 있는지를 확인 있는지가 중요.
                    for (int k = 0; k < number.length; k++) {
                        if (num == number[k]) {
                            same += num;
                            break;
                        }
                    }
                }
                //모든 number가 존재.
                //즉, x의 배수를 만족.
                if (same.equals(qu)){
                    return false;
                }
            }
        }
        return true;
    }
    //x가 num의 pow라면, true
    private static boolean pow(int x, int num) {
        for (int i = 2;; i++) {
            int number = (int)Math.pow(num,i);
            if (x>number){
                return false;
            }
            else if (x==number){
                return true;
            }
        }
    }

    public static void main(String[] args) {
        int[] calc = {0 ,1, 1, 0, 0, 1, 0, 0, 0, 0};
        int x = 60;
//        int[] calc = {1 ,1, 1, 1, 1, 1,1, 1, 1, 1};
//        int[] calc = {0, 0, 0, 0, 0, 0, 0, 0 ,0 ,1};

//        int x = 793881;
        //1 0 1 1 0 1 1 0 1 1
        //396335
        System.out.println(sol(calc,x));
        System.out.println((int)Math.pow(2,20));
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        s1.add(1);
        s2.add(1);
        System.out.println(s1.equals(s2));
    }
}
