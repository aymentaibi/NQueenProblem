import java.time.Duration;
import java.time.Instant;
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
        Instant start = Instant.now();
        bfsQueen.BFS(this,this.cases.length);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken BFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +bfsQueen.nbrSolution);
        for(int i=0 ; i<this.cases.length;i++){
            System.out.println("La ligne:" + i + " La Column:" + this.cases[i]);
        }
    }
    void SolveByHeurstique(){
        Heursitique_1 h = new Heursitique_1(this.cases.length);
        AEtoile AEtoile= new AEtoile(this.cases.length);
        System.out.println("************Start**********");
        Instant start = Instant.now();
        AEtoile.AppliquerA(this.cases.length,h.h,this);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("************END**********");
        System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +AEtoile.nbrSolution);
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
class Heursitique_1{
    int[][] h;
    public Heursitique_1(int n) {
        this.h = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                h[i][j] = Math.min((n-(i+1)),(n-(j+1))) + Math.min((n-(i+1)),(j));
            }
        }
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
                return;
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
class noeudEtoile{
    int[] cases;
    int row;
    int poid;
    public noeudEtoile(int n,int row,int poid) {
        this.cases = new int[n];
        this.row = row;
        this.poid = poid;
    }
    public noeudEtoile(noeudEtoile n){
        this.row = n.row;
        this.cases = new int[n.cases.length];
        this.poid = n.poid;
        for(int i=0;i<=n.row;i++){
            this.cases[i] = n.cases[i];
        }
    }
    public noeudEtoile(int n,int row,int col, int poid){
        this.row = row;
        this.poid = poid+row;
        this.cases = new int[n];
        this.cases[row] = col;
    }
}
class AEtoile{
        int nbrSolution = 0;
        int[] cases;
        LinkedList<noeudEtoile> ListsOverts;
    public AEtoile(int n) {
        cases = new int[n];
        ListsOverts = new LinkedList<>();
    }
    void AppliquerA(int n, int[][] h,Problem problem){
        int row = 0,i=0;
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud = new noeudEtoile(n,row,0);
        List<Integer> tempList;
        tempList = casePossible(row);
        for (Integer integer : tempList) {
            addNeudToList(new noeudEtoile(n,0,integer,h[0][integer]));
        }
        while(ListsOverts.size() != 0){
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                    if (n >= 0) System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                }
                return;
            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent);
                for (Integer integer : tempList) {
                    for(i=0; i<row;i++){
                        tempNeud.cases[i] = noeudCurrent.cases[i];
                    }
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[row] = integer;
                    tempNeud.poid = h[tempNeud.row][integer] + tempNeud.row;
                    addNeudToList(new noeudEtoile(tempNeud));
                }
            }
        }

    }
    List<Integer> casePossible(int row, noeudEtoile inst){
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
    void addNeudToList(noeudEtoile n){
        if(this.ListsOverts.size() == 0){
            this.ListsOverts.add(n);
        }
        else{
            for(int i=0;i<this.ListsOverts.size();i++){
                if(this.ListsOverts.get(i).poid > n.poid){
                    this.ListsOverts.add(i,n);
                    return;
                }
                else{
                    if(this.ListsOverts.get(i).poid == n.poid){
                        if(this.ListsOverts.get(i).row < n.row){
                            this.ListsOverts.add(i,n);
                            return;
                        }
                    }
                }
            }
            this.ListsOverts.add(n);
        }
    }
}