package com.linuxgods.kreiger.intellij.idea.url.encoded.psi;

import com.intellij.psi.tree.IElementType;
import com.linuxgods.kreiger.intellij.idea.url.encoded.UrlEncodedLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class UrlEncodedTokenType extends IElementType {
    public UrlEncodedTokenType(@NonNls @NotNull String debugName) {
        super(debugName, UrlEncodedLanguage.INSTANCE);
    }

    @Override public String toString() {
        return getClass().getSimpleName()+"."+super.toString();
    }
}
