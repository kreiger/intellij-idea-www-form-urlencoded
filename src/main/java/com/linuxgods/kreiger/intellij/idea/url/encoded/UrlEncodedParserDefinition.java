package com.linuxgods.kreiger.intellij.idea.url.encoded;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.linuxgods.kreiger.intellij.idea.url.encoded.parser.UrlEncodedParser;
import org.jetbrains.annotations.NotNull;

import static com.linuxgods.kreiger.intellij.idea.url.encoded.psi.UrlEncodedTypes.*;

public class UrlEncodedParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(UrlEncodedLanguage.INSTANCE);

    @Override public @NotNull Lexer createLexer(Project project) {
        return new UrlEncodedLexerAdapter();
    }

    @Override public @NotNull PsiParser createParser(Project project) {
        return new UrlEncodedParser();
    }

    @Override public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override public @NotNull TokenSet getWhitespaceTokens() {
        return TokenSet.WHITE_SPACE;
    }

    @NotNull @Override public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        IElementType leftET = left.getElementType();
        IElementType rightET = right.getElementType();
        if (leftET == AMPERSAND || rightET == AMPERSAND) {
            return SpaceRequirements.MUST_NOT;
        }
        return SpaceRequirements.MUST_NOT;
    }

    @Override public @NotNull TokenSet getStringLiteralElements() {
        return UrlEncodedTokenSets.STRING_LITERALS;
    }

    @Override public @NotNull PsiElement createElement(ASTNode node) {
        return Factory.createElement(node);
    }

    @Override public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new UrlEncodedFile(viewProvider);
    }
}
