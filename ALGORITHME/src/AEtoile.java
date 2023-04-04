import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class AEtoile extends dfsQueen{
    long nbrNExploiter = 0, nbrNGenerer =0;
    public AEtoile() {}
    void AppliquerA_2(Problem problem){
        int row,i;
        this.nbrNExploiter = 0;
        this.nbrNGenerer = 0;
        LinkedList<noeudEtoile> ListsOverts = new LinkedList<>();
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud;
        List<Integer> tempList;
        for (i=0;i<problem.cases.length;i++) {
            addNeudToList_2(ListsOverts,new noeudEtoile(problem.cases.length,0,i,Heurstique_2(problem.cases, 0,i)+problem.cases.length-1));
            nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            nbrNExploiter++;
            noeudCurrent = ListsOverts.removeFirst();
            for(int x: noeudCurrent.cases) System.out.print(x + " ");
            System.out.println( " Row =" + noeudCurrent.row +" Poid=" + noeudCurrent.poid);
            if(noeudCurrent.row == problem.cases.length-1){
                        System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, problem.cases.length);
                        return;
            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent.cases);
                for (Integer integer : tempList) {
                    tempNeud = new noeudEtoile(noeudCurrent);
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[noeudCurrent.row + 1] = integer;
                    tempNeud.poid = Heurstique_2(noeudCurrent.cases, tempNeud.row,integer) + (problem.cases.length-tempNeud.row-1) ;
                    addNeudToList_2(ListsOverts,new noeudEtoile(tempNeud));
                    nbrNGenerer++;
                }
            }
        }

    }
    void AppliquerA(Problem problem){
        int row,i;
        this.nbrNExploiter = 0;
        this.nbrNGenerer = 0;
        int [][] h = Heursitique_1(problem.cases.length);
        LinkedList<noeudEtoile> ListsOverts = new LinkedList<>();
        noeudEtoile noeudCurrent;
        noeudEtoile tempNeud;
        List<Integer> tempList;
        for (i=0;i<problem.cases.length;i++) {
            addNeudToList_2(ListsOverts,new noeudEtoile(problem.cases.length,0,i,problem.cases.length + h[0][i]));
            nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            nbrNExploiter++;
            noeudCurrent = ListsOverts.removeFirst();
            if(noeudCurrent.row == problem.cases.length-1){
                    System.arraycopy(noeudCurrent.cases, 0, problem.cases, 0, problem.cases.length);
                    return;

            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent.cases);
                for (Integer integer : tempList) {
                    tempNeud = new noeudEtoile(noeudCurrent);
                    //for(i=0; i<row;i++){
                    //    tempNeud.cases[i] = noeudCurrent.cases[i];
                    //}
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[noeudCurrent.row + 1] = integer;
                    tempNeud.poid = h[row][integer] + (problem.cases.length-tempNeud.row);
                    addNeudToList_2(ListsOverts,new noeudEtoile(tempNeud));
                    nbrNGenerer++;
                }
            }
        }

    }
    void addNeudToList_2(LinkedList<noeudEtoile> ListsOverts,noeudEtoile n){
        int i = 0;
        while ( i < ListsOverts.size() && ListsOverts.get(i).poid < n.poid ) i++;
        ListsOverts.add(i, n);
    }
     int Heurstique_2(int[] inst,int row,int column){
        int diag;
        boolean isSafe;
        int count =0;
        int[] n = Arrays.copyOf(inst,inst.length);
        n[row] = column;
        for(int row_i=row+1;row_i < n.length; row_i++) {
            for (int j = 0; j < n.length; j++) {
                isSafe = true;
                for (int i = 0; i <= row && isSafe; i++) {
                    diag = (row_i - i) + j; // digonalSuppAdd
                    if (diag < n.length && diag >= 0) {
                        if (n[i] == diag) {
                            isSafe = false;
                            continue;
                        }
                    }
                    diag = j - (row_i - i); // digonalSuppAgauche
                    if (diag < n.length && diag >= 0) {
                        if (n[i] == diag) {
                            isSafe = false;
                            continue;
                        }
                    }
                    // VerifierColumn
                    if (n[i] == j) isSafe = false;
                }
                if (isSafe) count++;
            }
        }
        return count;
    }
     int[][] Heursitique_1(int n) {
        int[][] h = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                h[i][j] = Math.min((n-(i+1)),(n-(j+1))) + Math.min((n-(i+1)),(j));
            }
        }
        return h;
    }
}