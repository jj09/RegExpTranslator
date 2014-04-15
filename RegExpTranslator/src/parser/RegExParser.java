// Generated from RegEx.g4 by ANTLR 4.2
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegExParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, LIT=5, WS=6, CLOSURE_EXP=7, UNION_EXP=8, 
		CONCAT_EXP=9, LIST=10;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'*'", "'+'", "LIT", "WS", "CLOSURE_EXP", "UNION_EXP", 
		"CONCAT_EXP", "LIST"
	};
	public static final int
		RULE_exp = 0, RULE_closure = 1, RULE_union = 2, RULE_concat = 3, RULE_str = 4;
	public static final String[] ruleNames = {
		"exp", "closure", "union", "concat", "str"
	};

	@Override
	public String getGrammarFileName() { return "RegEx.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RegExParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpContext extends ParserRuleContext {
		public ClosureContext closure() {
			return getRuleContext(ClosureContext.class,0);
		}
		public ConcatContext concat() {
			return getRuleContext(ConcatContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegExVisitor ) return ((RegExVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(10); closure();
				}
				break;

			case 2:
				{
				setState(11); union();
				}
				break;

			case 3:
				{
				setState(12); concat();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClosureContext extends ParserRuleContext {
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public ClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closure; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegExVisitor ) return ((RegExVisitor<? extends T>)visitor).visitClosure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClosureContext closure() throws RecognitionException {
		ClosureContext _localctx = new ClosureContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_closure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); union();
			setState(16); match(3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public List<ConcatContext> concat() {
			return getRuleContexts(ConcatContext.class);
		}
		public ConcatContext concat(int i) {
			return getRuleContext(ConcatContext.class,i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegExVisitor ) return ((RegExVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); match(1);
			setState(19); concat();
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(20); match(4);
				setState(21); concat();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27); match(2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConcatContext extends ParserRuleContext {
		public List<ClosureContext> closure() {
			return getRuleContexts(ClosureContext.class);
		}
		public List<StrContext> str() {
			return getRuleContexts(StrContext.class);
		}
		public ClosureContext closure(int i) {
			return getRuleContext(ClosureContext.class,i);
		}
		public List<UnionContext> union() {
			return getRuleContexts(UnionContext.class);
		}
		public UnionContext union(int i) {
			return getRuleContext(UnionContext.class,i);
		}
		public StrContext str(int i) {
			return getRuleContext(StrContext.class,i);
		}
		public ConcatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegExVisitor ) return ((RegExVisitor<? extends T>)visitor).visitConcat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConcatContext concat() throws RecognitionException {
		ConcatContext _localctx = new ConcatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_concat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(32);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(29); str();
					}
					break;

				case 2:
					{
					setState(30); closure();
					}
					break;

				case 3:
					{
					setState(31); union();
					}
					break;
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==1 || _la==LIT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrContext extends ParserRuleContext {
		public TerminalNode LIT(int i) {
			return getToken(RegExParser.LIT, i);
		}
		public List<TerminalNode> LIT() { return getTokens(RegExParser.LIT); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegExVisitor ) return ((RegExVisitor<? extends T>)visitor).visitStr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_str);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(36); match(LIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(39); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f,\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\5\2\20\n\2\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\7\4\31\n\4\f\4\16\4\34\13\4\3\4\3\4\3\5\3\5\3\5\6\5#\n\5\r\5"+
		"\16\5$\3\6\6\6(\n\6\r\6\16\6)\3\6\2\2\7\2\4\6\b\n\2\2-\2\17\3\2\2\2\4"+
		"\21\3\2\2\2\6\24\3\2\2\2\b\"\3\2\2\2\n\'\3\2\2\2\f\20\5\4\3\2\r\20\5\6"+
		"\4\2\16\20\5\b\5\2\17\f\3\2\2\2\17\r\3\2\2\2\17\16\3\2\2\2\20\3\3\2\2"+
		"\2\21\22\5\6\4\2\22\23\7\5\2\2\23\5\3\2\2\2\24\25\7\3\2\2\25\32\5\b\5"+
		"\2\26\27\7\6\2\2\27\31\5\b\5\2\30\26\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2"+
		"\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35\36\7\4\2\2\36\7\3\2\2"+
		"\2\37#\5\n\6\2 #\5\4\3\2!#\5\6\4\2\"\37\3\2\2\2\" \3\2\2\2\"!\3\2\2\2"+
		"#$\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\t\3\2\2\2&(\7\7\2\2\'&\3\2\2\2()\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*\13\3\2\2\2\7\17\32\"$)";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}