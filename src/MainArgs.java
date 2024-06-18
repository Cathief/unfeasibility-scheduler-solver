import domain.jobs.Job;
import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineInterface;
import logic.finders.jobfinders.JobFinderInterface;
import logic.finders.jobfinders.JobFinderRandom;
import logic.finders.machinefinders.MachineFinderInterface;
import logic.finders.machinefinders.MachineFinderRandom;
import logic.solvers.LinearSearch;
import parser.ParserMinizinc;
import records.DataRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainArgs {
    public static <Jobs> void main(String[] args)  {
        //Ubicación del archivo a leer
        //Primer argumento

        String filePath = "C:\\Users\\Xurde\\IdeaProjects\\TFM_Scheduling\\Instances";
        filePath = filePath + "\\" + args[0];

        // Lee el archivo y almacena su contenido en una variable
        try{
            int em = Integer.parseInt(args[1]);;

            //Instanciamos
            ParserMinizinc parser = ParserMinizinc.getInstance();
            parser.read(filePath);

            //Obtenemos el número de máquinas y jobs
            int nMachines = parser.parseMachineNumber();
            int nJobs = parser.parseJobNumber();

            //Generamos cada una de las máquinas
            ArrayList<MachineInterface> machines = new ArrayList<>();
            ArrayList<JobInterface> jobs = new ArrayList<>();

            //Obtenemos los valores de la máquina
            List<Integer> pe = parser.parsePassiveEnergy();

            //Obtenemos los valores relativo a los trabajos
            List<Integer> rt = parser.parseReadyTime();
            List<List<Integer>> pt = parser.parseProcessingTime();
            List<List<Integer>> ae = parser.parseActiveEnergy();

            //Ya con los datos leídos creamos los trabajos
            for (int i=0; i<nMachines; i++){
                machines.add(new Machine(i,pe.get(i)));
            }

            for (int i=0; i<nJobs; i++){
                jobs.add(new Job(i,rt.get(i),pt.get(i),ae.get(i)));
            }

            //Creamos los métodos de búsqueda que necesitamos:
            MachineFinderInterface mfi = new MachineFinderRandom();
            JobFinderInterface jfi = new JobFinderRandom();

            //Tras la lectura realizamos la busqueda
            LinearSearch s = new LinearSearch(machines,jobs,em,mfi,jfi);
            long time = System.currentTimeMillis();
            s.solveLinearSearch();
            time = System.currentTimeMillis() - time;

            //Creamos el record:
            DataRecord dr = new DataRecord(time,s,filePath,"Outputs//",args[0]);

            //Debug
            System.out.println("Fin de la ejecución.");

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}