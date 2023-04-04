import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class bfsQueen extends dfsQueen{
    long nbrNExploiter = 0, nbrNGenerer =0;
    public bfsQueen() {
    }
    void BFS(Problem problem){
        int row = 0,i;
        this.nbrNGenerer = 0;
        this.nbrNExploiter = 0;
        noeud noeudCurrent;
        noeud tempNeud = new noeud(problem.cases.length,row);
        LinkedList<noeud> ListsOverts = new LinkedList<>();
        List<Integer> tempList;
        //First itération tous Les Coluns sont possible
        for (i=0;i<problem.cases.length;i++) {
            ListsOverts.add(new noeud(problem.cases.length,0,i,0));
            this.nbrNGenerer++;
        }
        while(ListsOverts.size() != 0){
            this.nbrNExploiter++;
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
}
