import java.time.Duration;
import java.time.Instant;

public class Problem {
    public long nbrNGenerer;
    public long nbrNExploiter;
    public String timeElapsed;
    public int Fitness;
    int[] cases;
    public Problem(int n) {
        this.cases = new int[n];
    }
    int Fiteness(solution s){
        int diag;
        int malePositioner = 0;
        for(int row = 0;row<s.cases.length;row++){
            for(int i=0;i<row;i++){
                diag = (row-i)+s.cases[row]; //Verfier Diagonale Superieur a droite
                if(diag == s.cases[i]){
                    malePositioner++;
                    continue;}
                diag = s.cases[row] - (row-i);//Verfier Diagonale Superieur a gauche
                if(diag == s.cases[i]){
                    malePositioner++;
                    continue;
                }
                if(s.cases[i] == s.cases[row]){
                    malePositioner++;
                }//Verfier Diagonale Superieur a gauche
            }
        }
        return malePositioner;
    }
    void SolveByDFS(){
        System.out.println("Algo DFS sur n="+  this.cases.length);
        dfsQueen dfsQueen= new dfsQueen();
        Instant start = Instant.now();
        dfsQueen.DFS(this);
        Instant end = Instant.now();
        /*
        System.out.println("Time taken DFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de Noeud Exloité =" +dfsQueen.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +dfsQueen.nbrNGenerer);
         */

        this.nbrNGenerer = dfsQueen.nbrNGenerer;
        this.nbrNExploiter = dfsQueen.nbrNExploiter;
        this.timeElapsed = Long.toString(Duration.between(start, end).toMillis());;
        /*
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

         */
    }
    void SolveByBFS(){
        //System.out.println("Algo BFS sur n="+  this.cases.length);
        bfsQueen bfsQueen= new bfsQueen();
        Instant start = Instant.now();
        bfsQueen.BFS(this); //APPLIQUER L'agorithme BFS
        Instant end = Instant.now();
        /*
        System.out.println("Time taken BFS: "+ timeElapsed.toMillis() +" milliseconds");
        System.out.println("Le nombre de Noeud Exloité =" +bfsQueen.nbrNExploiter);
        System.out.println("Le nombre de Noeud Generer  =" +bfsQueen.nbrNGenerer);

         */
        this.nbrNGenerer = bfsQueen.nbrNGenerer;
        this.nbrNExploiter = bfsQueen.nbrNExploiter;
        this.timeElapsed = Long.toString(Duration.between(start, end).toMillis());;
    /*
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

     */

    }
    void SolveByHeurstique_2(){
        //System.out.println("Algo H2 sur n="+  this.cases.length);
        AEtoile AEtoile= new AEtoile();
        Instant start = Instant.now();
        AEtoile.AppliquerA_2(this);
        Instant end = Instant.now();
        //System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        //System.out.println("Le nombre de Noeud Exloité =" +AEtoile.nbrNExploiter);
        //System.out.println("Le nombre de Noeud Generer  =" +AEtoile.nbrNGenerer);
        this.nbrNGenerer = AEtoile.nbrNGenerer;
        this.nbrNExploiter = AEtoile.nbrNExploiter;
        this.timeElapsed = Long.toString(Duration.between(start, end).toMillis());;
/*
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

 */

    }
    void SolveByHeurstique(){
        //System.out.println("Algo H1 sur n="+  this.cases.length);
        AEtoile AEtoile= new AEtoile();
        Instant start = Instant.now();
        AEtoile.AppliquerA(this);
        Instant end = Instant.now();
        //System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        //System.out.println("Le nombre de Noeud Exloité =" +AEtoile.nbrNExploiter);
        //System.out.println("Le nombre de Noeud Generer  =" +AEtoile.nbrNGenerer);
        this.nbrNGenerer = AEtoile.nbrNGenerer;
        this.nbrNExploiter = AEtoile.nbrNExploiter;
        this.timeElapsed = Long.toString(Duration.between(start, end).toMillis());;

        /*
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

         */
    }
    solution SolveByGA(){
        GA ga= new GA();
        Instant start = Instant.now();
        solution s = ga.algorithmeGA(this.cases.length,200,5000,0,1);
        this.Fitness = s.fitness;
        System.arraycopy(s.cases, 0, this.cases, 0, this.cases.length);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        //System.out.println("Time taken HEURSTIQUE: "+ timeElapsed.toMillis() +" milliseconds");
        //System.out.println("Le nombre de Noeud Exloité =" +AEtoile.nbrNExploiter);
        //System.out.println("Le nombre de Noeud Generer  =" +AEtoile.nbrNGenerer);
        this.nbrNGenerer = 0;
        this.nbrNExploiter = 0;
        this.timeElapsed = Long.toString(Duration.between(start, end).toMillis());

        /*
        for (int aCase : this.cases) {
            System.out.print("|" + aCase);
        }
        System.out.println("");

         */
        return s;
    }
}
