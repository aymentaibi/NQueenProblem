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
    solution algorithmeGA(int n,int TailleP, int nbrIteration){
        ArrayList<solution> pop = generationSAleo(TailleP, n);
        pop.sort(Comparator.comparing(solution::getFitness));
        solution child_1C,child_1M,child_2C,child_2M,child_5,child_6;
        int max = pop.size();
        int worstF;
        int bestC1=0,bestC2=0;
        for (int i = 0; i < nbrIteration && pop.get(0).fitness != 0; i++) {
            child_1C = croisement(pop.get(0),pop.get(max-1));
            child_1M = mutation(child_1C);
            child_2C = croisement(pop.get(0),pop.get(1));
            child_2M = mutation(child_2C);
            worstF = pop.get(max-1).fitness;
            if(child_1C.fitness <= worstF){
                pop.remove(max-1);
                pop = insertElemn(pop,child_1C);
                bestC1++;
            }
            if(child_1M.fitness <= worstF){
                pop.remove(max-1);
                pop = insertElemn(pop,child_1M);
                bestC1++;
            }
            if(child_2C.fitness <= worstF){
                pop.remove(max-1);
                pop = insertElemn(pop,child_2C);
                bestC2++;
            }
            if(child_2M.fitness <= worstF){
                pop.remove(max-1);
                pop = insertElemn(pop,child_2M);
                bestC2++;
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
