package domain.machines;

public abstract class  MachineAbstract implements MachineInterface{

    //Las máquinas están definidas por:
    //El identificador de la máquina
    int identifier;
    //El consumo de energía pasivo
    int energyConsumption;

    // -- CONSTRUCTORES --
    public MachineAbstract(){

    }

    public MachineAbstract(int id, int ec){
        this.identifier = id;
        this.energyConsumption = ec;
    }

    // -- GETTERS -----
    public int getIdentifier() {
        return identifier;
    }
    public int getEnergyConsumption() {
        return energyConsumption;
    }


    // -- SETTERS -----
    public void setIdentifier(int identifier) {
        if (identifier < 0)
            throw new IllegalArgumentException("El identificador de la máquina no puede ser menor que cero.");
        this.identifier = identifier;
    }
    public void setEnergyConsumption(int energyConsumption) {
        if (energyConsumption < 0)
            throw new IllegalArgumentException("El consumo pasivo de la máquina no puede ser menor que cero.");
        this.energyConsumption = energyConsumption;
    }


    // -- LÓGICA ------




}
