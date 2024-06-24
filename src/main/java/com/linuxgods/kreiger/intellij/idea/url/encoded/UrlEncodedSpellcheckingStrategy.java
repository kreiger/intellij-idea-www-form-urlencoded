package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.IdentifierSplitter;
import com.intellij.spellchecker.inspections.PropertiesSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedFieldName;
import org.jetbrains.annotations.NotNull;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlEncodedSpellcheckingStrategy extends SpellcheckingStrategy {

    @Override public @NotNull Tokenizer getTokenizer(PsiElement element) {
        if (element instanceof UrlEncodedFieldName name) {
            return new UrlEncodedFieldNameTokenizer();
        }

        return super.getTokenizer(element);
    }

    private static class UrlEncodedFieldNameTokenizer extends Tokenizer<UrlEncodedFieldName> {

        @Override public void tokenize(@NotNull UrlEncodedFieldName name, @NotNull TokenConsumer consumer) {
            consumer.consumeToken(name, URLDecoder.decode(name.getText(), StandardCharsets.UTF_8), true, 0, TextRange.allOf(name.getText()), PropertiesSplitter.getInstance());
        }
    }
}
