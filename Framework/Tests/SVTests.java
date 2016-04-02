package Tests;

import java.io.IOException;
import java.util.Set;
import beaver.Parser.Exception;
import AST.*;


public class SVTests extends PL0TestCase {

    /** test VarStar */
    /*
    public void testVarStar() throws IOException, Exception{
        Program p = parseFromFile("Tests/Data/TestSV.pl0");

    }
    */
	
	/** tests based on the file Tests/Data/TestSV.pl0 */
	public void testSVentry1() throws IOException, Exception {
		Program p = parseFromFile("Tests/Data/TestSV.pl0");
		ProgramBlock pb = p.getProgramBlock();
        assertEquals("Var* size", 3, pb().VarStar().size());

        //Check each statements entry and exit
        //stmt1 const
        ConstS stmt1 = (ConstS)pb.getConst(0);
        Set<String> sv1en = stmt1.SVentry();
        assertEquals("sv1en", 0, sv1en.size());
        Set<String> sv1ex = stmt1.SVexit();
        assertEquals("sv1ex", 0, sv1ex.size());
        //stmt2 var
        VarS stmt2 = (VarS)pb.getVar(0);
        Set<String> sv2en = stmt2.SVentry();
        assertEquals("sv2en", 0, sv2en.size());
        Set<String> sv2ex = stmt2.SVexit();
        assertEquals("sv2ex", 0, sv2ex.size());
        //stmt3 var
        VarS stmt3 = (VarS)pb.getVar(1);
        Set<String> sv3en = stmt3.SVentry();
        assertEquals("sv3en", 0, sv3en.size());
        Set<String> sv3ex = stmt3.SVexit();
        assertEquals("sv3ex", 0, sv3ex.size());
        //stmt4 proc
        ProcS stmt4 = (ProcS)pb.getProc(0);
        ProgramBlock procb = stmt4.getBlock();
        Set<String> sv4en = stmt4.SVentry();
        assertEquals("sv4en", 0, sv4en.size());
        Set<String> sv4ex = stmt4.SVexit();
        assertEquals("sv4ex", 0, sv4ex.size());
        //stmt5 var
        VarS stmt5 = (VarS)procb.getVar(0);
        Set<String> sv5en = stmt5.SVentry();
        assertEquals("sv5en", 0, sv5en.size());
        Set<String> sv5ex = stmt5.SVexit();
        assertEquals("sv5ex", 0, sv5ex.size());
        //beginEndS 
        assertTrue(procb.getS() instanceof BeginEndS);
        BeginEndS body = (BeginEndS)procb.getS();
        //stmt6 readS
        ReadS stmt6 = (ReadS)body.getS(0);
        Set<String> sv6en = stmt6.SVentry();
        assertEquals("sv6en", 0, sv6en.size());
        Set<String> sv6ex = stmt6.SVexit();
        assertEquals("sv6ex", 0, sv6ex.size());
        //stmt7 IfS
        IfS stmt7 = (IfS)body.getS(1);
        LabeledExpr condition = stmt7.getLabeledExpr();
        Set<String> sv7en = condition.SVentry();
        assertEquals("sv7en", 0, sv7en.size());
        Set<String> sv7ex = condition.SVexit();
        assertEquals("sv7ex", 0, sv7ex.size());
        //beginEndS
        assertTrue(stmt7.getS() instanceof BeginEndS);
        BeginEndS body2 = (BeginEndS)stmt7.getS();
        //stmt8 AssignS
        AssignS stmt8 = (AssignS)body2.getS(0);
        Set<String> sv8en = stmt8.SVentry();
        assertEquals("sv8en", 0, sv8en.size());
        Set<String> sv8ex = stmt8.SVexit();
        assertEquals("sv8ex", 0, sv8ex.size());
        //stmt9 PrintS
        PrintS stmt9 = (PrintS)body2.getS(1);
        Set<String> sv9en = stmt9.SVentry();
        assertEquals("sv9en", 0, sv9en.size());
        Set<String> sv9ex = stmt9.SVexit();
        assertEquals("sv9ex", 0, sv9ex.size());
        //stmt10 assignS
        AssignS stmt10 = (AssignS)body.getS(2);
        Set<String> sv10en = stmt10.SVentry();
        assertEquals("sv10en", 0, sv10en.size());
        Set<String> sv10ex = stmt10.SVexit();
        assertEquals("sv10ex", 0, sv10ex.size());
        //beginEndS
        assertTrue(pb.getS() instanceof BeginEndS);
        BeginEndS body = (BeginEndS)pb.getS();
        //stmt11 assignS
        AssignS stmt11 = (AssignS)body.getS(0);
        Set<String> sv11en = stmt11.SVentry();
        assertEquals("sv11en", 0, sv11en.size());
        Set<String> sv11ex = stmt11.SVexit();
        assertEquals("sv11ex", 0, sv11ex.size());
        //stmt12 callS
        CallS stmt12 = (CallS)body.getS(1);
        Set<String> sv12en = stmt12.SVentry();
        assertEquals("sv12en", 0, sv12en.size());
        Set<String> sv12ex = stmt12.SVexit();
        assertEquals("sv12ex", 0, sv12ex.size());
        //stmt13 While
        WhileS stmt13 = (WhileS)body.getS(2);
        LabeledExpr condition = stmt13.getLabeledExpr();
        Set<String> sv13en = condition.SVentry();
        assertEquals("sv13en", 0, sv13en.size());
        Set<String> sv13em = condition.SVexit();
        assertEquals("sv13ex", 0, sv13ex.size());
        //stmt14 sanitizeS
        SanitizeS stmt14 = (SanitizeS)stmt13.getS();
        Set<String> sv14en = stmt14.SVentry();
        assertEquals("sv14en", 0, sv14en.size());
        Set<String> sv14ex = stmt14.SVexit();
        assertEquals("sv14ex", 1, sv14ex.size());
        assertTrue(sv14ex.contains(stmt14.getLabeledRef().getVar()));
        //stmt15 assignS
        AssignS stmt15 = (AssignS)body.getS(3);
        Set<String> sv15en = stmt15.SVentry();
        assertEquals("sv15en", 1, sv15en.size());
        Set<String> sv11ex = stmt11.SVexit();
        assertEquals("sv15ex", 0, sv15ex.size());
    }

		/** tests based on the file testsrc/ae2.wh */
    	/*
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
    */
	/** check that the set contains the expressions i*j-1 and i*j. */
	/*
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
    */
	public SVTests(String s) {
		super(s);
	}
	
	/** return a test suite for this class. */
	public static junit.framework.Test suite() {
		return new junit.framework.TestSuite(SVTests.class);
	}

	/** run the tests in text mode .*/
	public static void main(String args[]) {
		if (args.length == 1 && args[0].equals("-text")) {
			junit.textui.TestRunner.run(SVTests.class);
		} else {
			junit.swingui.TestRunner.run(SVTests.class);
		}
	}
}
