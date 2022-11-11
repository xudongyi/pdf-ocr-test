package com.example.pdfocrtest;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.PdfPageSize;
import com.spire.pdf.graphics.*;
import com.spire.pdf.graphics.layer.PdfLayer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class MultiLayeredPdf {

    public static void main(String[] args) throws Exception{

        //create a PdfDocument object
        PdfDocument pdf = new PdfDocument();

        //add a page
        PdfPageBase page = pdf.getPages().add(PdfPageSize.A4,new PdfMargins(0));

        //add the first layer as the background layer
        PdfLayer layer = pdf.getLayers().addLayer("background");
        PdfCanvas canvas = layer.createGraphics(pdf.getPages().get(0).getCanvas());
        canvas.drawRectangle(PdfBrushes.getLightGray(),new Rectangle2D.Float(0,0,(float) pdf.getPages().get(0).getActualSize().getWidth(),(float) pdf.getPages().get(0).getActualSize().getHeight()));

        //add the second layer as the content layer
        layer = pdf.getLayers().addLayer("content");
        canvas = layer.createGraphics(pdf.getPages().get(0).getCanvas());

        //string
        String string = "This blog post demonstrates how to create a multi-layered PDF. ";

        //create a solid brush object
        PdfSolidBrush brush = new PdfSolidBrush(new PdfRGBColor(Color.BLUE));

        //create a true type font
        PdfTrueTypeFont font= new PdfTrueTypeFont(new Font("Times New Roman",Font.PLAIN,15));

        //draw text on the content layer
        canvas.drawString(string, font, brush, new Point2D.Float(40, 40));

        //save the document
        pdf.saveToFile("output/MultiLayered.pdf");
        pdf.close();
    }
}
