package samsungSW.d3.searchSubstring;

/**
 * 한번 실패.. 왜냐하면 exception 처리때문.
 * 뒤에 -target.length()를 해주면서 +1까지 해줘야한다.
 */
public class SearchSubString {
    public static int searchSub(String target,String s){
        int counting = 0;
        //여기서 +1은 앞에 i값을 더해주는 것이다.
        for (int i = 0; i < s.length()-target.length()+1; i++) {
            if (target.equals(s.substring(i,target.length()+i))){
                counting++;
            }
        }
        return counting;
    }

    public static void main(String[] args) {
        String target = "es";
        String s = "Intheinitialstage,domesticgamesbasedonlineconcentratedongamedevelopmentfocusingonincomeforsomegenres.However,variouscontentsfocusingonsmartenvironmentandsocialnetworkareexpandedatpresentandgamematerialsaredevelopedformorevariousobjects.So,thisstudyintendstoexaminenewcategory,positivegame,fromtheaspectofgamedesignerforgameapproachbasedonvariousobjects.And,gameapproachingprocessinthecategorybasedonpleasurewasorganizedfromthestandpointofdesigner,forthedesignerapproachintheprecedentstageofpositivegamedevelopment.Fromtheaspectofdesigner,systemicityofgamecategoryanddesignapproacharenecessarytoexpandwire-wirelessenvironmentandnewenvironmentbasedontheconvergencemediatointeractivecontentsfocusingongames";
        System.out.println(searchSub(target,s));
    }
}
