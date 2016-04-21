# PL0Parser
Authors: Cody Beebe, Blake Klasing
Date: 04/21/2016
Course: COP 5021
PL0 Parser project for COP5021 HW 4.1

# Build
ant

# Test
ant test

# Collaboration
(i) participants: Cody Beebe, Blake Klasing
(ii) Each person actively contributed to and fully understands the solution.

# Analysis Questions
(i) Tainted Variables(TV): For each program point, which variables may have been assigned from user input.
(ii) Available Expressions(AE): For each program point, which expressions must have already been computed, and not later modified, on all paths to the program point. 
(iii) Sanitized Variables(SV): For each program point, which variables must have been sanitized.

# Modules
This project is broken into Modules for ease of building, debugging, and testing. The Modules are represented as directories in the root PL0Parser project. The Module List is as follows: ControlFlowGraph, Unparse, AvailableExpressions, SanitizedVariables, and TaintedVariables. Each Module relies on the core framework consisting of AST, Scanner, Parser, Tools, and Utilities. Modules will all have a set of Aspects and Tests within it that relate to the Module. The Aspects directory contains the JRAG files that define the operations necessary for the Module and the Tests folder contains tests ensuring that the operations perform as expected.

# Procedures
In terms of Control Flow Graphs, the Procedures are considered to be their own control flow graphs that do not interconnect with each other or the main program block. They are pieced together via Procedure Summaries, explained below, at the time of analysis. The "initial" node of a procedures control flow graph is considered to be the first statement in its program block.

# Procedure Summaries
In order to support Calls and Procedures in this project, we have implemented the concept of Procedure Summaries in all analyses. Procedure Summaries are broken into gen summaries and kill summaries that function much like kill and gen functions for statements but apply to the entire procedure. The kill summaries are necessary for our implementation of procedure summaries because we have global variables. In order to remove analysis information through a procedure summary, we assume that the input to the "initial" node is the entire set of analysis information possible, top, and calculate a fixed point for the union of the procedures final nodes. The set difference between the top and the calculated fixed point will be the information that is "killed" from the procedure. Similarly, starting from bottom and calculating the same fixed point for the procedure, we now have the information that is generated from a procedure. This process is used in TaintedVariables/Aspects/TaintedVariablesAnalysis.jrag, AvailableExpressions/Aspects/AvailableExpressionsAnalysis.jrag, and SanitizedVariables/Aspects/SanitizedVariablesAnalysis.jrag.

In order to link Calls to Procedures, a simple linear search is performed when a CallS is encountered to find the matching Procedure. If no procedure is found, an error should be thrown - but it was decided that this is outside the scope of the project.
