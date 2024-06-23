package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.platform.backend.navigation.NavigationRequest;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedField;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.impl.UrlEncodedFieldImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UrlEncodedStructureViewElement implements StructureViewTreeElement {
    private final NavigatablePsiElement element;

    public UrlEncodedStructureViewElement(@NotNull NavigatablePsiElement element) {
        this.element = element;
    }

    @Override public Object getValue() {
        return element;
    }

    @Override public @NotNull ItemPresentation getPresentation() {
        ItemPresentation presentation = element.getPresentation();
        return null != presentation ? presentation : new PresentationData();
    }

    @Override public TreeElement @NotNull [] getChildren() {
        if (element instanceof UrlEncodedFile) {
            List<UrlEncodedField> fields = PsiTreeUtil.getChildrenOfTypeAsList(element, UrlEncodedField.class);
            return fields
                    .stream()
                    .map(UrlEncodedStructureViewElement::new)
                    .toArray(TreeElement[]::new);
        }
        return EMPTY_ARRAY;
    }

    @Override
    public void navigate(boolean requestFocus) {
        element.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return element.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return element.canNavigateToSource();
    }
}
