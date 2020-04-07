package baekjoon.implementation.superEasy.getPrime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 에라스토스테네스의 체.
 * 1. 2~N까지 모든 수를 적기.
 * 2. 아직 지우지 않은 수 중 가장 작은 수 P를 찾기. 이 것은 소수
 * 3. P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지우기.
 * 4. 아직 모든 수를 지우지 않았다면, 다시 2번 단계로.
 */
public class GetPrime {
    //이 로직의 시간복잡도는 O(n) 이라고 볼 수 있다.
    //종료한 뒤 isPrime[i]=i가 소수인가?
    public void isPrimeInBook(int n){
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        //1은 항상 예외처리
        isPrime[0] = false;isPrime[1]=false;
        int sqrtn = (int)Math.sqrt(n);
        for (int i = 2; i <= sqrtn; i++) {
            //이 수가 아직 지워지지 않았다면,
            if (isPrime[i]){
                //i의 배수 j들에 대해 isPrime[j]=false로 둔다.
                //i*i 미만의 배수는 이미 지워졌으므로 신경 쓰지 않는다.
                for (int j = i*i; j <=n ; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    //n개의 수중 k번째 지워진 수 찾기.
    public int sol(int n,int k){
        //num의 원소들은 단순히 방문 여부만 체크.
        //핵심은 index가 숫자를 대신 표현.
        int[] num = new int[n+1];
        List<Integer> prime = new ArrayList<>();
        //이 로직은.. O(n^2)에 가깝다.. 물론 이것보단 훨씬 작지만.
        for (int i = 2; i <= n; i++) {
            int p = -1;
            if (num[i]!=-1) {
                prime.add(i);
                p = i;
                //p*p 로한다면 더 빠르게 계산가능.
                //p의 배수를 모두 지우기.(p를 포함)
                for (int j = p; j <= n; j+=p) {
                    if (num[j]!=-1){
                        System.out.println(j);
                        k--;
                        if (k==0) return j;
                        num[j] = -1;
                    }
                }
            }
        }
        System.out.println(prime);
        //답이 없다면,
        return -1;
    }

    public static void main(String[] args) {
        int n = 10;
        int k = 9;
        GetPrime getPrime = new GetPrime();
        System.out.println(getPrime.sol(n,k));
    }
}
