import java.util.ArrayList;
public class Parser
{
    public static final int MAIN        = 10;//
    public static final int PRINT       = 11;//
    public static final int INT         = 13;
    public static final int FLOAT       = 14; 
    public static final int BEGIN       = 30;//
    public static final int END         = 31;//
    public static final int LPAREN      = 33;//
    public static final int RPAREN      = 34;//
    public static final int ASSIGN      = 38;//
    public static final int PLUS        = 41;//
    public static final int MUL         = 43;//
    public static final int SEMI        = 47;//
    public static final int INT_LIT     = 55;//
    public static final int FLOAT_LIT   = 56;
    public static final int IDENT       = 58;//
    public static final int BCT_L       = 60;//[
    public static final int BCT_R       = 61;//]
    public static final int IF          = 62;//
    public static final int IT          = 63;// 
    public static final int OTHER       = 64;
    public static final int BOOL       = 67;
    public static final int COMMA       = 65;// 
    public static final int RET         = 2;//  
    public static final int MKR         = 3;//  
    
    

    public Parser(java.io.Reader r) throws java.io.IOException
    {
        this.lexer = new Lexer(r, this);
    }

    Lexer   lexer;
    public ParserVal yylval;
    
    public int yyparse() throws java.io.IOException
    {    
        ArrayList<String> variableTable = new ArrayList<String>();
        int lineCount = 1;
        int VbCounter = 0;
        
        while ( true ){    
            int token = lexer.yylex();
            Object attr = yylval.obj;
            
            if(token == 0){return 0;}
            if(token == -1){return -1;}
            if(token == 2) {lineCount++;};
            if(token == 10){System.out.print("<MAIN :" + lineCount + ">");}
            if(token == 11){System.out.print("<PRINT :" + lineCount + ">");}
            if(token == 30){System.out.print("<{ :" + lineCount + ">");} // {
            if(token == 31){System.out.print("<} :" + lineCount + ">");} // }
            if(token == 33){System.out.print("<( :" + lineCount + ">");} // (
            if(token == 34){System.out.print("<) :" + lineCount + ">");} // )
            if(token == 38){System.out.print("<= :" + lineCount + ">");} // =
            if(token == 41){System.out.print("<+ :" + lineCount + ">");} // +
            if(token == 43){System.out.print("<- :" + lineCount + ">");} // -
            if(token == 47){System.out.print("<; :" + lineCount + ">");} // ;    
            if(token == 55){System.out.print("<INT_VALUE, "+ attr+ " :" + lineCount + ">");} //Integers
            if(token == 56){System.out.print("<FLOAT_VALUE, "+ attr+ " :" + lineCount + ">");} //Floats
            if(token == 60){System.out.print("<[ :" + lineCount + ">");} // [
            if(token == 61){System.out.print("<] :" + lineCount + ">");} // ]  
            if(token == 65){System.out.print("<, :" + lineCount + ">");} // ]     
            if(token == 67){System.out.print("<bool :" + lineCount + ">");} // ]     
            if(token == 67){System.out.print("<<= :" + lineCount + ">");} // ]              
            if(token == 64){System.out.print("<"+ attr +" :" + lineCount + ">");} // 
            
            //Identifiers
            if(token == 58){
                if (variableTable.contains(attr)) {
                    System.out.print("<ID, " + variableTable.indexOf(attr) + " :"+lineCount + ">");
                } else {
                    variableTable.add((String) attr);
                    System.out.print("<< add "+ attr +" into symbol table at " + VbCounter + " >>");
                    System.out.print("<ID, " + VbCounter + " :"+lineCount + ">");//
                    VbCounter++;
                }
            }
            

        }
    }
}
