public class Main {

    public static void main(String[] args) {
        Problem prblm = new Problem(4);
        prblm.SolveByHeurstique();
        if(prblm.isValide()) System.out.println("True");
        else System.out.println("False");
    }
}
