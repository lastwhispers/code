# mybatis一级缓存导致的脏读



## mybatis下读已提交测试

| 步骤 | thread A                                           | thread B                  |
| ---- | -------------------------------------------------- | ------------------------- |
| 1    | 开启事务                                           |                           |
| 2    | User user1 = userMapper.selectById(1L);user1不为空 | 开启事务                  |
| 3    | Thread.sleep(10s);                                 | userMapper.deleteById(1L) |
| 4    |                                                    | 提交事务                  |
| 5    | User user2= userMapper.selectById(1L);user2不为空  |                           |
| 6    | 提交事务                                           |                           |

- 按照mysql`读已提交`的规则，第5步应该返回空
- 第2步有sql打印，第5步无sql打印，且user1==user2，说明user2是mybatis的缓存

