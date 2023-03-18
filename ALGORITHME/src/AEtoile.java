import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class AEtoile{
    int[] cases;
    long nbrSolution = 0;
    long nbrNExploiter = 0, nbrNGenerer =0;
    LinkedList<noeudEtoile> ListsOverts;

    public AEtoile(int n) {
        cases = new int[n];
        ListsOverts = new LinkedList<>();
    }
    void AppliquerA_2(int n,Problem problem){
        int row,i;
        this.nbrSolution = 0;
        this.nbrNExploiter = 0;
        this.nbrNGenerer = 0;
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud;
        List<Integer> tempList;
        for (i=0;i<n;i++) {
            addNeudToList_2(new noeudEtoile(n,0,i,Heursitique_1.Heurstique_2(problem.cases, 0,i)+n-1));
            nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            nbrNExploiter++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                    if (n >= 0) System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                        return;
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
                    tempNeud.poid = Heursitique_1.Heurstique_2(noeudCurrent.cases, tempNeud.row,integer) + (n-tempNeud.row-1) ;
                    addNeudToList_2(new noeudEtoile(tempNeud));
                    nbrNGenerer++;
                }
            }
        }

    }
    void AppliquerA(int n, int[][] h,Problem problem){
        int row,i;
        this.nbrSolution = 0;
        this.nbrNExploiter = 0;
        this.nbrNGenerer = 0;
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud;
        List<Integer> tempList;
        for (i=0;i<n;i++) {
            addNeudToList_2(new noeudEtoile(n,0,i,n + h[0][i]));
            nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            nbrNExploiter++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == n-1){
                nbrSolution++;
                if(nbrSolution == 1) {
                    if (n >= 0) System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, n);
                    return;
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
                    tempNeud.poid = h[row][integer] + (n-tempNeud.row);
                    addNeudToList_2(new noeudEtoile(tempNeud));
                    nbrNGenerer++;
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
        /*
        int i = 0;
        while(i<this.ListsOverts.size() && this.ListsOverts.get(i).poid < n.poid) i++;
        this.ListsOverts.add(i,n);
        */
            if (this.ListsOverts.size() == 0) {
                this.ListsOverts.add(n);
            } else {
                for (int i = 0; i < this.ListsOverts.size(); i++) {
                    if (this.ListsOverts.get(i).poid > n.poid) {
                        this.ListsOverts.add(i, n);
                        return;
                    } else {
                        if (this.ListsOverts.get(i).poid == n.poid) {
                            if (this.ListsOverts.get(i).row < n.row) {
                                this.ListsOverts.add(i, n);
                                return;
                            }
                        }
                    }
                }
                this.ListsOverts.add(n);
            }

    }

    void addNeudToList_2(noeudEtoile n){
        int i = 0;
        while ( i < this.ListsOverts.size() && this.ListsOverts.get(i).poid < n.poid ) i++;
        this.ListsOverts.add(i, n);
    }
}