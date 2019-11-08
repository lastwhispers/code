package cn.lastwhisper.jdk5.feature.reflect;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author lastwhisper
 */
public class TestClass {
    public static void main(String[] args) throws FileNotFoundException {
        String fileFullPath = "D:\\code\\GitRepository\\Code\\java-basic\\jdk5\\src\\main\\java\\cn\\lastwhisper\\jdk5\\feature\\reflect\\TestClass.java";
        JavaDocBuilder builder = new JavaDocBuilder();
        builder.addSource(new FileReader(fileFullPath));

        JavaSource src = builder.getSources()[0];
        String[] imports = src.getImports();

        for (String imp : imports) {
            System.out.println(imp);
        }
    }
}
