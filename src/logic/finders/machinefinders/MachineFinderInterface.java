package logic.finders.machinefinders;

import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.List;

public interface MachineFinderInterface {

    public MachineInterface obtainMachineForJob(List<MachineInterface> lmis, JobInterface ji);
}
