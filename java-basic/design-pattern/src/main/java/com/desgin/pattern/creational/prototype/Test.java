package com.desgin.pattern.creational.prototype;

/**
 * Create by lastwhisper on 2019/1/27
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Mail mail = new Mail();
        mail.setContent("初始化模板");
        System.out.println("初始化mail:"+mail);
        for (int i = 0; i < 10; i++) {
            Mail mailTemp = (Mail) mail.clone();
//            mail.setName("姓名" + i);
//            mail.setEmailAddress("姓名" + i + "@qq.com");
//            mail.setContent("恭喜您，QQ号中大奖了");
            mailTemp.setName("姓名" + i);
            mailTemp.setEmailAddress("姓名" + i + "@qq.com");
            mailTemp.setContent("恭喜您，QQ号中大奖了");
            MailUtils.sendMail(mailTemp);
            System.out.println("克隆的mailTemp:"+mailTemp);
        }
        MailUtils.saveOriginMailRecord(mail);
    }
}
