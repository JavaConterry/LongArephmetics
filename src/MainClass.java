import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        var some = new ArbitraryLength("10");
        var some2 = new ArbitraryLength("190");
        var some3 = new ArbitraryLength("800");
        var some4 = new ArbitraryLength(9000);
        var sum = new ArbitraryLength().sumOf(some, some2, some3);
        sum.ToString();

    }
}
