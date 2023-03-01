import java.util.ArrayList;
import java.util.LinkedList;
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
    void SolveByBFS(){
        bfsQueen bfsQueen= new bfsQueen(this.cases.length);
        bfsQueen.BFS(this,this.cases.length);
        System.out.println("Le nombre de solution =" +bfsQueen.nbrSolution);
        for(int i=0 ; i<this.cases.length;i++){
            System.out.println("La ligne:" + i + " La Column:" + this.cases[i]);
        }
    }
}
class dfsQueen{
    List<Integer> tempList;
    int[] cases;
    int nbrSolution = 0;
    public dfsQueen(int n) {
        cases = new int[n];
    }
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
class bfsQueen{
    int nbrSolution = 0;
    int[] cases;
    public bfsQueen(int n) {
        cases = new int[n];
    }
    void BFS(Problem problem, int n){
        int row = 0,i=0;
        noeud noeudCurrent;
        noeud tempNeud = new noeud(n,row);
        LinkedList<noeud> ListsOverts = new LinkedList<noeud>();
        List<Integer> tempList;
        tempList = casePossible(row);
        for (Integer integer : tempList) {
            ListsOverts.add(new noeud(n,0,integer,0));
        }
        while(ListsOverts.size() != 0){
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                    if (n >= 0) System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                }
            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent);
                for (Integer integer : tempList) {
                    i=0;
                    for(i=0; i<row;i++){
                        tempNeud.cases[i] = noeudCurrent.cases[i];
                    }
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[row] = integer;
                    ListsOverts.add(new noeud(tempNeud));
                }
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
    List<Integer> casePossible(int row, noeud inst){
        boolean isSafe = true;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int col =0;col<inst.cases.length; col++){
            isSafe = true;
            // Vérifier la diagonale sup adr
            for (int i = row -1 , j = col+1; i >= 0 && j < inst.cases.length && isSafe; i--, j++) {
                if (inst.cases[i] == j) {
                    isSafe = false;
                }
            }
            // Vérifier la diagonale sup fauch

            for (int i = row -1 , j = col-1; i >= 0 && j >= 0 && isSafe; i--, j--) {
                if (inst.cases[i] == j) {
                    isSafe = false;
                }
            }
            // Vérifier la column

            for(int i = 0;i<row && isSafe;i++){
                if(inst.cases[i] == col) isSafe = false;
            }
            //put in the liste
            if(isSafe) casePosiible.add(col);
        }
        return casePosiible;
    }
}
class noeud{
    int[] cases;
    int row;
    public noeud(int n,int row) {
        this.cases = new int[n];
        this.row = row;
    }
    public noeud(noeud n){
        this.row = n.row;
        this.cases = new int[n.cases.length];
        System.arraycopy(n.cases, 0, this.cases, 0, n.cases.length);
    }
    public noeud(int n,int row,int colm,int row2) {
        this.cases = new int[n];
        this.cases[row] = colm;
        this.row = row2;
    }
}