package logic.schedules;

import domain.jobs.Job;
import domain.jobs.JobInterface;
import domain.machines.Machine;
import domain.machines.MachineInterface;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends ScheduleAbstract{

    // -- Lista de atributos propios --
    private List<PocketSpace> pS = new ArrayList<>();
    private int mSize = 0;
    public Schedule(){
        super();
    }

    public Schedule(MachineInterface m){
        super(m);
    }

    // -- GETTERS --

    public List<PocketSpace> getpS() {
        return pS;
    }

    public int getmSize() {
        return mSize;
    }
// -- SETTERS --

    public void setpS(List<PocketSpace> pS) {
        this.pS = pS;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }
// -- LÓGICA --

    @Override
    public int getScheduleTime(JobInterface jId) {

        //Si no hay más trabajos es trivial:
        if (getpS().isEmpty()) {
            //Si es mayor la cabeza que el makespan:
            if (jId.getHead() > getMakespan()){
                //Se crea un espacio:
                addSpace(0,jId.getHead());
                return jId.getHead();
            }
            //Si no es mayor devolvemos makespan:
            else
                return getMakespan();
        }

        //En caso contrario tendremos que mirar si entra en un espacio:
        else{
            //Si no hay espacio:
            if (getmSize()<jId.getProcessingTime().get(getMachineId()))
                //Lo ponemos al final:
                return getMakespan();
            else if (getmSize()>jId.getProcessingTime().get(getMachineId())){
                boolean found = false;
                int time;
                //Buscamos el espacio, aquí hay miga:
                for (PocketSpace ps: getpS()){
                    if (!found && (ps.size<jId.getProcessingTime().get(getMachineId()))){
                        time = ps.getStart();
                        return time + spaceMorph(ps,jId.getProcessingTime().get(getMachineId()));
                    }
                    else
                        return makespan;
                }
            }
        }
        return 0;
    }

    public void addSpace(int start, int size){
        getpS().add(new PocketSpace(start,size));
        if (size > mSize)
            setmSize(size);
    }

    public int spaceMorph(PocketSpace ps, int reduction){
        ps.setSize(ps.getSize()-reduction);
        if (ps.getSize() == 0){
            getpS().remove(ps);
        }
        return ps.getSize();
    }

    // -- CLASE AUXILIAR --
    public class PocketSpace{
        //Lista de atributos:
        protected int start;
        protected int size;

        public PocketSpace(int start, int size){
            setStart(start);
            setSize(size);
        }

        // -- GETTERS --

        public int getStart() {
            return start;
        }

        public int getSize() {
            return size;
        }

        // -- SETTERS --

        public void setStart(int start) {
            this.start = start;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

}
