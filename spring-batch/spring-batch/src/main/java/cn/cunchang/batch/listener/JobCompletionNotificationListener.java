package cn.cunchang.batch.listener;

import cn.cunchang.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job 执行开始. jobName={}, jobId={}, jobExecutionId={}, jobParameters={}",
                jobExecution.getJobInstance().getJobName(), jobExecution.getJobId(), jobExecution.getId(),
                jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            String jobName = jobExecution.getJobInstance().getJobName();
            Long jobExecutionId = jobExecution.getId();
            BatchStatus batchStatus = jobExecution.getStatus();
            ExitStatus exitStatus = jobExecution.getExitStatus();
            JobParameters jobParameters = jobExecution.getJobParameters();
            log.info("Job 执行结束. jobName={}, jobId={}, jobExecutionId={}, status={}, exitStatus={}, jobParameters={}",
                    jobName, jobExecution.getJobId(), jobExecutionId, batchStatus, exitStatus, jobParameters);

            jdbcTemplate.query("SELECT first_name, last_name FROM people",
                    (rs, row) -> new Person(
                            rs.getString(1),
                            rs.getString(2))
            ).forEach(person -> log.info("jobName:{},Found <" + person + "> in the database.", jobName));
        }
    }

}