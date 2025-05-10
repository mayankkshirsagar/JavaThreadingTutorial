package lambdaexp;

// used to define functional interface where it expects a SAM
// if we add more method, this annotation will throw error making sure there's just a single abstract method
@FunctionalInterface
public interface IncrementByFiveInterface {

    // abstract method with single parameter
    public int incrementByFive(int num);

}
