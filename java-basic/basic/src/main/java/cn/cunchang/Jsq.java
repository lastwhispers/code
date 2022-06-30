package cn.cunchang;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Jsq extends JFrame implements ActionListener{
	private final String[] KEYS = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	
    private JTextField resultText = new JTextField("hello world");//文本框
    private JButton Clear = new JButton("C");
    private JButton keys[] = new JButton[KEYS.length];//定义数组
    
     // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private boolean firstDigit = true;
     // 计算的中间结果。
    private double resultNum = 0.0;
    // 当前运算的运算符
    private String operator = "=";
     // 操作是否合法
    private boolean operateValidFlag = true;
	
	public Jsq() {	
        super("计算器");//设置窗体标题
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);//设置窗体的相对位置(屏幕中间)
        this.setResizable(true);// 修改计算器的大小，默认是true.如果是false,则不能拉伸。
        init();

    }
	

    private void init() {
    	// 建立一个画板放文本框和清空按钮。
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());//边框的布局管理器，设置样式
        resultText.setHorizontalAlignment(JTextField.RIGHT);// Java文本框左右对齐属性；文本框中的内容采用右对齐方式。
        resultText.setEditable(false);// 不允许修改结果文本框，使调用这个函数的控件不能被编辑。
        resultText.setBackground(Color.white);
        top.add("Center", resultText);//文本框位于top画板中间。
        Clear.setForeground(Color.red);
        Clear.setBackground(Color.black);//黑色
        top.add("East",Clear);
        
        JPanel calckeysPanel = new JPanel();  // 建立另一个画板放按键
        calckeysPanel.setLayout(new GridLayout(4, 4, 5, 5)); 
        // 用网格布局器，4行，4列的网格，网格之间的水平方向间隔为5个象素，垂直方向间隔为5个象素
        for (int i = 0; i < KEYS.length; i++) {      //从keys0开始数。
            keys[i] = new JButton(KEYS[i]);
            keys[i].setForeground(Color.blue);
            keys[i].setFont(new Font("alias", Font.PLAIN, 6));
            calckeysPanel.add(keys[i]);
        }
        keys[3].setForeground(Color.red);
        keys[7].setForeground(Color.red);
        keys[11].setForeground(Color.red);
        keys[14].setForeground(Color.red);
        keys[15].setForeground(Color.red);
   
        // 整体布局
        getContentPane().setLayout(new BorderLayout());//布局管理器
        getContentPane().add("North", top);
        getContentPane().add("Center", calckeysPanel);
        
// 为各按钮添加事件侦听器,都使用同一个事件侦听器，即本对象。 本类的声明中有implements ActionListener（能点击按钮）
       for (int i = 0; i < KEYS.length; i++) {
           keys[i].addActionListener(this);//this表示当前类的对象
       }
        Clear.addActionListener(this);
       
   }
    
    public void actionPerformed(ActionEvent e) {
        // 获取事件源的标签
        String label = e.getActionCommand();//返回的是当前动作指向对象的名称
        
        if (label.equals("C")) {// 用户按了"C"键
            resultText.setText("0");
            firstDigit = true;
            operator = "=";
        }  else if ("0123456789.".indexOf(label) >= 0) { // 用户按了数字键或者小数点键(按下任何一个数字进行运算)
            handleNumber(label);
        } else {
            handleOperator(label);// 用户按了运算符键
        }
    }
    
    //处理数字键被按下的事件
    private void handleNumber(String key) {
        if (firstDigit) {
        	 // 输入的第一个数字
            resultText.setText(key);//向text中放置数值
        } 
        else {
            resultText.setText(resultText.getText() + key);//获取text的值
        }
        firstDigit = false;
    } 

    //处理运算符键被按下的事件
    private void handleOperator(String key) {
        if (operator.equals("/")) {
        	 // 除法运算
            // 如果当前结果文本框中的值等于0
            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;// 操作不合法
                resultText.setText("除数不能为0");
            } else  {
                resultNum /= getNumberFromText();
            }
        } else if (operator.equals("+")) { // 加法运算
            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {// 减法运算
              resultNum -= getNumberFromText();
        } else if (operator.equals("*")) {// 乘法运算
            resultNum *=getNumberFromText();
        } 
        if (operator.equals("+/-")) {// 正负数运算
            resultNum = resultNum * (-1);
        } else if (operator.equals("=")) {  // 赋值运算
            resultNum = getNumberFromText();
        }
    
        if (operateValidFlag) {  // 双精度浮点数的运算
          	long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        // 运算符等于用户按的按钮
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }
    //从结果文本框中获取数字
    private double getNumberFromText() {   //得到结果
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }
 
    public static void main(String args[]) {
    	Jsq jsq = new Jsq();
    	jsq.setVisible(true);//窗口是否可见
    	jsq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击窗体右上角的关闭图标后，结束应用。
    }
}
