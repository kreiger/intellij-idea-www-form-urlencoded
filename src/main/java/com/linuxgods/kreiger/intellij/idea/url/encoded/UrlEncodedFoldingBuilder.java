package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.psi.PsiElement;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedEscaped;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedString;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URLDecoder;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UrlEncodedFoldingBuilder extends FoldingBuilderEx {


    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        Deque<FoldingDescriptor> foldingDescriptors = new ArrayDeque<>();
        root.accept(new UrlEncodedVisitor() {
            @Override public void visitElement(@NotNull PsiElement o) {
                o.acceptChildren(this);
            }

            @Override public void visitString(@NotNull UrlEncodedString string) {
                List<UrlEncodedEscaped> escapedList = string.getEscapedList();
                if (escapedList.isEmpty()) {
                    return;
                }
                FoldingGroup group = FoldingGroup.newGroup(string.getText());
                for (UrlEncodedEscaped escaped : escapedList) {
                    FoldingDescriptor fd = new FoldingDescriptor(escaped.getNode(), escaped.getTextRange(), group);
                    foldingDescriptors.add(fd);
                }
            }
        });
        return foldingDescriptors.toArray(FoldingDescriptor[]::new);
    }

    @Override public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        return URLDecoder.decode(node.getText(), UTF_8);
    }

    @Override public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}
