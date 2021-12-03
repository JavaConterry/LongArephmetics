public class MainClass {
    public static void main(String[] args) {

        var variable1 = new ArbitraryValue("99999999999999");
        var variable2 = new ArbitraryValue("100000000000");
        System.out.println(variable1.multiply(variable1, variable2));
        System.out.println(variable1.IsSmallerOrEqual(variable2, variable1));
        var variable3 = new ArbitraryValue("44");
        var variable4 = new ArbitraryValue("11");
        System.out.println(variable3.divide(variable3, variable4));
        var variable0 = new ArbitraryValue(0);
        System.out.println(variable0);
        System.out.println(variable0.sumOf(variable3, variable0));
    }
}
