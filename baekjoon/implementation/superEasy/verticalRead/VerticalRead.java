package baekjoon.implementation.superEasy.verticalRead;

public class VerticalRead {
    public String sol(String[] s) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < s.length; i++) {
                if (j<s[i].length()){
                    sb.append(s[i].charAt(j));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
//            System.out.println("??");
            if (sb.length()>0) sb.append("\n");
            sb.append("??");
        }
        System.out.println(sb);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
