package domain.jobs;

import java.util.List;

public class Job extends JobAbstract{

    public Job(){
        super();
    }

    public Job(int jId, int head, List lPt, List lEc){
        super(jId, head, lPt, lEc);
    }

    @Override
    public int compareTo(JobPriorityRule o) {
        return 0;
    }
}
