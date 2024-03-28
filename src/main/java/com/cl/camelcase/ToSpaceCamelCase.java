package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToSpaceCamelCase extends TextComponentEditorAction {

    public ToSpaceCamelCase() {
        super(new CamelCaseEditorActionHandler<>("Camel Case"));
    }


}
