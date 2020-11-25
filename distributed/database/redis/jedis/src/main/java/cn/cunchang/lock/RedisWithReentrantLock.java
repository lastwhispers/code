package cn.cunchang.lock;

import cn.cunchang.JedisUtil;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

//redis可重入锁，java实现
public class RedisWithReentrantLock {
	/*
	*ThreadLocal是一个关于创建线程局部变量的类。
	*通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。
	* 而使用ThreadLocal创建的变量只能被当前线程访问，其他线程则无法访问和修改。
	*/
	private ThreadLocal<Map<String , Integer>> lockers = new ThreadLocal<>();
	
	private Jedis jedis;
	
	public RedisWithReentrantLock(Jedis jedis) {
		this.jedis = jedis;
	}
	
	private boolean _lock(String key){
		//使用 set ket value ex number nx 指令上锁（“给萝卜占个坑”）
		return jedis.set(key , "","nx" , "ex" ,5L) != null;
	}
	
	private void _unlock(String key){
		jedis.del(key);
	}
	
	private Map<String , Integer> currentLockers(){
		Map<String , Integer> refs = lockers.get();
		if (refs != null){
			return refs;
		}
		lockers.set(new HashMap<>());
		return lockers.get();
	}
	
	public boolean lock(String key ){
		Map<String , Integer> refs = currentLockers();
		Integer refCnt = refs.get(key);
		if (refCnt != null){ //如果加过锁
			refs.put(key , refCnt + 1); //那就在threadlocal再加一把锁
			return true;
		}
		boolean ok = this._lock(key); //这里是没加过锁，那就进行加锁
		if (!ok){
			return false; //加锁不成功，返回false
		}
		refs.put(key , 1 ); //加锁成功，则在threalocal里面加上加锁信息
		return true;
	}
	
	public boolean unlock(String key){
		Map<String , Integer> refs = currentLockers();
		Integer refCnt = refs.get(key);
		if (refCnt == null){ //如果没有加锁的对象，那就不需要解锁
			return false;
		}
		refCnt -= 1; //如果有加锁的对象，那就去掉一层锁
		if (refCnt > 0 ){
			refs.put(key , refCnt); //更新threadlocal对象信息
		}else { //如果刚才减锁的对象只有一层锁，减一之后就没有锁了，那么就把threalocal里面的对象移除
			refs.remove(key);
			this._unlock(key);
		}
		return true;
	}

	public static void main(String[] args) {
		Jedis jedis = JedisUtil.getJedis();
		RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
		System.out.println(redis.lock("codehole"));
		System.out.println(redis.lock("codehole"));
		System.out.println(redis.unlock("codehole"));
		System.out.println(redis.unlock("codehole"));
	}
}

