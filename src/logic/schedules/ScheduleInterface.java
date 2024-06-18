package logic.schedules;

import dataStructures.Pair;
import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.List;

public interface ScheduleInterface {

    // -- SETTERS ---

    // -- GETTERS ---

    public List<Pair<JobInterface,Integer>> getJobs();

    public JobInterface getJob(int jId);

    public Pair<JobInterface,Integer> getScheduledJob(int jId);

    public MachineInterface getMachine();

    public int getMakespan();

    public int getMachineId();
    public boolean containsJobInterface(JobInterface job);
    // -- LÓGICA ---
    public int schedule(JobInterface jId, int time);
    public int getScheduleTime(JobInterface jId);
    public int obtainActiveEnergy();
    public int obtainPassiveEnergy();
    public int obtainTotalEnergy();

}
