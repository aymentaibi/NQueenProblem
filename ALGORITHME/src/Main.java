import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int i = 100;
        PSO pso = new PSO();
        int taillePop ,nbrIteration ;
        double c1,  c2,  alpha;
        taillePop = 100;
        nbrIteration = 5000;
        c1 = 0.8;
        c2 = 1.5;
        alpha = 0;
        solution s = pso.pso_algorithme(i,taillePop,c1,c2,alpha,nbrIteration);
        System.out.println(i + " ----- "+s.fitness);
        /*
            GA ga = new GA();
            solution s = ga.algorithmeGA(i,50,8000,0);
            System.out.println(i + " ----- "+s.fitness);
         */

    }
}
