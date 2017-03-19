/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.problem.Trap;

import ProOF.apl.sample1.FMS.combinatorial.CombProblem;
import ProOF.apl.sample1.FMS.combinatorial.CombVar;
import ProOF.com.language.Factory;
import ProOF.gen.best.BestSol;
import ProOF.opt.abst.problem.meta.Objective;

/**
 *
 * @author marci
 */
public class Trap extends CombProblem<BestSol>{
    protected final int K = 5;  //trap size
    protected final int M = 5;  //number of traps;

    public Trap(Factory fCombOperator) {
        super(fCombOperator);
    }
    
    @Override
    public String name() {
        return "Trap";
    }
    @Override
    public CombVar<Integer>[] build_var() throws Exception {
        CombVar<Integer>[] var = new CombVar[K*M];
        for(int i=0; i<var.length; i++){
            var[i] = new CombVar<>(0, 1);
        }
        return var;
    }
    @Override
    public BestSol best() {
        return BestSol.object();
    }
    @Override
    public Objective build_obj() throws Exception {
        return new TrapObjective();
    }
}
