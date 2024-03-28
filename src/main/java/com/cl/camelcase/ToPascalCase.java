package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToPascalCase extends TextComponentEditorAction {

    public ToPascalCase() {
        super(new CamelCaseEditorActionHandler<>("CamelCase"));
    }


}
