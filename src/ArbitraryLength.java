import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbitraryLength {



    public List<Integer> arbitrary = new ArrayList<>();
//    private int rank;



    public ArbitraryLength(){}

    public ArbitraryLength(List<Integer> value){
        arbitrary = value;
    }

    public ArbitraryLength(Integer value){
        List<Integer> ReversedArbitrary = new ArrayList<Integer>();
        while(value!=0){
            ReversedArbitrary.add((int)(value%10));
            value/=10;
        }
        for(int i=ReversedArbitrary.size()-1; i>=0; i--){
            arbitrary.add(ReversedArbitrary.get(i));
        }
    }

    public ArbitraryLength(long value){
        List<Integer> ReversedArbitrary = new ArrayList<Integer>();
        while(value!=0){
            ReversedArbitrary.add((int)(value%10));
            value/=10;
        }
        for(int i=ReversedArbitrary.size()-1; i>=0; i--){
            arbitrary.add(ReversedArbitrary.get(i));
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
            int CurrentChar = value.charAt(i);
            if(CurrentChar<48 && CurrentChar>57){
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

    public int length(){
        return this.arbitrary.size();
    }

    public ArbitraryLength sumOf(ArbitraryLength... arguments) {
        if (arguments.length == 1) {
            return arguments[0];
        }
        if (arguments.length == 2) {
            return MakeSumOfTwo(arguments[0], arguments[1]);
        } else {
            ArbitraryLength TemporaryArgument = arguments[0];
            for (int i = 0; i < arguments.length - 1; i++) {
                TemporaryArgument = MakeSumOfTwo(TemporaryArgument, arguments[i + 1]);
            }
            return TemporaryArgument;
        }
    }

    public ArbitraryLength MakeSumOfTwo(ArbitraryLength first, ArbitraryLength second) {
        int MinLength = Math.min(first.length(), second.length());
        int MaxLength = Math.max(first.length(), second.length());
        int Delta = MaxLength - MinLength;
        List<Integer> ReversedFirst = first.arbitrary;
        Collections.reverse((ReversedFirst));
        List<Integer> ReversedSecond = second.arbitrary;
        Collections.reverse((ReversedSecond));
        int Adder = 0;

        if(MinLength == first.length()){
            for(int i=0; i<Delta; i++){
                ReversedFirst.add(0);
            }
        }
        else {
            for(int i=0; i<Delta; i++){
                ReversedSecond.add(0);
            }
        }

        List<Integer> newReversedArbitrary = new ArrayList<Integer>();
        for(int i=0; i<MaxLength; i++){
            newReversedArbitrary.add(ReversedFirst.get(i) + ReversedSecond.get(i));
        }

        for (int i=0; i<MaxLength; i++){
            int CurrentValue = newReversedArbitrary.get(i);
            if(Adder == 1){
                CurrentValue += 1;
                Adder = 0;
            }
            if(CurrentValue>=10){
                Adder = 1;
                CurrentValue-=10;
            }
            newReversedArbitrary.set(i, CurrentValue);
        }
        if(Adder == 1){
            newReversedArbitrary.add(1);
        }

        Collections.reverse(newReversedArbitrary);
        return new ArbitraryLength(newReversedArbitrary);
    }
}
