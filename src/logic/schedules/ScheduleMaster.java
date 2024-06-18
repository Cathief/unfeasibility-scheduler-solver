package logic.schedules;

import domain.jobs.JobInterface;
import domain.machines.MachineInterface;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMaster {
    //Este planificador solo contiene cada una de las planificaciones:
    List<ScheduleInterface> schedules = new ArrayList<>();

    //La última planificación en terminar, que delimita cuando se acaban las planificaciones:
    ScheduleInterface lastSchedule;

    public ScheduleMaster(){

    }

    // -- GETTERS ---

    public List<ScheduleInterface> getSchedules() {
        return schedules;
    }

    public ScheduleInterface getSchedule(int mId){
        return schedules.get(mId);
    }

    public ScheduleInterface getLastSchedule() {
        return lastSchedule;
    }

    // -- SETTERS ---

    public void setSchedules(List<ScheduleInterface> schedules) {
        this.schedules = schedules;
    }

    public void setLastSchedule(ScheduleInterface lastSchedule) {
        this.lastSchedule = lastSchedule;
    }

    // -- LÓGICA ---
    public void addSchedule(ScheduleInterface sch){
        lastSchedule = sch;
        this.schedules.add(sch);
    }

    public void schedule(MachineInterface mi, JobInterface ji){
        //Obtenemos la planificación actual:
        ScheduleInterface s = getSchedule(mi.getIdentifier());
        if (s.schedule(ji,s.getScheduleTime(ji))> getLastSchedule().getMakespan())
            setLastSchedule(s);
    }

    public int obtainPassiveEnergy(){
        int pEnergy = 0;
        int makespan = getLastSchedule().getMakespan();
        for (ScheduleInterface s: getSchedules())
            pEnergy += s.getMachine().getEnergyConsumption() * makespan;
        return pEnergy;
    }

    public int obtainActiveEnergy(){
        int aEnergy = 0;
        for (ScheduleInterface s: getSchedules())
            aEnergy += s.obtainActiveEnergy();
        return aEnergy;
    }
    /**
     * Las máquinas se paran en el momento.
     * @return
     */
    public int obtainTotalEnergy(){
        int tEnergy = 0;
        for (ScheduleInterface s: getSchedules())
            tEnergy += s.obtainTotalEnergy();
        return tEnergy;
    }
}
