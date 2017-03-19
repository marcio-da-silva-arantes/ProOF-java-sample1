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
import ProOF.gen.stopping.CountIteration;
import ProOF.gen.stopping.Stop;
import ProOF.opt.abst.run.MetaHeuristic;
import ProOF.opt.abst.problem.meta.Solution;

/**
 *
 * @author marcio
 */
public class CombinatorialAlgorithm extends MetaHeuristic{
    private CombProblem problem;
    private Stop stop;
    private oCombAddConstraints add;
    
    private final CountIteration loop = CountIteration.obj;
    
    private final Factory fProblem;
    private final Factory fStop;
    public CombinatorialAlgorithm(Factory fProblem, Factory fStop) {
        this.fProblem = fProblem;
        this.fStop = fStop;
    }
    
    @Override
    public String name() {
        return "Combinatorial Algorithm";
    }
    @Override
    public void services(LinkerApproaches link) throws Exception {
        link.add(loop);
        problem = link.get(fProblem, CombProblem.class, problem);
        stop    = link.get(fStop, stop);
        add     = link.need(oCombAddConstraints.class, add);
    }
    
    @Override
    public void execute() throws Exception {
        CombVar var[] = problem.variables();
        Solution<?,?,CombCodification,?> current = problem.build_sol();
        CombConstraint constraints[] = add.constraints(problem, current.codif());
        
        //sort in crescent order by last_id 
        for(int i=1; i<constraints.length; i++){
            CombConstraint temp = constraints[i];
            int j = i-1;
            while(j>=0 && constraints[j].last_id > temp.last_id){
                constraints[j+1] = constraints[j];
                j--;
            }
            constraints[j+1] = temp;
        }
        
        for(int i=0; i<constraints.length; i++){
            System.out.println("last_id = " + constraints[i].last_id);
        }
        
        System.out.println("---------- deth_search ----------");        
        deth_search(0, current, var, constraints);
    }
    private boolean feasible(int n, CombConstraint constraints[]) throws Exception{
        for(CombConstraint c : constraints){
            if(c.last_id == n-1){
                return c.feasible();
            }
        }
        return true;
    }
    private void deth_search(int n, Solution<?,?,CombCodification,?> current, 
            CombVar var[], CombConstraint constraints[]) throws Exception{
        if(stop.end()){
            throw new ExceptionForceFinish();
        }else if(feasible(n, constraints)){
            if(n==var.length){    //full solution
                problem.evaluate(current);
            }else{  //partial solution
                for(Object v : var[n].all()){
                    current.codif().cromo[n] = v;
                    deth_search(n+1, current, var, constraints);
                    current.codif().cromo[n] = null;
                }
            }
        }
    }
}
