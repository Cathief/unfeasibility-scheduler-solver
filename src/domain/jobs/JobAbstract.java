package domain.jobs;

import java.util.List;

/**
 * Clase de trabajo abstracta, cada uno de los trabajos deberá poseer dichos atributos.
 */
public abstract class JobAbstract implements JobInterface{

    //Cada trabajo estará compuesto por una serie de atributos:
    //Identificador que le aporta identidad y será su referencia:
    int identifier;
    //Tiempo de espera para que el trabajo esté disponible, también llamado head.
    int head;
    //Tiempo de espera para que el trabajo cuente como finalizado tras ejecutarse, llamado tail.
    int tail;
    //Atributo de peso de las tareas
    double weight = 1.0;
    //Lista de tiempos de procesamiento, habrá uno por cada máquina.
    List<Integer> processingTime;
    //Lista de costes de energía, habrá uno por cada máquina.
    List<Integer> energyConsumption;

    public JobAbstract(){

    }

    public JobAbstract(int id, int rt, List pt, List ec){
        setIdentifier(id);
        setHead(rt);
        setProcessingTime(pt);
        setEnergyConsumption(ec);
    }

    // -- SETTERS ---------

    public void setIdentifier(int identifier) {
        if (identifier < 0)
            throw new IllegalArgumentException("Identificador de trabajo menor que 0.");
        this.identifier = identifier;
    }

    public void setHead(int head) {
        if (head < 0)
            throw new IllegalArgumentException("La cabeza del trabajo no puede ser menor que 0");
        this.head = head;
    }

    public void setWeight(double w){
        this.weight = w;
    }
    public void setTail(int tail) {
        if (tail < 0)
            throw new IllegalArgumentException("La cola del trabajo no puede ser menor que 0");
        this.tail = tail;
    }

    public void setProcessingTime(List<Integer> processingTime) {
        for(Integer pt : processingTime)
            if (pt < 0)
                throw new IllegalArgumentException("El tiempo de procesamiento no puede ser negativo.");
        this.processingTime = processingTime;
    }

    public void setEnergyConsumption(List<Integer> energyConsumption) {
        for(Integer ec : energyConsumption)
            if (ec < 0)
                throw new IllegalArgumentException("El coste de energía no puede ser negativo.");
        this.energyConsumption = energyConsumption;
    }


    // -- GETTERS ---------

    public int getIdentifier() {
        return identifier;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public double getWeight(){return weight;}
    public List<Integer> getProcessingTime() {
        return processingTime;
    }

    public List<Integer> getEnergyConsumption() {
        return energyConsumption;
    }

    public Integer getProcessingTime(Integer machineId){
        return processingTime.get(machineId);
    }

    public Integer getEnergyConsumption(Integer machineId) {
        return energyConsumption.get(machineId);
    }

    public abstract int compareTo(JobPriorityRule o);
    // ----- LÓGICA ------
}
