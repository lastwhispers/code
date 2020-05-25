package cn.lastwhisper.feature5.throwable;

import java.io.FileNotFoundException;

/**
 * 异常
 * @author lastwhisper
 */
public class ErrorAndException {
     // Error
     private void throwError(){
         throw new StackOverflowError();
     }

    /**
     * uncheck exception
     *  （1）RuntimeException 的子类
     *  （2）在方法签名上可以不显示声明 throws *Exception
     *  （3）调用方可以不 try/catch ，或者 throws *Exception
     *  比如：NullPointerException
     *
     */
     private void throwRuntimeException() throws RuntimeException{
         throw new RuntimeException();
     }

     /**
      * check exception
      *  （1）非 RuntimeException 的子类
      *  （2）在方法签名上必须显示声明 throws *Exception
      *  （3）调用方必须 try/catch ，或者 throws *Exception
      *  比如：FileNotFoundException
      */
     private void throwCheckedException() throws FileNotFoundException {
         throw new FileNotFoundException();
     }

     public static void main(String[] args)  {
         ErrorAndException errorAndException = new ErrorAndException();
         errorAndException.throwError();
         errorAndException.throwRuntimeException();
         // 受检查异常，声明throws FileNotFoundException或者try/catch
         try {
             errorAndException.throwCheckedException();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }
}
