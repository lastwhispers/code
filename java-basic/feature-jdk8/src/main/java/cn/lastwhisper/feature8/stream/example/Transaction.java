package cn.lastwhisper.feature8.stream.example;

//交易类
public class Transaction {

    private Trader trader;
    private Integer year;
    private Integer value;

    public Transaction() {
    }

    public Transaction(Trader trader, Integer year, Integer value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction [trader=" + trader + ", year=" + year + ", value="
                + value + "]";
    }

}
