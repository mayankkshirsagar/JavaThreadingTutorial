package lambdaexp;

public class IncrementByFiveLambda {

    public static void main(String[] args) {

        IncrementByFiveInterface incrementByFiveInterface = (num) -> {
            return num + 5;
        };
        IncrementByFiveInterface incrementByFiveInterface1 = (num) -> num + 5;
        IncrementByFiveInterface incrementByFiveInterface2 = num -> num + 5;

        System.out.println(incrementByFiveInterface.incrementByFive(5));
        System.out.println(incrementByFiveInterface1.incrementByFive(5));
        System.out.println(incrementByFiveInterface2.incrementByFive(5));


    }

}
