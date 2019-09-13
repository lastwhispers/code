package cn.lastwhisper.aop.dao;

import cn.lastwhisper.aop.domain.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationLogDao extends JpaRepository<OperationLog, Long> {
}
