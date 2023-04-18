import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int i;
        /*PrintStream out = new PrintStream(new FileOutputStream("Test_2.txt"));
        System.setOut(out);
        */
        for(i=4;i<=100;i=i+2){
            //Problem prblm = new Problem(i);
            //prblm.SolveByDFS();
            //prblm.SolveByHeurstique();
            //prblm.SolveByHeurstique_2();
            GA ga = new GA();
            solution s = ga.algorithmeGA(i,500,10000);
            System.out.println(i + " ----- "+s.fitness);
        }
        /*
        for(i=8;i<=38;i=i+2){
            Problem prblm = new Problem(i);
            prblm.SolveByBFS();
        }
        */

    }
}
