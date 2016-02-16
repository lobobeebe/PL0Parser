{Comment}     { /* discard token */ }
{WhiteSpace}  { /* discard token */ }

// Rules
":="                 { return sym(Terminals.ASSIGN); }
";"                  { return sym(Terminals.SEMICOLON); }
"if"                 { return sym(Terminals.IF); }
"then"				 { return sym(Terminals.THEN); }
"else"			     { return sym(Terminals.ELSE); }
"while"              { return sym(Terminals.WHILE); }
"do"				 { return sym(Terminals.DO); }
"skip"               { return sym(Terminals.SKIP); }
"not"                { return sym(Terminals.NOT); }
"true"               { return sym(Terminals.TRUE); }
"false"              { return sym(Terminals.FALSE); }
"assert"			 { return sym(Terminals.ASSERT); }

// Operators
"and"                { return sym(Terminals.AND); }
"or"                 { return sym(Terminals.OR); }
("=="|"!="|"<"|"<="|">="|">")   { return sym(Terminals.OPRELATIONAL); }
// split + and - so later could handle unary minus separately...
"+"                       { return sym(Terminals.OPPLUS); }
"-"                       { return sym(Terminals.OPMINUS); }
("*"|"/")                       { return sym(Terminals.OPMUL); }

// Separators
"("                             { return sym(Terminals.LPAREN); }
")"                             { return sym(Terminals.RPAREN); }
"{"                             { return sym(Terminals.LBRACE); }
"}"                             { return sym(Terminals.RBRACE); }
