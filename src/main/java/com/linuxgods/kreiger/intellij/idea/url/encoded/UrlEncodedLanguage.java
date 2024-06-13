package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lang.Language;

public class UrlEncodedLanguage extends Language {
    public static final UrlEncodedLanguage INSTANCE = new UrlEncodedLanguage();

    protected UrlEncodedLanguage() {
        super("application/x-www-form-urlencoded", "application/x-www-form-urlencoded");
    }
}
