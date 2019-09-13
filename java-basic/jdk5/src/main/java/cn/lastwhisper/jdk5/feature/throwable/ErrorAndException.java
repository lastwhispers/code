package cn.lastwhisper.jdk5.feature.throwable;

import java.io.FileNotFoundException;

/**
 * 异常
 * @author lastwhisper
 */
public class ErrorAndException {
     private void throwError(){
         throw new StackOverflowError();
     }
     private void throwRuntimeException(){
         throw new RuntimeException();
     }
     private void throwCheckedException() throws FileNotFoundException {
         throw new FileNotFoundException();
     }

     public static void main(String[] args) throws FileNotFoundException {
         ErrorAndException errorAndException = new ErrorAndException();
         errorAndException.throwError();
         errorAndException.throwRuntimeException();
         errorAndException.throwCheckedException();
     }
}
