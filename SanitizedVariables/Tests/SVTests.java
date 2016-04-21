package Tests;

import java.io.IOException;
import java.util.Set;
import beaver.Parser.Exception;
import AST.*;


public class SVTests extends PL0TestCase
{

	/** tests based on the file Tests/Data/TestSV.pl0 */
	public void testSVentry1() throws IOException, Exception
    {
		Program p = parseFromFile("Tests/Data/TestSV.pl0");
		ProgramBlock pb = p.getProgramBlock();
        assertEquals("Var* size", 5, pb.VarStar().size());

        //Check each statements entry and exit

        //============================================================================**
        // Variables
        //============================================================================**

        // var a
        VarS varA = (VarS)pb.getVar(0);
        Set<String> varAen = varA.SVentry();
        assertEquals("varAen", 0, varAen.size());
        Set<String> varAex = varA.SVexit();
        assertEquals("varAex", 0, varAex.size());

        // var b
        VarS varB = (VarS)pb.getVar(1);
        Set<String> varBen = varB.SVentry();
        assertEquals("varBen", 0, varBen.size());
        Set<String> varBex = varB.SVexit();
        assertEquals("varBex", 0, varBex.size());

        // var c
        VarS varC = (VarS)pb.getVar(2);
        Set<String> varCen = varC.SVentry();
        assertEquals("varCen", 0, varCen.size());
        Set<String> varCex = varC.SVexit();
        assertEquals("varCex", 0, varCex.size());

        // var d
        VarS varD = (VarS)pb.getVar(3);
        Set<String> varDen = varD.SVentry();
        assertEquals("varDen", 0, varDen.size());
        Set<String> varDex = varD.SVexit();
        assertEquals("varDex", 0, varDex.size());

        // var e
        VarS varE = (VarS)pb.getVar(4);
        Set<String> varEen = varE.SVentry();
        assertEquals("varEen", 0, varEen.size());
        Set<String> varEex = varE.SVexit();
        assertEquals("varEex", 0, varEex.size());

        //============================================================================**
        // Procedure readAll
        //============================================================================**

        ProcS procReadAll = (ProcS) pb.getProc(0);
        ProgramBlock procReadAllPB = procReadAll.getBlock();
        BeginEndS procReadAllBeginEndS = (BeginEndS) procReadAllPB.getS();

        // read a
        ReadS readA = (ReadS) procReadAllBeginEndS.getS(0);
        Set<String> readAen = readA.SVentry();
        assertEquals("readAen", 0, readAen.size());
        Set<String> readAex = readA.SVexit();
        assertEquals("readAex", 0, readAex.size());

        // read b
        ReadS readB = (ReadS) procReadAllBeginEndS.getS(1);
        Set<String> readBen = readB.SVentry();
        assertEquals("readBen", 0, readBen.size());
        Set<String> readBex = readB.SVexit();
        assertEquals("readBex", 0, readBex.size());

        // read c
        ReadS readC = (ReadS) procReadAllBeginEndS.getS(2);
        Set<String> readCen = readC.SVentry();
        assertEquals("readCen", 0, readCen.size());
        Set<String> readCex = readC.SVexit();
        assertEquals("readCex", 0, readCex.size());

        // read d
        ReadS readD = (ReadS) procReadAllBeginEndS.getS(3);
        Set<String> readDen = readD.SVentry();
        assertEquals("readDen", 0, readDen.size());
        Set<String> readDex = readD.SVexit();
        assertEquals("readDex", 0, readDex.size());

        // read e
        ReadS readE = (ReadS) procReadAllBeginEndS.getS(4);
        Set<String> readEen = readE.SVentry();
        assertEquals("readEen", 0, readEen.size());
        Set<String> readEex = readE.SVexit();
        assertEquals("readEex", 0, readEex.size());

        //============================================================================**
        // Procedure sanitizeAll
        //============================================================================**

        ProcS procSanitizeAll = (ProcS) pb.getProc(1);
        ProgramBlock procSanitizeAllPB = procSanitizeAll.getBlock();
        BeginEndS procSanitizeAllBeginEndS = (BeginEndS) procSanitizeAllPB.getS();

        // sanitize a
        SanitizeS sanitizeA = (SanitizeS) procSanitizeAllBeginEndS.getS(0);
        Set<String> sanitizeAen = sanitizeA.SVentry();
        assertEquals("sanitizeAen", 0, sanitizeAen.size());
        Set<String> sanitizeAex = sanitizeA.SVexit();
        assertEquals("sanitizeAex", 1, sanitizeAex.size());
        assertTrue("sanitizeAex contains a", sanitizeAex.contains("a"));

        // sanitize b
        SanitizeS sanitizeB = (SanitizeS) procSanitizeAllBeginEndS.getS(1);
        Set<String> sanitizeBen = sanitizeB.SVentry();
        assertEquals("sanitizeBen", 1, sanitizeBen.size());
        assertTrue("sanitizeBen contains a", sanitizeBen.contains("a"));
        Set<String> sanitizeBex = sanitizeB.SVexit();
        assertEquals("sanitizeBex", 2, sanitizeBex.size());
        assertTrue("sanitizeBex contains a", sanitizeBex.contains("a"));
        assertTrue("sanitizeBex contains b", sanitizeBex.contains("b"));

        // sanitize c
        SanitizeS sanitizeC = (SanitizeS) procSanitizeAllBeginEndS.getS(2);
        Set<String> sanitizeCen = sanitizeC.SVentry();
        assertEquals("sanitizeCen", 2, sanitizeCen.size());
        assertTrue("sanitizeCen contains a", sanitizeCen.contains("a"));
        assertTrue("sanitizeCen contains b", sanitizeCen.contains("b"));
        Set<String> sanitizeCex = sanitizeC.SVexit();
        assertEquals("sanitizeCex", 3, sanitizeCex.size());
        assertTrue("sanitizeCex contains a", sanitizeCex.contains("a"));
        assertTrue("sanitizeCex contains b", sanitizeCex.contains("b"));
        assertTrue("sanitizeCex contains c", sanitizeCex.contains("c"));

        // sanitize d
        SanitizeS sanitizeD = (SanitizeS) procSanitizeAllBeginEndS.getS(3);
        Set<String> sanitizeDen = sanitizeD.SVentry();
        assertEquals("sanitizeDen", 3, sanitizeDen.size());
        assertTrue("sanitizeDen contains a", sanitizeDen.contains("a"));
        assertTrue("sanitizeDen contains b", sanitizeDen.contains("b"));
        assertTrue("sanitizeDen contains c", sanitizeDen.contains("c"));
        Set<String> sanitizeDex = sanitizeD.SVexit();
        assertEquals("sanitizeDex", 4, sanitizeDex.size());
        assertTrue("sanitizeDex contains a", sanitizeDex.contains("a"));
        assertTrue("sanitizeDex contains b", sanitizeDex.contains("b"));
        assertTrue("sanitizeDex contains c", sanitizeDex.contains("c"));
        assertTrue("sanitizeDex contains d", sanitizeDex.contains("d"));

        // sanitize e
        SanitizeS sanitizeE = (SanitizeS) procSanitizeAllBeginEndS.getS(4);
        Set<String> sanitizeEen = sanitizeE.SVentry();
        assertEquals("sanitizeEen", 4, sanitizeEen.size());
        assertTrue("sanitizeEen contains a", sanitizeEen.contains("a"));
        assertTrue("sanitizeEen contains b", sanitizeEen.contains("b"));
        assertTrue("sanitizeEen contains c", sanitizeEen.contains("c"));
        assertTrue("sanitizeEen contains d", sanitizeEen.contains("d"));
        Set<String> sanitizeEex = sanitizeE.SVexit();
        assertEquals("sanitizeEex", 5, sanitizeEex.size());
        assertTrue("sanitizeEex contains a", sanitizeEex.contains("a"));
        assertTrue("sanitizeEex contains b", sanitizeEex.contains("b"));
        assertTrue("sanitizeEex contains c", sanitizeEex.contains("c"));
        assertTrue("sanitizeEex contains d", sanitizeEex.contains("d"));
        assertTrue("sanitizeEex contains e", sanitizeEex.contains("e"));

        //============================================================================**
        // Procedure sanitizeSome
        //============================================================================**

        ProcS procSanitizeSome = (ProcS) pb.getProc(2);
        ProgramBlock procSanitizeSomePB = procSanitizeSome.getBlock();
        BeginEndS procSanitizeSomeBeginEndS = (BeginEndS) procSanitizeSomePB.getS();

        // sanitize a
        sanitizeA = (SanitizeS) procSanitizeSomeBeginEndS.getS(0);
        sanitizeAen = sanitizeA.SVentry();
        assertEquals("sanitizeAen", 0, sanitizeAen.size());
        sanitizeAex = sanitizeA.SVexit();
        assertEquals("sanitizeAex", 1, sanitizeAex.size());
        assertTrue("sanitizeAex contains a", sanitizeAex.contains("a"));

        // sanitize b
        sanitizeB = (SanitizeS) procSanitizeSomeBeginEndS.getS(1);
        sanitizeBen = sanitizeB.SVentry();
        assertEquals("sanitizeBen", 1, sanitizeBen.size());
        assertTrue("sanitizeBen contains a", sanitizeBen.contains("a"));
        sanitizeBex = sanitizeB.SVexit();
        assertEquals("sanitizeBex", 2, sanitizeBex.size());
        assertTrue("sanitizeBex contains a", sanitizeBex.contains("a"));
        assertTrue("sanitizeBex contains b", sanitizeBex.contains("b"));

        // sanitize c
        sanitizeC = (SanitizeS) procSanitizeSomeBeginEndS.getS(2);
        sanitizeCen = sanitizeC.SVentry();
        assertEquals("sanitizeCen", 2, sanitizeCen.size());
        assertTrue("sanitizeCen contains a", sanitizeCen.contains("a"));
        assertTrue("sanitizeCen contains b", sanitizeCen.contains("b"));
        sanitizeCex = sanitizeC.SVexit();
        assertEquals("sanitizeCex", 3, sanitizeCex.size());
        assertTrue("sanitizeCex contains a", sanitizeCex.contains("a"));
        assertTrue("sanitizeCex contains b", sanitizeCex.contains("b"));
        assertTrue("sanitizeCex contains c", sanitizeCex.contains("c"));

        //============================================================================**
        // Primary Program Block
        //============================================================================**

        BeginEndS primaryS = (BeginEndS) pb.getS();

        // call readAll 1
        CallS callReadAll1 = (CallS) primaryS.getS(0);
        Set<String> callReadAll1En = callReadAll1.SVentry();
        assertEquals("callReadAll1En", 0, callReadAll1En.size());
        Set<String> callReadAll1Ex = callReadAll1.SVexit();
        assertEquals("callReadAll1Ex", 0, callReadAll1Ex.size());

        // b := 1
        AssignS b1 = (AssignS) primaryS.getS(1);
        Set<String> b1en = b1.SVentry();
        assertEquals("b1en", 0, b1en.size());
        Set<String> b1ex = b1.SVexit();
        assertEquals("b1ex", 0, b1ex.size());

        // d := (b + a)
        AssignS dba = (AssignS) primaryS.getS(2);
        Set<String> dbaen = dba.SVentry();
        assertEquals("dbaen", 0, dbaen.size());
        Set<String> dbaex = dba.SVexit();
        assertEquals("dbaex", 0, dbaex.size());

        // call sanitizeAll
        CallS callSanitizeAll = (CallS) primaryS.getS(3);
        Set<String> callSanitizeAllen = callSanitizeAll.SVentry();
        assertEquals("callSanitizeAllen", 0, callSanitizeAllen.size());
        Set<String> callSanitizeAllex = callSanitizeAll.SVexit();
        assertEquals("callSanitizeAllex", 5, callSanitizeAllex.size());
        assertTrue("callSanitizeAllex contains a", callSanitizeAllex.contains("a"));
        assertTrue("callSanitizeAllex contains b", callSanitizeAllex.contains("b"));
        assertTrue("callSanitizeAllex contains c", callSanitizeAllex.contains("c"));
        assertTrue("callSanitizeAllex contains d", callSanitizeAllex.contains("d"));
        assertTrue("callSanitizeAllex contains e", callSanitizeAllex.contains("e"));

        // if c - e < b + a then
        IfS ifS = (IfS) primaryS.getS(4);

        // call readAll 2
        CallS callReadAll2 = (CallS) ifS.getS();
        Set<String> callReadAll2En = callReadAll2.SVentry();
        assertEquals("callReadAll2En", 5, callReadAll2En.size());
        assertTrue("callReadAll2En contains a", callReadAll2En.contains("a"));
        assertTrue("callReadAll2En contains b", callReadAll2En.contains("b"));
        assertTrue("callReadAll2En contains c", callReadAll2En.contains("c"));
        assertTrue("callReadAll2En contains d", callReadAll2En.contains("d"));
        assertTrue("callReadAll2En contains e", callReadAll2En.contains("e"));
        Set<String> callReadAll2Ex = callReadAll2.SVexit();
        assertEquals("callReadAll2Ex", 0, callReadAll2Ex.size());

        // call sanitizeSome
        CallS callSanitizeSome = (CallS) primaryS.getS(5);
        Set<String> callSanitizeSomeEn = callSanitizeSome.SVentry();
        assertEquals("callSanitizeSomeEn", 0, callSanitizeSomeEn.size());
        Set<String> callSanitizeSomeEx = callSanitizeSome.SVexit();
        assertEquals("callSanitizeSomeEx", 3, callSanitizeSomeEx.size());
        assertTrue("callSanitizeSomeEx contains a", callSanitizeSomeEx.contains("a"));
        assertTrue("callSanitizeSomeEx contains b", callSanitizeSomeEx.contains("b"));
        assertTrue("callSanitizeSomeEx contains c", callSanitizeSomeEx.contains("c"));

        // sanitize d
        sanitizeD = (SanitizeS) primaryS.getS(6);
        Set<String> sanitizeDEn = sanitizeD.SVentry();
        assertEquals("sanitizeDEn", 3, sanitizeDEn.size());
        assertTrue("sanitizeDEn contains a", sanitizeDEn.contains("a"));
        assertTrue("sanitizeDEn contains b", sanitizeDEn.contains("b"));
        assertTrue("sanitizeDEn contains c", sanitizeDEn.contains("c"));
        Set<String> sanitizeDEx = sanitizeD.SVexit();
        assertEquals("sanitizeDEx", 4, sanitizeDEx.size());
        assertTrue("sanitizeDEx contains a", sanitizeDEx.contains("a"));
        assertTrue("sanitizeDEx contains b", sanitizeDEx.contains("b"));
        assertTrue("sanitizeDEx contains c", sanitizeDEx.contains("c"));
        assertTrue("sanitizeDEx contains d", sanitizeDEx.contains("d"));

        // sanitize e
        sanitizeE = (SanitizeS) primaryS.getS(7);
        Set<String> sanitizeEEn = sanitizeE.SVentry();
        assertEquals("sanitizeEEn", 4, sanitizeEEn.size());
        assertTrue("sanitizeEEn contains a", sanitizeEEn.contains("a"));
        assertTrue("sanitizeEEn contains b", sanitizeEEn.contains("b"));
        assertTrue("sanitizeEEn contains c", sanitizeEEn.contains("c"));
        assertTrue("sanitizeEEn contains d", sanitizeEEn.contains("d"));
        Set<String> sanitizeEEx = sanitizeE.SVexit();
        assertEquals("sanitizeEEx", 5, sanitizeEEx.size());
        assertTrue("sanitizeEEx contains a", sanitizeEEx.contains("a"));
        assertTrue("sanitizeEEx contains b", sanitizeEEx.contains("b"));
        assertTrue("sanitizeEEx contains c", sanitizeEEx.contains("c"));
        assertTrue("sanitizeEEx contains d", sanitizeEEx.contains("d"));
        assertTrue("sanitizeEEx contains e", sanitizeEEx.contains("e"));

        /*
        ProcS stmt4 = (ProcS)pb.getProc(0);
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
