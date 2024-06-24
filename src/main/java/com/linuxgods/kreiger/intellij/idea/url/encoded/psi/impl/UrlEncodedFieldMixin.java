package com.linuxgods.kreiger.intellij.idea.url.encoded.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.icons.AllIcons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedElementFactory;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedField;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public abstract class UrlEncodedFieldMixin extends ASTWrapperPsiElement implements UrlEncodedField, NavigatablePsiElement {

    public UrlEncodedFieldMixin(ASTNode node) {
        super(node);
    }

    public String getName() {
        ASTNode keyNode = this.getNode().findChildByType(UrlEncodedTypes.FIELD_NAME);
        if (keyNode != null) {
            return URLDecoder.decode(keyNode.getText(), StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    public PsiElement setName(String newName) {
        ASTNode node = getNode().findChildByType(UrlEncodedTypes.FIELD_NAME);
        if (node != null) {
            UrlEncodedField field =
                    UrlEncodedElementFactory.createField(getProject(), newName);
            ASTNode newNode = field.getFirstChild().getNode();
            getNode().replaceChild(node, newNode);
        }
        return this;
    }


    public PsiElement getNameIdentifier() {
        ASTNode keyNode = getNode().findChildByType(UrlEncodedTypes.FIELD_NAME);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            @Override public String getPresentableText() {
                return getName();
            }

            @Override public String getLocationString() {
                PsiFile containingFile = getContainingFile();
                return containingFile == null ? null : containingFile.getName();            }

            @Override public @Nullable Icon getIcon(boolean unused) {
                return AllIcons.Nodes.Property;
            }
        };
    }

}
