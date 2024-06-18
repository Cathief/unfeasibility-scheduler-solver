package Math;

import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineInterface;

import java.util.List;

public class Calculator {

    public static int findMax(List<Integer> numbers) {

        // Verifica si la lista está vacía
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("La lista no puede estar vacía");
        }

        // Inicializa el máximo con el primer valor de la lista
        int max = numbers.get(0);

        // Recorre la lista y encuentra el valor máximo
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }

        return max;
    }

    public static int findMaxConsumptionJob(JobInterface job) {

        // Vamos a dar fé que los trabajos están bien.

        // Inicializamos el valor primero con la máquina inicial:
        int maxEnergy = job.getEnergyConsumption().get(0) * job.getProcessingTime().get(0);

        // Recorre la lista y encuentra el valor máximo
        for (int i = 0; i<job.getProcessingTime().size(); i++) {
            int energy = job.getEnergyConsumption().get(i) * job.getProcessingTime().get(i);
            if (energy > maxEnergy) {
                maxEnergy = energy;
            }
        }

        return maxEnergy;
    }

    public static int findMaxMakespan(int energyAvailable, List<MachineInterface> lMfis){
        int consumoPasivoPorT = 0;
        for (MachineInterface mf: lMfis)
            consumoPasivoPorT += mf.getEnergyConsumption();
        return energyAvailable/consumoPasivoPorT;
    }
}
