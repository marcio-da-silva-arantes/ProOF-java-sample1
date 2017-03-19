/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;

import ProOF.gen.operator.oInitialization;

/**
 *
 * @author marci
 */
public class iCombInitRandom extends oInitialization<CombProblem, CombCodification>{
    @Override
    public String name() {
        return "Random";
    }
    @Override
    public void initialize(CombProblem prob, CombCodification ind) throws Exception {
        for(int n=0; n<prob.size(); n++){
            ind.cromo[n] = prob.rnd(n);
        }
    }
}
