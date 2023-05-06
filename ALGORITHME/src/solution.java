import java.util.ArrayList;
import java.util.Collections;

public class solution {
    int[] cases;
    int fitness;
     solution(int n){
        this.cases = new int[n];
    }

    public solution(int[] n) {
        this.cases = new int[n.length];
        System.arraycopy(n, 0, this.cases, 0, n.length);
        this.Fiteness();
    }

    void setRandomSolution(){
        ArrayList<Integer> tab=  new ArrayList<Integer>();
        for (int i = 0; i < this.cases.length; i++) {
            tab.add(i);
        }
        Collections.shuffle(tab);
        for (int i = 0; i < this.cases.length; i++) {
            this.cases[i] = tab.get(i);
        }
        this.Fiteness();
     }
    void Fiteness(){
        int diag;
        int malePositioner = 0;
        boolean enAttqck;
        for(int row = 0;row<this.cases.length;row++){
            enAttqck =  false;
            for(int i=0;i<row && !enAttqck;i++){
                diag = (row-i)+this.cases[row]; //Verfier Diagonale Superieur a droite
                if(diag == this.cases[i]){
                    malePositioner++;
                    enAttqck = true;
                    break;}
                diag = this.cases[row] - (row-i);//Verfier Diagonale Superieur a gauche
                if(diag == this.cases[i]){
                    malePositioner++;
                    enAttqck = true;
                    break;
                }
                if(this.cases[i] == this.cases[row]){
                    enAttqck = true;
                    malePositioner++;
                    break;
                }//Verfier Diagonale Superieur a gauche
            }
        }
        this.fitness =  malePositioner;
    }
    int getFitness(){
         return this.fitness;
    }
}
