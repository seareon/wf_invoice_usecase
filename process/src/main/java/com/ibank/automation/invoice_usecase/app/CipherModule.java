package com.ibank.automation.invoice_usecase.app;

import com.workfusion.intake.core.Module;
import com.ibank.automation.invoice_usecase.utils.Cipher;
import com.ibank.automation.invoice_usecase.utils.InMemoryBase64Encoder;

import org.codejargon.feather.Provides;

public class CipherModule implements Module {

    @Provides
    public Cipher cipher(InMemoryBase64Encoder inMemoryBase64Encoder) {
        return inMemoryBase64Encoder;
    }
}
