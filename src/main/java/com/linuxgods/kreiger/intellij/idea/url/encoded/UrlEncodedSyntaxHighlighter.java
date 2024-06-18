package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class UrlEncodedSyntaxHighlighter implements SyntaxHighlighter {

    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("URL_ENCODED_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey NAME =
            createTextAttributesKey("URL_ENCODED_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("URL_ENCODED_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey VALID_ESCAPE =
            createTextAttributesKey("URL_ENCODED_VALID_ESCAPE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
    public static final TextAttributesKey INVALID_ESCAPE =
            createTextAttributesKey("URL_ENCODED_INVALID_ESCAPE", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("URL_ENCODED_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] NAME_KEYS = new TextAttributesKey[]{NAME};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] VALID_ESCAPE_KEYS = new TextAttributesKey[]{VALID_ESCAPE};
    private static final TextAttributesKey[] INVALID_ESCAPE_KEYS = new TextAttributesKey[]{INVALID_ESCAPE};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override public @NotNull Lexer getHighlightingLexer() {
        return new UrlEncodedLexerAdapter();
    }



    @Override public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(UrlEncodedTypes.AMPERSAND) || tokenType.equals(UrlEncodedTypes.EQUALS)) {
            return SEPARATOR_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.FIELD_NAME)) {
            return NAME_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.FIELD_VALUE)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.UNESCAPED_NAME)) {
            return NAME_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.UNESCAPED_VALUE)) {
            return VALUE_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.VALID_ESCAPE)) {
            return VALID_ESCAPE_KEYS;
        }
        if (tokenType.equals(UrlEncodedTypes.INVALID_ESCAPE)) {
            return INVALID_ESCAPE_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }
}
