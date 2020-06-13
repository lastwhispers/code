package cn.lastwhisper.cache.service;

import cn.lastwhisper.cache.bean.Department;
import cn.lastwhisper.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    //@Qualifier("deptCacheManager")
    //@Autowired
    //RedisCacheManager deptCacheManager;


    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    // 使用缓存管理器得到缓存，进行api调用
    //public Department getDeptById(Integer id) {
    //    System.out.println("查询部门" + id);
    //    Department department = departmentMapper.getDeptById(id);
    //
    //    //获取某个缓存
    //    Cache dept = deptCacheManager.getCache("dept");
    //    dept.put("dept:1", department);
    //
    //    return department;
    //}


}
