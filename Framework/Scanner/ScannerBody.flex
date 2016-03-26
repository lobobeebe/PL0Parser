{WhiteSpace}  { /* discard token */ }

// Rules
"."					{ return sym(Terminals.ENDPROGRAM); }
"const"				{ return sym(Terminals.CONST); }
"var"				{ return sym(Terminals.VAR); }
":="                { return sym(Terminals.ASSIGN); }
","					{ return sym(Terminals.COMMA); }
";"                 { return sym(Terminals.SEMICOLON); }
"if"                { return sym(Terminals.IF); }
"then"              { return sym(Terminals.THEN); }
"while"             { return sym(Terminals.WHILE); }
"do"				{ return sym(Terminals.DO); }
"procedure"			{ return sym(Terminals.PROCEDURE); }
"call"              { return sym(Terminals.CALL); }
"begin"				{ return sym(Terminals.BEGIN); }
"end"				{ return sym(Terminals.END); }

// Operators
"="                         { return sym(Terminals.EQUALS); }
("#"|"<"|"<="|">="|">")   	{ return sym(Terminals.OPRELATIONAL); }
"odd"							{ return sym(Terminals.ODD); }
// split + and - so later could handle unary minus separately...
"+"                             { return sym(Terminals.OPPLUS); }
"-"                             { return sym(Terminals.OPMINUS); }
("*"|"/")                       { return sym(Terminals.OPMUL); }

// Separators
"("                             { return sym(Terminals.LPAREN); }
")"                             { return sym(Terminals.RPAREN); }