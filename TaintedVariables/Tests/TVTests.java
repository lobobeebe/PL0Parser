package Tests;

import java.io.IOException;
import java.util.Set;
import beaver.Parser.Exception;
import AST.*;


public class TVTests extends PL0TestCase
{

	/** tests based on the file Tests/Data/AnalysisTest1.pl0 */
	public void testTV() throws IOException, Exception
    {
		Program p = parseFromFile("Tests/Data/AnalysisTest1.pl0");
		ProgramBlock pb = p.getProgramBlock();

        //Check each statements entry and exit

        //============================================================================**
        // Variables
        //============================================================================**

        // var a
        VarS varA = (VarS)pb.getVar(0);
        Set<String> varAen = varA.TVentry();
        assertEquals("varAen", 0, varAen.size());
        Set<String> varAex = varA.TVexit();
        assertEquals("varAex", 0, varAex.size());

        // var b
        VarS varB = (VarS)pb.getVar(1);
        Set<String> varBen = varB.TVentry();
        assertEquals("varBen", 0, varBen.size());
        Set<String> varBex = varB.TVexit();
        assertEquals("varBex", 0, varBex.size());

        // var c
        VarS varC = (VarS)pb.getVar(2);
        Set<String> varCen = varC.TVentry();
        assertEquals("varCen", 0, varCen.size());
        Set<String> varCex = varC.TVexit();
        assertEquals("varCex", 0, varCex.size());

        // var d
        VarS varD = (VarS)pb.getVar(3);
        Set<String> varDen = varD.TVentry();
        assertEquals("varDen", 0, varDen.size());
        Set<String> varDex = varD.TVexit();
        assertEquals("varDex", 0, varDex.size());

        // var e
        VarS varE = (VarS)pb.getVar(4);
        Set<String> varEen = varE.TVentry();
        assertEquals("varEen", 0, varEen.size());
        Set<String> varEex = varE.TVexit();
        assertEquals("varEex", 0, varEex.size());

        //============================================================================**
        // Procedure readAll
        //============================================================================**

        ProcS procReadAll = (ProcS) pb.getProc(0);
        ProgramBlock procReadAllPB = procReadAll.getBlock();
        BeginEndS procReadAllBeginEndS = (BeginEndS) procReadAllPB.getS();

        // read a
        ReadS readA = (ReadS) procReadAllBeginEndS.getS(0);
        Set<String> readAEn = readA.TVentry();
        assertEquals("readAEn", 0, readAEn.size());
        Set<String> readAEx = readA.TVexit();
        assertEquals("readAEx", 1, readAEx.size());
        assertTrue("readAEx contains a", readAEx.contains("a"));

        // read b
        ReadS readB = (ReadS) procReadAllBeginEndS.getS(1);
        Set<String> readBEn = readB.TVentry();
        assertEquals("readBEn", 1, readBEn.size());
        assertTrue("readBEn contains a", readBEn.contains("a"));
        Set<String> readBEx = readB.TVexit();
        assertEquals("readBEx", 2, readBEx.size());
        assertTrue("readBEx contains a", readBEx.contains("a"));
        assertTrue("readBEx contains b", readBEx.contains("b"));

        // read c
        ReadS readC = (ReadS) procReadAllBeginEndS.getS(2);
        Set<String> readCEn = readC.TVentry();
        assertEquals("readCEn", 2, readCEn.size());
        assertTrue("readCEn contains a", readCEn.contains("a"));
        assertTrue("readCEn contains b", readCEn.contains("b"));
        Set<String> readCEx = readC.TVexit();
        assertEquals("readCEx", 3, readCEx.size());
        assertTrue("readCEx contains a", readCEx.contains("a"));
        assertTrue("readCEx contains b", readCEx.contains("b"));
        assertTrue("readCEx contains c", readCEx.contains("c"));

        // read d
        ReadS readD = (ReadS) procReadAllBeginEndS.getS(3);
        Set<String> readDEn = readD.TVentry();
        assertEquals("readDEn", 3, readDEn.size());
        assertTrue("readDEn contains a", readDEn.contains("a"));
        assertTrue("readDEn contains b", readDEn.contains("b"));
        assertTrue("readDEn contains c", readDEn.contains("c"));
        Set<String> readDEx = readD.TVexit();
        assertEquals("readDEx", 4, readDEx.size());
        assertTrue("readDEx contains a", readDEx.contains("a"));
        assertTrue("readDEx contains b", readDEx.contains("b"));
        assertTrue("readDEx contains c", readDEx.contains("c"));
        assertTrue("readDEx contains d", readDEx.contains("d"));

        // read e
        ReadS readE = (ReadS) procReadAllBeginEndS.getS(4);
        Set<String> readEEn = readE.TVentry();
        assertEquals("readEEn", 4, readEEn.size());
        assertTrue("readEEn contains a", readEEn.contains("a"));
        assertTrue("readEEn contains b", readEEn.contains("b"));
        assertTrue("readEEn contains c", readEEn.contains("c"));
        assertTrue("readEEn contains d", readEEn.contains("d"));
        Set<String> readEEx = readE.TVexit();
        assertEquals("readEEx", 5, readEEx.size());
        assertTrue("readEEx contains a", readEEx.contains("a"));
        assertTrue("readEEx contains b", readEEx.contains("b"));
        assertTrue("readEEx contains c", readEEx.contains("c"));
        assertTrue("readEEx contains d", readEEx.contains("d"));
        assertTrue("readEEx contains e", readEEx.contains("e"));

        //============================================================================**
        // Procedure sanitizeAll
        //============================================================================**

        ProcS procSanitizeAll = (ProcS) pb.getProc(1);
        ProgramBlock procSanitizeAllPB = procSanitizeAll.getBlock();
        BeginEndS procSanitizeAllBeginEndS = (BeginEndS) procSanitizeAllPB.getS();

        // sanitize a
        SanitizeS sanitizeA = (SanitizeS) procSanitizeAllBeginEndS.getS(0);
        Set<String> sanitizeAen = sanitizeA.TVentry();
        assertEquals("sanitizeAen", 0, sanitizeAen.size());
        Set<String> sanitizeAex = sanitizeA.TVexit();
        assertEquals("sanitizeAex", 0, sanitizeAex.size());

        // sanitize b
        SanitizeS sanitizeB = (SanitizeS) procSanitizeAllBeginEndS.getS(1);
        Set<String> sanitizeBen = sanitizeB.TVentry();
        assertEquals("sanitizeBen", 0, sanitizeBen.size());
        Set<String> sanitizeBex = sanitizeB.TVexit();
        assertEquals("sanitizeBex", 0, sanitizeBex.size());

        // sanitize c
        SanitizeS sanitizeC = (SanitizeS) procSanitizeAllBeginEndS.getS(2);
        Set<String> sanitizeCen = sanitizeC.TVentry();
        assertEquals("sanitizeCen", 0, sanitizeCen.size());
        Set<String> sanitizeCex = sanitizeC.TVexit();
        assertEquals("sanitizeCex", 0, sanitizeCex.size());

        // sanitize d
        SanitizeS sanitizeD = (SanitizeS) procSanitizeAllBeginEndS.getS(3);
        Set<String> sanitizeDen = sanitizeD.TVentry();
        assertEquals("sanitizeDen", 0, sanitizeDen.size());
        Set<String> sanitizeDex = sanitizeD.TVexit();
        assertEquals("sanitizeDex", 0, sanitizeDex.size());

        // sanitize e
        SanitizeS sanitizeE = (SanitizeS) procSanitizeAllBeginEndS.getS(4);
        Set<String> sanitizeEen = sanitizeE.TVentry();
        assertEquals("sanitizeEen", 0, sanitizeEen.size());
        Set<String> sanitizeEex = sanitizeE.TVexit();
        assertEquals("sanitizeEex", 0, sanitizeEex.size());

        //============================================================================**
        // Procedure sanitizeSome
        //============================================================================**

        ProcS procSanitizeSome = (ProcS) pb.getProc(2);
        ProgramBlock procSanitizeSomePB = procSanitizeSome.getBlock();
        BeginEndS procSanitizeSomeBeginEndS = (BeginEndS) procSanitizeSomePB.getS();

        // sanitize a
        sanitizeA = (SanitizeS) procSanitizeSomeBeginEndS.getS(0);
        sanitizeAen = sanitizeA.TVentry();
        assertEquals("sanitizeAen", 0, sanitizeAen.size());
        sanitizeAex = sanitizeA.TVexit();
        assertEquals("sanitizeAex", 0, sanitizeAex.size());

        // sanitize b
        sanitizeB = (SanitizeS) procSanitizeSomeBeginEndS.getS(1);
        sanitizeBen = sanitizeB.TVentry();
        assertEquals("sanitizeBen", 0, sanitizeBen.size());
        sanitizeBex = sanitizeB.TVexit();
        assertEquals("sanitizeBex", 0, sanitizeBex.size());

        // sanitize c
        sanitizeC = (SanitizeS) procSanitizeSomeBeginEndS.getS(2);
        sanitizeCen = sanitizeC.TVentry();
        assertEquals("sanitizeCen", 0, sanitizeCen.size());
        sanitizeCex = sanitizeC.TVexit();
        assertEquals("sanitizeCex", 0, sanitizeCex.size());

        //============================================================================**
        // Primary Program Block
        //============================================================================**

        BeginEndS primaryS = (BeginEndS) pb.getS();

        // call readAll 1
        CallS callReadAll1 = (CallS) primaryS.getS(0);
        Set<String> callReadAll1En = callReadAll1.TVentry();
        assertEquals("callReadAll1En", 0, callReadAll1En.size());
        Set<String> callReadAll1Ex = callReadAll1.TVexit();
        assertEquals("callReadAll1Ex", 5, callReadAll1Ex.size());
        assertTrue("callReadAll1Ex contains a", callReadAll1Ex.contains("a"));
        assertTrue("callReadAll1Ex contains b", callReadAll1Ex.contains("b"));
        assertTrue("callReadAll1Ex contains c", callReadAll1Ex.contains("c"));
        assertTrue("callReadAll1Ex contains d", callReadAll1Ex.contains("d"));
        assertTrue("callReadAll1Ex contains e", callReadAll1Ex.contains("e"));

        // b := 1
        AssignS b1 = (AssignS) primaryS.getS(1);
        Set<String> b1En = b1.TVentry();
        assertEquals("b1En", 5, b1En.size());
        assertTrue("b1En contains a", b1En.contains("a"));
        assertTrue("b1En contains b", b1En.contains("b"));
        assertTrue("b1En contains c", b1En.contains("c"));
        assertTrue("b1En contains d", b1En.contains("d"));
        assertTrue("b1En contains e", b1En.contains("e"));
        Set<String> b1Ex = b1.TVexit();
        assertEquals("b1Ex", 4, b1Ex.size());
        assertTrue("b1Ex contains a", b1Ex.contains("a"));
        assertTrue("b1Ex contains c", b1Ex.contains("c"));
        assertTrue("b1Ex contains d", b1Ex.contains("d"));
        assertTrue("b1Ex contains e", b1Ex.contains("e"));

        // d := (b + a)
        AssignS dba = (AssignS) primaryS.getS(2);
        Set<String> dbaEn = dba.TVentry();
        assertEquals("dbaEn", 4, dbaEn.size());
        assertTrue("dbaEn contains a", dbaEn.contains("a"));
        assertTrue("dbaEn contains c", dbaEn.contains("c"));
        assertTrue("dbaEn contains d", dbaEn.contains("d"));
        assertTrue("dbaEn contains e", dbaEn.contains("e"));
        Set<String> dbaEx = dba.TVexit();
        assertEquals("dbaEx", 3, dbaEx.size());
        assertTrue("dbaEx contains a", dbaEx.contains("a"));
        assertTrue("dbaEx contains c", dbaEx.contains("c"));
        assertTrue("dbaEx contains e", dbaEx.contains("e"));

        // call sanitizeAll
        CallS callSanitizeAll = (CallS) primaryS.getS(3);
        Set<String> callSanitizeAllEn = callSanitizeAll.TVentry();
        assertEquals("callSanitizeAllEn", 3, callSanitizeAllEn.size());
        assertTrue("callSanitizeAllEn contains a", callSanitizeAllEn.contains("a"));
        assertTrue("callSanitizeAllEn contains c", callSanitizeAllEn.contains("c"));
        assertTrue("callSanitizeAllEn contains e", callSanitizeAllEn.contains("e"));
        Set<String> callSanitizeAllEx = callSanitizeAll.TVexit();
        assertEquals("callSanitizeAllEx", 0, callSanitizeAllEx.size());

        // if c - e < b + a then
        IfS ifS = (IfS) primaryS.getS(4);

        // call readAll 2
        CallS callReadAll2 = (CallS) ifS.getS();
        Set<String> callReadAll2En = callReadAll2.TVentry();
        assertEquals("callReadAll2En", 0, callReadAll2En.size());
        Set<String> callReadAll2Ex = callReadAll2.TVexit();
        assertEquals("callReadAll2Ex", 5, callReadAll2Ex.size());
        assertTrue("callReadAll2Ex contains a", callReadAll2Ex.contains("a"));
        assertTrue("callReadAll2Ex contains b", callReadAll2Ex.contains("b"));
        assertTrue("callReadAll2Ex contains c", callReadAll2Ex.contains("c"));
        assertTrue("callReadAll2Ex contains d", callReadAll2Ex.contains("d"));
        assertTrue("callReadAll2Ex contains e", callReadAll2Ex.contains("e"));

        // call sanitizeSome
        CallS callSanitizeSome = (CallS) primaryS.getS(5);
        Set<String> callSanitizeSomeEn = callSanitizeSome.TVentry();
        assertEquals("callSanitizeSomeEn", 5, callSanitizeSomeEn.size());
        assertTrue("callSanitizeSomeEn contains a", callSanitizeSomeEn.contains("a"));
        assertTrue("callSanitizeSomeEn contains b", callSanitizeSomeEn.contains("b"));
        assertTrue("callSanitizeSomeEn contains c", callSanitizeSomeEn.contains("c"));
        assertTrue("callSanitizeSomeEn contains d", callSanitizeSomeEn.contains("d"));
        assertTrue("callSanitizeSomeEn contains e", callSanitizeSomeEn.contains("e"));
        Set<String> callSanitizeSomeEx = callSanitizeSome.TVexit();
        assertEquals("callSanitizeSomeEx", 2, callSanitizeSomeEx.size());
        assertTrue("callSanitizeSomeEx contains d", callSanitizeSomeEx.contains("d"));
        assertTrue("callSanitizeSomeEx contains e", callSanitizeSomeEx.contains("e"));

        // sanitize d
        sanitizeD = (SanitizeS) primaryS.getS(6);
        Set<String> sanitizeDEn = sanitizeD.TVentry();
        assertEquals("sanitizeDEn", 2, sanitizeDEn.size());
        assertTrue("sanitizeDEn contains d", sanitizeDEn.contains("d"));
        assertTrue("sanitizeDEn contains e", sanitizeDEn.contains("e"));
        Set<String> sanitizeDEx = sanitizeD.TVexit();
        assertEquals("sanitizeDEx", 1, sanitizeDEx.size());
        assertTrue("sanitizeDEx contains e", sanitizeDEx.contains("e"));

        // sanitize e
        sanitizeE = (SanitizeS) primaryS.getS(7);
        Set<String> sanitizeEEn = sanitizeE.TVentry();
        assertEquals("sanitizeEEn", 1, sanitizeEEn.size());
        assertTrue("sanitizeEEn contains e", sanitizeEEn.contains("e"));
        Set<String> sanitizeEEx = sanitizeE.TVexit();
        assertEquals("sanitizeEEx", 0, sanitizeEEx.size());
    }

	public TVTests(String s) {
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
