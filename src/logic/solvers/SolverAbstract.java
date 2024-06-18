package logic.solvers;

import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineInterface;
import logic.finders.jobfinders.JobFinderInterface;
import logic.finders.machinefinders.MachineFinderInterface;
import logic.schedules.Schedule;
import logic.schedules.ScheduleMaster;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class SolverAbstract implements SolverInterface{

    private List<JobInterface> totalJobs;
    //Elementos del problema
    private List<MachineInterface> machines;
    private List<JobInterface> nonScheduledJobs;
    private List<JobInterface> scheduledJobs;
    private int energyMax;

    //Elementos adicionales:
    private ScheduleMaster sMaster = new ScheduleMaster();
    private MachineFinderInterface mFinder;
    private JobFinderInterface jFinder;

    public SolverAbstract(){
    }

    public SolverAbstract(List<MachineInterface> m, List<JobInterface> j, int em,
                          MachineFinderInterface mI, JobFinderInterface jI){
        this.machines = m;
        this.totalJobs = new ArrayList<>(j);
        this.nonScheduledJobs = j;
        this.scheduledJobs = new ArrayList<>();
        this.energyMax = em;
        setmFinder(mI);
        setjFinder(jI);
        for (MachineInterface machine: m)
            sMaster.addSchedule(new Schedule(machine));
    }

    // -- SETTERS --

    public void setMachines(List<MachineInterface> machines) {
        this.machines = machines;
    }

    public void setNonScheduledJobs(List<JobInterface> nonScheduledJobs) {
        this.nonScheduledJobs = nonScheduledJobs;
    }

    public void setScheduledJobs(List<JobInterface> scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    public void setEnergyMax(int energyMax) {
        this.energyMax = energyMax;
    }

    public void setsMaster(ScheduleMaster sMaster) {
        this.sMaster = sMaster;
    }

    public void setmFinder(MachineFinderInterface mFinder) {
        this.mFinder = mFinder;
    }

    public void setjFinder(JobFinderInterface jFinder) {
        this.jFinder = jFinder;
    }

    // -- GETTERS --

    public List<MachineInterface> getMachines() {
        return machines;
    }

    public List<JobInterface> getNonScheduledJobs() {
        return nonScheduledJobs;
    }

    public List<JobInterface> getScheduledJobs() {
        return scheduledJobs;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public ScheduleMaster getsMaster() {
        return sMaster;
    }

    public List<JobInterface> getTotalJobs() {
        return totalJobs;
    }

    public MachineFinderInterface getmFinder() {
        return mFinder;
    }

    public JobFinderInterface getjFinder() {
        return jFinder;
    }

    // -- LÓGICA --
}
