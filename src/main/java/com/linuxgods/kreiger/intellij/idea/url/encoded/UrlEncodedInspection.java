package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedElementFactory;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedString;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedUnescaped;
import com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedVisitor;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UrlEncodedInspection extends LocalInspectionTool {

    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new UrlEncodedVisitor() {
            @Override public void visitUnescaped(@NotNull UrlEncodedUnescaped unescaped) {
                String text = unescaped.getText();
                PsiElement parent = unescaped.getParent();
                if (parent instanceof UrlEncodedString string && string.getUnescapedList().size() > 1) {
                    holder.registerProblem(unescaped, "URL encode all", new UrlEncodeQuickFix(null));
                }
                if (" ".equals(text)) {
                    holder.registerProblem(unescaped, "URL encode " + text + " to +", new UrlEncodeQuickFix("+"));
                    holder.registerProblem(unescaped, "URL encode " + text + " to %20", new UrlEncodeQuickFix("%20"));
                } else {
                    String encoded = URLEncoder.encode(text, UTF_8);
                    holder.registerProblem(unescaped, "URL encode " + text + " to " + encoded, new UrlEncodeQuickFix(encoded));
                }
            }
        };
    }

    private static class UrlEncodeQuickFix implements LocalQuickFix {
        private final String encoded;

        public UrlEncodeQuickFix(String encoded) {
            this.encoded = encoded;
        }

        @NotNull
        @Override
        public String getName() {
            return encoded != null ? "URL encode to "+encoded : "URL encode all";
        }

        @NotNull
        @Override
        public String getFamilyName() {
            return getName();
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            PsiElement element = descriptor.getPsiElement();
            if (encoded != null) {
                element.replace(UrlEncodedElementFactory.createEscaped(project, encoded));
            } else {
                PsiElement parent = element.getParent();
                for (PsiElement child : parent.getChildren()) {
                    if (child instanceof UrlEncodedUnescaped) {
                        child.replace(UrlEncodedElementFactory.createEscaped(project, URLEncoder.encode(child.getText(), UTF_8)));
                    }
                }
            }
        }
    }
}
