package Tests;

import java.io.IOException;
import java.util.Set;
import beaver.Parser.Exception;
import AST.*;


public class AETests extends PL0TestCase
{

	/** tests based on the file Tests/Data/AnalysisTest2.pl0 */
	public void testAE() throws IOException, Exception
    {
		Program p = parseFromFile("Tests/Data/AnalysisTest2.pl0");
		ProgramBlock pb = p.getProgramBlock();


        //============================================================================**
        // Initialize Expressions
        //============================================================================**

        Set<ABinaryExpr> AExprStar = pb.AExprStar();
        ABinaryExpr cPlusB = new ABinaryExpr(new VarRefExpr("c"), new Op_a("+"), new VarRefExpr("b"));
        ABinaryExpr aPlusB = new ABinaryExpr(new VarRefExpr("a"), new Op_a("+"), new VarRefExpr("b"));
        ABinaryExpr cMinusB = new ABinaryExpr(new VarRefExpr("c"), new Op_a("-"), new VarRefExpr("b"));
        ABinaryExpr aPlusBDivCMinusB = new ABinaryExpr(aPlusB, new Op_a("/"), cMinusB);

        assertEquals("AExprStar()", 4, AExprStar.size());
        List<ABinaryExpr> aExprs = new List<>();
        aExprs.add(cPlusB);
        aExprs.add(aPlusB);
        aExprs.add(cMinusB);
        aExprs.add(aPlusBDivCMinusB);

        for (ABinaryExpr aExpr : aExprs)
        {
            assertTrue("AExprStar should contain " + aExpr.unparse() + ": " + AExprStar, AExprStar.contains(aExpr));
        }


        //Check each statements entry and exit

        //============================================================================**
        // Variables
        //============================================================================**

        // var a
        VarS varA = (VarS)pb.getVar(0);
        Set<ABinaryExpr> varAen = varA.AEentry();
        assertEquals("varAen", 0, varAen.size());
        Set<ABinaryExpr> varAex = varA.AEexit();
        assertEquals("varAex", 0, varAex.size());

        // var b
        VarS varB = (VarS)pb.getVar(1);
        Set<ABinaryExpr> varBen = varB.AEentry();
        assertEquals("varBen", 0, varBen.size());
        Set<ABinaryExpr> varBex = varB.AEexit();
        assertEquals("varBex", 0, varBex.size());

        // var c
        VarS varC = (VarS)pb.getVar(2);
        Set<ABinaryExpr> varCen = varC.AEentry();
        assertEquals("varCen", 0, varCen.size());
        Set<ABinaryExpr> varCex = varC.AEexit();
        assertEquals("varCex", 0, varCex.size());

        //============================================================================**
        // Procedure stuff
        //============================================================================**

        ProcS procStuff = (ProcS) pb.getProc(0);
        ProgramBlock procStuffPB = procStuff.getBlock();
        BeginEndS procStuffBeginEndS = (BeginEndS) procStuffPB.getS();

        // a := c + b
        AssignS aCB = (AssignS) procStuffBeginEndS.getS(0);
        Set<ABinaryExpr> aCBEn = aCB.AEentry();
        assertEquals("aCBEn", 0, aCBEn.size());
        Set<ABinaryExpr> aCBEx = aCB.AEexit();
        assertEquals("aCBEx", 1, aCBEx.size());
        assertTrue("aCBEx should contain " + cPlusB.unparse() + ": " + aCBEx, aCBEx.contains(cPlusB));

        //============================================================================**
        // Primary Program Block
        //============================================================================**

        BeginEndS primaryS = (BeginEndS) pb.getS();

        // c := a + b
        AssignS cAB = (AssignS) primaryS.getS(0);
        Set<ABinaryExpr> cABEn = cAB.AEentry();
        assertEquals("cABEn", 0, cABEn.size());
        Set<ABinaryExpr> cABEx = cAB.AEexit();
        assertEquals("cABEx", 1, cABEx.size());
        assertTrue("cABEx should contain " + aPlusB.unparse() + ": " + cABEx, cABEx.contains(aPlusB));

        // call stuff
        CallS callStuff = (CallS) primaryS.getS(1);
        Set<ABinaryExpr> callStuffEn = callStuff.AEentry();
        assertEquals("callStuffEn", 1, callStuffEn.size());
        assertTrue("callStuffEn should contain " + aPlusB.unparse() + ": " + callStuffEn, callStuffEn.contains(aPlusB));
        Set<ABinaryExpr> callStuffEx = callStuff.AEexit();
        assertEquals("callStuffEx", 1, callStuffEx.size());
        assertTrue("callStuffEx should contain " + cPlusB.unparse() + ": " + callStuffEx, callStuffEx.contains(cPlusB));

        // a := (a + b) / (c - b);
        AssignS aABCB = (AssignS) primaryS.getS(2);
        Set<ABinaryExpr> aABCBEn = aABCB.AEentry();
        assertEquals("aABCBEn", 1, aABCBEn.size());
        assertTrue("aABCBEn should contain " + cPlusB.unparse(), aABCBEn.contains(cPlusB));
        Set<ABinaryExpr> aABCBEx = aABCB.AEexit();
        assertEquals("aABCBEx", 2, aABCBEx.size());
        assertTrue("aABCBEx should contain c + b", aABCBEx.contains(cPlusB));
        assertTrue("aABCBEx should contain c - b", aABCBEx.contains(cMinusB));

        // c := c - b
        AssignS cCB = (AssignS) primaryS.getS(3);
        Set<ABinaryExpr> cCBEn = cCB.AEentry();
        assertEquals("cCBEn", 2, cCBEn.size());
        assertTrue("cCBEn should contain " + cPlusB.unparse(), cCBEn.contains(cPlusB));
        assertTrue("cCBEn should contain " + cMinusB.unparse(), cCBEn.contains(cMinusB));
        Set<ABinaryExpr> cCBEx = cCB.AEexit();
        assertEquals("cCBEx", 0, cCBEx.size());
    }

	public AETests(String s)
    {
		super(s);
	}

	/** return a test suite for this class. */
	public static junit.framework.Test suite() {
		return new junit.framework.TestSuite(AETests.class);
	}

	/** run the tests in text mode .*/
	public static void main(String args[]) {
		if (args.length == 1 && args[0].equals("-text")) {
			junit.textui.TestRunner.run(AETests.class);
		} else {
			junit.swingui.TestRunner.run(AETests.class);
		}
	}
}
