package Tests;

import Tests.UnparseTests;

public class IoUnparseTests extends UnparseTests {

    private static final int NUM_TAINT_UNPARSE_TESTS = 4;

    public IoUnparseTests(String s) {
        super(s);
    }

    /** Compare the source file to the unparsed string of the AST. Matching proves correctness of AST. */
    public void testUnparse() {
        super.testUnparse();
        for (int i = 1; i <= NUM_TAINT_UNPARSE_TESTS; i++) {
            compareUnparse("Tests/Data/TestUnparseIo" + i + "_in.PL0",
                    "Tests/Data/TestUnparseIo" + i + "_out.PL0");
        }
    }

    public static junit.framework.Test suite() {
        return new junit.framework.TestSuite(Tests.IoUnparseTests.class);
    }

    public static void main(String args[]) {
        if (args.length == 1 && args[0].equals("-text")) {
            junit.textui.TestRunner.run(IoUnparseTests.class);
        } else {
            junit.swingui.TestRunner.run(IoUnparseTests.class);
        }
    }

}
