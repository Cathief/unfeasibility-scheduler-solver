package logic.finders.machinefinders;

import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.List;

public class MachineFinderRoundRobin extends MachineFinderAbstract{
    int sIndex = 0;


    public MachineFinderRoundRobin(){

    }

    @Override
    public MachineInterface obtainMachineForJob(List<MachineInterface> lmis, JobInterface ji) {
        int index = sIndex;
        sIndex = sIndex + 1;
        if (sIndex>=lmis.size())
            sIndex = 0;
        return lmis.get(index);
    }

    @Override
    public String toString(){
        return "Round Robin selection";
    }
}
