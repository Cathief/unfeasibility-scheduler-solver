package logic.finders.jobfinders;

import domain.jobs.JobInterface;

import java.util.List;
import java.util.Random;

public class JobFinderRandom extends JobFinderAbstract{

    @Override
    public JobInterface obtainNextJob(List<JobInterface> jis) {
        Random R = new Random();
        return jis.get(R.nextInt(jis.size()));
    }
}
