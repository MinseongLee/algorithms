package baekjoon.implementation.superEasy.gimbap;

public class GimbapRound {
    public String sol(double x,double y,int n,double[][] list){
        double price = ((double)1000/y)*x;
        for (int i = 0; i < n; i++) {
            price = Math.min(price,((double)1000/list[i][1])*list[i][0]);
        }
//        return Math.round(price*100/100.0);
        return String.format("%.2f",price);
    }
    public static void main(String[] args) {
        double x=5,y=100;
        int n =3;
        double[][] list ={
                {4,100},
                {3,100},
                {5,100},
        };
        GimbapRound gimbapRound = new GimbapRound();
        System.out.println(gimbapRound.sol(x,y,n,list));
    }
}
