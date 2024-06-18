package logic.finders.jobfinders;

import domain.jobs.JobInterface;

import java.util.List;

public interface JobFinderInterface {

    public JobInterface obtainNextJob(List<JobInterface> jis);
}
