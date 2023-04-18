public class noeud{
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

