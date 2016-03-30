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
        TestControlFlowGraphProgram(program.getBlock());
        TestProgramOutFlows(program.getBlock());
        TestOuterConstOutFlows(program.getBlock());
    }

    public void TestControlFlowGraphProgram(Block block) throws Exception
    {
        // Init
        String programInitLabel = ((NumLabel)block.init()).getNum();
        assertTrue(programInitLabel + " -Failure", programInitLabel.equals("1"));

        // blocks: Verify amount of blocks
        Set<ElementaryBlock> elementaryBlocks = block.blocks();
        assertTrue(elementaryBlocks.size() + "", elementaryBlocks.size() == 13);

        // blocks: Verify all constants exist
        List<ConstDecl> constants = new List<ConstDecl>();
        constants.add(new ConstDecl(new NumLabel("1"), "y", "5"));
        constants.add(new ConstDecl(new NumLabel("2"), "z", "0"));
        for(ConstDecl constant : constants)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(constant));
        }

        // blocks: Verify all Variables exist
        List<VarDecl> variables = new List<VarDecl>();
        variables.add(new VarDecl(new NumLabel("3"), "x"));
        variables.add(new VarDecl(new NumLabel("4"), "sum"));
        for(VarDecl variable : variables)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(variable));
        }

        // blocks: Inner Variables
        List<VarDecl> innerVariables = new List<VarDecl>();
        innerVariables.add(new VarDecl(new NumLabel("5"), "a"));
        innerVariables.add(new VarDecl(new NumLabel("6"), "b"));
        for(VarDecl variable : innerVariables)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(variable));
        }

        // blocks: Inner Statements
        List<S> innerStatements = new List<S>();
        innerStatements.add(new AssignS(new NumLabel("7"), "a", new VarRefExpr("x")));
        innerStatements.add(new AssignS(new NumLabel("8"), "b", new VarRefExpr("y")));
        innerStatements.add(new AssignS(new NumLabel("9"), "sum", new ABinaryExpr(new VarRefExpr("a"), new Op_a("+"), new VarRefExpr("b"))));
        for(S statement : innerStatements)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(statement));
        }

        // blocks: Procedures
        List<ProcDecl> procedures = new List<ProcDecl>();
        procedures.add(new ProcDecl(new NumLabel("10"), "addition", new Block(new List<ConstDecl>(), innerVariables, new List<ProcDecl>(), new BeginEndS(innerStatements))));
        for(ProcDecl proc : procedures)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(proc));
        }

        // blocks: Statements
        List<S> statements = new List<S>();
        statements.add(new AssignS(new NumLabel("11"), "x", new NumLitExpr("2")));
        statements.add(new CallS(new NumLabel("12"), "addition"));
        statements.add(new AssignS(new NumLabel("13"), "sum", new ABinaryExpr(new VarRefExpr("sum"), new Op_a("+"), new NumLitExpr("1"))));
        for(S statement : statements)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(statement));
        }

        // finals: Verify there is only one
        Set<Label> finalLabels = block.finals();
        assertTrue(finalLabels.size() == 1);
        assertTrue(finalLabels + " -Failure", finalLabels.contains(new NumLabel("13")));

        // outFlows: Verify there are no outflows to the program block
        Set<Label> outs = block.outFlows();
        assertEquals(0, outs.size());
    }

    public void TestProgramOutFlows(Block block) throws Exception
    {
    }

    public void TestOuterConstOutFlows(Block block) throws Exception
    {
        assertTrue(block.getNumConstant() == 2);
        ConstDecl const1 = block.getConstant(0);
        Set<Label> outs1 = const1.outFlows();
        assertEquals(1, outs1.size());
        assertTrue(outs1.toString(), outs1.contains(new NumLabel("2")));

        ConstDecl const2 = block.getConstant(1);
        Set<Label> outs2 = const2.outFlows();
        assertEquals(1, outs2.size());
        assertTrue(outs2.toString(), outs2.contains(new NumLabel("3")));
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
