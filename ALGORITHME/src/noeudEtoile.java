public class noeudEtoile{
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

