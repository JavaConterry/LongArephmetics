import java.util.ArrayList;
import java.util.List;

public class ArbitraryLength {
    public List<Integer> arbitrary = new ArrayList<>();
//    private int rank;

    public ArbitraryLength(int value){
        List<Integer> reversedArbitrary = new ArrayList<Integer>();
        while(value!=0){
            reversedArbitrary.add((int)(value%10));
            value/=10;
        }
        for(int i=reversedArbitrary.size()-1; i>=0; i++){
            arbitrary.add(reversedArbitrary.get(i));
        }
    }

    public ArbitraryLength(long value){
        List<Integer> reversedArbitrary = new ArrayList<Integer>();
        while(value!=0){
            reversedArbitrary.add((int)(value%10));
            value/=10;
        }
        for(int i=reversedArbitrary.size()-1; i>=0; i++){
            arbitrary.add(reversedArbitrary.get(i));
        }

    }

    public ArbitraryLength(String value){
        if(checkIfStringIsNumber(value)){
            for(int i=0; i<value.length(); i++){
                char charValue = value.charAt(i);
                int intedCharValue = Character.getNumericValue(charValue);
                arbitrary.add(intedCharValue);
            }
        }

    }

    private Boolean checkIfStringIsNumber(String value){

        for(int i=0; i<value.length(); i++){
            int currentChar = value.charAt(i);
            if(currentChar<48 && currentChar>57){
                return false;
            }
        }
        return true;

    }

    public void ToString(){
        for(int i=0; i<arbitrary.size();i++){
            System.out.print(arbitrary.get(i));
        }
    }

}
