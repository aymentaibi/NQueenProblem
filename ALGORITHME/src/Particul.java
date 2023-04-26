import java.util.ArrayList;
import java.util.List;

public class Particul extends solution{
    int[] pBest;
    int pBestF;
    double[] v;
    public Particul(int n) {
        super(n);
        this.setRandomSolution();
        this.pBest = new int[n];
        this.v = new double[n];
        System.arraycopy(this.cases, 0, pBest, 0, this.cases.length);
        pBestF = this.fitness;
        for (int i = 0; i < n; i++) {
            this.v[i] = Math.random()*2 -1;
        }
    }

    void updateV(double alpha, double c1, double c2,int[] Gbest){
        double r1 = Math.random();
        double r2 = Math.random();
        //alpha = Math.random();
        for (int i = 0; i < this.v.length; i++) {
            this.v[i] = alpha * this.v[i] + c1*r1*(this.pBest[i] - this.cases[i]) + c2*r2*(Gbest[i] * this.cases[i]);
        }
    }
    void update_Position_PBEST(){
        for (int i = 0; i < this.cases.length; i++) {
            this.cases[i] = (this.cases[i] + (int)this.v[i])% this.cases.length;
        }
        this.Fiteness();
        if(this.pBestF > this.fitness){
            this.pBestF = this.fitness;
            System.arraycopy(this.cases, 0, this.pBest, 0, this.cases.length);
        }
    }
    void fixCases(){
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < this.cases.length; i++) {
            integerList.add(i);
        }
        for (int i = 0; i <this.cases.length; i++) {
            if(integerList.contains(Integer.valueOf(this.cases[i]))){
                integerList.remove(Integer.valueOf(this.cases[i]));
            }
            this.cases[i] = -1;
        }
    }
}
