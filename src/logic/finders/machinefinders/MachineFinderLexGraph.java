package logic.finders.machinefinders;

import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.List;

public class MachineFinderLexGraph extends MachineFinderAbstract{
    @Override
    public MachineInterface obtainMachineForJob(List<MachineInterface> lmis, JobInterface ji) {
        //Buscamos la clase
        return lmis.get(0);
    }
}
