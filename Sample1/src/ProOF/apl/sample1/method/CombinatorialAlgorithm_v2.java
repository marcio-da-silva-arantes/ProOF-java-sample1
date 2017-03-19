/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.method;

import ProOF.apl.sample1.FMS.combinatorial.CombCodification;
import ProOF.apl.sample1.FMS.combinatorial.CombConstraint;
import ProOF.apl.sample1.FMS.combinatorial.CombProblem;
import ProOF.apl.sample1.FMS.combinatorial.CombVar;
import ProOF.com.Linker.LinkerApproaches;
import ProOF.com.runner.ExceptionForceFinish;
import ProOF.apl.sample1.FMS.combinatorial.oCombAddConstraints;
import ProOF.com.language.Factory;
import ProOF.gen.stopping.Stop;
import ProOF.opt.abst.run.MetaHeuristic;
import ProOF.opt.abst.problem.meta.Solution;
import java.util.LinkedList;

/**
 *
 * @author marcio
 */
public class CombinatorialAlgorithm_v2 extends MetaHeuristic{
    private CombProblem problem;
    private Stop stop;
    private oCombAddConstraints add;
    
    private final Factory fProblem;
    private final Factory fStop;
    public CombinatorialAlgorithm_v2(Factory fProblem, Factory fStop) {
        this.fProblem = fProblem;
        this.fStop = fStop;
    }
    
    @Override
    public String name() {
        return "Combinatorial Algorithm";
    }
    @Override
    public void services(LinkerApproaches link) throws Exception {
        problem = link.get(fProblem, CombProblem.class, problem);
        stop    = link.get(fStop, stop);
        add     = link.need(oCombAddConstraints.class, add);
    }
    
    @Override
    public void execute() throws Exception {
        CombVar var[] = problem.variables();
        Solution<?,?,CombCodification,?> current = problem.build_sol();
        CombConstraint constraints[] = add.constraints(problem, current.codif());
        
        System.out.println("---------- preprocess  ----------");
        for(CombConstraint c : constraints){
            System.out.println("---------- Feasibles c ----------");      
            CombVar sub_var[] = c.var();
            LinkedList<Object[]> feasibles = new LinkedList<>();
            var_search(0, c, feasibles, current, sub_var);
            for(Object[] comb : feasibles){
                for(Object o : comb){
                    System.out.printf("%3s ", o);
                }
                System.out.println();
            }
        }
                
        System.out.println("---------- deth_search ----------");        
        deth_search(0, current, var);
    }
    private void var_search(int n, CombConstraint c, LinkedList<Object[]> feasibles, 
            Solution<?,?,CombCodification,?> current, CombVar sub_var[]) throws Exception{
        //System.out.println("n = "+ n + " / " + sub_var.length);
        if(stop.end()){
            throw new ExceptionForceFinish();
        }else if(n==sub_var.length){    //full solution
            if(c.feasible()){
                Object[] copy = new Object[sub_var.length];
                for(int i=0; i<sub_var.length; i++){
                    int id = sub_var[i].id;
                    copy[i] = current.codif().cromo[id];
                }
                feasibles.addLast(copy);
            }
        }else{  //partial solution
            for(Object v : sub_var[n].all()){
                int id = sub_var[n].id;
                current.codif().cromo[id] = v;
                var_search(n+1, c, feasibles, current, sub_var);
                current.codif().cromo[id] = null;
            }
        }
    }
    private void deth_search(int n, Solution<?,?,CombCodification,?> current, CombVar var[]) throws Exception{
        if(stop.end()){
            throw new ExceptionForceFinish();
        }else if(n==var.length){    //full solution
            problem.evaluate(current);
        }else{  //partial solution
            for(Object v : var[n].all()){
                current.codif().cromo[n] = v;
                deth_search(n+1, current, var);
                current.codif().cromo[n] = null;
            }
        }
    }
}
