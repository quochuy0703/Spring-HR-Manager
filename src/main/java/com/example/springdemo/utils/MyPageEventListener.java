package com.example.springdemo.utils;

import java.io.IOException;
import java.util.Date;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.example.springdemo.entity.User;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class MyPageEventListener extends PdfPageEventHelper {
  private User user;

  

  public MyPageEventListener(User user) {
    this.user = user;
  }



  @Override
  public void onEndPage(PdfWriter writer, Document document) {
     //code skeleton to write page header

     ResourceLoader resourceLoader = new DefaultResourceLoader();
    BaseFont bfComic;
    try {
      bfComic = BaseFont.createFont(resourceLoader.getResource("classpath:static/fonts/times.ttf").getFile().getAbsolutePath() , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      Font font = new Font(bfComic, 9);

            
     PdfPTable tbl = new PdfPTable(2);
     tbl.getDefaultCell().setBorder(0);
     tbl.setTotalWidth(523);
     PdfPCell cell = new PdfPCell();
     
    Paragraph p = new Paragraph();
    p.add(new Chunk("Phòng: "+ user.getDepartment(), font));
    p.setAlignment(Element.ALIGN_LEFT );
    cell.setBorder(0);
     cell.addElement(p) ;
     tbl.addCell(cell);

     
     cell = new PdfPCell();

     cell.setBorder(0);
     p = new Paragraph();
    p.add(new Chunk(new Date().toString(), font));
    p.setAlignment(Element.ALIGN_RIGHT);
     cell.addElement(p) ;
     tbl.addCell(cell);

     float x = document.leftMargin();
     float hei = 30f; //custom method that return header's height 
     //align bottom between page edge and page margin
     float y = document.top() + hei;

     //write the table
     tbl.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
    } catch (DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }    
    
    
} 
