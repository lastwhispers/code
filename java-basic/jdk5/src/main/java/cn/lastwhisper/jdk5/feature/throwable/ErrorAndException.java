package cn.lastwhisper.jdk5.feature.throwable;

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
     // uncheck exception
     private void throwRuntimeException(){
         throw new RuntimeException();
     }
     // check exception


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
