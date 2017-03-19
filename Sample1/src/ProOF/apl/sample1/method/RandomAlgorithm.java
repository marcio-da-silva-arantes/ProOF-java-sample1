/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.method;

import ProOF.com.Linker.LinkerApproaches;
import ProOF.com.language.Factory;
import ProOF.gen.operator.Initialization;
import ProOF.gen.stopping.Stop;
import ProOF.opt.abst.run.MetaHeuristic;
import ProOF.opt.abst.problem.meta.Problem;
import ProOF.opt.abst.problem.meta.Solution;

/**
 *
 * @author marcio
 */
public class RandomAlgorithm extends MetaHeuristic{
    private Initialization init;
    private Problem problem;
    private Stop stop;
    
    private final Factory fProblem;
    private final Factory fStop;
    public RandomAlgorithm(Factory fProblem, Factory fStop) {
        this.fProblem = fProblem;
        this.fStop = fStop;
    }
    
    @Override
    public String name() {
        return "Random Algorithm";
    }
    @Override
    public void services(LinkerApproaches link) throws Exception {
        init    = link.add(Initialization.obj);
        problem = link.get(fProblem, problem);
        stop    = link.get(fStop, stop);
    }
    @Override
    public void execute() throws Exception {
        //Declares the solution and makes memory allocation
        Solution sol = problem.build_sol();
        //Generates random solutions until the stopping criterion is reached
        do{
            init.initialize(sol);
            problem.evaluate(sol);
        }while(!stop.end());
    }
}
