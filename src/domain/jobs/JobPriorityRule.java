package domain.jobs;

import java.util.List;

public class JobPriorityRule extends JobAbstract{

    public JobPriorityRule(){

    }

    public JobPriorityRule(int id, int rt, List pt, List ec){
        super(id,rt,pt,ec);
    }

    // -- SETTERS Y GETTERS
    public List<Integer> getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(List energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    // ---- En la primera máquina... Podría ser el mínimo.
    public int getPriority(){
        return this.energyConsumption.get(0)/this.processingTime.get(0);
    }

    @Override
    public int compareTo(JobPriorityRule o) {
        return Integer.compare(this.getPriority(),o.getPriority());
    }

    @Override
    public String toString(){
        return "Priority Rule";
    }
}
