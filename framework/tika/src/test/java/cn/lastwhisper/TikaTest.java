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

/**
 * @author cunchang
 * @date 2020/8/26 下午7:38
 */
public class TikaTest {

    public static void main(String[] args) throws IOException, TikaException {
//        InputStream is = TikaTest.class.getClassLoader().getResourceAsStream("timg");
//        InputStream is1 = TikaTest.class.getClassLoader().getResourceAsStream("template.xls");
//        InputStream is2 = TikaTest.class.getClassLoader().getResourceAsStream("timg.jpg.zip");
//        InputStream is3 = TikaTest.class.getClassLoader().getResourceAsStream("mapreduce-osdi04.pdf");
//        InputStream is4 = TikaTest.class.getClassLoader().getResourceAsStream("测试.docx");
//        InputStream is5 = TikaTest.class.getClassLoader().getResourceAsStream("测试.xlsx");
//        InputStream is6 = TikaTest.class.getClassLoader().getResourceAsStream("office.xlsx");
//        InputStream is7 = TikaTest.class.getClassLoader().getResourceAsStream("transportation.png");
//        InputStream is8 = TikaTest.class.getClassLoader().getResourceAsStream("timg.gif");
//        InputStream is9 = TikaTest.class.getClassLoader().getResourceAsStream("application.properties");
        InputStream is10 = TikaTest.class.getClassLoader().getResourceAsStream("TikaApplication.java");
        getMimeType(is10);
//        xxx(is);
//        xxx(is1);
//        xxx(is2);
        xxx(is10);
//        xxx(is4);
//        xxx(is5);
    }

    public static void xxx(InputStream is) throws TikaException, IOException {
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
