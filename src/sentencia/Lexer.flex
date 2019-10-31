package sentencia;
import static sentencia.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
signos=[.,:,;]
comparadores=[<,>,=,!]
%{
    public String lexeme;
%}
%%
delete |
from |
using |
limit |
and |
order |
by |
or |
where {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
{signos} {lexeme=yytext(); return Signos;}
{comparadores} {lexeme=yytext(); return Comparadores;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 % {return ERROR;}
