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
        assertEquals("Var* size", 3, pb.VarStar().size());

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
        /*
        assertEquals("sv5ex", 0, sv5ex.size());
        //beginEndS
        assertTrue(procb.getS() instanceof BeginEndS);
        BeginEndS body = (BeginEndS)procb.getS();
        //stmt6 SanitizeS
        SanitizeS stmt6 = (SanitizeS)body.getS(0);
        Set<String> sv6en = stmt6.SVentry();
        assertEquals("sv6en", 0, sv6en.size());
        Set<String> sv6ex = stmt6.SVexit();
        assertEquals("sv6ex", 1, sv6ex.size());
        assertTrue(sv6ex.contains(stmt6.getVar()));
        //stmt7 IfS
        IfS stmt7 = (IfS)body.getS(1);
        LabeledExpr condition = stmt7.getLabeledExpr();
        Set<String> sv7en = condition.SVentry();
        assertEquals("sv7en", 1, sv7en.size());
        Set<String> sv7ex = condition.SVexit();
        assertEquals("sv7ex", 1, sv7ex.size());
        //beginEndS
        assertTrue(stmt7.getS() instanceof BeginEndS);
        BeginEndS body2 = (BeginEndS)stmt7.getS();
        //stmt8 AssignS
        AssignS stmt8 = (AssignS)body2.getS(0);
        Set<String> sv8en = stmt8.SVentry();
        assertEquals("sv8en", 1, sv8en.size());
        Set<String> sv8ex = stmt8.SVexit();
        assertEquals("sv8ex", 1, sv8ex.size());
        //stmt9 PrintS
        PrintS stmt9 = (PrintS)body2.getS(1);
        Set<String> sv9en = stmt9.SVentry();
        assertEquals("sv9en", 1, sv9en.size());
        Set<String> sv9ex = stmt9.SVexit();
        assertEquals("sv9ex", 1, sv9ex.size());
        //stmt10 assignS
        AssignS stmt10 = (AssignS)body.getS(2);
        Set<String> sv10en = stmt10.SVentry();
        assertEquals("sv10en", 1, sv10en.size());
        Set<String> sv10ex = stmt10.SVexit();
        assertEquals("sv10ex", 1, sv10ex.size());
        //beginEndS
        assertTrue(pb.getS() instanceof BeginEndS);
        BeginEndS body3 = (BeginEndS)pb.getS();
        //stmt11 assignS
        AssignS stmt11 = (AssignS)body3.getS(0);
        Set<String> sv11en = stmt11.SVentry();
        assertEquals("sv11en", 0, sv11en.size());
        Set<String> sv11ex = stmt11.SVexit();
        assertEquals("sv11ex", 0, sv11ex.size());
        //stmt12 callS
        CallS stmt12 = (CallS)body3.getS(1);
        Set<String> sv12en = stmt12.SVentry();
        assertEquals("sv12en", 0, sv12en.size());
        Set<String> sv12ex = stmt12.SVexit();
        assertEquals("sv12ex", 0, sv12ex.size());
        //stmt13 While
        WhileS stmt13 = (WhileS)body3.getS(2);
        LabeledExpr condition2 = stmt13.getLabeledExpr();
        Set<String> sv13en = condition2.SVentry();
        assertEquals("sv13en", 1, sv13en.size());
        Set<String> sv13ex = condition2.SVexit();
        assertEquals("sv13ex", 1, sv13ex.size());
        //stmt14 sanitizeS
        SanitizeS stmt14 = (SanitizeS)stmt13.getS();
        Set<String> sv14en = stmt14.SVentry();
        assertEquals("sv14en", 1, sv14en.size());
        Set<String> sv14ex = stmt14.SVexit();
        assertEquals("sv14ex", 2, sv14ex.size());
        assertTrue(sv14ex.contains(stmt14.getVar()));
        assertTrue(sv14ex.contains(stmt6.getVar()));
        //stmt15 assignS
        AssignS stmt15 = (AssignS)body3.getS(3);
        Set<String> sv15en = stmt15.SVentry();
        assertEquals("sv15en", 1, sv15en.size());
        Set<String> sv15ex = stmt15.SVexit();
        assertEquals("sv15ex", 1, sv15ex.size());
        */
    }

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
