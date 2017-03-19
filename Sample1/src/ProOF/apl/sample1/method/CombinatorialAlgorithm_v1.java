/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.method;

import ProOF.apl.sample1.FMS.combinatorial.CombCodification;
import ProOF.apl.sample1.FMS.combinatorial.CombProblem;
import ProOF.apl.sample1.FMS.combinatorial.CombVar;
import ProOF.com.Linker.LinkerApproaches;
import ProOF.com.language.Factory;
import ProOF.com.runner.ExceptionForceFinish;
import ProOF.gen.stopping.Stop;
import ProOF.opt.abst.run.MetaHeuristic;
import ProOF.opt.abst.problem.meta.Solution;

/**
 *
 * @author marcio
 */
public class CombinatorialAlgorithm_v1 extends MetaHeuristic{
    private CombProblem problem;
    private Stop stop;
    
    private final Factory fProblem;
    private final Factory fStop;
    public CombinatorialAlgorithm_v1(Factory fProblem, Factory fStop) {
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
    }
    
    @Override
    public void execute() throws Exception {
        CombVar var[] = problem.variables();
        Solution<?,?,CombCodification,?> current = problem.build_sol();
        deth_search(0, current, var);
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
