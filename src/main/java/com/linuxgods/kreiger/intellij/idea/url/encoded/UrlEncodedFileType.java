package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class UrlEncodedFileType extends LanguageFileType {
    public static final UrlEncodedFileType INSTANCE = new UrlEncodedFileType();

    private UrlEncodedFileType() {
        super(UrlEncodedLanguage.INSTANCE);
    }

    @Override public @NonNls @NotNull String getName() {
        return "application/x-www-form-urlencoded";
    }

    @Override public @NlsContexts.Label @NotNull String getDescription() {
        return "URL-encoded form-data";
    }

    @Override public @NlsSafe @NotNull String getDefaultExtension() {
        return "urlencoded";
    }

    @Override public Icon getIcon() {
        return AllIcons.FileTypes.UiForm;
    }
}
