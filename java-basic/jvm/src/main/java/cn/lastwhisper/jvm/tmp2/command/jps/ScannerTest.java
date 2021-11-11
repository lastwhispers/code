package cn.lastwhisper.jvm.tmp2.command.jps;

import java.util.Scanner;

/**
 *
 * 开启如下参数，jps将无法探知java进程
 * -XX:-UsePerfData
 *
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.next();
    }
}
