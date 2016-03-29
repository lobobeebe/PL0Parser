package Tests;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import Utility.Squeezer;
import java.util.Set;

import AST.*;

public class ControlFlowGraphTests extends PL0TestCase
{
    public ControlFlowGraphTests(String s)
    {
        super(s);
    }

    /** Compare the source file to the unparsed string of the AST. Matching proves correctness of AST. */
    public void testControlFlowGraph() throws IOException, Exception
    {
        Program program = parseFromFile("Tests/Data/TestControlFlowGraph.pl0");
        TestControlFlowGraphInit(program.getBlock());
        TestProgramOutFlows(program.getBlock());
        TestConstOutFlows(program.getBlock());
    }

    public void TestControlFlowGraphInit(Block block) throws Exception
    {
        String programInitLabel = ((NumLabel)block.init()).getNum();
        assertTrue(programInitLabel + " -Failure", programInitLabel.equals("1"));
    }

    public void TestProgramOutFlows(Block block) throws Exception
    {
        Set<Label> outs = block.outFlows();
        assertEquals(0, outs.size());
    }

    public void TestConstOutFlows(Block block) throws Exception
    {
        assertTrue(block.getNumConstant() == 2);

        ConstDecl const1 = block.getConstant(0);
        Set<Label> outs1 = const1.outFlows();
        assertEquals(1, outs1.size());
        assertTrue(outs1.contains(new NumLabel("2")));

        ConstDecl const2 = block.getConstant(1);
        Set<Label> outs2 = const2.outFlows();
        assertEquals(1, outs2.size());
        assertTrue(outs2.contains(new NumLabel("3")));
    }
/*
    public void testOutFlows2() throws IOException, Exception {
        Program p = parseFromFile("testsrc/cfg1.wh");
        assertTrue(p.getS() instanceof CompoundS);
        S stmt2 = ((CompoundS)p.getS()).getSList(1);
        Set<Label> outs = stmt2.outFlows();
        assertEquals(1, outs.size());
        assertTrue(outs.contains(new NumLabel("6")));
    }

    public void testOutFlows3() throws IOException, Exception {
        Program p = parseFromFile("testsrc/cfg1.wh");
        assertTrue(p.getS() instanceof CompoundS);
        S stmt3 = ((CompoundS)p.getS()).getSList(2);
        assertTrue(stmt3 instanceof WhileS);
        WhileS wh = (WhileS)stmt3;
        LabeledExpr blk6 = wh.getLabeledExpr();
        Set<Label> outs = blk6.outFlows();
        assertEquals(2, outs.size());
        assertTrue(outs.contains(new NumLabel("3")));
        assertTrue(outs.contains(new NumLabel("7")));
    }

    public void testInFlows3() throws IOException, Exception {
        Program p = parseFromFile("testsrc/cfg1.wh");
        assertTrue(p.getS() instanceof CompoundS);
        S stmt3 = ((CompoundS)p.getS()).getSList(2);
        assertTrue(stmt3 instanceof WhileS);
        WhileS wh = (WhileS)stmt3;
        LabeledExpr blk6 = wh.getLabeledExpr();
        Set<Label> ins = blk6.inFlows();
        assertEquals(2, ins.size());
        assertTrue(ins.contains(new NumLabel("5")));
        assertTrue(ins.contains(new NumLabel("2")));
    }

    public void testWhileBodyFlows() throws IOException, Exception {
        Program p = parseFromFile("testsrc/cfg1.wh");
        assertTrue(p.getS() instanceof CompoundS);
        S stmt6 = ((CompoundS)p.getS()).getSList(2);
        assertTrue(stmt6 instanceof WhileS);
        WhileS wh = (WhileS)stmt6;
        CompoundS body = (CompoundS)wh.getS();
        S stmt3 = body.getSList(0);
        Set<Label> ins = stmt3.inFlows();
        assertEquals(1, ins.size());
        assertTrue(ins.contains(new NumLabel("6")));
        Set<Label> outs = stmt3.outFlows();
        assertEquals(1, outs.size());
        assertTrue(outs.contains(new NumLabel("4")));
    }

    public void testProgLabels() throws IOException, Exception {
        Program p = parseFromFile("testsrc/cfg1.wh");
        assertTrue(p.getS() instanceof CompoundS);
        S stmt6 = ((CompoundS)p.getS()).getSList(2);
        assertTrue(stmt6 instanceof WhileS);
        WhileS wh = (WhileS)stmt6;
        CompoundS body = (CompoundS)wh.getS();
        AssignS stmt3 = (AssignS) body.getSList(0);
        Set<Label> labs = stmt3.progLabels();
        assertEquals(7, labs.size());
        assertTrue(labs.contains(new NumLabel("1")));
        assertTrue(labs.contains(new NumLabel("2")));
        assertTrue(labs.contains(new NumLabel("3")));
        assertTrue(labs.contains(new NumLabel("4")));
        assertTrue(labs.contains(new NumLabel("5")));
        assertTrue(labs.contains(new NumLabel("6")));
        assertTrue(labs.contains(new NumLabel("7")));
    }
*/
    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite(Tests.ControlFlowGraphTests.class);
    }

    public static void main(String args[])
    {
        if (args.length == 1 && args[0].equals("-text"))
        {
            junit.textui.TestRunner.run(ControlFlowGraphTests.class);
        }
        else
        {
            junit.swingui.TestRunner.run(ControlFlowGraphTests.class);
        }
    }

}
