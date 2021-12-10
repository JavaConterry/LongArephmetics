public class MainClass {
    public static void main(String[] args) {

        var variable1 = new ArbitraryValue("5000");
        var variable2 = new ArbitraryValue("5000");
//        System.out.println(variable1.multiply(variable1, variable2));
//        System.out.println(variable1.IsSmallerOrEqual(variable2, variable1));
        var variable3 = new ArbitraryValue("9");
        var variable4 = new ArbitraryValue("3");
        System.out.println(variable3.minus(variable4));
        System.out.println(variable3.multiplyTwo(variable4, variable3));
        System.out.println(variable3.findIntResultOfDividingOn(variable4));
        System.out.println(variable3.IsSmallerOrEqual(variable1.multiply(variable4, new ArbitraryValue(4)), variable3));
    }
}
