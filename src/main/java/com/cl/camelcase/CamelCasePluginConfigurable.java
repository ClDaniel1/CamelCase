package com.cl.camelcase;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * This ProjectConfigurable class appears on Settings dialog,
 * to let user configure this plugin's behavior.
 */
public class CamelCasePluginConfigurable implements SearchableConfigurable {

    private OptionGui mGUI;
    private final CamelCaseConfig mConfig;

    public CamelCasePluginConfigurable() {
        mConfig = CamelCaseConfig.getInstance();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mGUI = new OptionGui();
        mGUI.createUI();
        return mGUI.getRootPanel();
    }

    @Override
    public void disposeUIResources() {
        mGUI = null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Camel Case";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preference.CamelCasePluginConfigurable";
    }

    @NotNull
    @Override
    public String getId() {
        return "preference.CamelCasePluginConfigurable";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        mGUI.apply();
    }

    @Override
    public void reset() {

    }

}