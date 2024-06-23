package com.linuxgods.kreiger.intellij.idea.url.encoded;
import com.github.weisj.jsvg.N;import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedTypes;
import com.intellij.psi.TokenType;

%%

%class UrlEncodedLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

NEWLINE=\R
QUESTION_MARK="?"
AMPERSAND="&"
EQUALS="="
PERCENT="%"
PLUS="+"
HEX=[0-9a-fA-F]
UNRESERVED=[^?&=%+\s]

%state NAME
%state VALUE

%%

<YYINITIAL> {
    {QUESTION_MARK}        { return UrlEncodedTypes.QUESTION_MARK; }
    [^]                    { yypushback(1); yybegin(NAME); }
}

<NAME> {
    {EQUALS}               { yybegin(VALUE); return UrlEncodedTypes.EQUALS; }
    {NEWLINE}+             { return TokenType.WHITE_SPACE; }
    {AMPERSAND}            { return UrlEncodedTypes.AMPERSAND; }
    {PLUS}                 { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX} {HEX}  { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX}?       { return UrlEncodedTypes.INVALID_ESCAPE; }
    {UNRESERVED}+          { return UrlEncodedTypes.UNESCAPED_NAME; }
}

<VALUE> {
    {NEWLINE}+             { yybegin(NAME); return TokenType.WHITE_SPACE; }
    {AMPERSAND}            { yybegin(NAME); return UrlEncodedTypes.AMPERSAND; }
    {PLUS}                 { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX} {HEX}  { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX}?       { return UrlEncodedTypes.INVALID_ESCAPE; }
    {UNRESERVED}+          { return UrlEncodedTypes.UNESCAPED_VALUE; }
}

[^]                        { return TokenType.BAD_CHARACTER; }
