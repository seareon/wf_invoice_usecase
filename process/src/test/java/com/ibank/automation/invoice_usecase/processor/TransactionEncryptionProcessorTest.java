package com.ibank.automation.invoice_usecase.processor;

import com.workfusion.intake.api.domain.Document;
import com.workfusion.intake.api.domain.Transaction;
import com.workfusion.intake.core.Module;
import com.ibank.automation.invoice_usecase.app.AppExample;
import com.ibank.automation.invoice_usecase.processor.TransactionEncryptionProcessor;
import com.ibank.automation.invoice_usecase.utils.Cipher;
import com.workfusion.rpa.core.storage.S3Manager;
import groovy.lang.Binding;
import org.codejargon.feather.Provides;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class TransactionEncryptionProcessorTest {

    @Inject
    private TransactionEncryptionProcessor transactionEncryptionProcessor;

    private S3Manager s3Manager;

    private Cipher cipher;

    private Logger logger;

    private Module testModule = new Module(){
        @Provides
        public S3Manager s3Manager() {
            return s3Manager;
        }

        @Provides
        public Cipher cipher() {
            return cipher;
        }

        @Provides
        public Logger logger() {
            return logger;
        }

    };

    @Before
    public void init() {
        s3Manager = Mockito.mock(S3Manager.class);
        cipher = Mockito.mock(Cipher.class);
        logger = Mockito.mock(Logger.class);
        Binding binding = new Binding();
        AppExample.init(binding).override(testModule).injectFields(this).get();
    }

    @Test
    public void transform() {
        //given
        final Transaction transaction = getTransaction();
        byte[] content = "Some text".getBytes();

        //when
        Mockito.when(s3Manager.getContent(Mockito.any())).thenReturn(content);
        Mockito.when(cipher.encode(Mockito.any())).thenReturn(new byte[0]);
        final Transaction processedTransaction = transactionEncryptionProcessor.transform(transaction);

        //then
        Mockito.verify(cipher, Mockito.times(1)).encode(content);
    }

    private Transaction getTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setId("1");
        final List<Document> docs = new ArrayList<>();

        Document doc = new Document();
        doc.setId("1");
        doc.setTextLink("link1.txt");
        docs.add(doc);
        transaction.setDocs(docs);

        return transaction;
    }
}