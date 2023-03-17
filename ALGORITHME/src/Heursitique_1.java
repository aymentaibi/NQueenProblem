import java.util.Arrays;

public class Heursitique_1{
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
}


