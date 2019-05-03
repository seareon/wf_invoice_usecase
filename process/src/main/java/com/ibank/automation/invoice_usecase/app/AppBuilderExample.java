package com.ibank.automation.invoice_usecase.app;

import com.workfusion.intake.core.Module;
import groovy.lang.Binding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppBuilderExample {
    private Binding context;
    private Map<String, String> params = new HashMap<>();
    private List<Module> overrideModules;
    private Object injectContext;

    public AppBuilderExample(Binding context) {
        this.context = context;
    }

    public AppBuilderExample params(Map<String, String> params) {
        this.setParams(params);
        return this;
    }

    public AppBuilderExample override(Module... modules) {
        overrideModules = Arrays.asList(modules);
        return this;
    }

    public AppBuilderExample injectFields(Object context) {
        this.injectContext = context;
        return this;
    }

    public AppExample get() {
        Module myModule = new ModuleExample();
        Module cipherModule = new CipherModule();
        Module securityModule = new SecurityModule(context);

        List<Module> modules = Arrays.asList(myModule, cipherModule, securityModule);
        return new AppExample(context, modules, overrideModules, injectContext);
    }

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
