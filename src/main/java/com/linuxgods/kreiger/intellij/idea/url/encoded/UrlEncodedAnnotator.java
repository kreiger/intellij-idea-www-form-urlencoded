package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedFieldName;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedUnreserved;
import org.jetbrains.annotations.NotNull;

public class UrlEncodedAnnotator implements Annotator {
    @Override public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof UrlEncodedUnreserved && element.getParent() instanceof UrlEncodedFieldName) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(UrlEncodedSyntaxHighlighter.NAME).create();
        }
    }
}
