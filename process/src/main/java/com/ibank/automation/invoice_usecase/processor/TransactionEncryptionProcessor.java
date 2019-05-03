package com.ibank.automation.invoice_usecase.processor;

import com.workfusion.intake.api.domain.Document;
import com.workfusion.intake.api.domain.Transaction;
import com.workfusion.intake.processor.TransactionProcessor;
import com.ibank.automation.invoice_usecase.utils.Cipher;
import com.workfusion.rpa.core.storage.S3Manager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionEncryptionProcessor implements TransactionProcessor {

    private S3Manager s3Manager;
    private Logger logger;
    private Cipher cipher;

    @Inject
    public TransactionEncryptionProcessor(S3Manager s3Manager, Cipher cipher,  Logger logger) {
        this.s3Manager = s3Manager;
        this.logger = logger;
        this.cipher = cipher;
    }

    @Override
    public Transaction transform(Transaction transaction) {
        List<Document> encodedDocuments = transaction
                .getDocs()
                .stream()
                .map(this::encryptDocumentContent)
                .collect(Collectors.toList());

        transaction.setDocs(encodedDocuments);
        logger.debug("Documents were successfully encrypted");
        return transaction;
    }

    private Document encryptDocumentContent(final Document document) {
        if (StringUtils.isNotEmpty(document.getTextLink())) {
            byte[] content = s3Manager.getContent(document.getTextLink());
            byte[] base64Content = cipher.encode(content);
            String link = s3Manager.putFile(
                    document.getId() + "_encoded",
                    new ByteArrayInputStream(base64Content),
                    MediaType.TEXT_HTML_VALUE
            );
            Document base64Document = document.createChildDocument();
            base64Document.setTextLink(link);
            return base64Document;
        }
        return document;
    }
}
