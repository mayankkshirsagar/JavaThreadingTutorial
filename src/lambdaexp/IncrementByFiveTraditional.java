package lambdaexp;

public class IncrementByFiveTraditional {
    public static void main(String[] args) {
        IncrementByFiveInterface incrementByFiveInterface = new IncrementByFiveInterface() {
            @Override
            public int incrementByFive(int num) {
                return num + 5;
            }
        };
        System.out.println(incrementByFiveInterface.incrementByFive(5));

        IncrementByFiveTraditional2 incrementByFiveTraditional2 = new IncrementByFiveTraditional2();
        System.out.println(incrementByFiveTraditional2.incrementByFive(5));
    }
}

class IncrementByFiveTraditional2 implements IncrementByFiveInterface{

    @Override
    public int incrementByFive(int num) {
        return num + 5;
    }
}
