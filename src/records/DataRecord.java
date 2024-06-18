package records;

import dataStructures.Pair;
import domain.jobs.Job;
import domain.jobs.JobInterface;
import logic.schedules.ScheduleInterface;
import logic.solvers.LinearSearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataRecord {

    String outputPath;
    String instancePath;
    long time;
    String id;
    LinearSearch lSearch;

    public DataRecord(long time, LinearSearch ls, String ip, String op, String id){
        this.time = time;
        this.lSearch = ls;
        this.instancePath = ip;
        this.outputPath = op;
        this.id = id;
        String fileoutputhname = outputPath + "data_"+id+".csv";
        writeCSV(fileoutputhname);
        String fileoutputhch = outputPath + "ch_"+id+".txt";
        writeChromosome(fileoutputhch);
        writeSolution(outputPath + "sol_"+id+".txt");
    }

    private void writeChromosome(String fileoutputhch) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileoutputhch))) {

            writer.write("[");

            int elementos = this.lSearch.getTotalJobs().size();
            for (JobInterface ji : this.lSearch.getTotalJobs()) {
                elementos--;
                boolean found = false;
                //Miramos a ver si está planificado:
                for (ScheduleInterface si : this.lSearch.getsMaster().getSchedules()) {
                    //Buscamos si está en la máquina
                    if (si.containsJobInterface(ji)) {
                        writer.write(String.valueOf(si.getMachineId()));
                        found = true;
                    }
                }
                if (!found)
                    writer.write("0");
                if (elementos>0)
                    writer.write(",");
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSolution(String fileoutputhch) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileoutputhch))) {

            writer.write("[");

            int elementos = this.lSearch.getTotalJobs().size();
            for (JobInterface ji : this.lSearch.getTotalJobs()) {
                elementos--;
                boolean found = false;
                //Miramos a ver si está planificado:
                for (ScheduleInterface si : this.lSearch.getsMaster().getSchedules()) {
                    //Buscamos si está en la máquina
                    if (si.containsJobInterface(ji)) {
                        for(Pair<JobInterface,Integer> p: si.getJobs())
                            if(p.getKey() == ji)
                                writer.write(String.valueOf(p.getValue()));
                        found = true;
                    }
                }
                if (!found)
                    writer.write("0");
                if (elementos>0)
                    writer.write(",");
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", "Problem Instance",instancePath));
            writer.newLine();
            writer.write(String.join(",","Machines size",
                    String.valueOf(this.lSearch.getMachines().size()), "Jobs size",
                    String.valueOf(this.lSearch.getScheduledJobs().size()+this.lSearch.getNonScheduledJobs().size())));
            writer.newLine();
            writer.write(String.join(",","Selected jobs",
                    String.valueOf(this.lSearch.getScheduledJobs().size())));
            writer.newLine();
            writer.write(String.join(",","Max energy",
                    String.valueOf(this.lSearch.getEnergyMax())));
            writer.newLine();
            writer.write(String.join(",","Non selected jobs",
                    String.valueOf(this.lSearch.getNonScheduledJobs().size())));
            writer.newLine();
            writer.write(String.join(",","Passive Energy",
                    String.valueOf(this.lSearch.getsMaster().obtainPassiveEnergy())));
            writer.newLine();
            writer.write(String.join(",","Active Energy",
                    String.valueOf(this.lSearch.getsMaster().obtainActiveEnergy())));
            writer.newLine();
            writer.write(String.join(",","Makespan",
                    String.valueOf((this.lSearch.getsMaster().getLastSchedule().getMakespan()))));
            writer.newLine();
            writer.write(String.join(",","Completion time",String.valueOf(this.time)));
            writer.newLine();
            writer.write(String.join(",","Machine Heuristic",
                    this.lSearch.getmFinder().toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}