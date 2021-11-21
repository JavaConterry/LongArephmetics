public class MainClass {
    public static void main(String[] args) {

        var crash = new ArbitraryValue("21h3");
        System.out.println(crash.toString());

        var variable1 = new ArbitraryValue("0012");
        var variable2 = new ArbitraryValue("88");
        //var variable3 = new ArbitraryValue("5000000000000");

        var sum = ArbitraryValue.MakeSumOfTwo(variable1, variable2).toString();
        System.out.println(sum);

    }
}
