package com.epam.jmp.spring.mvc.util;

import com.epam.jmp.spring.mvc.model.Ticket;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * GeneratePdfUtil
 * Date: 03/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class GeneratePdfUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratePdfUtil.class);
    private static final int NUM_COLUMNS = 5;

    public static ByteArrayInputStream ticketsReport(List<Ticket> tickets) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(NUM_COLUMNS);
            table.setWidthPercentage(60);
            addHeader("Id", table);
            addHeader("EventId", table);
            addHeader("UserId", table);
            addHeader("Category", table);
            addHeader("Place", table);

            tickets.forEach(ticket -> {
                addCell(String.valueOf(ticket.getId()), table);
                addCell(String.valueOf(ticket.getEventId()), table);
                addCell(String.valueOf(ticket.getUserId()), table);
                addCell(String.valueOf(ticket.getCategory()), table);
                addCell(String.valueOf(ticket.getPlace()), table);
            });

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            LOGGER.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addCell(String name, PdfPTable table) {
        PdfPCell cell = new PdfPCell(new Phrase(name));
        cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static void addHeader(String name, PdfPTable table) {
        PdfPCell hcell = new PdfPCell(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);
    }
}
