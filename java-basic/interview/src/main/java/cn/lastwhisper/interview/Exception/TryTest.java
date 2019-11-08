package cn.lastwhisper.interview.Exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author lastwhisper
 */
public class TryTest {
    public void test(){
        try (FileInputStream fis = new FileInputStream("d:/59035.zip")){
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 反编译后
    public void test1()
    {
        try
        {
            FileInputStream fileinputstream = new FileInputStream("d:/59035.zip");
            Throwable throwable = null;
            if(fileinputstream != null)
                if(throwable != null)
                    try
                    {
                        fileinputstream.close();
                    }
                    catch(Throwable throwable1)
                    {
                        throwable.addSuppressed(throwable1);
                    }
                else
                    fileinputstream.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }
}
