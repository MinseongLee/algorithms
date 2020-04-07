package baekjoon.implementation.superEasy.angry;

public class Angry {
    public String sol(int n,int w,int h,int[] stickLen){
        int[] len = new int[3];
        len[0] = w;
        len[1] = h;
        len[2] = (int)Math.sqrt(w*w+h*h);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            if (sb.length()>0) sb.append("\n");
            for (int j = 0; j < 3; j++) {
                if (stickLen[i]<=len[j]){
                    sb.append("DA");
                    ok = false;
                    break;
                }
            }
            if (ok) {
                sb.append("NE");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int n = 5;
        int w=3,h=4;
        int[] list ={3,4,5,6,7};
        Angry angry = new Angry();
        System.out.println(angry.sol(n,w,h,list));
    }
}
