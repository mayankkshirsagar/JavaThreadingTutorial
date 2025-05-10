package lambdaexp;

/**
 * use lambda on the SAM HelloWorldInterface
 */
public class HelloWorldLambda {

    public static void main(String[] args) {

        HelloWorldInterface helloWorldInterface1 = () -> {
            return "hello world";
        };

        // more simplified version
        HelloWorldInterface helloWorldInterface2 = () -> "hello world";

        System.out.println(helloWorldInterface1.sayHelloWorld());
        System.out.println(helloWorldInterface2.sayHelloWorld());
    }
}
