import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem {
    int[] cases;
    public Problem(int n) {
        this.cases = new int[n];

    }
    boolean isValide(){
        int diag;
        for(int row = 0;row<this.cases.length;row++){
            for(int i=0;i<row;i++){
                diag = (row-i)+this.cases[row]; //Verfier Diagonale Superieur a droite
                if(diag == this.cases[i]) return false;
                diag = this.cases[row] - (row-i);//Verfier Diagonale Superieur a gauche
                if(diag == this.cases[i]) return false;
            }
        }
        return true;
    }
    void SolveByDFS(){
        System.out.println("Algo DFS sur n="+  this.cases.length);
        dfsQueen dfsQueen= new dfsQueen(this.cases.length);
        Instant start = Instant.now();
        dfsQueen.DFS(this);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken DFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +dfsQueen.nbrSolution);
        System.out.println("Le nombre de Noeud Exloité =" +dfsQueen.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +dfsQueen.nbrNGenerer);
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");
    }
    void SolveByBFS(){
        System.out.println("Algo BFS sur n="+  this.cases.length);
        bfsQueen bfsQueen= new bfsQueen(this.cases.length);
        Instant start = Instant.now();
        bfsQueen.BFS(this,this.cases.length);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken BFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +bfsQueen.nbrSolution);
        System.out.println("Le nombre de Noeud Exloité =" +bfsQueen.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +bfsQueen.nbrNGenerer);
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

    }
    void SolveByHeurstique_2(){
        System.out.println("Algo H2 sur n="+  this.cases.length);
        Heursitique_1 h = new Heursitique_1(this.cases.length);
        AEtoile AEtoile= new AEtoile(this.cases.length);
        Instant start = Instant.now();
        AEtoile.AppliquerA_2(this.cases.length,this);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +AEtoile.nbrSolution);
        System.out.println("Le nombre de Noeud Exloité =" +AEtoile.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +AEtoile.nbrNGenerer);
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

    }
    void SolveByHeurstique(){
        System.out.println("Algo H1 sur n="+  this.cases.length);
        Heursitique_1 h = new Heursitique_1(this.cases.length);
        AEtoile AEtoile= new AEtoile(this.cases.length);
        Instant start = Instant.now();
        AEtoile.AppliquerA(this.cases.length,h.h,this);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de solution =" +AEtoile.nbrSolution);
        System.out.println("Le nombre de Noeud Exloité =" +AEtoile.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +AEtoile.nbrNGenerer);
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");
    }
}
