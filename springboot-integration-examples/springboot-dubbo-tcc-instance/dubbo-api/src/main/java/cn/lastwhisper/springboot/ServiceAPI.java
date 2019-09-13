package cn.lastwhisper.springboot;


import org.mengyun.tcctransaction.api.Compensable;

public interface ServiceAPI {
    @Compensable
    String sendMessage(String message);

    /*
     * 传入购票数量、传入购票座位，影厅编号
     *
     */

    //判断座位是否存在，模拟一个事务
    @Compensable
    boolean isTrueSeats(String seats);

    //判断座位是否售出，模拟一个事务
    @Compensable
    boolean isNotSold(String seats);

    //保存订单，模拟一个事务
    @Compensable
    String saveOrder(String field, String seats, String seatsNum);

}
