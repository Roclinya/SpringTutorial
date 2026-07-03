package poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcelInsertImageTest {

    @Test
    void insertPictureIntoExcel() throws Exception {
        byte[] imageBytes = createTestPng();
        Path output = Path.of("target", "test-output", "excel-with-picture.xls");

        Files.createDirectories(output.getParent());

        try (HSSFWorkbook workbook = new HSSFWorkbook();
             OutputStream outputStream = Files.newOutputStream(output)) {

            var sheet = workbook.createSheet("圖片測試");
            int pictureIndex = workbook.addPicture(imageBytes, HSSFWorkbook.PICTURE_TYPE_PNG);

            HSSFPatriarch drawing = sheet.createDrawingPatriarch();
            HSSFClientAnchor anchor = new HSSFClientAnchor(
                    0, 0, 0, 0,
                    (short) 2, 2,
                    (short) 4, 6
            );
            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

            HSSFPicture picture = drawing.createPicture(anchor, pictureIndex);
            HSSFPictureData pictureData = picture.getPictureData();

            // Get the binary data and file extension
            byte[] data = pictureData.getData();
            String extension = pictureData.suggestFileExtension();
            System.out.println("Inserted picture extension: " + extension); // png

            // write picture to excel
            workbook.write(outputStream);

            assertNotNull(pictureData);
            System.out.println("Max image start row: " + getMaxImageStartRow(sheet));

            // sheet.getLastRowNum() 只代表 POI 認定最後一列有 Row 資料的 index，不一定包含圖片所在列。
            // 若圖片 anchor 起點比最後一列更下面，仍要迭代到圖片 row，否則該圖片不會被轉進 PDF。
            int rowLength = Math.max(sheet.getLastRowNum(), getMaxImageStartRow(sheet)) + 1;

        }

        assertTrue(Files.exists(output));
        assertTrue(Files.size(output) > 0);

        try (InputStream inputStream = Files.newInputStream(output);
             HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {

            assertNotNull(workbook.getSheet("圖片測試"));
            assertEquals(1, workbook.getAllPictures().size());
            assertEquals(imageBytes.length, workbook.getAllPictures().get(0).getData().length);
        }
    }

    private byte[] createTestPng() throws Exception {
        BufferedImage image = new BufferedImage(80, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 80, 40);
            graphics.setColor(new Color(0, 120, 215));
            graphics.fillRect(8, 8, 64, 24);
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(32, 10, 16, 16);
        } finally {
            graphics.dispose();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }


    /**
     *  取得 Sheet 中所有圖片的最大起始列，避免圖片被切掉
     *  操作方式：掃描 sheet drawing 中所有 HSSFPicture 的 anchor.row1，取最大值
     *  目的：解決sheet圖片 row58，但是 sheet.getLastRowNum() 只有 57 的問題
     */
    private int getMaxImageStartRow(Sheet sheet) {
        int result = sheet.getLastRowNum();
        if (!(sheet instanceof HSSFSheet)) {
            return result;
        }

        HSSFSheet hssfSheet = (HSSFSheet) sheet;
        if (hssfSheet.getDrawingPatriarch() == null) {
            return result;
        }

        for (HSSFShape shape : hssfSheet.getDrawingPatriarch().getChildren()) {
            if (shape instanceof HSSFPicture) {
                result = Math.max(result, ((HSSFPicture) shape).getClientAnchor().getRow1());
            }
        }
        return result;
    }
}
