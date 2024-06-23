package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UrlEncodedStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {
    public UrlEncodedStructureViewModel(@Nullable Editor editor, @NotNull PsiFile psiFile) {
        super(psiFile, editor, new UrlEncodedStructureViewElement(psiFile));
    }

    @Override public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }

    @Override protected Class<?> @NotNull [] getSuitableClasses() {
        return new Class[] { UrlEncodedField.class};
    }
}
