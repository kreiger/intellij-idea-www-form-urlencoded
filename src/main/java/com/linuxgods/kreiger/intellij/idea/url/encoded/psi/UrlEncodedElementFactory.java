package com.linuxgods.kreiger.intellij.idea.url.encoded.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.linuxgods.kreiger.intellij.idea.url.encoded.UrlEncodedFile;
import com.linuxgods.kreiger.intellij.idea.url.encoded.UrlEncodedFileType;

public class UrlEncodedElementFactory {

    public static UrlEncodedField createField(Project project, String name) {
        UrlEncodedFile file = createFile(project, name);
        return (UrlEncodedField) file.getFirstChild();
    }

    public static UrlEncodedFile createFile(Project project, String text) {
        String name = "dummy.UrlEncoded";
        return (UrlEncodedFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, UrlEncodedFileType.INSTANCE, text);
    }

    public static UrlEncodedEscaped createEscaped(Project project, String urlEncoded) {
        String name = "dummy.UrlEncoded";
        PsiFile file = PsiFileFactory.getInstance(project).createFileFromText(name, UrlEncodedFileType.INSTANCE, urlEncoded);
        return PsiTreeUtil.findChildOfType(file, UrlEncodedEscaped.class);
    }
}
