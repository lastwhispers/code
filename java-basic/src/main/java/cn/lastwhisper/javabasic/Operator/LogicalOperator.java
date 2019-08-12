package cn.lastwhisper.javabasic.Operator;

/**
 * 逻辑运算符
 * @author lastwhisper
 */
public class LogicalOperator {
    public static void main(String[] args) {
        // 1. "!"表示非，对逻辑表达式进行取反运算
        System.out.println(!true);//false
        System.out.println(!false);//true

        // 2. "^"表示异或，逻辑表达式相同为false，逻辑表达式不相同为true
        System.out.println(true ^ true);//false
        System.out.println(true ^ false);//true
        System.out.println(false ^ true);//true
        System.out.println(false ^ false);//false

        // 3. "&&"和"&"表示与，逻辑表达式相同为true，逻辑表达式不相同为false；其中"&&"为短路运算符。
        System.out.println(true && true);//true
        System.out.println(true && false);//false
        //  "&&"为短路运算符，只有左侧逻辑表达式为true时，才会运算右侧逻辑表达式。
        //  与运算，左侧为false时，其实并不用继续往下运算了。
        System.out.println(false && true);//false
        System.out.println(false && false);//false
        System.out.println(true & true);//true
        System.out.println(true & false);//false
        //  "&"为非短路运算符，无论左侧逻辑表达式为true或false，都要运算右侧逻辑表达式。
        //  与运算，左侧为false时，其实并不用继续往下运算了。
        System.out.println(false & true);//false
        System.out.println(false & false);//false

        // 4. "||"和"|"表示或，运算符两侧逻辑表达式同时为false时，运算结果为false;其中"||"为短路运算符。
        System.out.println(true || true);// true
        System.out.println(true || false);// true
        //  "||"为短路运算符，只有在其左侧为false时，才运算其右侧的逻辑表达式。
        //  或运算，左侧为true时，其实并不用继续往下运算了。
        System.out.println(false || true);// true
        System.out.println(false || false);// false
        System.out.println(true | true);// true
        System.out.println(true | false);// true
        //  "|"为非短路运算符，无论左侧逻辑表达式为true或false，都要运算右侧逻辑表达式。
        //  或运算，左侧为true时，其实并不用继续往下运算了。
        System.out.println(false | true);// true
        System.out.println(false | false);// false


    }
}
