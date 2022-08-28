package com.example.springdemo.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.User;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UserPDFExport {
    public void exportPdf(HttpServletResponse response){

        // step 1: creation of a document-object
        Document document = new Document();

        

        System.out.println("the Paragraph object");

        
        try {
            // step 2:
            // we create a writer that listens to the document
            PdfWriter.getInstance(document, response.getOutputStream());

            // step 3: we open the document
            document.open();
            // step 4:
            Paragraph p1 = new Paragraph(new Chunk(
                    "This is my first paragraph. ",
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
            p1.add("The leading of this paragraph is calculated automagically. ");
            p1.add("The default leading is 1.5 times the fontsize. ");
            p1.add(new Chunk("You can add chunks "));
            p1.add(new Phrase("or you can add phrases. "));
            p1.add(new Phrase(
                "Unless you change the leading with the method setLeading, the leading doesn't change if you add text with another leading. This can lead to some problems.",
                FontFactory.getFont(FontFactory.HELVETICA, 18)));
            document.add(p1);
            Paragraph p2 = new Paragraph(new Phrase(
                    "This is my second paragraph. ", FontFactory.getFont(
                            FontFactory.HELVETICA, 12)));
            p2.add("As you can see, it started on a new line.");
            document.add(p2);
            Paragraph p3 = new Paragraph("This is my third paragraph.",
                    FontFactory.getFont(FontFactory.HELVETICA, 12));
            document.add(p3);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        // step 5: we close the document
        document.close();
    }

    public void exportCovidPdf(HttpServletResponse response, User user){

        // step 1: creation of a document-object
        Document document = new Document();

        System.out.println("the Paragraph object");

        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new MyPageEventListener(user));
            
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            System.out.println(resourceLoader.getResource("classpath:static/fonts/times.ttf").getFile().getAbsolutePath() );
            BaseFont bfComic = BaseFont.createFont(resourceLoader.getResource("classpath:static/fonts/times.ttf").getFile().getAbsolutePath() , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bfComic, 8);

            // HeaderFooter header = new HeaderFooter(new Phrase("Phòng: " + user.getDepartment(), font), false);
            
            
            // PdfPTable tbl = new PdfPTable(3);
            // tbl.setTotalWidth(523);
            // tbl.addCell("1st cell");
            // tbl.addCell("2nd cell");
            // tbl.addCell("3rd cell");
            // Phrase chunk = new Phrase();
            // chunk.add(tbl);
            // HeaderFooter header = new HeaderFooter(chunk, false);
            
            // HeaderFooter footer = new HeaderFooter(new Phrase("footer "), new Phrase("."));
            // header.setBorder(Rectangle.NO_BORDER);
            
            // document.setHeader(header);
            

            // step 3: we open the document
            document.open();
            // step 4:

            Paragraph p = new Paragraph(new Chunk(
                "Thông tin Covid ",
                FontFactory.getFont(FontFactory.HELVETICA, 18)));
                p.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(p);

            printUserInfoCovid(user, document, font);

            
            

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        // step 5: we close the document
        document.close();
    }

    private void printUserInfoCovid(User user, Document document, Font font) {
        font.setSize(12);
        
        Paragraph p = new Paragraph(new Chunk(
                "Tên: ", font));
        p.add(user.getLastName()+ ' '+ user.getFirstName());
        document.add(p);

        p = new Paragraph(new Chunk(
                "Covid: ", font));
        p.add(user.getCovids().size() !=0 ? (user.getCovids().get(user.getCovids().size() -1).getCovidInfo()? "Dương tính" : "Âm tính" ): "Chưa có thông tin");
        document.add(p);

        // p3 = new Paragraph(new Chunk(
            //         "Mũi tiêm: ", FontFactory.getFont(
            //                 FontFactory.HELVETICA, 12)));
        p = new Paragraph(new Chunk(
                "Mũi tiêm: ", font));
        p.add(user.getInjections().size() + " Mũi");
        document.add(p);

        p = new Paragraph(new Chunk(
                "Nhiệt độ: ", font));
        p.add(user.getTemps().size()!=0?user.getTemps().get(user.getTemps().size() - 1).getTemp() + " độ C" : "Chưa có thông tin");
        document.add(p);
    }

    public void exportALLStaffCovidPdf(HttpServletResponse response, List<User> users){
        // step 1: creation of a document-object

        if(users.size()==0){
            return;
        }


        Document document = new Document();

        System.out.println("the Paragraph object");

        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            writer.setPageEvent(new MyPageEventListener(users.get(0)));
            
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            System.out.println(resourceLoader.getResource("classpath:static/fonts/times.ttf").getFile().getAbsolutePath() );
            BaseFont bfComic = BaseFont.createFont(resourceLoader.getResource("classpath:static/fonts/times.ttf").getFile().getAbsolutePath() , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bfComic, 8);
            

            // step 3: we open the document
            document.open();
            // step 4:

            Paragraph p = new Paragraph(new Chunk(
                "Thông tin Covid ",
                FontFactory.getFont(FontFactory.HELVETICA, 18)));
                p.setAlignment(Paragraph.ALIGN_CENTER);
                
            document.add(p);

            for(User user : users){
                printUserInfoCovid(user, document, font);

                p = new Paragraph(new Chunk(
                "------------- ", font));
                document.add(p);
            }
            

            
            

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        // step 5: we close the document
        document.close();
    }
}
