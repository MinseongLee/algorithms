package baekjoon.bruteForce.candyGame;

public class CandyGame {
    //C=1,P=2,Z=3,Y=4
    public int sol(int n,String[] list){
        int[][] candy = createCandy(list);
        //바꾸지 않고 최고길이 계산.
        int maxElement = cntAllElements(candy);
        //바꾼 후 최고길이 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //column 계산
                if (j+1<n){
                    swap(i,j,i,j+1,candy);
                    maxElement = Math.max(maxElement,getLongestCol(i,j,candy));
                    maxElement = Math.max(maxElement,getLongestCol(i,j+1,candy));
                    maxElement = Math.max(maxElement,getLongestRow(i,j,candy));
                    maxElement = Math.max(maxElement,getLongestRow(i,j+1,candy));
                    swap(i,j,i,j+1,candy);
                }
                //row 계산
                if (i+1<n){
                    swap(i,j,i+1,j,candy);
                    maxElement = Math.max(maxElement,getLongestCol(i,j,candy));
                    maxElement = Math.max(maxElement,getLongestCol(i+1,j,candy));
                    maxElement = Math.max(maxElement,getLongestRow(i,j,candy));
                    maxElement = Math.max(maxElement,getLongestRow(i+1,j,candy));
                    swap(i,j,i+1,j,candy);
                }
            }
        }
        return maxElement;
    }

    private void swap(int x1, int y1, int x2, int y2, int[][] candy) {
        int tmp = candy[x1][y1];
        candy[x1][y1] = candy[x2][y2];
        candy[x2][y2] = tmp;
    }

    private int cntAllElements(int[][] candy) {
        int max =0,n=candy.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max,getLongestCol(i,j,candy));
                max = Math.max(max,getLongestRow(i,j,candy));
            }
        }
        return max;
    }

    private int getLongestRow(int x, int y, int[][] candy) {
        int theCandy = candy[x][y],cnt=1;
        int top = x-1,bottom=x+1;
        while(top>=0||bottom<candy.length){
            if (top>=0&&candy[top][y]==theCandy){
                top--;
                cnt++;
            }
            else if (bottom<candy.length&&candy[bottom][y]==theCandy){
                bottom++;
                cnt++;
            }
            else{
                break;
            }
        }
        return cnt;
    }

    //y를 기준으로 앞뒤롤 확인. - or +
    private int getLongestCol(int x, int y, int[][] candy) {
        int theCandy = candy[x][y],cnt=1;
        int left = y-1,right=y+1;
        //"PCPPP",
        while(left>=0||right<candy[0].length){
            if (left>=0&&candy[x][left]==theCandy){
                cnt++;
                left--;
            }
            else if (right<candy.length&&candy[x][right]==theCandy){
                cnt++;
                right++;
            }
            //둘 중하나도 걸리지 않는다면 break.
            else{
                break;
            }
        }
        return cnt;
    }

    private int[][] createCandy(String[] list) {
        int n = list.length;
        int[][] candy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (list[i].charAt(j)=='C'){
                    candy[i][j] = 1;
                }
                else if (list[i].charAt(j)=='P'){
                    candy[i][j] = 2;
                }
                else if (list[i].charAt(j)=='Z'){
                    candy[i][j] = 3;
                }
                else if (list[i].charAt(j)=='Y'){
                    candy[i][j] = 4;
                }
            }
        }
        return candy;
    }

    public static void main(String[] args) {
        int n =5;
        String[] list = {
                "YCPZY",
                "CPZZP",
                "PCPPP",
                "YCYZC",
                "CPPZZ",
        };
        CandyGame candyGame = new CandyGame();
        System.out.println(candyGame.sol(n,list));
    }
}
