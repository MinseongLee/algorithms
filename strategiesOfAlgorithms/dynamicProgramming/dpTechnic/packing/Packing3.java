package strategiesOfAlgorithms.dynamicProgramming.dpTechnic.packing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Packing3 {
    private int[][] cache;
    public String sol(int n,int w,String[] product){
        cache = new int[n+1][w+1];
        initCache();
        List<State> products = createProducts(product);
        int maxElement = maxWant(0,0,w,0,products);
        int maxElement2 = maxWant2(0,0,w,products);
        int maxElement3 = maxWantDp(0,0,w,products);
        List<String> productName = new ArrayList<>();
        reconstruct(0,0,w,products,productName);
        System.out.println(productName);
        return String.valueOf(maxElement)+" "+maxElement2+" "+maxElement3;
    }
    //아주 좋은 예제**
    private void reconstruct(int pick, int weight, int w, List<State> products, List<String> productName) {
        //모든 경우 다 고려함.
        if (pick==products.size()) return;
        //선택을 안했단 의미.
        if (maxWant2(pick+1,weight,w,products)==maxWant2(pick,weight,w,products)){
            reconstruct(pick+1,weight,w,products,productName);
        }
        //선택을 했단 의미.
        else{
            productName.add(products.get(pick).name);
            reconstruct(pick+1,weight+products.get(pick).volume,w,products,productName);
        }
    }

    private int maxWantDp(int pick, int weight, int w, List<State> products) {
        if (cache[pick][weight]>-1) return cache[pick][weight];
        if (pick==products.size()) return 0;
        //선택안하는 경우.
        cache[pick][weight] = maxWantDp(pick+1,weight,w,products);
        if (weight+products.get(pick).volume<=w){
            cache[pick][weight] = Math.max(cache[pick][weight],maxWantDp(pick+1,weight+products.get(pick).volume,w,products)+
                    products.get(pick).want);
        }
        return cache[pick][weight];
    }

    private void initCache() {
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i],-1);
        }
    }

    //O(2^n)
    //wants의 합계를 밖으로 꺼낸 경우
    private int maxWant2(int pick, int weight, int w, List<State> products) {
        if (pick==products.size()) {
            return 0;
        }
        //선택안하는 경우
        int ans = maxWant2(pick+1,weight,w,products);
        //하는 경우
        if (weight+products.get(pick).volume<=w){
            ans = Math.max(ans,maxWant2(pick+1,weight+products.get(pick).volume,w,products)+products.get(pick).want);
        }
        return ans;
    }
    //wants를 parameter로 계속 더하는 경우.
    private int maxWant(int pick, int weight, int w, int wants, List<State> products) {
        if (pick==products.size()){
            return wants;
        }
        //선택안하는 경우. 이땐 max를 넣어줄 필요가 없다.
        int ans = maxWant(pick+1,weight,w,wants,products);
        //선택하는 경우
        if (weight+products.get(pick).volume<=w){
            ans = Math.max(ans,maxWant(pick+1,weight+products.get(pick).volume,w,wants+products.get(pick).want,products));
        }
        return ans;
    }

    private List<State> createProducts(String[] product) {
        List<State> products = new ArrayList<>();
        for (int i = 0; i < product.length; i++) {
            String[] s = product[i].split(" ");
            products.add(new State(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2])));
        }
        return products;
    }

    public static void main(String[] args) {
        int n = 6;
        int w = 17;
        String[] product = {"encyclopedia 10 4","dumbell 2 5", "xbox 6 6",
                "grinder 4 7" , "camera 2 10","laptop 4 7"};
        Packing3 packing3 = new Packing3();
        System.out.println(packing3.sol(n,w,product));
    }
}
