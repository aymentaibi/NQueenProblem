import java.util.ArrayList;

public class PSO {
    int GbestF;
    private int[] Gbest;

    ArrayList<Particul> genererPartiCuleRadom(int n,int taillePop){
        ArrayList<Particul> pop = new ArrayList<>();
        this.GbestF = n;
        for (int i = 0; i < taillePop; i++) {
            Particul new_Particul = new Particul(n);
            pop.add(new_Particul);
            if(this.GbestF > new_Particul.pBestF){
                this.GbestF = new_Particul.pBestF;
                if (n >= 0) System.arraycopy(new_Particul.cases, 0, this.Gbest, 0, n);
            }
        }
        return pop;
    }
    solution pso_algorithme(int n, int taillePop, double c1, double c2, double alpha, int nbrIteration){
        this.Gbest = new int[n];
        ArrayList<Particul> pop = genererPartiCuleRadom(n,taillePop);
        for (int i = 0; i < nbrIteration ; i++) {
            for (Particul particul : pop) {
                particul.updateV(alpha, c1, c2, this.Gbest);
                particul.update_Position_PBEST();
                if (GbestF > particul.pBestF) {
                    this.GbestF = particul.pBestF;
                    System.arraycopy(particul.cases, 0, this.Gbest, 0, n);
                }
            }
            if(i%100 == 0){
                System.out.print("iteration i:" + i +" Best Fiteness ");
                System.out.println(this.GbestF);
            }
        }

        return new solution(Gbest);
    }
}
