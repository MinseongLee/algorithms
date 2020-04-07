package strategiesOfAlgorithms.divideAndConquer.quadTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuadTreeMain {
    public static void main(String[] args) {
        String quad = "xbwxwbbwb";
        //String quad = "xbwwb";
        List<Character> quads = new ArrayList<>();
        for (int i = 0; i < quad.length(); i++) {
            quads.add(quad.charAt(i));
        }
        Iterator<Character> it = quads.iterator();
        System.out.println(reverse(it));
    }

    private static String reverse(Iterator<Character> it) {
        char head = it.next();
        if (head=='b' || head=='w'){
            return String.valueOf(head);
        }
        String upperLeft = reverse(it);
        String upperRight = reverse(it);
        String lowerLeft = reverse(it);
        String lowerRight = reverse(it);
        return "x"+lowerLeft+lowerRight+upperLeft+upperRight;
    }
}
