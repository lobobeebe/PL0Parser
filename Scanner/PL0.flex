// Scanner for the WHILE language of "Principles of Program Analysis", adapted from JastAdd's PicoJava scanner
//
// AUTHOR: Gary T. Leavens (with help from Rochelle Elva and Ghaith Haddad)

package AST;

import beaver.Symbol;
import beaver.Scanner;
import AST.PL0Parser.Terminals;

%%
%public
%final
%class PL0Scanner
%extends Scanner
%unicode
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%line
%column

%{
  private Symbol sym(short id) {
    return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

// Helper Definitions
LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]
IdChar = ([:letter:]|['_])
Identifier = {IdChar}({IdChar} | [:digit:])*

%%

{WhiteSpace}  { /* discard token */ }

// Rules
"."                { return sym(Terminals.ENDPROGRAM); }
"const"            { return sym(Terminals.CONST); }
"var"              { return sym(Terminals.VAR); }
":="               { return sym(Terminals.ASSIGN); }
","                { return sym(Terminals.COMMA); }
";"                { return sym(Terminals.SEMICOLON); }
"if"               { return sym(Terminals.IF); }
"then"             { return sym(Terminals.THEN); }
"while"            { return sym(Terminals.WHILE); }
"do"               { return sym(Terminals.DO); }
"procedure"        { return sym(Terminals.PROCEDURE); }
"call"             { return sym(Terminals.CALL); }
"begin"            { return sym(Terminals.BEGIN); }
"end"              { return sym(Terminals.END); }
"read"             { return sym(Terminals.READ); }
"sanitize"         { return sym(Terminals.SANITIZE); }
"print"            { return sym(Terminals.PRINT); }

// Operators
"="                         { return sym(Terminals.EQUALS); }
("#"|"<"|"<="|">="|">")     { return sym(Terminals.OPRELATIONAL); }
"odd"                       { return sym(Terminals.ODD); }
// split + and - so later could handle unary minus separately...
"+"                         { return sym(Terminals.OPPLUS); }
"-"                         { return sym(Terminals.OPMINUS); }
("*"|"/")                   { return sym(Terminals.OPMUL); }

// Separators
"("                         { return sym(Terminals.LPAREN); }
")"                         { return sym(Terminals.RPAREN); }

[:digit:]+    { return sym(Terminals.NUMBER); }
{Identifier}  { return sym(Terminals.IDENTIFIER); }

<<EOF>>       { return sym(Terminals.EOF); }
.          { throw new RuntimeException("Illegal character \""+yytext()+ "\" at line "+yyline+", column "+yycolumn); }
