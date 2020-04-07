package baekjoon.implementation.superEasy.restMoney;

public class RestMoney {
    public int sol(int price){
        int rest = 1000-price;
        int cnt =0;
        while(rest!=0){
            if (rest>=500){
                rest-=500;
            }else if (rest>=100){
                rest-=100;
            }
            else if (rest>=50){
                rest-=50;
            }
            else if (rest>=10){
                rest-=10;
            }
            else if (rest>=5){
                rest-=5;
            }
            else if (rest>=1){
                rest-=1;
            }
            cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        RestMoney restMoney = new RestMoney();
//        System.out.println(restMoney.sol(n));
        int a = 5*5+12*12;
        System.out.println(a);
        int b = 13*13;
        int c = 1800000000;
        System.out.println(a+ " "+b);

    }
}
