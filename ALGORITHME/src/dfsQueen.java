import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class dfsQueen{
    long nbrNGenerer = 0,nbrNExploiter =0;
    public dfsQueen() {
    }
    void DFS(Problem p){
        int row = 0,i;
        int n = p.cases.length;
        noeud noeudCurrent;
        noeud tempNeud = new noeud(n,row);
        LinkedList<noeud> ListsOverts = new LinkedList<noeud>();
        List<Integer> tempList; // Pour Les Cases Possible
        //First itération tous Les Coluns sont possible
        for (i=0;i<n;i++) {
            nbrNGenerer++;
            ListsOverts.add(new noeud(n,0,i,0));
        }
        while(ListsOverts.size() != 0){
            nbrNExploiter++;
            noeudCurrent = ListsOverts.removeLast();
            if(noeudCurrent.row == n-1){

                    System.arraycopy(noeudCurrent.cases, 0, p.cases, 0, n);
                    return;

            }
            else {
                //NeudCurrent
                row = noeudCurrent.row + 1;
                tempList = casePossible(row, noeudCurrent.cases);
                for (Integer integer : tempList) {
                    for(i=0; i<row;i++){
                        tempNeud.cases[i] = noeudCurrent.cases[i];
                    }
                    tempNeud.row = noeudCurrent.row + 1;
                    tempNeud.cases[row] = integer;
                    ListsOverts.add(new noeud(tempNeud));
                    nbrNGenerer++;
                }
            }
        }
    }
    List<Integer> casePossible(int row,int[] noeud_temp){
        boolean isSafe;
        int diag;
        List<Integer> casePosiible = new ArrayList<Integer>();
        for(int j=0;j<noeud_temp.length;j++) {
            isSafe = true;
            for (int i = 0; i < row && isSafe; i++) {
                diag = (row-i)+j; // digonalSuppAdd
                if(diag<noeud_temp.length){
                    if(noeud_temp[i] == diag){
                        isSafe=false;
                        continue;
                    }
                }
                diag = j - (row-i); // digonalSuppAgauche
                if(diag>=0){
                    if(noeud_temp[i] == diag) {
                        isSafe = false;
                        continue;
                    }
                }
                // VerifierColumn
                if(noeud_temp[i] == j) isSafe = false;
            }
            if(isSafe) casePosiible.add(j);
        }

        return casePosiible;
    }
}
