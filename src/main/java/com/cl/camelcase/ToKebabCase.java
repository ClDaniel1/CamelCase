package com.cl.camelcase;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

/**
 * Switch between snake_case, SNAKE_CASE, SnakeCase, snakeCase.
 */
public class ToKebabCase extends TextComponentEditorAction {

    public ToKebabCase() {
        super(new CamelCaseEditorActionHandler<>("kebab-case"));
    }


}
