import java.util.ArrayList;
import java.util.Comparator;

public class GA {
    public ArrayList<solution> generationSAleo(int nbrSolution, int n){
        ArrayList<solution> Solutions = new ArrayList<>();
        for (int i = 0; i < nbrSolution; i++) {
            solution new_sol = new solution(n);
            new_sol.setRandomSolution();
            Solutions.add(new_sol);
        }
        return Solutions;
    }
    solution croisement(solution solu_1,solution solu_2){
        solution new_solution = new solution(solu_1.cases.length);
        int indice = (int)(Math.random()*(solu_1.cases.length+1));
        for (int i = 0; i < indice; i++) {
            new_solution.cases[i] = solu_1.cases[i];
        }
        for (int i = indice; i < solu_1.cases.length; i++) {
            new_solution.cases[i] = solu_2.cases[i];
        }
        new_solution.Fiteness();
        return new_solution;
    }
    solution mutation(solution solu){
        //fixer indice
        int indice = (int)(Math.random()*(solu.cases.length));
        int indice_2 = (int)(Math.random()*(solu.cases.length));
        solution new_solution = new solution(solu.cases.length);
        for (int i = 0; i < solu.cases.length; i++) {
            if (i == indice) new_solution.cases[i] = solu.cases[indice_2];
            else {
                if (i == indice_2) new_solution.cases[i] = solu.cases[indice];
                else new_solution.cases[i] = solu.cases[i];
            }
        }
        new_solution.Fiteness();
        return new_solution;
    }
    solution algorithmeGA(int n,int TailleP, int nbrIteration,double taux_croisement){
        int TauxCroisement_1 = (int) Math.round(TailleP * 0.2);
        int TauxCroisement_2 = (int) Math.round(TailleP * 0.2);
        int newPopSize = (int) Math.round(TailleP * 0.4);
        ArrayList<solution> pop = generationSAleo(TailleP, n);
        ArrayList<solution> pop_2;
        ArrayList<solution> children = new ArrayList<>();
        pop.sort(Comparator.comparing(solution::getFitness));
        solution child_1C,child_1M,child_2C,child_2M,child_5,child_6;
        int max = pop.size();
        int worstF;
        int TailleC,bestF=pop.get(0).fitness,nbrChangementBF=0;
        for (int i = 0; i < nbrIteration && pop.get(0).fitness != 0; i++) {
            children.clear();
            for (int j = 0; j <TauxCroisement_1 ; j=j+2) {
                if(taux_croisement > Math.random()) {
                    children.add(croisement(pop.get(j), pop.get(max - j - 1)));
                }
                else{
                    children.add(mutation(pop.get(j)));
                    children.add(mutation(pop.get(max - j - 1)));
                }
            }
            for (int j = 0; j <TauxCroisement_2 ; j=j+2) {
                if(taux_croisement > Math.random()) {
                    children.add(croisement(pop.get(j), pop.get(j+1)));
                }
                else{
                    children.add(mutation(pop.get(j)));
                    children.add(mutation(pop.get(j+1)));
                }
            }
            TailleC = children.size();
            for (int j = 0; j < TailleC; j++) {
                children.add(mutation(children.get(j)));
            }
            worstF = pop.get(max-1).fitness;
            for (int j = 0; j < children.size(); j++) {
                if (children.get(j).fitness <= worstF){
                    pop.remove(max-1);
                    pop = insertElemn(pop,children.get(j));
                }
                children.remove(j);
            }
            if(i%100 == 0){
                TailleC = pop.get(0).fitness;
                if(bestF > TailleC){ nbrChangementBF = 0; bestF = TailleC;}
                else nbrChangementBF++;
                System.out.print("iteration i:" + i +" Best Fiteness ");
                System.out.println(TailleC);
            }
        }
        return pop.get(0);
    }
    ArrayList<solution> insertElemn(ArrayList<solution> listS,solution solu){
        int i =0;
        while(i < listS.size() && listS.get(i).fitness < solu.fitness) i++;
        listS.add(i,solu);
        return listS;
    }

}
