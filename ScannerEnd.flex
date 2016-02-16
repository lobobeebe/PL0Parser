  
[:digit:]+    { return sym(Terminals.NUMBER); }
{Identifier}  { return sym(Terminals.IDENTIFIER); }

<<EOF>>       { return sym(Terminals.EOF); }
.          { throw new RuntimeException("Illegal character \""+yytext()+ "\" at line "+yyline+", column "+yycolumn); }