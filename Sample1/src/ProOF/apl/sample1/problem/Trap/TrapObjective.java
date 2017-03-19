/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.problem.Trap;

import ProOF.apl.sample1.FMS.combinatorial.CombCodification;
import ProOF.opt.abst.problem.meta.objective.SingleObjective;

/**
 *
 * @author marci
 */
public class TrapObjective extends SingleObjective<Trap, CombCodification, SingleObjective>{
    public TrapObjective() throws Exception {}
    @Override
    protected void evaluate(Trap prob, CombCodification codif) throws Exception {
        double cost = 0;
        for(int m=0; m<prob.M; m++){
            int u = 0;
            for(int k=0; k<prob.K; k++){
                int n = prob.K*m + k;
                u += (int)codif.cromo[n];
            }
            cost += (u==prob.K ? 0 : u+1);
        }
        set(cost);
    }
    @Override
    public SingleObjective build(Trap prob) throws Exception {
        return new TrapObjective();
    }
}
