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
    private Program mProgram;
    private ProgramBlock mProgramBlock;

    public ControlFlowGraphTests(String s)
    {
        super(s);

        try
        {
            mProgram = parseFromFile("Tests/Data/TestControlFlowGraph.pl0");
            mProgramBlock = mProgram.getProgramBlock();
        }
        catch(Exception exception)
        {
            System.err.println(exception.getMessage());
        }
    }

    public void testProgram() throws Exception
    {
        // Init
        String programInitLabel = ((NumLabel)mProgramBlock.init()).getNum();
        assertTrue("Initial label of the program should be 1: " + programInitLabel, programInitLabel.equals("1"));

        // blocks: Verify amount of blocks
        Set<ElementaryBlock> elementaryBlocks = mProgramBlock.blocks();
        assertTrue("There should be 13 elementary blocks in the program: " + elementaryBlocks.size(), elementaryBlocks.size() == 16);

        // blocks: Verify all constants exist
        List<ConstS> constants = new List<ConstS>();
        constants.add(new ConstS(new NumLabel("1"), "y", "5"));
        constants.add(new ConstS(new NumLabel("2"), "z", "0"));
        for(ConstS constant : constants)
        {
            assertTrue("Constants should contain " + constant + ": " + elementaryBlocks.toString(), elementaryBlocks.contains(constant));
        }

        // blocks: Verify all Variables exist
        List<VarS> variables = new List<VarS>();
        variables.add(new VarS(new NumLabel("3"), "x"));
        variables.add(new VarS(new NumLabel("4"), "sum"));
        for(VarS variable : variables)
        {
            assertTrue("Variables should contain " + variable + ": " + elementaryBlocks.toString(), elementaryBlocks.contains(variable));
        }

        // blocks: Inner Variables
        List<VarS> innerVariables = new List<VarS>();
        innerVariables.add(new VarS(new NumLabel("5"), "a"));
        innerVariables.add(new VarS(new NumLabel("6"), "b"));
        for(VarS variable : innerVariables)
        {
            assertTrue("Inner variables should contain " + variable + ": " + elementaryBlocks.toString(), elementaryBlocks.contains(variable));
        }

        // blocks: Inner Statements
        List<UsageS> innerStatements = new List<UsageS>();
        innerStatements.add(new AssignS(new NumLabel("7"), "a", new VarRefExpr("x")));
        innerStatements.add(new AssignS(new NumLabel("8"), "b", new VarRefExpr("y")));
        AssignS thenS = new AssignS(new NumLabel("9"), "sum", new ABinaryExpr(new VarRefExpr("a"), new Op_a("+"), new VarRefExpr("b")));
        innerStatements.add(thenS);
        innerStatements.add(new IfS(new LabeledExpr(new NumLabel("10"), new BBinaryExpr(new VarRefExpr("a"), new Op_r("="), new VarRefExpr("b"))), thenS));
        for(UsageS statement : innerStatements)
        {
            assertTrue("Inner statements should contain " + statement + ": " + elementaryBlocks.toString(), elementaryBlocks.contains(statement));
        }

        // blocks: Procedures
        List<ProcS> procedures = new List<ProcS>();
        procedures.add(new ProcS(new NumLabel("11"), "addition", new ProgramBlock(new List<ConstS>(), innerVariables, new List<ProcS>(), new BeginEndS(innerStatements))));
        for(ProcS proc : procedures)
        {
            assertTrue("Procedures should contain " + proc + ": " + elementaryBlocks.toString(), elementaryBlocks.contains(proc));
        }

        // blocks: Statements
        List<UsageS> statements = new List<UsageS>();
        statements.add(new AssignS(new NumLabel("11"), "x", new NumLitExpr("2")));
        statements.add(new CallS(new NumLabel("12"), "addition"));
        statements.add(new AssignS(new NumLabel("13"), "sum", new ABinaryExpr(new VarRefExpr("sum"), new Op_a("+"), new NumLitExpr("1"))));
        for(UsageS statement : statements)
        {
            assertTrue(elementaryBlocks.toString(), elementaryBlocks.contains(statement));
        }

        // finals: Verify there is only one
        Set<Label> finalLabels = mProgramBlock.finals();
        assertTrue(finalLabels.size() == 1);
        assertTrue(finalLabels + " -Failure", finalLabels.contains(new NumLabel("13")));

        // outFlows: Verify there are no outflows to the program block
        Set<Label> outs = mProgramBlock.outFlows();
        assertEquals(0, outs.size());
    }

    public void testConsts() throws Exception
    {
        // Verify number of constants
        assertTrue(mProgramBlock.getNumConst() == 2);
        ConstS const1 = mProgramBlock.getConst(0);
        ConstS const2 = mProgramBlock.getConst(1);

        // init
        assertTrue(const1.init().equals(new NumLabel("1")));
        assertTrue(const2.init().equals(new NumLabel("2")));

        // inFlows
        Set<Label> ins1 = const1.inFlows();
        assertTrue("Size of const1.inFlows should be 0, was: " + ins1.size(), ins1.size() == 0);

        Set<Label> ins2 = const2.inFlows();
        assertTrue("Size of const2.inFlows should be 1, was: " + ins2.size(), ins2.size() == 1);
        assertTrue("Label ^1 should be in const2.inFlows: const2.inFlows = " + ins2, ins2.contains(new NumLabel("1")));

        // outFlows
        Set<Label> outs1 = const1.outFlows();
        assertTrue("Size of const1.outFlows should be 1, was: " + outs1.size(), outs1.size() == 1);
        assertTrue("Label ^2 should be in const1.outFlows: const1.outFlows = " + outs1, outs1.contains(new NumLabel("2")));

        Set<Label> outs2 = const2.outFlows();
        assertTrue("Size of const2.outFlows should be 2, was: " + outs2.size(), outs2.size() == 1);
        assertTrue("Label ^3 should be in const2.outFlows: const2.outFlows = " + outs2, outs2.contains(new NumLabel("3")));
    }

    public void testVars() throws IOException, Exception
    {
        // Verify number of variables
        assertTrue("There should be 2 variables at the program scope: " + mProgramBlock.getNumVar(), mProgramBlock.getNumVar() == 2);
        VarS var1 = mProgramBlock.getVar(0);
        VarS var2 = mProgramBlock.getVar(1);

        // init
        assertTrue(var1.init().equals(new NumLabel("3")));
        assertTrue(var2.init().equals(new NumLabel("4")));

        // inFlows
        Set<Label> ins1 = var1.inFlows();
        assertTrue("Size of var1.inFlows should be 1, was: " + ins1.size(), ins1.size() == 1);
        assertTrue("Label ^2 should be in var2.inFlows: var2.inFlows = " + ins1, ins1.contains(new NumLabel("2")));

        Set<Label> ins2 = var2.inFlows();
        assertTrue("Size of var2.inFlows should be 1, was: " + ins2.size(), ins2.size() == 1);
        assertTrue("Label ^3 should be in var2.inFlows: var2.inFlows = " + ins2, ins2.contains(new NumLabel("3")));

        // outFlows
        Set<Label> outs1 = var1.outFlows();
        assertTrue("Size of var1.outFlows should be 1, was: " + outs1.size(), outs1.size() == 1);
        assertTrue("Label ^4 should be in var1.outFlows: var1.outFlows = " + outs1, outs1.contains(new NumLabel("4")));

        Set<Label> outs2 = var2.outFlows();
        assertTrue("Size of var2.outFlows should be 2, was: " + outs2.size(), outs2.size() == 1);
        assertTrue("Label ^11 should be in var22.outFlows: var2.outFlows = " + outs2, outs2.contains(new NumLabel("11")));
    }
/*
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
