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
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]
IdChar = ([:letter:]|['_])
Identifier = {IdChar}({IdChar} | [:digit:])*

%% 