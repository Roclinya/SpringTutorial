package com.tutorial.SpringTutorial.Service;

import com.tutorial.SpringTutorial.vo.StockDto;
import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.Utf8ByteOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;

@Service
public class JTEBuilder {
    private static TemplateEngine templateEngine = null;
    public String build() throws URISyntaxException {
        HashMap<String, Object> cResult = new HashMap<String, Object>();
        HashMap<String, Object> cReport01 = new HashMap<String, Object>();
        cReport01.put("year",  "1993");
        cResult.put("global", cReport01);
        cResult.put("stockDto", new StockDto());
        // 创建 Utf8ByteOutput 对象来存储渲染后的内容
        Utf8ByteOutput cOutput = new Utf8ByteOutput(4 * 1024 * 1024);

        // 获取模板文件的路径
        URI cUri = JTEBuilder.class.getResource("/htmlTemplate").toURI();
        String sUri = cUri.toString();
        File cFile = new File(cUri);
        CodeResolver codeResolver = new DirectoryCodeResolver(Path.of(cFile.getAbsolutePath()));
        templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        // 渲染模板到输出流
        templateEngine.render("example.jte", cResult, cOutput);
        // 将输出流的内容转换为字符串并返回
//        return cOutput.toString(); //此數cOutput是物件直接toSting() 會變成 gg.jte.output.Utf8ByteOutput@425509e2
// 返回生成的HTML內容
        return  new String(cOutput.toByteArray(), StandardCharsets.UTF_8);
    }

}
