package cn.cunchang.mr;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.File;

/**
 * 小文件解决方案之SequenceFile
 * Created by xuwei
 */
public class SmallFileSeq {

    public static void main(String[] args) throws Exception{
        //生成SequenceFile文件
        write("D:\\smallFile","/seqFile");
        //读取SequenceFile文件
        read("/seqFile");
    }


    /**
     * 生成SequenceFile文件
     * @param inputDir 输入目录-windows目录
     * @param outputFile 输出文件-hdfs文件
     * @throws Exception
     */
    private static void write(String inputDir,String outputFile) throws Exception{
        //创建一个配置对象
        Configuration conf = new Configuration();
        //指定HDFS的地址
        conf.set("fs.defaultFS","hdfs://bigdata01:9000");

        //获取操作HDFD的对象
        FileSystem fileSystem = FileSystem.get(conf);

        //删除HDFS上的输出文件
        fileSystem.delete(new Path(outputFile),true);

        //构造opts数组，有三个元素
        /*
        第一个是输出路径【文件】
        第二个是key的类型
        第三个是value的类型
         */
        SequenceFile.Writer.Option[] opts = new SequenceFile.Writer.Option[]{
                SequenceFile.Writer.file(new Path(outputFile)),
                SequenceFile.Writer.keyClass(Text.class),
                SequenceFile.Writer.valueClass(Text.class)
        };
        //创建了一个writer实例
        SequenceFile.Writer writer = SequenceFile.createWriter(conf, opts);

        //指定需要压缩的文件的目录
        File inputDirPath = new File(inputDir);
        if(inputDirPath.isDirectory()){
            //获取目录中的文件
            File[] files = inputDirPath.listFiles();
            //迭代文件
            for (File file: files) {
                //获取文件的全部内容
                String content = FileUtils.readFileToString(file, "UTF-8");
                //获取文件名
                String fileName = file.getName();
                Text key = new Text(fileName);
                Text value = new Text(content);
                //向SequenceFile中写入数据
                writer.append(key,value);
            }
        }
        writer.close();
    }

    /**
     * 读取SequenceFile文件
     * @param inputFile SequenceFile文件路径
     * @throws Exception
     */
    private static void read(String inputFile) throws Exception{
        //创建一个配置对象
        Configuration conf = new Configuration();
        //指定HDFS的地址
        conf.set("fs.defaultFS","hdfs://bigdata01:9000");
        //创建阅读器
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(new Path(inputFile)));
        Text key = new Text();
        Text value = new Text();
        //循环读取数据
        while(reader.next(key,value)){
            //输出文件名称
            System.out.print("文件名："+key.toString()+",");
            //输出文件内容
            System.out.println("文件内容："+value.toString()+"");
        }
        reader.close();
    }

}
