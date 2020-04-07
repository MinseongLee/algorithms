package baekjoon.implementation.superEasy.playYut;

public class PlayYut {
    public void sol(int y1,int y2,int y3,int y4){
        int[] yut = new int[2];
        yut[y1]++;
        yut[y2]++;
        yut[y3]++;
        yut[y4]++;
        if (yut[0]==1&&yut[1]==3){
            System.out.println("A");
        }
        else if (yut[0]==2&&yut[1]==2){
            System.out.println("B");
        }
        else if (yut[0]==3&&yut[1]==1){
            System.out.println("C");
        }
        else if (yut[0]==4){
            System.out.println("D");
        }
        else if (yut[1]==4){
            System.out.println("E");
        }
    }
}
