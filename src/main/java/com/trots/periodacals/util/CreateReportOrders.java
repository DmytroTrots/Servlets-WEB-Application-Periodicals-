package com.trots.periodacals.util;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.entity.Receipt;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class CreateReportOrders extends TimerTask {

    public static final String ORDER_REPORT_PDF = "C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\order_report.pdf";

    @Override
    public void run() {
        Date date = new Date();
        String file = ORDER_REPORT_PDF;
        {
            try {
                PdfWriter writer = new PdfWriter(file);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                float [] pointColumnWidths = {150F, 150F, 150F, 150F};
                Table table = new Table(pointColumnWidths);

                Paragraph paragraph = new Paragraph("Daily Report about Orders\n"+date);
                document.add(paragraph);

                table.addCell(new Cell().add("User ID"));
                table.addCell(new Cell().add("Title"));
                table.addCell(new Cell().add("Total price"));
                table.addCell(new Cell().add("Months"));
                List<Receipt> list = ReceiptDaoImpl.getInstance().getAllAcceptedOrder();
                for (Receipt r: list){
                    table.addCell(new Cell().add(String.valueOf(r.getUserId())));
                    table.addCell(new Cell().add(r.getTitle()));
                    table.addCell(new Cell().add(String.valueOf(r.getMonths()*r.getPricePerMonth())));
                    table.addCell(new Cell().add(String.valueOf(r.getMonths())));
                }

                document.add(table);

                System.out.println("pdfCreate");
                document.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            Mailer.sendMailToAdminReportOrders("headerperiodicalsiteepam@gmail.com", "Daily report about orders", "Daily report");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
