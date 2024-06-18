package logic.finders.machinefinders;

import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.List;
import java.util.Random;

public class MachineFinderRandom extends MachineFinderAbstract{
    @Override
    public MachineInterface obtainMachineForJob(List<MachineInterface> lmis, JobInterface ji) {
        Random R = new Random();
        return lmis.get(R.nextInt(lmis.size()));
    }

    @Override
    public String toString(){
        return "Random selection";
    }
}
