package cn.itcast.storage.service;

public interface StorageService{

    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}