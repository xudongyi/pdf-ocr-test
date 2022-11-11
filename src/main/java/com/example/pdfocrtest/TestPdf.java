package com.example.pdfocrtest;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.pdfocr.OcrPdfCreator;
import com.itextpdf.pdfocr.OcrPdfCreatorProperties;
import com.itextpdf.pdfocr.PdfOcrFontProvider;
import com.itextpdf.pdfocr.tesseract4.Tesseract4LibOcrEngine;
import com.itextpdf.pdfocr.tesseract4.Tesseract4OcrEngineProperties;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestPdf
 * @Description
 * @Author xu.dongyi
 * @Date 2022/11/10 14:51
 * @Version 1.0
 **/
public class TestPdf {
    static final Tesseract4OcrEngineProperties tesseract4OcrEngineProperties = new Tesseract4OcrEngineProperties();
    private static final String TESS_DATA_FOLDER = "D:\\Program Files\\Tesseract-OCR\\tessdata";
    private static List LIST_IMAGES_OCR = Arrays.asList(new File("C:\\Users\\xu.dongyi\\Desktop\\emobile_2022-11-10_14-42-26.png"));
    private static String OUTPUT_PDF = "emobile_2022-11-10_14-42-26.pdf";

    public static void main(String[] args) throws IOException {

        final Tesseract4LibOcrEngine tesseractReader = new Tesseract4LibOcrEngine(tesseract4OcrEngineProperties);
        tesseract4OcrEngineProperties.setPathToTessData(new File(TESS_DATA_FOLDER));
        tesseract4OcrEngineProperties.setLanguages(Arrays.asList("chi_sim"));
        OcrPdfCreatorProperties properties = new OcrPdfCreatorProperties();
        properties.setPdfLang("zh");
        properties.setTextColor(DeviceRgb.RED);
        properties.setTextLayerName("text");
        properties.setImageLayerName("image");
        OcrPdfCreator ocrPdfCreator = new OcrPdfCreator(tesseractReader, properties);
        final PdfOcrFontProvider fontProvider = new PdfOcrFontProvider();
        fontProvider.addFont("C:\\Windows\\Fonts\\simhei.ttf");
        properties.setFontProvider(fontProvider);

        try (PdfWriter writer = new PdfWriter(OUTPUT_PDF)) {
            ocrPdfCreator.createPdf(LIST_IMAGES_OCR, writer).close();
        }
    }
}
