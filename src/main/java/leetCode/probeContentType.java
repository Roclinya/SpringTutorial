package leetCode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class probeContentType {

    public static void main(String[] args) throws Exception {

        Path pathWithFilename = Paths.get("/","test.xlsx");
        String contentType = getContentType(pathWithFilename);
        System.out.println("contentType: "+contentType);
    }

    private static String getContentType(Path fullFilePath) throws Exception {
        try {
            return Files.probeContentType(fullFilePath);
        } catch (Exception e) {
//            this.logger.error(StackTraceUtil.logStackTrace(e));
//            throw new CommonException("execution error");
            throw new Exception();
        }
    }
}
