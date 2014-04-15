grammar RegEx;

options { language = Java; }

tokens
{
	CLOSURE_EXP,
	UNION_EXP,
	CONCAT_EXP,
	LIST
}


exp
	:	(closure | union | concat)
	;

closure  
	:	union '*' //-> ^(CLOSURE_EXP union) | LIT '*' -> ^(CLOSURE_EXP LIT)
	;


union 	
	:	'(' concat ('+' concat)* ')' //-> ^(UNION_EXP concat*) 
	;

concat
	:	 (str | closure | union)+ 
	;

str
        :       LIT+
        ;
        

LIT	
	:	'0' | '1' | '2' | 'E' | 'e'
	;

// Whitespace -- ignored
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out
