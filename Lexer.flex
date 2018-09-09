/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%class Lexer
%byaccj

%{

  public Parser   yyparser;
  public int      lineno;

  public Lexer(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    this.lineno   = 1;
  }
%}

float      = [+-]?([0-9]*[.])?[0-9]+
int        = [0-9]+
identifier = [a-zA-Z][a-zA-Z0-9]*
newline    =  \n
whitespace = [ \t\r]+
comment    = "//".*

//otherKeywords = "bool".*



%%

"main"                              { yyparser.yylval = new ParserVal(null            ); return Parser.MAIN   ; }
"print"                             { yyparser.yylval = new ParserVal(null            ); return Parser.PRINT  ; }
"bool"                              { yyparser.yylval = new ParserVal(null            ); return Parser.BOOL     ; }

"int"                               { yyparser.yylval = new ParserVal(null            ); return Parser.INT    ; }
"{"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.BEGIN  ; }
"}"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.END    ; }
"("                                 { yyparser.yylval = new ParserVal(null            ); return Parser.LPAREN ; }
")"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.RPAREN ; }
"="                                 { yyparser.yylval = new ParserVal(null            ); return Parser.ASSIGN ; }
"+"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.PLUS   ; }
"*"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.MUL    ; }
";"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.SEMI   ; }
"["                                 { yyparser.yylval = new ParserVal(null            ); return Parser.BCT_L  ; }
"]"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.BCT_R  ; }
"if"                                { yyparser.yylval = new ParserVal(null            ); return Parser.IF     ; }
","                                 { yyparser.yylval = new ParserVal(null            ); return Parser.COMMA  ; }
"<"                                 { yyparser.yylval = new ParserVal(null            ); return Parser.MKR    ; }

//{otherKeywords}                     { yyparser.yylval = new ParserVal((Object)yytext()); return Parser.OTHER; }

{int}                               { yyparser.yylval = new ParserVal((Object)yytext()); return Parser.INT_LIT; }
{float}                             { yyparser.yylval = new ParserVal((Object)yytext()); return Parser.FLOAT_LIT; } 
{identifier}                        { yyparser.yylval = new ParserVal((Object)yytext()); return Parser.IDENT  ; }
{comment}                           { System.out.print(yytext()); /* skip */ }
{newline}                           { System.out.println(""); return Parser.RET;}
{whitespace}                        { System.out.print(yytext()); /* skip */ }

\b     { System.err.println("Sorry, backspace doesn't work"); }
/* error fallback */
[^]   { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }