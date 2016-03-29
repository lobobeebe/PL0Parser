package Tests;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import Utility.Squeezer;

import AST.*;

/**
 * JUnit (3) tests for unparse and prettyPrint attributes.
 * The expected results are in the .wh-prettyprint files.
 * These files were made by running WHILEPrettyPrinter on the
 * corresponding .wh file and then hand checking the results.
 */
public class UnparseTests extends PL0TestCase
{
    public UnparseTests(String s)
    {
        super(s);
    }

    /** The number of syntax test files in testsrc. */
    private static final int NUMTESTS = 6;

    /** Compare the source file to the unparsed string of the AST. Matching proves correctness of AST. */
    public void testUnparse() {
        for (int i = 1; i <= NUMTESTS; i++) {
            compareUnparse("Tests/Data/test" + i + "_in.PL0",
                    "Tests/Data/test" + i + "_out.PL0");
        }
    }

    /** Read the given file name into the returned string. */
    public String file2String(String fileName) throws IOException {
        File f = new File(fileName);
        long flen = f.length();
        byte[] bytes = new byte[(int) flen];
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f));
        bis.read(bytes);
        bis.close();
        return new String(bytes);
    }

    /** Compare the unparse of the sourceFile with the contents of the expectedFile. */
    protected void compareUnparse(String sourceFile, String expectedFile) {
        try {
            Program p = parseFromFile(sourceFile);
            String unparsedp = Squeezer.squeeze(p.unparse());
            String expectedString = Squeezer.squeeze(file2String(expectedFile));
            if (!unparsedp.equals(expectedString)) {
                fail("differences between " + sourceFile + "(" + unparsedp
                        + ") and " + expectedFile + "(" + expectedString + ")");
            }
        } catch (Throwable t) {
            fail(t.getMessage());
            throw new Error("This should not happen");
        }
    }

    public static junit.framework.Test suite() {
        return new junit.framework.TestSuite(Tests.UnparseTests.class);
    }

    public static void main(String args[]) {
        if (args.length == 1 && args[0].equals("-text")) {
            junit.textui.TestRunner.run(UnparseTests.class);
        } else {
            junit.swingui.TestRunner.run(UnparseTests.class);
        }
    }

}
