package cn.lastwhisper.feature5.generic.Generic4;

/**
 * extends BaseDaoImpl<Person>让通用模板知道你的泛型参数是啥，并且可以使用通用的方法
 * implements PersonDao是为了面向接口编程、以及更好的整合Spring
 * @author lastwhisper
 */
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

}
