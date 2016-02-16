package utility;

import AST.ASTNode;
import beaver.Symbol;

/** Message utility. */
public class PositionMessage {

	/** Return a string for use in error messages. */
	public static String posInfo(ASTNode<?> ast) {
		return "line " + Symbol.getLine(ast.getStart());
	}
}
