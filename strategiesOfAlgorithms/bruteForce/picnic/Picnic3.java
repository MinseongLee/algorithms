package strategiesOfAlgorithms.bruteForce.picnic;

public class Picnic3 {
    private static int best;
    public static int sol(int n,int[] friends){
        boolean[][] areFriends = createFriends(friends,n);
        boolean[] visited = new boolean[n];
        countingPair(areFriends,visited,n);
        return best;
    }

    private static void countingPair(boolean[][] areFriends, boolean[] visited,int n) {
        int here = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                here=i;
                break;
            }
        }
        if (here==-1) {
            best++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (areFriends[here][i]&&!visited[i]){
                visited[here] = true;
                visited[i] = true;
                countingPair(areFriends,visited,n);
                visited[here] = false;
                visited[i] = false;
            }
        }
    }


    private static boolean[][] createFriends(int[] friends, int n) {
        boolean[][] areFriends = new boolean[n][n];
        //even always
        for (int i = 0; i < friends.length; i+=2) {
            areFriends[friends[i]][friends[i+1]] = true;
            areFriends[friends[i+1]][friends[i]] = true;
        }
        return areFriends;
    }

    public static void main(String[] args) {
        int n = 6;
        int m = 10;
        int[] friends = {0,1,0,2,1,2,1,3,1,4,2,3,2,4,3,4,3,5,4,5};
        System.out.println(sol(n,friends));
    }
}
