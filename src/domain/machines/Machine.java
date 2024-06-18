package domain.machines;

public class Machine extends MachineAbstract implements MachineInterface {

    public Machine(){
        super();
    }

    public Machine(int mId, int ec){
        super(mId, ec);
    }
}
