package parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.Set;

public class RegExCompiler {

	public static Enfa getEnfa(String regEx) {

		// create a CharStream that reads from standard input
		ANTLRInputStream input = new ANTLRInputStream(regEx);

		// create a lexer that feeds off of input CharStream
		RegExLexer lexer = new RegExLexer(input);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		RegExParser parser = new RegExParser(tokens);

		ParseTree tree = parser.exp(); // begin parsing at init rule

		RegExExtendedVisitor rev = new RegExExtendedVisitor();

		rev.visit(tree);

		return rev.finalEnfa;
	}

}
