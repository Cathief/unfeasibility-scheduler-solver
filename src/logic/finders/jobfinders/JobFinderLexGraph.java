package logic.finders.jobfinders;

import domain.jobs.JobInterface;

import java.util.List;

public class JobFinderLexGraph extends JobFinderAbstract{

    @Override
    public JobInterface obtainNextJob(List<JobInterface> jis) {
        return jis.get(0);
    }
}
