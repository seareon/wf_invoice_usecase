package com.ibank.automation.invoice_usecase.utils;

public interface Cipher {

    byte[] encode(byte[] content);

    byte[] decode(byte[] content);

}
