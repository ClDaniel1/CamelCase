package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToCamelCase extends TextComponentEditorAction {

    public ToCamelCase() {
        super(new CamelCaseEditorActionHandler<>("camelCase"));
    }


}
