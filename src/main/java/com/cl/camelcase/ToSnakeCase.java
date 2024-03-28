package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToSnakeCase extends TextComponentEditorAction {

    public ToSnakeCase() {
        super(new CamelCaseEditorActionHandler<>("snake_case"));
    }


}
