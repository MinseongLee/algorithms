package baekjoon.simulation.easy.rollDice;

public class RollDice {
    public void sol(int n,int m,int x,int y,int k,int[][] map,int[] order){
        int[] dice = new int[6];
        printTops(x,y,map,order,dice);
    }

    private void printTops(int x, int y, int[][] map, int[] order, int[] dice) {
        for (int i = 0; i < order.length; i++) {
            //east
            if (order[i]==1){
                if (y+1<map[0].length){
                    y++;
                    dice = gotoEast(dice);
                    changeValue(x,y,map,dice);
                    System.out.println(dice[0]);
                }
            }
            //west
            else if (order[i]==2){
                if (y-1>=0){
                    y--;
                    dice = gotoWest(dice);
                    changeValue(x,y,map,dice);
                    System.out.println(dice[0]);

                }
            }
            //north
            else if (order[i]==3){
                if (x-1>=0){
                    x--;
                    dice = gotoNorth(dice);
                    changeValue(x,y,map,dice);
                    System.out.println(dice[0]);
                }
            }
            //south
            else if (order[i]==4){
                if (x+1<map.length){
                    x++;
                    dice = gotoSouth(dice);
                    changeValue(x,y,map,dice);
                    System.out.println(dice[0]);
                }
            }
        }
    }

    private void changeValue(int x, int y, int[][] map, int[] dice) {
        if (map[x][y]==0){
            map[x][y] = dice[5];
        }else{
            dice[5] = map[x][y];
            map[x][y] =0;
        }
    }

    private int[] gotoSouth(int[] dice) {
        int[] d = new int[dice.length];
        d[0]=dice[1];d[1]=dice[5];d[2]=dice[2];
        d[3]=dice[3];d[4]=dice[0];d[5]=dice[4];
        return d;
    }

    private int[] gotoNorth(int[] dice) {
        int[] d = new int[dice.length];
        d[0]=dice[4];d[1]=dice[0];d[2]=dice[2];
        d[3]=dice[3];d[4]=dice[5];d[5]=dice[1];
        return d;
    }

    private int[] gotoWest(int[] dice) {
        int[] d = new int[dice.length];
        d[0]=dice[2];d[1]=dice[1];d[2]=dice[5];
        d[3]=dice[0];d[4]=dice[4];d[5]=dice[3];
        return d;
    }

    private int[] gotoEast(int[] dice) {
        int[] d = new int[dice.length];
        d[0]=dice[3];d[1]=dice[1];d[2]=dice[0];
        d[3]=dice[5];d[4]=dice[4];d[5]=dice[2];
        for (int i = 0; i < 6; i++) {
            dice[i]=d[i];
        }
        return d;
    }

    public static void main(String[] args) {
        int n=2, m=2,x=0,y=0,k=16;
        int[][] map={{0,2},{3,4}};
        int[] order ={4,4,4,4,1,1,1,1,3,3,3,3,2,2,2,2};
//        int n=4, m=3,x=0,y=0,k=8;
//        int[][] map={{0,2},{3,4},{5,6},{7,8},};
//        int[] order ={4,4,4,1,3,3,3,2};
        RollDice rollDice  =new RollDice();
        rollDice.sol(n,m,x,y,k,map,order);
    }
}
