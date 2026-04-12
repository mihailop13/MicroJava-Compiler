
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;		//klasa koja pravi novi token

%%

//ovako se otvara blok, to znaci da sve odavde iz tog bloka mora biti ugradjeno u moju buducu klasu
%{
	//ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type){
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	//ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline+1, yycolumn, value);
	}
%}	

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 		{ }
"\b"		{ }
"\t"		{ }
"\r\n"		{ }
"\f"		{ }

//yytext() je metoda koja se automatski generise
//Prioriteti: 1. Duzi regex, 2. Redosled nabrajanja (prvi ima veci prioritet)
"program" 	{return new_symbol(sym.PROG, yytext());} 
"return" 	{return new_symbol(sym.RETURN, yytext());}
"void" 		{return new_symbol(sym.VOID, yytext());}
"read" 		{return new_symbol(sym.READ, yytext());}
"const" 	{return new_symbol(sym.CONST, yytext());}
"print" 	{return new_symbol(sym.PRINT, yytext());}
"new" 		{return new_symbol(sym.NEW, yytext());}
"length" 	{return new_symbol(sym.LENGTH, yytext());}
"for" 		{return new_symbol(sym.FOR, yytext());}
"switch" 	{return new_symbol(sym.SWITCH, yytext());}
"case" 		{return new_symbol(sym.CASE, yytext());}
"if" 		{return new_symbol(sym.IF, yytext());}
"else" 		{return new_symbol(sym.ELSE, yytext());}
"break" 	{return new_symbol(sym.BREAK, yytext());}
"continue" 	{return new_symbol(sym.CONTINUE, yytext());}
"enum" 		{return new_symbol(sym.ENUM, yytext());}

"+" 	{return new_symbol(sym.PLUS, yytext());}
"*" 	{return new_symbol(sym.MUL, yytext());}
"/" 	{return new_symbol(sym.DIV, yytext());}
"%" 	{return new_symbol(sym.MOD, yytext());}
"," 	{return new_symbol(sym.COMMA, yytext());}
"-" 	{return new_symbol(sym.MINUS, yytext());}
"<" 	{return new_symbol(sym.LESS, yytext());}
"<=" 	{return new_symbol(sym.LESS_EQUAL, yytext());}
">" 	{return new_symbol(sym.GRATER, yytext());}
">=" 	{return new_symbol(sym.GRATER_EQUAL, yytext());}
"=" 	{return new_symbol(sym.ASSIGN, yytext());}
"==" 	{return new_symbol(sym.EQUAL, yytext());}
"!=" 	{return new_symbol(sym.NOT_EQUAL, yytext());}
"++" 	{return new_symbol(sym.INC, yytext());}
"--" 	{return new_symbol(sym.DEC, yytext());}
"(" 	{return new_symbol(sym.OPEN_BRACKET, yytext());}
")" 	{return new_symbol(sym.CLOSED_BRACKET, yytext());}
";" 	{return new_symbol(sym.SEMICOLON, yytext());}
"." 	{return new_symbol(sym.DOT, yytext());}
":" 	{return new_symbol(sym.DOT_DOUBLE, yytext());}
"?" 	{return new_symbol(sym.QUESTION, yytext());}
"[" 	{return new_symbol(sym.LEFT_SQUARE, yytext());}
"]" 	{return new_symbol(sym.RIGHT_SQUARE, yytext());}
"{" 	{return new_symbol(sym.LEFT_CURLY, yytext());}
"}" 	{return new_symbol(sym.RIGHT_CURLY, yytext());}
"&&" 	{return new_symbol(sym.AND, yytext());}
"||" 	{return new_symbol(sym.OR, yytext());}
		
					//ybegin je ugradjena funkcija
"//"				{yybegin(COMMENT);}
<COMMENT>.			{yybegin(COMMENT);}
<COMMENT> "\r\n" 	{yybegin(YYINITIAL);}

[0-9]+							{return new_symbol(sym.NUMBER, new Integer(yytext()));}
"'"."'"							{return new_symbol(sym.CHARACTER, new Character(yytext().charAt(1)));}
("true"|"false")				{return new_symbol(sym.BOOL, yytext().equals("true")?1:0);}
([a-z]|[A-Z])[a-z|A-Z|0-9|_]*   {return new_symbol(sym.IDENT, yytext());}

. 								{System.err.println("Leksicka greska (" + yytext() + ") na liniji " + (yyline + 1) +" u koloni " + (yycolumn + 1) + "\n");}