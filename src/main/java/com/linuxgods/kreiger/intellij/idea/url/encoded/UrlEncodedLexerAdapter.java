package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lexer.FlexAdapter;
import com.linuxgods.kreiger.intellij.idea.url.encoded.UrlEncodedLexer;

public class UrlEncodedLexerAdapter extends FlexAdapter {
    public UrlEncodedLexerAdapter() {
        super(new UrlEncodedLexer( null));
    }
}
