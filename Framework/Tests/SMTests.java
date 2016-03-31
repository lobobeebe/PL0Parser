package tests;

import java.io.IOException;
import java.util.Set;

import beaver.Parser.Exception;
import AST.*;


public class SMTests extends PL0TestCase {

    /** test VarStar */
    public void testVarStar() throws IOException, Exception{
        Program p = parseFromFile("Tests/Data/TestSM.pl0");

    }
	
	/** tests based on the file testsrc/ae1.wh */
	public void testAEentry1() throws IOException, Exception {
		Program p = parseFromFile("Tests/Data/TestSM.pl0");
		assertEquals("Aexp* size", 3, p.AexpStar().size());
		assertTrue(p.getS() instanceof CompoundS);
		CompoundS body = (CompoundS)p.getS();
		AssignS stmt1 = (AssignS)body.getSList(0);
		Set<Expr> ae1n = stmt1.AEentry();
		assertEquals("ae1n", 0, ae1n.size());
		Set<Expr> ae1ex = stmt1.AEexit();
		assertEquals("ae1ex", 1, ae1ex.size());
		assertTrue("ae1ex value", ae1ex.contains(stmt1.getExpr()));
		AssignS stmt2 = (AssignS)body.getSList(1);
		Set<Expr> ae2n = stmt2.AEentry();
		assertEquals("ae2n", 1, ae2n.size());
		assertTrue("ae2n value", ae2n.contains(stmt1.getExpr()));
		Set<Expr> ae2ex = stmt2.AEexit();
		assertEquals("ae2ex", 2, ae2ex.size());
		assertTrue("ae2ex value", ae2ex.contains(stmt1.getExpr()));
		assertTrue(ae2ex.contains(stmt2.getExpr()));
		WhileS stmt3 = (WhileS)body.getSList(2);
		LabeledExpr test = stmt3.getLabeledExpr();
		Set<Expr> ae3n = test.AEentry();
		// System.out.println("ae3n is: " + ae3n.toString());
		assertEquals("ae3n", 1, ae3n.size());
		assertTrue(ae3n.contains(stmt1.getExpr()));
		Set<Expr> ae3ex = test.AEexit();
		assertEquals("ae3ex", 1, ae3ex.size());
		assertTrue(ae3ex.contains(stmt1.getExpr()));
		CompoundS whb = (CompoundS) stmt3.getS();
		AssignS stmt4 = (AssignS) whb.getSList(0);
		Set<Expr> ae4n = stmt4.AEentry();
		assertEquals("ae4n", 1, ae4n.size());
		assertTrue(ae4n.contains(stmt1.getExpr()));
		Set<Expr> ae4ex = stmt4.AEexit();
		assertEquals("ae4ex", 0, ae4ex.size());
		AssignS stmt5 = (AssignS) whb.getSList(1);
		Set<Expr> ae5n = stmt5.AEentry();
		assertEquals("ae5n", 0, ae5n.size());
		Set<Expr> ae5ex = stmt5.AEexit();
		assertEquals("ae5ex", 1, ae5ex.size());
		assertTrue(ae5ex.contains(stmt5.getExpr()));
	}

		/** tests based on the file testsrc/ae2.wh */
    	public void testAEentry2() throws IOException, Exception {
		Program p = parseFromFile("testsrc/ae2.wh");
		assertEquals("Aexp* size", 4, p.AexpStar().size());
                S bod = p.getS();
		assertTrue(bod instanceof CompoundS);
		CompoundS body = (CompoundS)bod;
		AssignS stmt1 = (AssignS)body.getSList(0);
		Set<Expr> ae1n = stmt1.AEentry();
		assertEquals("ae1n", 0, ae1n.size());
		Set<Expr> ae1ex = stmt1.AEexit();
                checkAEij(1, ae1ex);
                assertTrue(body.getSList(1) instanceof WhileS);
                WhileS stmt2 = (WhileS)body.getSList(1);
		LabeledExpr e2 = stmt2.getLabeledExpr();
		Set<Expr> ae2n = e2.AEentry();
                checkAEij(2, ae2n);
		Set<Expr> ae2ex = e2.AEexit();
                checkAEij(2, ae2ex);
		CompoundS whbod = (CompoundS)stmt2.getS();
                AssignS stmt3 = (AssignS)whbod.getSList(0);
		Set<Expr> ae3n = stmt3.AEentry();
		// System.out.println("ae3n is: " + ae3n.toString());
		checkAEij(3, ae3n);
		Set<Expr> ae3ex = stmt3.AEexit();
		assertEquals("ae3ex", 3, ae3ex.size());
		assertTrue(ae3ex.contains(stmt3.getExpr()));
		AssignS stmt4 = (AssignS) whbod.getSList(1);
		Set<Expr> ae4n = stmt4.AEentry();
		assertEquals("ae4n", 3, ae4n.size());
		assertTrue(ae3ex.contains(stmt3.getExpr()));
		Set<Expr> ae4ex = stmt4.AEexit();
		assertEquals("ae4ex", 1, ae4ex.size());
		assertTrue(ae3ex.contains(stmt3.getExpr()));
		AssignS stmt5 = (AssignS) whbod.getSList(2);
		Set<Expr> ae5n = stmt5.AEentry();
		assertEquals("ae5n", 1, ae5n.size());
		assertTrue(ae3ex.contains(stmt3.getExpr()));
		Set<Expr> ae5ex = stmt5.AEexit();
		checkAEij(5, ae5ex);
	}

	/** check that the set contains the expressions i*j-1 and i*j. */
	protected void checkAEij(int lab, Set<Expr> aes) {
		assertEquals("label " + lab + " aes size in checkAEij for " + aes.toString(),
                	     2, aes.size());
                for (Expr e : aes) {
                    String estr = e.toString();
                    assertTrue("label " + lab + " values for " + aes.toString(), 
                               estr.equals("((i * j) - 1)")
                               || estr.equals("(i * j)"));
                }
	}

	public AETests(String s) {
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
