package logic.solvers;

import domain.jobs.Job;
import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineInterface;
import logic.finders.jobfinders.JobFinderInterface;
import logic.finders.machinefinders.MachineFinderInterface;
import logic.schedules.Schedule;
import logic.schedules.ScheduleInterface;
import logic.schedules.ScheduleMaster;

import Math.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinearSearch extends SolverAbstract {

   public LinearSearch(){

   }

   public LinearSearch(List<MachineInterface> m, List<JobInterface> j, int em,
                       MachineFinderInterface mI, JobFinderInterface jI){
        super(m,j,em,mI, jI);
    }

    /**
     * Método que resuelve en función de las lexicográficas
     */
    public void solveLinearSearch(){

        //Máquinas donde vamos a ejecutar la tarea:
        JobInterface scheduledJob;
        MachineInterface scheduledMachine;

        //Mientras tengamos energía:
        while (getsMaster().obtainTotalEnergy() < getEnergyMax()
                && !getNonScheduledJobs().isEmpty() ){

            //Obtenemos el trabajo:
            scheduledJob = getjFinder().obtainNextJob(getNonScheduledJobs());

            //Cuando exploramos el trabajo lo eliminamos:
            getNonScheduledJobs().remove(scheduledJob);

            int maxJobEnergyConsumption = Calculator.findMaxConsumptionJob(scheduledJob);
            int maxMakespan = getsMaster().getLastSchedule().getMakespan();

            //Miramos a ver primero si hay energía disponible:
            if (getsMaster().obtainTotalEnergy() + maxJobEnergyConsumption <= getEnergyMax()){
                //Luego miramos el makespan:
                maxMakespan +=
                        Calculator.findMaxMakespan(getsMaster().obtainTotalEnergy()
                                + maxJobEnergyConsumption,getMachines());

                if (getsMaster().getLastSchedule().getMakespan()
                        +scheduledJob.getProcessingTime().get(getsMaster().getLastSchedule().getMachineId())
                        < maxMakespan){

                    scheduledMachine = getmFinder().obtainMachineForJob(getMachines(),scheduledJob);

                    //Planificamos el trabajo:
                    schedule(scheduledMachine, scheduledJob);
                }
            }

        }
        System.out.println("Fin del método linear search");
    }

    /**
     * Schedule a job in a machine
     * @param m machine where the job will be scheduled
     * @param j job to be scheduled
     */
    public void schedule(MachineInterface m, JobInterface j){
        //Añadimos a la planificación
        getsMaster().schedule(m,j);

        //Actualizamos de las listas:
        this.getScheduledJobs().add(j);
        this.getNonScheduledJobs().remove(j);
    }


    /**
     * Visitamos los vecinos del trabajo
     * @param m máquina donde se encuentra el trabajo
     * @param j trabajo del que buscamos los vecinos
     * @return -1 en caso de que no haya ningún vecino mejor, id del trabajo en otro caso.
     */
    public int exploreNeighboursMachine(Machine m, Job j){
        int mId = m.getIdentifier();
        int eJ = -1;
        int eN = -1;
        for (JobInterface n: getNonScheduledJobs()){
             eJ = -1;
             eN = -1;
             if (n.getProcessingTime().get(mId)<j.getProcessingTime().get(mId)
                || n.getEnergyConsumption().get(mId)<j.getEnergyConsumption().get(mId)){
                    eJ = j.getProcessingTime().get(mId)*m.getEnergyConsumption() + j.getEnergyConsumption().get(mId);
                    eN = n.getProcessingTime().get(mId)*m.getEnergyConsumption() + n.getEnergyConsumption().get(mId);
                    if (eJ>eN)
                        return n.getIdentifier();
             }
        }
        return -1;
    }
}
