/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.problem.Trap;

import ProOF.apl.sample1.FMS.combinatorial.CombCodification;
import ProOF.apl.sample1.FMS.combinatorial.CombConstraint;
import ProOF.apl.sample1.FMS.combinatorial.CombVar;
import ProOF.apl.sample1.FMS.combinatorial.oCombAddConstraints;
import java.util.List;

/**
 *
 * @author marci
 */
public class iTrapLocal extends oCombAddConstraints<Trap, CombCodification>{
    @Override
    public String name() {
        return "TrapLocal";
    }
    @Override
    public CombConstraint<Integer>[] constraints(Trap prob, CombCodification codif) throws Exception {
        CombConstraint<Integer>[] constraints = new CombConstraint[prob.M];
        for(int m = 0; m<prob.M; m++){
            final int m2 = m;
            constraints[m] = new CombConstraint<Integer>(codif) {
                @Override
                public void select_variables(List<CombVar<Integer>> list) throws Exception {
                    for(int k=0; k<prob.K; k++){
                        list.add(prob.var(m2*prob.K + k));
                    }
                }
                @Override
                public boolean feasible() throws Exception {
                    for(int i=1; i<size(); i++){
                        if(x(i-1)!=x(i)){
                            return false;
                        }
                    }
                    return true;
                }
            };
        }
        return constraints;
    }
}
