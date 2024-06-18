package logic.schedules;

import dataStructures.Pair;
import domain.jobs.Job;
import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineAbstract;
import domain.machines.MachineInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class ScheduleAbstract implements ScheduleInterface{

    //Atributos del schedule
    //Lista de trabajos que componen la planificación:
    public List<Pair<JobInterface,Integer>> jobs = new ArrayList<>();
    //La máquina en la cual se está planificando la tarea:
    public MachineInterface machine;
    //El makespan de la planificación:
    public int makespan = 0;

    // -- CONSTRUCTORES --
    public ScheduleAbstract(){

    }

    public ScheduleAbstract(MachineInterface m){
        setMachine(m);
    }

    // -- SETTERS ---
    public void setMachine(MachineInterface machine) {
        this.machine = machine;
    }

    public void setMakespan(int makespan) {
        if (makespan < 0)
            throw new IllegalArgumentException("El makespan no puede ser negativo");
        this.makespan = makespan;
    }

    // -- GETTERS ---

    public List<Pair<JobInterface,Integer>> getJobs() {
        return jobs;
    }

    public JobInterface getJob(int jId){
        return jobs.get(jId).getKey();
    }

    public Pair<JobInterface,Integer> getScheduledJob(int jId){
        return jobs.get(jId);
    }

    public MachineInterface getMachine() {
        return machine;
    }

    public int getMakespan() {
        return makespan;
    }

    public int getMachineId(){
        return machine.getIdentifier();
    }

    // -- LÓGICA ---
    public int schedule(JobInterface jId, int time){
        //Primero añadimos el trabajo con la hora a planificar.
        this.jobs.add(new Pair<JobInterface,Integer>(jId,time));
        //Actualizamos el makespan en caso de que el tiempo del trabajo acabado sea mayor al makspam guardado:
        //Calculamos cuando acabará de ejecutarse el trabajo:
        int finishTime= time + jId.getProcessingTime(getMachineId());
        if (getMakespan() < finishTime)
            setMakespan(finishTime);
        return finishTime;
    }

    public int obtainActiveEnergy(){
        int activeEnergy = 0;
        for (Pair<JobInterface,Integer> p: getJobs())
            activeEnergy += p.getKey().getProcessingTime(getMachineId()) *
                            p.getKey().getEnergyConsumption(getMachineId());
        return activeEnergy;
    }

    public int obtainPassiveEnergy(){
        return getMakespan() * getMachine().getEnergyConsumption();
    }

    public int obtainTotalEnergy(){
        return obtainActiveEnergy() + obtainPassiveEnergy();
    }

    public boolean containsJobInterface(JobInterface ji) {
        for (Pair<JobInterface, Integer> pair : jobs) {
            if (pair.getKey() == ji) { // Comparación por referencia
                return true;
            }
        }
        return false;
    }

    @Override
    public abstract int getScheduleTime(JobInterface jId);
}
