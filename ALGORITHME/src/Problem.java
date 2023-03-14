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
        Instant start = Instant.now();
        dfsQueen.DFS(0,this,this.cases.length);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken DFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +dfsQueen.nbrSolution);
        System.out.println("Le nombre de Noeud Generer =" +dfsQueen.nbrN);
        System.out.println("Le nombre de Noeud Generer avant La SOlution =" +dfsQueen.nbrNF);
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
        System.out.println("Le nombre de Noeud Generer =" +bfsQueen.nbrG);
        System.out.println("Le nombre de Noeud Generer avant La SOlution =" +bfsQueen.NBo);
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
        System.out.println("Le nombre des Neud generer =" +AEtoile.nbrG);
        System.out.println("Le nombre des Neud generer Avant La premier Solution =" +AEtoile.NBo);
        for(int i=0 ; i<this.cases.length;i++){
            System.out.println("La ligne:" + i + " La Column:" + this.cases[i]);
        }
    }
}
class dfsQueen{
    List<Integer> tempList;
    int[] cases;
    int nbrSolution = 0;
    int nbrN = 0,nbrNF =0;
    public dfsQueen(int n) {
        cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
    }
    void DFS(int row,Problem prblm,int n){
        nbrN++;
        if(row == n){
            nbrSolution++;
            if(nbrSolution == 1) {
                if (n >= 0) System.arraycopy(this.cases, 0, prblm.cases, 0, n);
                nbrNF = nbrN;
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
        boolean isSafe;
        int diag;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int j=0;j<this.cases.length;j++) {
            isSafe = true;
            for (int i = 0; i < row && isSafe; i++) {
                diag = (row-i)+j; // digonalSuppAdd
                if(diag<this.cases.length){
                    if(this.cases[i] == diag){
                        isSafe=false;
                        continue;
                    }
                }
                diag = j - (row-i); // digonalSuppAgauche
                if(diag>=0){
                    if(this.cases[i] == diag) {
                        isSafe = false;
                        continue;
                    }
                }
                // VerifierColumn
                if(this.cases[i] == j) isSafe = false;
            }
            if(isSafe) casePosiible.add(j);
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
    static int Heurstique_2(int[] inst,int row,int column){
        int diag;
        boolean isSafe;
        int count =0;
        for(int row_i=row+1;row_i < inst.length; row_i++) {
            for (int j = 0; j < inst.length; j++) {
                isSafe = true;
                for (int i = 0; i < row && isSafe; i++) {
                    diag = (row - i) + j; // digonalSuppAdd
                    if (diag < inst.length && diag >= 0) {
                        if (inst[i] == diag) {
                            isSafe = false;
                            continue;
                        }
                    }
                    diag = j - (row - i); // digonalSuppAgauche
                    if (diag < inst.length && diag >= 0) {
                        if (inst[i] == diag) {
                            isSafe = false;
                            continue;
                        }
                    }
                    // VerifierColumn
                    if (inst[i] == j) isSafe = false;
                }
                if (isSafe) count++;
            }
        }
        return count;
    }
}
class bfsQueen{
    int nbrSolution = 0;
    int[] cases;
    int nbrG = 0,NBo=0;
    public bfsQueen(int n) {
        cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
    }
    void BFS(Problem problem, int n){
        int row = 0,i;
        noeud noeudCurrent;
        noeud tempNeud = new noeud(n,row);
        LinkedList<noeud> ListsOverts = new LinkedList<noeud>();
        List<Integer> tempList;
        //First itération tous Les Coluns sont possible
        for (i=0;i<n;i++) {
            ListsOverts.add(new noeud(n,0,i,0));
        }
        while(ListsOverts.size() != 0){
            nbrG++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                     System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                    NBo = nbrG;
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


    List<Integer> casePossible(int row, noeud inst){
        boolean isSafe;
        int diag;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int j=0;j<inst.cases.length;j++) {
            isSafe = true;
            for (int i = 0; i < row && isSafe; i++) {
                diag = (row-i)+j; // digonalSuppAdd
                if(diag<inst.cases.length){
                    if(inst.cases[i] == diag){
                        isSafe=false;
                        continue;
                    }
                }
                diag = j - (row-i); // digonalSuppAgauche
                if(diag>=0){
                    if(inst.cases[i] == diag) {
                        isSafe = false;
                        continue;
                    }
                }
                // VerifierColumn
                if(inst.cases[i] == j) isSafe = false;
            }
            if(isSafe) casePosiible.add(j);
        }

        return casePosiible;
    }
}
class noeud{
    int[] cases;
    int row;
    public noeud(int n,int row) {
        this.cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
        this.row = row;
    }
    public noeud(noeud n){
        this.row = n.row;
        this.cases = new int[n.cases.length];
        System.arraycopy(n.cases, 0, this.cases, 0, n.cases.length);
    }
    public noeud(int n,int row,int colm,int row2) {

        this.cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
        this.cases[row] = colm;
        this.row = row2;
    }
}
class noeudEtoile{
    int[] cases;
    int row;
    int poid;
    public noeudEtoile(noeudEtoile n){
        this.row = n.row;
        this.cases = new int[n.cases.length];
        for(int i= 0;i<n.cases.length;i++){
            this.cases[i] = -1;
        }
        this.poid = n.poid;
        if (n.row + 1 >= 0) System.arraycopy(n.cases, 0, this.cases, 0, n.row + 1);
    }
    public noeudEtoile(int n,int row,int col, int poid){
        this.row = row;
        this.poid = poid+row;
        this.cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
        this.cases[row] = col;
    }
}
class AEtoile{
    int nbrSolution = 0;
    int[] cases;
    int nbrG = 0,NBo=0;
    LinkedList<noeudEtoile> ListsOverts;

    public AEtoile(int n) {
        cases = new int[n];
        ListsOverts = new LinkedList<>();
    }
    void AppliquerA(int n, int[][] h,Problem problem){
        int row = 0,i=0;
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud;
        List<Integer> tempList;
        for (i=0;i<n;i++) {
            addNeudToList(new noeudEtoile(n,0,i,h[0][i]));
        }
        while(ListsOverts.size() != 0){
            nbrG++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                    if (n >= 0) System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                    NBo = nbrG;

                }
            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent);
                for (Integer integer : tempList) {
                    tempNeud = new noeudEtoile(noeudCurrent);
                    //for(i=0; i<row;i++){
                    //    tempNeud.cases[i] = noeudCurrent.cases[i];
                    //}
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[noeudCurrent.row + 1] = integer;
                    //tempNeud.poid = h[row][integer] + (n-noeudCurrent.row - 1);
                    tempNeud.poid = Heursitique_1.Heurstique_2(noeudCurrent.cases, tempNeud.row,integer) + (noeudCurrent.row);
                    addNeudToList_2(new noeudEtoile(tempNeud));
                }
            }
        }

    }
    List<Integer> casePossible(int row, noeudEtoile inst){
        boolean isSafe;
        int diag;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int j=0;j<inst.cases.length;j++) {
            isSafe = true;
            for (int i = 0; i < row && isSafe; i++) {
                diag = (row-i)+j; // digonalSuppAdd
                if(diag<inst.cases.length){
                    if(inst.cases[i] == diag){
                        isSafe=false;
                        continue;
                    }
                }
                diag = j - (row-i); // digonalSuppAgauche
                if(diag>=0){
                    if(inst.cases[i] == diag) {
                        isSafe = false;
                        continue;
                    }
                }
                // VerifierColumn
                if(inst.cases[i] == j) isSafe = false;
            }
            if(isSafe) casePosiible.add(j);
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
    void addNeudToList_2(noeudEtoile n){
        if(this.ListsOverts.size() == 0){
            this.ListsOverts.add(n);
        }
        else{
            for(int i=0;i<this.ListsOverts.size();i++){
                if(this.ListsOverts.get(i).poid < n.poid){
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