import java.util.ArrayList;
import java.util.Collections;

public class solution {
    int[] cases;
    int fitness;
     solution(int n){
        this.cases = new int[n];
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
        for(int row = 0;row<this.cases.length;row++){
            for(int i=0;i<row;i++){
                diag = (row-i)+this.cases[row]; //Verfier Diagonale Superieur a droite
                if(diag == this.cases[i]){
                    malePositioner++;
                    continue;}
                diag = this.cases[row] - (row-i);//Verfier Diagonale Superieur a gauche
                if(diag == this.cases[i]){
                    malePositioner++;
                    continue;
                }
                if(this.cases[i] == this.cases[row]){
                    malePositioner++;
                }//Verfier Diagonale Superieur a gauche
            }
        }
        this.fitness =  malePositioner;
    }
    int getFitness(){
         return this.fitness;
    }
}
