import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbitraryValue {

    private List<Integer> arbitrary = new ArrayList<>(); //contains reversed values
    private int rank = 10;

    public ArbitraryValue(List<Integer> value){
        List<Integer> copy = value;
        arbitrary = copy;
    }

    public ArbitraryValue(Integer value){
        this.arbitrary = IntegerToList(value);
    }

    public ArbitraryValue(String value){
        this.arbitrary = StringToList(value);
    }


    //too open with fields
    private void deleteZerosIfArbitraryValueStartsAtZero(){
        if(!IsSmallerOrEqual(this, new ArbitraryValue(1))) {
            List<Integer> copy = this.arbitrary;
            int i = copy.size() - 1;
            while (copy.get(i) == 0 && i!=0) {
                copy.remove(i);
                i--;
            }
            this.arbitrary = copy;
        }
    }

    private List<Integer> StringToList(String value) {
        List<Integer> ListOfRankedValues = new ArrayList<Integer>();
        if(checkIfStringIsNumber(value)){
            for(int i=value.length()-1; i>=0; i--){
                char charValue = value.charAt(i);
                int intedCharValue = Character.getNumericValue(charValue);
                ListOfRankedValues.add(intedCharValue);
            }
            deleteZerosIfArbitraryValueStartsAtZero();
        }
        return ListOfRankedValues;
    }

    private List<Integer> IntegerToList(Integer value){
        List<Integer> ListOfRankedValues = new ArrayList<Integer>();
        if(value>0) {
            List<Integer> ArbitraryList = new ArrayList<Integer>();
            while (value != 0) {
                ArbitraryList.add((int) (value % rank));
                value /= rank;
            }
            ListOfRankedValues = ArbitraryList;
        }
        else{
            ListOfRankedValues.add(0);
        }
        return ListOfRankedValues;
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
            //ToDo rank field unavailable
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


    private ArbitraryValue multiplyTwo(ArbitraryValue firstValue, ArbitraryValue secondValue){
        var CopyFirst = firstValue;
        var CopySecond = secondValue;
        var Result = new ArrayList<Integer>();
        var Sum = new ArbitraryValue(0);

        for(int j = 0; j<CopyFirst.arbitrary.size(); j++){
            Result.add(CopyFirst.arbitrary.get(j)*CopySecond.arbitrary.get(0));
        }
        Sum = ArbitraryValue.sumOf(Sum, new ArbitraryValue(Result));
        Result.clear();

        for(int i = 1; i<CopySecond.arbitrary.size(); i++){
            for(int j = 0; j<CopyFirst.arbitrary.size(); j++){
                Result.add(CopyFirst.arbitrary.get(j)*CopySecond.arbitrary.get(i));
            }
            for(int k = 0; k<i; k++) {
                Result.add(0, 0);
            }
            Sum = ArbitraryValue.sumOf(Sum, new ArbitraryValue(Result));
            Result.clear();
        }

        return Sum;
    }

    public Boolean IsSmallerOrEqual(ArbitraryValue smaller, ArbitraryValue bigger){
        if(smaller.length() < bigger.length()){
            return true;
        }
        else if(smaller.length() == bigger.length()){
            for(int i = smaller.length()-1; i >= 0 ; i--){
                if(smaller.arbitrary.get(i) > bigger.arbitrary.get(i)){
                    return false;
                }
                else if(smaller.arbitrary.get(i) < bigger.arbitrary.get(i)){
                    return true;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public ArbitraryValue minus(ArbitraryValue second){

        ArbitraryValue CopyFirst = this;
        ArbitraryValue CopySecond = second;
        int MinLength = Math.min(CopyFirst.length(), CopySecond.length());
        int MaxLength = Math.max(CopyFirst.length(), CopySecond.length());
        int Delta = MaxLength - MinLength;
        int Minuser = 0;

        if(IsSmallerOrEqual(CopyFirst, CopySecond)){
            var rez = CopyFirst;
            CopyFirst = CopySecond;
            CopySecond = rez;
        }

        if(MinLength == CopyFirst.length()){
            for(int i=0; i<Delta; i++){
                CopyFirst.arbitrary.add(0);
            }
        }
        else {
            for(int i=0; i<Delta; i++){
                CopySecond.arbitrary.add(0);
            }
        }

        List<Integer> resultList = new ArrayList<Integer>();
        for(int i=0; i<MaxLength; i++){
            resultList.add(CopyFirst.arbitrary.get(i) - CopySecond.arbitrary.get(i));
        }

        for (int i=0; i<MaxLength; i++){
            int CurrentValue = resultList.get(i);
            if(Minuser == 1){
                CurrentValue -= 1;
                Minuser = 0;
            }
            if(CurrentValue < 0){
                Minuser = 1;
                CurrentValue += 10;
            }
            resultList.set(i, CurrentValue);
        }
        var result = new ArbitraryValue(resultList);
        result.deleteZerosIfArbitraryValueStartsAtZero();
        return new ArbitraryValue(resultList);
    }

    //FixMe bug
    public ArbitraryValue divide(ArbitraryValue first, ArbitraryValue second){
        ArbitraryValue CopyFirst = first;
        ArbitraryValue CopySecond = second;
        if(IsSmallerOrEqual(CopyFirst, CopySecond)){
            var rez = CopyFirst;
            CopyFirst = CopySecond;
            CopySecond = rez;
        }
        var result = new ArbitraryValue(0);
        var factor = new ArbitraryValue(0);
        var reminder = new ArbitraryValue(0);


        if(CopyFirst.length()>1) {

            for (int i = CopyFirst.length() - 2; i >= 0; i--) {
                var CurrentValue = getTwoLastOf(CopyFirst);
                while(IsSmallerOrEqual(multiplyTwo(CurrentValue, factor), CurrentValue)){
                    result = factor;
                    factor = sumOf(factor, new ArbitraryValue(1));
                }
                reminder = CurrentValue.minus(result);//remove 2 privious 10^length+reminder
                CopyFirst.arbitrary.remove(CopyFirst.length()-1);
                CopyFirst.arbitrary.remove(CopyFirst.length()-1);
                CopyFirst = multiplyTwo(CopyFirst, new ArbitraryValue((int)Math.pow(10, (reminder.length()-1))));
                CopyFirst = sumOf(CopyFirst, reminder);
            }
        }


        return result;
    }

    private ArbitraryValue getTwoLastOf(ArbitraryValue value){ //arbitrary value contains reversed list
        return new ArbitraryValue(value.arbitrary.get(value.length()-1)*rank+value.arbitrary.get(value.length()-2));
    }

}

