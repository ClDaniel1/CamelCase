package com.cl.camelcase;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.editor.actions.EditorActionUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.util.ThrowableRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCaseEditorActionHandler<T> extends EditorActionHandler {

    private final String doAction;

    CamelCaseEditorActionHandler(String doAction) {
        super(true);
        this.doAction = doAction;
    }

    private static void replaceText(final Editor editor, final String replacement) {

        try {
            WriteAction.run((ThrowableRunnable<Throwable>) () -> {
                int start = editor.getSelectionModel().getSelectionStart();
                EditorModificationUtil.insertStringAtCaret(editor, replacement);
                editor.getSelectionModel().setSelection(start, start + replacement.length());
            });
        } catch (Throwable ignored) {

        }
    }

    @Override
    protected final void doExecute(@NotNull final Editor editor, @Nullable final Caret caret, final DataContext dataContext) {

        final Pair<Boolean, T> additionalParameter = beforeWriteAction(editor);
        if (!additionalParameter.first) {
            return;
        }

        new EditorWriteActionHandler(false) {
            @Override
            public void executeWriteAction(@NotNull Editor editor1, @Nullable Caret caret1, DataContext dataContext1) {
            }
        }.doExecute(editor, caret, dataContext);
    }

    @NotNull
    private Pair<Boolean, T> beforeWriteAction(Editor editor) {
        String text = editor.getSelectionModel().getSelectedText();
        if (text == null || text.isEmpty()) {
            editor.getSelectionModel().selectWordAtCaret(true);
            boolean moveLeft = true;
            boolean moveRight = true;
            int start = editor.getSelectionModel().getSelectionStart();
            int end = editor.getSelectionModel().getSelectionEnd();
            Pattern p = Pattern.compile("[^A-z0-9.\\-]");

            // move caret left
            while (moveLeft && start != 0) {
                start--;
                EditorActionUtil.moveCaret(editor.getCaretModel().getCurrentCaret(), start, true);
                Matcher m = p.matcher(Objects.requireNonNull(editor.getSelectionModel().getSelectedText()));
                if (m.find()) {
                    start++;
                    moveLeft = false;
                }
            }

            editor.getSelectionModel().setSelection(end - 1, end);

            // move caret right
            while (moveRight && end != editor.getDocument().getTextLength()) {
                end++;
                EditorActionUtil.moveCaret(editor.getCaretModel().getCurrentCaret(), end, true);
                Matcher m = p.matcher(Objects.requireNonNull(editor.getSelectionModel().getSelectedText()));
                if (m.find()) {
                    end--;
                    moveRight = false;
                }
            }
            editor.getSelectionModel().setSelection(start, end);

            text = editor.getSelectionModel().getSelectedText();
        }

        String newText;
        CamelCaseConfig config = CamelCaseConfig.getInstance();
        if (Objects.equals(this.doAction, "") && config != null && (config.getcb1State() || config.getcb2State() || config.getcb3State() || config.getcb4State() || config.getcb5State() || config.getcb6State())) {
            newText = Conversion.transform(text, config.getcb7State(), // pascal case with space
                    config.getcb6State(), // space case
                    config.getcb1State(), // kebab case
                    config.getcb2State(), // upper snake case
                    config.getcb3State(), // pascal case
                    config.getcb4State(), // camel case
                    config.getcb5State(), // lower snake case
                    config.getmodel(), "");
        } else {
            newText = this.runWithoutConfig(text);
        }

        final Editor fEditor = editor;
        final String fReplacement = newText;
        Runnable runnable = () -> CamelCaseEditorActionHandler.replaceText(fEditor, fReplacement);
        ApplicationManager.getApplication().runWriteAction(getRunnableWrapper(fEditor.getProject(), runnable));
        return continueExecution();
    }

    private String runWithoutConfig(String text) {
        String[] conversionList = {"kebab-case", "SNAKE_CASE", "CamelCase", "camelCase", "snake_case", "space case", "Camel Case"};
        boolean usePascalCaseWithSpace = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "Camel Case");
        boolean useSpaceCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "space case");
        boolean useKebabCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "kebab-case");
        boolean useUpperSnakeCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "SNAKE_CASE");
        boolean usePascalCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "CamelCase");
        boolean useCamelCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "camelCase");
        boolean useLowerSnakeCase = Objects.equals(this.doAction, "") || Objects.equals(this.doAction, "snake_case");

        String replaceToQuotes = Objects.equals(this.doAction, "'") || Objects.equals(this.doAction, "\"") || Objects.equals(this.doAction, "`") ? this.doAction : "";
        return Conversion.transform(text, usePascalCaseWithSpace, // pascal case with space
                useSpaceCase, // space case
                useKebabCase, // kebab case
                useUpperSnakeCase, // upper snake case
                usePascalCase, // pascal case
                useCamelCase, // camel case
                useLowerSnakeCase, // lower snake case
                conversionList,
                replaceToQuotes);
    }

    private Pair<Boolean, T> continueExecution() {
        return new Pair<>(true, null);
    }


    private Runnable getRunnableWrapper(final Project project, final Runnable runnable) {
        return () -> CommandProcessor.getInstance().executeCommand(project, runnable, "CamelCase", ActionGroup.EMPTY_GROUP);
    }

}