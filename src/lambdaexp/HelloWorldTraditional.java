package lambdaexp;

/**
 * old traditional 2 ways
 * 1. create object of HelloWorldInterface by overriding the sayHelloWorld and calling it using the interface object
 * 2. Implement the interface, override the method and create the object of the implementation class, call sayHelloWorld
 */
public class HelloWorldTraditional {

    public static void main(String[] args) {

        // traditional option 1
        HelloWorldInterface helloWorldInterface = new HelloWorldInterface() {
            @Override
            public String sayHelloWorld() {
                return "Hello world";
            }
        };
        System.out.println(helloWorldInterface.sayHelloWorld());

        // traditional option 2
        HelloWorldTraditional2 helloWorldTraditional2 = new HelloWorldTraditional2();
        System.out.println(helloWorldTraditional2.sayHelloWorld());
    }
}

class HelloWorldTraditional2 implements HelloWorldInterface {

    @Override
    public String sayHelloWorld() {
        return "hello world";
    }
}
