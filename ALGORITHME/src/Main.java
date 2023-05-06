import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int i = 100;
        PSO pso = new PSO();
        int taillePop ,nbrIteration ;
        double c1,  c2,  alpha;
        taillePop = 500;
        nbrIteration = 100;
        c1 =1;
        c2 = 2;
        alpha = 0.5;
        //Tests for Genetic:
        int[] tailleDeTest = {25,100,300,1000};
        int[] nbrIteration_Test = {500,1000,2000,3000,4000,5000,6000};
        int nbrIteration_GA;
        nbrIteration_GA = 3000;
        int nbrPop = 200;
        GA ga = new GA();
        Instant start,end;
        Duration timeElapsed;
        solution s_2;
        System.out.print(" PopT: " + nbrPop);
        System.out.print(" TC: 0.5");
        System.out.print(" TM: 0.5");
        System.out.println("******************************");
        int moyF,moyT;
        for (int testSize : tailleDeTest) {
            System.out.println("TP: " + testSize);
            for (int iteration :nbrIteration_Test) {
                System.out.print(" Iteration: " + iteration);
                System.out.print("\n");
                moyF = 0;
                moyT = 0;
                for(int m=0;m<3;m++) {
                    start = Instant.now();
                    s_2 = ga.algorithmeGA(testSize, nbrPop, iteration, 0.5, 0.5);
                    end = Instant.now();
                    timeElapsed = Duration.between(start, end);
                    moyF = moyF + s_2.fitness;
                    moyT = moyT + (int)timeElapsed.toMillis();
                }

                moyF = (int) (moyF/3);
                moyT = (int) (moyT/3);
                System.out.print(" F:" + moyF);
                System.out.println(" TE:" + moyT);
                System.out.println("******************************");
            }
        }

        /*
        solution s = pso.pso_algorithme(i,taillePop,c1,c2,alpha,nbrIteration);
        System.out.println(i + " ----- "+s.fitness);
        GA ga = new GA();
        solution s_2 = ga.algorithmeGA(i,50,8000,0,1);
        System.out.println(i + " ----- "+s_2.fitness);
         */
    }
}
