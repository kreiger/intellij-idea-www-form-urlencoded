package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.psi.PsiElement;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedEscapes;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedFieldName;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class UrlEncodedFoldingBuilder extends FoldingBuilderEx {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        FoldingGroup group = FoldingGroup.newGroup("url-encoded");
        Deque<FoldingDescriptor> foldingDescriptors = new ArrayDeque<>();
        root.accept(new UrlEncodedVisitor() {
            @Override public void visitElement(@NotNull PsiElement o) {
                o.acceptChildren(this);
            }

            @Override public void visitEscapes(@NotNull UrlEncodedEscapes o) {
                FoldingDescriptor fd = new FoldingDescriptor(o.getNode(), o.getTextRange(), group);
                foldingDescriptors.add(fd);
            }
        });
        return foldingDescriptors.toArray(FoldingDescriptor[]::new);
    }

    @Override public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        String text = node.getText();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length();) {
            char c = text.charAt(i);
            if (c == '%') {
                builder.append((char)Integer.parseInt(text.substring(i+1, i+3), 16));
                i += 3;
            } else {
                builder.append(' ');
                i++;
            }
        }
        return builder.toString();
    }

    @Override public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}
