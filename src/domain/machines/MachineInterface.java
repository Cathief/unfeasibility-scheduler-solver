package domain.machines;

public interface MachineInterface {

    public int getIdentifier();
    public int getEnergyConsumption();
    public void setIdentifier(int id);
    public void setEnergyConsumption(int ec);
}
