{Comment}     { /* discard token */ }
{WhiteSpace}  { /* discard token */ }

// Rules
"."					{ return sym(Terminals.ENDPROGRAM); }
"const"				{ return sym(Terminals.CONST); }
"var"				{ return sym(Terminals.VAR); }
"="					{ return sym(Terminals.CONSTASSIGN); }
":="                { return sym(Terminals.ASSIGN); }
","					{ return sym(Terminals.COMMA); }
";"                 { return sym(Terminals.SEMICOLON); }
"if"                { return sym(Terminals.IF); }
"then"				{ return sym(Terminals.THEN); }
"while"             { return sym(Terminals.WHILE); }
"do"				{ return sym(Terminals.DO); }
"procedure"			{ return sym(Terminals.PROCEDURE); }
"begin"				{ return sym(Terminals.BEGIN); }
"end"				{ return sym(Terminals.END); }
"!"					{ return sym(Terminals.EXCLAMATION); }
"?"					{ return sym(Terminals.QUESTION); }
"print"				{ return sym(Terminals.PRINT); }
"read"				{ return sym(Terminals.READ); }
"sanitize"			{ return sym(Terminals.SANITIZE); }

// Operators
("="|"#"|"<"|"<="|">="|">")   	{ return sym(Terminals.OPRELATIONAL); }
"odd"							{ return sym(Terminals.ODD); }
// split + and - so later could handle unary minus separately...
"+"                       { return sym(Terminals.OPPLUS); }
"-"                       { return sym(Terminals.OPMINUS); }
("*"|"/")                       { return sym(Terminals.OPMUL); }

// Separators
"("                             { return sym(Terminals.LPAREN); }
")"                             { return sym(Terminals.RPAREN); }