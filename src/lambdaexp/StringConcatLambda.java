package lambdaexp;

public class StringConcatLambda {
    public static void main(String[] args) {

        // old way 1
        StringConcatTraditional stringConcatTraditional = new StringConcatTraditional();
        System.out.println(stringConcatTraditional.concat("hello", "world"));

        // old way 2
        StringConcatFunctionalInterface oldInterface = new StringConcatFunctionalInterface() {
            @Override
            public String concat(String s1, String s2) {
                return s1 + " " + s2;
            }
        };
        System.out.println(oldInterface.concat("hello", "world"));

        // lambda way
        StringConcatFunctionalInterface stringConcatFunctionalInterface = (s1, s2) -> s1 + " " + s2;
        System.out.println(stringConcatFunctionalInterface.concat("hello", "world"));
    }
}

class StringConcatTraditional implements StringConcatFunctionalInterface {

    @Override
    public String concat(String s1, String s2) {
        return s1 + " " + s2;
    }
}
