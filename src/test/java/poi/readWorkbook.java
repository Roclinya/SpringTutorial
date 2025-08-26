package poi;

import com.tutorial.SpringTutorial.Demo1Application;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest(classes = Demo1Application.class)
public class readWorkbook {
    private String workingDirectory = System.getProperty("user.dir");
    private String path = workingDirectory+"/src/main/resources/templates/formulas_template.xls";
    @Test
    public void test() throws IOException {

        // 创建工作簿，会根据excel命名选择不同的Workbook实现类
        Workbook wb = WorkbookFactory.create(new File(path));

        // 获取工作表
        Sheet sheet = wb.getSheetAt(0);

        // 获取行
        Row row = sheet.getRow(0);

        // 获取单元格
        Cell cell = row.getCell(0);

        // 也可以采用以下方式获取单元格
        // Cell cell = SheetUtil.getCell(sheet, 0, 0);

        // 获取单元格内容
        String value = cell.getStringCellValue();
        System.err.println("第一個單元格字符：" + value);

        // 释放资源
        wb.close();

    }
}
