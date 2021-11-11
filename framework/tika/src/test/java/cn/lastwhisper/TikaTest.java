package cn.lastwhisper;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.AutoDetectParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author cunchang
 * @date 2020/8/26 下午7:38
 */
public class TikaTest {

    public static void main(String[] args) throws IOException, TikaException {

        List<String> fileNames = new ArrayList<>();
        fileNames.add("template.xls");
        fileNames.add("mapreduce-osdi04.pdf");
        fileNames.add("测试.docx");
        fileNames.add("测试.xlsx");
        fileNames.add("测试.xlsx");
        fileNames.add("transportation.png");
        fileNames.add("timg.gif");
        fileNames.add("timg");

        for (String fileName : fileNames) {
            InputStream stream = TikaTest.class.getClassLoader().getResourceAsStream(fileName);
            System.out.print(fileName+"\t");
            getMimeType(stream);
        }



    }

    public static void getType(InputStream is) throws TikaException, IOException {
        TikaConfig tikaConfig = new TikaConfig();

        Detector detector = tikaConfig.getDetector();
        Metadata metadata = new Metadata();
        MediaType mediaType = detector.detect(is, metadata);
        System.out.println(mediaType.getBaseType());
        System.out.println(mediaType.getSubtype());
        System.out.println("-------------------------------------------------------");
    }

    public static void getMimeType(InputStream inputStream) throws TikaException, IOException {
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        DefaultHandler handler = new DefaultHandler();
        try {
            parser.parse(inputStream, handler, metadata);
        } catch (TikaException | SAXException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(metadata.get(HttpHeaders.CONTENT_TYPE));
    }
}
