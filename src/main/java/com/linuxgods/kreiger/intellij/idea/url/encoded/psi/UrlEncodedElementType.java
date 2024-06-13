package com.linuxgods.kreiger.intellij.idea.url.encoded.psi;

import com.intellij.psi.tree.IElementType;
import com.linuxgods.kreiger.intellij.idea.url.encoded.UrlEncodedLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class UrlEncodedElementType extends IElementType {
    public UrlEncodedElementType(@NonNls @NotNull String debugName) {
        super(debugName, UrlEncodedLanguage.INSTANCE);
    }
}
