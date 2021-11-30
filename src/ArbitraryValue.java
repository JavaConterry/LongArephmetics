import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbitraryValue {

    private List<Integer> arbitrary = new ArrayList<>(); //contains reversed values
    private int rank = 10;

//ToDo take out the logic from the constructor
    public ArbitraryValue(List<Integer> value){
        List<Integer> copy = value;
        arbitrary = copy;
    }

    public ArbitraryValue(Integer value){
        List<Integer> ArbitraryList = new ArrayList<Integer>();
        while(value!=0){
            ArbitraryList.add((int)(value%10));
            value/=10;
        }
        arbitrary = ArbitraryList;
    }

    public ArbitraryValue(long value){
        List<Integer> ArbitraryList = new ArrayList<Integer>();
        while(value!=0){
            ArbitraryList.add((int)(value%10));
            value/=10;
        }
        arbitrary = ArbitraryList;
    }

//ToDO make exception for zero-only numer
    public ArbitraryValue(String value){
        if(checkIfStringIsNumber(value)){
            for(int i=value.length()-1; i>=0; i--){
                char charValue = value.charAt(i);
                int intedCharValue = Character.getNumericValue(charValue);
                arbitrary.add(intedCharValue);
            }
            deleteZerosIfArbitraryValueStartsAtZero();
        }
    }

    private void deleteZerosIfArbitraryValueStartsAtZero(){
        List<Integer> copy = arbitrary;
        int i = copy.size()-1;
        while(copy.get(i)==0){
            copy.remove(i);
            i--;
        }
        arbitrary = copy;
    }

    private Boolean checkIfStringIsNumber(String value){
        for(int i=0; i<value.length(); i++){
            int CurrentChar = value.charAt(i);
            if(CurrentChar<48 || CurrentChar>57){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        String result = "";
        for(int i=arbitrary.size()-1; i>=0;i--){
            result += arbitrary.get(i);
        }
        return result;
    }

    private int length(){
        return this.arbitrary.size();
    }

    public static ArbitraryValue sumOf(ArbitraryValue... arguments) {
        if (arguments.length == 1) {
            return arguments[0];
        }
        else if (arguments.length == 2) {
            return MakeSumOfTwo(arguments[0], arguments[1]);
        } else {
            ArbitraryValue TemporaryArgument = arguments[0];
            for (int i = 0; i < arguments.length - 1; i++) {
                TemporaryArgument = MakeSumOfTwo(TemporaryArgument, arguments[i + 1]);
            }
            return TemporaryArgument;
        }
    }

    private static ArbitraryValue MakeSumOfTwo(ArbitraryValue first, ArbitraryValue second) {
        int MinLength = Math.min(first.length(), second.length());
        int MaxLength = Math.max(first.length(), second.length());
        int Delta = MaxLength - MinLength;
        int Adder = 0;
        List<Integer> CopyFirst = first.arbitrary;
        List<Integer> CopySecond = second.arbitrary;

        if(MinLength == first.length()){
            for(int i=0; i<Delta; i++){
                CopyFirst.add(0);
            }
        }
        else {
            for(int i=0; i<Delta; i++){
                CopySecond.add(0);
            }
        }

        List<Integer> newArbitrary = new ArrayList<Integer>();
        for(int i=0; i<MaxLength; i++){
            newArbitrary.add(CopyFirst.get(i) + CopySecond.get(i));
        }

        for (int i=0; i<MaxLength; i++){
            int CurrentValue = newArbitrary.get(i);
            if(Adder == 1){
                CurrentValue += 1;
                Adder = 0;
            }
            if(CurrentValue>= 10){
                Adder = 1;
                CurrentValue-=10;
            }
            newArbitrary.set(i, CurrentValue);
        }

        if(Adder == 1) {
            newArbitrary.add(1);
        }

        return new ArbitraryValue(newArbitrary);
    }

    public ArbitraryValue multiply(ArbitraryValue... arguments){
        if (arguments.length == 1) {
            return arguments[0];
        }
        else if (arguments.length == 2) {
            return multiplyTwo(arguments[0], arguments[1]);
        } else {
            ArbitraryValue TemporaryArgument = arguments[0];
            for (int i = 0; i < arguments.length - 1; i++) {
                TemporaryArgument = multiplyTwo(TemporaryArgument, arguments[i + 1]);
            }
            return TemporaryArgument;
        }
    }


    public ArbitraryValue multiplyTwo(ArbitraryValue firstValue, ArbitraryValue secondValue){
        var CopyFirst = firstValue;
        var CopySecond = secondValue;
        var Result = new ArrayList<Integer>();
        var Sum = new ArbitraryValue(0);

        for(int j = 0; j<CopyFirst.arbitrary.size(); j++){
            Result.add(CopyFirst.arbitrary.get(j)*CopySecond.arbitrary.get(0));
            //System.out.println(Result);
        }
        Sum = ArbitraryValue.sumOf(Sum, new ArbitraryValue(Result));
        System.out.println(Sum);
        Result.clear();

        for(int i = 1; i<CopySecond.arbitrary.size(); i++){
            for(int j = 0; j<CopyFirst.arbitrary.size(); j++){
                Result.add(CopyFirst.arbitrary.get(j)*CopySecond.arbitrary.get(i));
                System.out.println(Result);
            }
            for(int k = 0; k<i; k++) {
                Result.add(0, 0);
            }
            Sum = ArbitraryValue.sumOf(Sum, new ArbitraryValue(Result));
            System.out.println(Sum);
            Result.clear();
        }

        return Sum;
    }


}

