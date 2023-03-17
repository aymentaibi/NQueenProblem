import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class bfsQueen{

    int[] cases;
    int nbrSolution = 0;
    int nbrNExploiter = 0, nbrNGenerer =0;
    public bfsQueen(int n) {
        cases = new int[n];
        for(int i= 0;i<n;i++){
            this.cases[i] = -1;
        }
    }
    void BFS(Problem problem, int n){
        int row = 0,i;
        this.nbrNGenerer = 0;
        this.nbrNExploiter = 0;
        this.nbrSolution = 0;
        noeud noeudCurrent;
        noeud tempNeud = new noeud(n,row);
        LinkedList<noeud> ListsOverts = new LinkedList<>();
        List<Integer> tempList;
        //First itération tous Les Coluns sont possible
        for (i=0;i<n;i++) {
            ListsOverts.add(new noeud(n,0,i,0));
            this.nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            this.nbrNExploiter++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                this.nbrSolution++;
                if(nbrSolution == 1) {
                    System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                    return;
                }
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
                    ListsOverts.add(new noeud(tempNeud));
                    this.nbrNGenerer++;
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
