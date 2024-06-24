package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.navigation.NavigationItem;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UrlEncodedNavBarExtension extends StructureAwareNavBarModelExtension {
    @NotNull @Override protected Language getLanguage() {
        return UrlEncodedLanguage.INSTANCE;
    }

    @Override public @Nullable String getPresentableText(Object object) {
        if (object instanceof UrlEncodedFile file) {
            return file.getName();
        }
        if (object instanceof UrlEncodedField field) {
            return field.getName();
        }
        return null;
    }

    @Override public @Nullable Icon getIcon(Object object) {
        if (object instanceof NavigationItem item) {
            return item.getPresentation().getIcon(false);
        }
        return null;
    }
}
