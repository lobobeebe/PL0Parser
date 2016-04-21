package Tests;

import java.io.IOException;
import java.util.Set;
import beaver.Parser.Exception;
import AST.*;


public class TETests extends PL0TestCase
{
	/** tests based on the file Tests/Data/AnalysisTest2.pl0 */
	public void testTE() throws IOException, Exception
    {
		Program p = parseFromFile("Tests/Data/AnalysisTest2.pl0");
		ProgramBlock pb = p.getProgramBlock();

        // Initialize ABinaryExprs
        ABinaryExpr onePlusE = new ABinaryExpr(new NumLitExpr("1"), new Op_a("+"), new VarRefExpr("e"));
        ABinaryExpr onePlusEDivD = new ABinaryExpr(new ABinaryExpr(new NumLitExpr("1"), new Op_a("+"), new VarRefExpr("e")), new Op_a("/"), new VarRefExpr("d"));
        ABinaryExpr bPlusA = new ABinaryExpr(new VarRefExpr("b"), new Op_a("+"), new VarRefExpr("a"));
        ABinaryExpr bPlusAMultOnePlusE = new ABinaryExpr(
            new ABinaryExpr(new VarRefExpr("b"), new Op_a("+"), new VarRefExpr("a")),
            new Op_a("*"),
            new ABinaryExpr(new NumLitExpr("1"), new Op_a("+"), new VarRefExpr("e")));
        ABinaryExpr cMinusE = new ABinaryExpr(new VarRefExpr("c"), new Op_a("-"), new VarRefExpr("e"));

        Set<ABinaryExpr> AExprStar = pb.AExprStar();
        assertEquals("AExprStar", 5, AExprStar.size());
        List<ABinaryExpr> aExprs = new List<>();
        aExprs.add(onePlusE);
        aExprs.add(onePlusEDivD);
        aExprs.add(bPlusA);
        aExprs.add(bPlusAMultOnePlusE);
        aExprs.add(cMinusE);

        for(ABinaryExpr aExpr : aExprs)
        {
            assertTrue("AExprStar should contain " + aExpr.unparse() + ": " + AExprStar, AExprStar.contains(aExpr));
        }
    }

	public TETests(String s)
    {
		super(s);
	}

	/** return a test suite for this class. */
	public static junit.framework.Test suite()
    {
		return new junit.framework.TestSuite(SVTests.class);
	}

	/** run the tests in text mode .*/
	public static void main(String args[])
    {
		if (args.length == 1 && args[0].equals("-text"))
        {
			junit.textui.TestRunner.run(SVTests.class);
		}
        else
        {
			junit.swingui.TestRunner.run(SVTests.class);
		}
	}
}
