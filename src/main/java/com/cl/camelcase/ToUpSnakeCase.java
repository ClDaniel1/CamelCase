package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToUpSnakeCase extends TextComponentEditorAction {

    public ToUpSnakeCase() {
        super(new CamelCaseEditorActionHandler<>("SNAKE_CASE"));
    }


}
