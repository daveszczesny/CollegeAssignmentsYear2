
public class App {
    public App() {
        
        Tests test = new Tests();

        // test methods
        test.test_TimeTaken();
        test.test_TimeTakenBinary();
        test.test_OperationsPerformed();
        test.test_OperationsPerformedBinary();
        test.test_binaryPallinedromCheck();

    }

    public static void main(String[] args) {
        new App();
    }
}