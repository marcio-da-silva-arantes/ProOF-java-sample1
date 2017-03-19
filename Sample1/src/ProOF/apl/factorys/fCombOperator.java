/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.factorys;


import ProOF.apl.sample1.FMS.combinatorial.iCombInitRandom;
import ProOF.apl.sample1.problem.Trap.iTrapLocal;
import ProOF.com.language.Factory;
import ProOF.opt.abst.problem.meta.codification.Operator;


/**
 *
 * @author marcio
 */
public class fCombOperator extends Factory<Operator>{
    public static final fCombOperator obj = new fCombOperator();

    @Override
    public String name() {
        return "Codif-Comb Operators";
    }
    
    @Override
    public Operator build(int index) {
        switch(index){
            case  0: return new iCombInitRandom();
            case  1: return new iTrapLocal();
        }
        return null;
    }
}
