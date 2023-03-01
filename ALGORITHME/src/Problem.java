import java.util.ArrayList;
import java.util.List;

public class Problem {
    int[] cases;
    public Problem(int n) {
        this.cases = new int[n];
    }

    boolean isValide(){
        for(int row=0;row<this.cases.length;row++){
            int col = this.cases[row];
            // Vérifier la diagonale superieur a droite
            for (int i = row-1, j = col+1; i >= 0 && j < this.cases.length; i--, j++) {
                if (this.cases[i] == j) {
                    return false;
                }
            }

            // Vérifier la diagonale superieur gauche
            for (int i = row+1, j = col+1; i < this.cases.length && j < this.cases.length; i++, j++) {
                if (this.cases[i] == j) {
                    return false;
                }
            }
            // Vérifier la diagonale inférieure a droite
            for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
                if (this.cases[i] == j) {
                    return false;
                }
            }
            // Vérifier la diagonale inférieure gauche
            for (int i = row+1, j = col-1; i < this.cases.length && j >= 0; i++, j--) {
                if (this.cases[i] == j) {
                    return false;
                }
            }
            //Vérifier Column
            for(int i = row+1;i<this.cases.length;i++){
                if(this.cases[i] == col) return false;
            }
        }
        return true;
    }


    void SolveByDFS(){
        dfsQueen dfsQueen= new dfsQueen(this.cases.length);
        dfsQueen.DFS(0,this,this.cases.length);
        System.out.println("Le nombre de solution =" +dfsQueen.nbrSolution);
        for(int i=0 ; i<this.cases.length;i++){
            System.out.println("La ligne:" + i + " La Column:" + this.cases[i]);
        }
    }
}
class dfsQueen{
    List<Integer> tempList;
    int[] cases;
    public dfsQueen(int n) {
        cases = new int[n];
    }

    int nbrSolution = 0;
    void DFS(int row,Problem prblm,int n){
        if(row == n){
            nbrSolution++;
            if(nbrSolution == 1) {
                if (n >= 0) System.arraycopy(this.cases, 0, prblm.cases, 0, n);
            }
        }
        else{
            tempList = this.casePossible(row);
            for (Integer integer : tempList) {
                this.cases[row] = integer;
                DFS(row + 1, prblm, n);
            }

        }
    }
    List<Integer> casePossible(int row){
        boolean isSafe = true;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int col =0;col<this.cases.length; col++){
            isSafe = true;
            // Vérifier la diagonale sup adr
            for (int i = row -1 , j = col+1; i >= 0 && j < this.cases.length && isSafe; i--, j++) {
                if (this.cases[i] == j) {
                    isSafe = false;
                }
            }
            // Vérifier la diagonale sup fauch

            for (int i = row -1 , j = col-1; i >= 0 && j >= 0 && isSafe; i--, j--) {
                if (this.cases[i] == j) {
                    isSafe = false;
                }
            }
            // Vérifier la column

            for(int i = 0;i<row && isSafe;i++){
                if(this.cases[i] == col) isSafe = false;
            }
            //put in the liste
            if(isSafe) casePosiible.add(col);
        }
        return casePosiible;
    }
}