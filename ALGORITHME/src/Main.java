import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int i;
        PrintStream out = new PrintStream(new FileOutputStream("Test_2.txt"));
        System.setOut(out);
        /*for(i=40;i<50;i=i+2){
            Problem prblm = new Problem(i);
            prblm.SolveByHeurstique();
            prblm.SolveByHeurstique_2();
        }*/
        for(i=8;i<=38;i=i+2){
            Problem prblm = new Problem(i);
            prblm.SolveByBFS();
        }
    }
}
