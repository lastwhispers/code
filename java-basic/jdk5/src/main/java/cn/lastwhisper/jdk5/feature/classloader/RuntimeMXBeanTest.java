package cn.lastwhisper.jdk5.feature.classloader;

import com.sun.tools.attach.AttachNotSupportedException;
import sun.tools.attach.HotSpotVirtualMachine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 *  引入jdk/lib/tools.jar
 * @author lastwhisper
 * @date 2020/4/5
 */
public class RuntimeMXBeanTest {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {

        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String name = bean.getName();
        int index = name.indexOf('@');
        String pid = name.substring(0, index);
        //这里要区分操作系统
        HotSpotVirtualMachine machine = (HotSpotVirtualMachine) new sun.tools.attach.WindowsAttachProvider().attachVirtualMachine(pid);
        InputStream is = machine.heapHisto("-all");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int len;
        byte[] buff = new byte[1024];
        while ((len = is.read(buff)) > 0)
            os.write(buff, 0, len);
        is.close();
        machine.detach();
        System.out.println(os);

    }

}
