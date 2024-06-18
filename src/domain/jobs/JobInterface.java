package domain.jobs;

import java.util.List;

public interface JobInterface {

    //Lista de métodos que debe añadir la interfaz:
    int getIdentifier();
    int getHead();
    int getTail();
    double getWeight();
    List<Integer> getProcessingTime();
    Integer getProcessingTime(Integer mId);
    List<Integer> getEnergyConsumption();
    Integer getEnergyConsumption(Integer mId);
}
