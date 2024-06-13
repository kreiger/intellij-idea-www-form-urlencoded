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

AMPERSAND="&"
EQUALS="="
PERCENT="%"
PLUS="+"
HEX=[0-9a-fA-F]

%state NAME
%state VALUE
%state AMPERSAND

%%

<YYINITIAL> {
    \R+                    { return TokenType.WHITE_SPACE; }
    {AMPERSAND}            { yybegin(NAME); return UrlEncodedTypes.AMPERSAND; }
    [^]                    { yypushback(1); yybegin(NAME); }
}

<NAME> {
    {EQUALS}               { yybegin(VALUE); return UrlEncodedTypes.EQUALS; }
    {PLUS}                 { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX} {HEX}  { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX}?       { return UrlEncodedTypes.INVALID_ESCAPE; }
    [^&=%+\s]+             { return UrlEncodedTypes.UNESCAPED_NAME; }
}

<VALUE> {
    \R+                    { yybegin(AMPERSAND); return TokenType.WHITE_SPACE; }
    {AMPERSAND}            { yybegin(YYINITIAL); return UrlEncodedTypes.AMPERSAND; }
    {PLUS}                 { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX} {HEX}  { return UrlEncodedTypes.VALID_ESCAPE; }
    {PERCENT} {HEX}?       { return UrlEncodedTypes.INVALID_ESCAPE; }
    [^&=%+\s]+             { return UrlEncodedTypes.UNESCAPED_VALUE; }
}

<AMPERSAND> {
    {AMPERSAND}            { yybegin(YYINITIAL); return UrlEncodedTypes.AMPERSAND; }
}

[^]                        { return TokenType.BAD_CHARACTER; }
