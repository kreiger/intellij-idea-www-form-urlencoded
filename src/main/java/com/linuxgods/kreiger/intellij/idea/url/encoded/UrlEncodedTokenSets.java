package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.psi.tree.TokenSet;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedTypes;

public class UrlEncodedTokenSets {
    public static final TokenSet STRING_LITERALS = TokenSet.create(UrlEncodedTypes.FIELD_NAME, UrlEncodedTypes.FIELD_VALUE);
}
