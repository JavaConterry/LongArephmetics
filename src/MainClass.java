public class MainClass {
    public static void main(String[] args) {

        var variable1 = new ArbitraryValue("99999999999999");
        var variable2 = new ArbitraryValue("100000000000");
        System.out.println(variable1.multiply(variable1, variable2));
        System.out.println(variable1.IsSmallerOrEqual(variable2, variable1));
    }
}
