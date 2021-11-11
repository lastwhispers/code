package cn.cunchang.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author cunchang
 * @date 2021/9/17 6:54 下午
 */
@Component
public class StepCompletionNotificationListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("BEFORE_STEP stepId={}, stepName={}, status={}, exitStatus={}, executionContext={}",
                stepExecution.getId(), stepExecution.getStepName(), stepExecution.getStatus(),
                stepExecution.getExitStatus(), stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();
        Long jobExecutionId = stepExecution.getJobExecutionId();
        String stepName = stepExecution.getStepName();
        Long stepExecutionId = stepExecution.getId();
        BatchStatus batchStatus = stepExecution.getStatus();
        ExitStatus exitStatus = stepExecution.getExitStatus();
        log.info("AFTER_STEP jobName={}, jobExecutionId={}, stepId={}, stepName={}, status={}, exitStatus={}, executionContext={}",
                jobName, jobExecutionId, stepExecutionId, stepName, batchStatus, exitStatus, stepExecution.getExecutionContext());

        return null;
    }

}
