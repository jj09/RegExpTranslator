// Generated from C:\SkyDrive\KSU\CIS770 - Formal Languages Theory\workspace\Assignment2\src\parser2\RegEx.g4 by ANTLR 4.1
package parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link RegExVisitor}, which
 * can be extended to create a visitor which only needs to handle a subset of
 * the available methods.
 * 
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public class RegExExtendedVisitor extends RegExBaseVisitor<Enfa> {

	public Enfa finalEnfa;
	
	@Override
	public Enfa visitExp(@NotNull RegExParser.ExpContext ctx) {
		finalEnfa = visitChildren(ctx);
		return finalEnfa;
	}
	
//	public Enfa visitExp(@NotNull RegExParser.ExpContext ctx) {
//		Enfa enfa = this.visit(ctx.children.get(0));
//		for(int i=1; i<ctx.children.size(); ++i) {
//			if(ctx.children.get(i).getText().compareTo("(") != 0
//					&& ctx.children.get(i).getText().compareTo(")") != 0
//					&& ctx.children.get(i).getText().compareTo("*") != 0) {
//			Enfa right = this.visit(ctx.children.get(i));
//			enfa = concat(enfa, right);
//			}
//		}
//		return enfa;
//	}
//	
//	private Enfa concat(Enfa left, Enfa right) {
//		for(Node n: left.end) {
//			n.IsFinal = false;
//			n.OnE.add(right.start);
//		}
//		left.end = right.end;
//		return left;		
//	}

	@Override
	public Enfa visitClosure(@NotNull RegExParser.ClosureContext ctx) {
		Enfa enfa = visit(ctx.children.get(0));
		for(Node n : enfa.end) {
			enfa.start.OnE.add(n);
			n.OnE.add(enfa.start);
		}
		return enfa;
	}
	
	@Override
	public Enfa visitUnion(@NotNull RegExParser.UnionContext ctx) {
		int startInd = ctx.children.get(0).getText().compareTo("(")!=0 ? 0 : 1;
		Enfa enfa = this.visit(ctx.children.get(startInd));
		for(int i=1; i<ctx.children.size(); ++i) {
			String skip = "+()";	// tokens to ignore
			if(!skip.contains(ctx.children.get(i).getText())) {
				Enfa right = this.visit(ctx.children.get(i));
				enfa = union(enfa, right);
			}
		}
		return enfa;
	}
	
	private Enfa union(Enfa left, Enfa right) {
		Enfa newEnfa = new Enfa();
		left.start.OnE.add(right.start);
		right.start.IsStart = false;
		for(Node finalLeft : left.end) {
			for(Node finalRight : right.end) {
				finalLeft.OnE.add(finalRight);
				finalLeft.IsFinal = false;				
			}
		}
		newEnfa.start = left.start;
		newEnfa.end = right.end;
		return newEnfa;
	}
	
	@Override
	public Enfa visitConcat(@NotNull RegExParser.ConcatContext ctx) {
		Enfa enfa = this.visit(ctx.children.get(0));
		for (int i = 1; i < ctx.children.size(); ++i) {
			String skip = "+()";	// tokens to ignore
			if (!skip.contains(ctx.children.get(i).getText())) {
				Enfa right = this.visit(ctx.children.get(i));
				enfa = concat(enfa, right);
			}
		}
		return enfa;
	}

	private Enfa concat(Enfa left, Enfa right) {
		for (Node n : left.end) {
			n.IsFinal = false;
			n.OnE.add(right.start);
		}
		left.end = right.end;
		return left;
	}

	@Override
	public Enfa visitStr(@NotNull RegExParser.StrContext ctx) {
		Enfa enfa = new Enfa();
		
		Node start = new Node();
		start.IsStart = true;
		
		Node end = new Node();
		end.IsFinal = true;
		
		enfa.start = start;
		enfa.end.add(end);
		
		Node cur = start;
		for (int i = 0; i < ctx.getChildCount(); ++i) {
			Node newNode = i == (ctx.getChildCount() - 1) ? end : new Node();
			String lit = ctx.getChild(i).getText();
			if (lit.equalsIgnoreCase("E")) {
				cur.OnE.add(newNode);
			} else {
				int value = Integer.parseInt(lit);

				switch (value) {
				case 0:
					cur.On0.add(newNode);
					break;
				case 1:
					cur.On1.add(newNode);
					break;
				case 2:
					cur.On2.add(newNode);
					break;
				}
			}
			cur = newNode;
		}
		
		return enfa;
	}	
	
}