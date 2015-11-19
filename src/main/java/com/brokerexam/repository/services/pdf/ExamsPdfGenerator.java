package com.brokerexam.repository.services.pdf;

import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.quiz.QuizResult;
import com.brokerexam.web.util.MessageUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ExamsPdfGenerator {

    private static final String SYLFAEN_RESOURCE_NAME = "/com/brokerexam/common/fonts/sylfaen.ttf";

    private static final DateFormat dateFormat = new SimpleDateFormat(
            "dd.MM.yyyy");



    private static void createMemberPDF(ExamMember examMember, Document document,
                                        Font fontBoldHeader, Font fontBoldTitles, Font fontText)
            throws DocumentException

    {
        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(50);

        PdfPCell emptycell = new PdfPCell(new Phrase(new Chunk(" ")));
        PdfPCell cell = new PdfPCell(new Paragraph(
                MessageUtil.getMessage("exam.first_name"), fontBoldTitles));
        table.addCell(cell);
        if (examMember.getFirstName() != null) {
            PdfPCell distCell = new PdfPCell(new Paragraph(
                    examMember.getFirstName(), fontText));
            table.addCell(distCell);
        } else {
            table.addCell(emptycell);
        }

        cell = new PdfPCell(new Paragraph(
                MessageUtil.getMessage("exam.surename"), fontBoldTitles));
        table.addCell(cell);
        if (examMember.getSurname() != null) {
            PdfPCell distCell = new PdfPCell(new Paragraph(
                    examMember.getSurname(), fontText));
            table.addCell(distCell);
        } else {
            table.addCell(emptycell);
        }

        cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("exam.last_name"),
                fontBoldTitles));
        table.addCell(cell);
        if (examMember.getLastName() != null) {
            PdfPCell distCell = new PdfPCell(new Paragraph(
                    examMember.getLastName(), fontText));
            table.addCell(distCell);
        } else {
            table.addCell(emptycell);
        }

        cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("common.passport_number"),
                fontBoldTitles));
        table.addCell(cell);
        if (examMember.getPassport() != null) {
            PdfPCell distCell = new PdfPCell(new Paragraph(examMember.getPassport(),
                    fontText));
            table.addCell(distCell);
        } else {
            table.addCell(emptycell);
        }


        cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("exam.session_id"),
                fontBoldTitles));
        table.addCell(cell);
        if (examMember.getSessionId() != null) {
            PdfPCell distCell = new PdfPCell(new Paragraph(examMember.getSessionId(),
                    fontText));
            table.addCell(distCell);
        } else {
            table.addCell(emptycell);
        }

        document.add(table);
    }


    public static byte[] generateExamMemberPDF(ExamMember examMember) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BaseFont sylfaen = null;
        Font fontBoldHeader = null;
        Font fontBoldTitles = null;
        Font fontText = null;
        try {
            sylfaen = getSylfaenFont();
            fontBoldHeader = new Font(sylfaen, 12f, Font.BOLD);
            fontBoldTitles = new Font(sylfaen, 10f, Font.BOLD);
            fontText = new Font(sylfaen, 9f, Font.NORMAL);
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Phrase newLine = new Phrase(new Chunk("\n"));
            Paragraph title;
            document.add(newLine);
            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(50);
            title = new Paragraph(MessageUtil.getMessage("exam.appl"),
                    fontBoldHeader);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(newLine);
            table.setWidthPercentage(50);
            createMemberPDF(examMember, document, fontBoldHeader, fontBoldTitles,
                    fontText);
            document.close();

            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            // log.error("Error while generating PDF: " + ex.getMessage(), ex);
            // throw new PdfGenerationException("Error while generating PDF: " +
            // ex.getMessage(), ex);
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public static byte[] generateExamAllResultPDF(Exam exam,
                                                          List<ExamMember> examMembers, List<CommitteeMember> committeeMembers, CommitteeMember head) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BaseFont sylfaen = null;
        Font fontBoldHeader = null;
        Font fontBoldTitles = null;
        Font fontText = null;
        try {
            sylfaen = getSylfaenFont();
            fontBoldHeader = new Font(sylfaen, 12f, Font.BOLD);
            fontBoldTitles = new Font(sylfaen, 10f, Font.BOLD);
            fontText = new Font(sylfaen, 9f, Font.NORMAL);
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            Phrase newLine = new Phrase(new Chunk("\n"));

            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(95);

            PdfPCell emptycell = new PdfPCell(new Phrase(new Chunk(" ")));

            PdfPCell cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.exam_name"), fontBoldTitles));
            table.addCell(cell);
            if (exam.getName() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        exam.getName(), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }
            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.exam_start_date"), fontBoldTitles));
            table.addCell(cell);
            if (exam.getStartDate() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        dateFormat.format(exam.getStartDate()), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }

            document.add(table);
            document.add(newLine);


            table = new PdfPTable(3);
            table.setWidths(new int[] { 5, 65, 30 });
            table.setWidthPercentage(95);

            cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("exam.number"),
                    fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.anun_azganun"), fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.gnahatakan"), fontBoldTitles));
            table.addCell(cell);



            int i=0;

            for (ExamMember member : examMembers) {
                i++;
                cell = new PdfPCell(new Paragraph(String.valueOf(i), fontText));
                table.addCell(cell);

                StringBuffer name = new StringBuffer();

                if (member.getLastName() != null){
                    name.append(member.getLastName()).append(" ");
                }

                if (member.getFirstName() != null){
                    name.append(member.getFirstName()).append(" ");
                }
                if (member.getSurname() != null){
                    name.append(member.getSurname()).append(" ");
                }

                if (name.toString() != null) {
                    PdfPCell distCell = new PdfPCell(new Paragraph(name.toString(),
                            fontBoldTitles));
                    table.addCell(distCell);
                } else {
                    table.addCell(emptycell);
                }



                cell = new PdfPCell(new Paragraph(String.valueOf(member.getResult())+" %", fontBoldTitles));
                //cell.setColspan(4);
                table.addCell(cell);


            }


            document.add(table);
            document.add(newLine);
            document.add(newLine);

            if(head != null){
                PdfPTable headSignatureTable = createSignatures(fontText, head, MessageUtil.getMessage("exam.head_under"));
                document.add(headSignatureTable);
                document.add(newLine);
                document.add(newLine);
            }

            for (CommitteeMember committeeMember : committeeMembers){

                PdfPTable examSignatureTable = createSignatures(fontText, committeeMember, MessageUtil.getMessage("exam.exam_under"));
                document.add(examSignatureTable);
                document.add(newLine);
            }

            // document.add(space1);

            document.close();

            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            // log.error("Error while generating PDF: " + ex.getMessage(), ex);
            // throw new PdfGenerationException("Error while generating PDF: " +
            // ex.getMessage(), ex);
            return null;
        }

    }

    private static PdfPTable createSignatures(Font fontText, CommitteeMember signer, String under) {
        try{
            PdfPTable table1 = new PdfPTable(3);
            table1.setWidths(new int[] { 50,20,30 });


            PdfPTable table = new PdfPTable(1);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            // table.setSpacingBefore(30f);
            table.setWidthPercentage(50);

            Phrase p = new Phrase(signer.getFirstName() + " " + signer.getLastName(), fontText);
            PdfPCell cell = new PdfPCell(p);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            table.addCell(cell);

            p = new Phrase(under, fontText);
            cell = new PdfPCell(p);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(table);
            cell.setBorder(0);
            table1.addCell(cell);

            PdfPCell emptycell = new PdfPCell(new Phrase(new Chunk(" ")));
            emptycell.setBorder(0);
            table1.addCell(emptycell);

            table = new PdfPTable(1);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            // table.setSpacingBefore(30f);
            table.setWidthPercentage(50);

            p = new Phrase("", fontText);
            cell = new PdfPCell(p);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            table.addCell(cell);

            p = new Phrase(MessageUtil.getMessage("exam.sign"), fontText);
            cell = new PdfPCell(p);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(table);
            cell.setBorder(0);
            table1.addCell(cell);

            return table1;
        }catch (Exception ex) {
            // log.error("Error while generating PDF: " + ex.getMessage(), ex);
            // throw new PdfGenerationException("Error while generating PDF: " +
            // ex.getMessage(), ex);
            return null;
        }

    }

    public static byte[] generateQuizResultPDF(ExamMember examMember,
                                                       List<QuizResult> list, String percentString, int percent, Exam exam) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BaseFont sylfaen = null;
        Font fontBoldHeader = null;
        Font fontBoldTitles = null;
        Font fontText = null;
        try {
            sylfaen = getSylfaenFont();
            fontBoldHeader = new Font(sylfaen, 12f, Font.BOLD);
            fontBoldTitles = new Font(sylfaen, 10f, Font.BOLD);
            fontText = new Font(sylfaen, 9f, Font.NORMAL);
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            Phrase newLine = new Phrase(new Chunk("\n"));

            Paragraph title = new Paragraph(
                    MessageUtil.getMessage("exam.personal_data"), fontBoldHeader);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(newLine);

            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(95);

            PdfPCell emptycell = new PdfPCell(new Phrase(new Chunk(" ")));

            PdfPCell cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.exam_name"), fontBoldTitles));
            table.addCell(cell);
            if (exam.getName() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        exam.getName(), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }
            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.exam_start_date"), fontBoldTitles));
            table.addCell(cell);
            if (exam.getStartDate() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        dateFormat.format(exam.getStartDate()), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }
            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.first_name"), fontBoldTitles));
            table.addCell(cell);
            if (examMember.getFirstName() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        examMember.getFirstName(), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.last_name"), fontBoldTitles));
            table.addCell(cell);
            if (examMember.getLastName() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        examMember.getLastName(), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.surename"), fontBoldTitles));
            table.addCell(cell);
            if (examMember.getSurname() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(
                        examMember.getSurname(), fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }


            cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("common.passport_number"),
                    fontBoldTitles));
            table.addCell(cell);
            if (examMember.getPassport() != null) {
                PdfPCell distCell = new PdfPCell(new Paragraph(examMember.getPassport(),fontText));
                table.addCell(distCell);
            } else {
                table.addCell(emptycell);
            }



            document.add(table);
            document.add(newLine);

            title = new Paragraph(MessageUtil.getMessage("exam.exam_results"),
                    fontBoldHeader);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(newLine);


            table = new PdfPTable(5);
            table.setWidths(new int[] { 5, 45, 20, 20, 10 });
            table.setWidthPercentage(95);

            cell = new PdfPCell(new Paragraph(MessageUtil.getMessage("exam.number"),
                    fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.question"), fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.applicant_answer"), fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.right_answer"), fontBoldTitles));
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.result_percent"), fontBoldTitles));
            table.addCell(cell);

            int i = 0;

            float unit_percent = 0;
            if (list.size() != 0) {
                unit_percent = (float)100 / list.size();
            }

            for (QuizResult result : list) {
                i++;
                cell = new PdfPCell(new Paragraph(String.valueOf(i), fontText));
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(result.getQuestion(),
                        fontText));
                table.addCell(cell);
                switch(result.getAnswer()){
                    case 1:
                        cell = new PdfPCell(new Paragraph(result.getAnswerA(), fontText));
                        break;
                    case 2:
                        cell = new PdfPCell(new Paragraph(result.getAnswerB(), fontText));
                        break;
                    case 3:
                        cell = new PdfPCell(new Paragraph(result.getAnswerC(), fontText));
                        break;
                    case 4:
                        cell = new PdfPCell(new Paragraph(result.getAnswerD(), fontText));
                        break;
                    default:
                        cell = new PdfPCell(new Phrase(new Chunk(" ")));

                }
                table.addCell(cell);
                switch(result.getRightAnswer()){
                    case 1:
                        cell = new PdfPCell(new Paragraph(result.getAnswerA(), fontText));
                        break;
                    case 2:
                        cell = new PdfPCell(new Paragraph(result.getAnswerB(), fontText));
                        break;
                    case 3:
                        cell = new PdfPCell(new Paragraph(result.getAnswerC(), fontText));
                        break;
                    case 4:
                        cell = new PdfPCell(new Paragraph(result.getAnswerD(), fontText));
                        break;
                    default:
                        cell = new PdfPCell(new Phrase(new Chunk(" ")));

                }
                table.addCell(cell);

                if(result.getAnswer() == result.getRightAnswer()){
                    cell = new PdfPCell(new Paragraph(String.format("%.2f", unit_percent) + " %" , fontText));
                }else{
                    cell = new PdfPCell(new Paragraph(String.valueOf(0) + " %" , fontText));
                }
                table.addCell(cell);

            }

            cell = new PdfPCell(new Paragraph(
                    MessageUtil.getMessage("exam.total"), fontBoldTitles));
            cell.setColspan(4);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(percentString, fontBoldTitles));
            cell.setColspan(4);
            table.addCell(cell);

            document.add(table);
            // document.add(space1);

            document.close();

            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            // log.error("Error while generating PDF: " + ex.getMessage(), ex);
            // throw new PdfGenerationException("Error while generating PDF: " +
            // ex.getMessage(), ex);
            return null;
        }

    }

    private static BaseFont getSylfaenFont() throws DocumentException,
            IOException {
        URL url = ExamsPdfGenerator.class.getResource(SYLFAEN_RESOURCE_NAME);
        return BaseFont.createFont(url.toString(), BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED);
    }

}
