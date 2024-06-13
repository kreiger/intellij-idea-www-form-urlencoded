package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class UrlEncodedFile extends PsiFileBase {
    protected UrlEncodedFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, UrlEncodedLanguage.INSTANCE);
    }

    @Override public @NotNull FileType getFileType() {
        return UrlEncodedFileType.INSTANCE;
    }

    @Override public String toString() {
        return "URL-Encoded File";
    }
}
