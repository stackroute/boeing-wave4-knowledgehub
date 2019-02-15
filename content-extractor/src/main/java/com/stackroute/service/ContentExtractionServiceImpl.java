package com.stackroute.service;

import com.stackroute.domain.PdfDomain;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ContentExtractionServiceImpl implements ContentExtractionService {


    public PdfDomain extractFromFile(final Parser parser, final String fileName ) throws IOException , SAXException,
            TikaException{

        System.out.println("Pdf ExtractFrom FIle");
        PdfDomain pdfDomain = new PdfDomain();
        String uniqueID = UUID.randomUUID().toString();
        BodyContentHandler handler = new BodyContentHandler(1000000);
        Metadata metadata =new Metadata();
        System.out.println(fileName);
        FileInputStream content = new FileInputStream(fileName);
        parser.parse(content,handler,metadata,new ParseContext());
        pdfDomain.setDocumentId(uniqueID);
        pdfDomain.setDocumentText(handler.toString());
        pdfDomain.setDocumentMetaData(metadata.names());
        return pdfDomain;
    }



    public PdfDomain extractPdf(String path) throws IOException , SAXException,
            TikaException {
        //String path = "/home/user/Documents/boeing-wave4-knowledgehub/content-extractor/src/main/resources/static/pdf-sample.pdf";
        String[] ext=path.split("\\.");
        Parser parser = new AutoDetectParser();
        System.out.println("--------- Parsing PdfDomain --------:");
        return extractFromFile(parser,path);
    }
}
