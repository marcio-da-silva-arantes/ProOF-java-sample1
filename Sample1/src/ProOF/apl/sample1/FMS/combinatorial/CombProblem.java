/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;

import ProOF.com.Linker.LinkerApproaches;
import ProOF.com.language.Factory;
import ProOF.opt.abst.problem.meta.Best;
import ProOF.opt.abst.problem.meta.Problem;

/**
 *
 * @author marcio
 * @param <B>
 */
public abstract class CombProblem <B extends Best> extends Problem<B>{
    protected abstract CombVar[] build_var() throws Exception;
    
    private CombVar[] variables;
    
    private final Factory fCombOperator;
    public CombProblem(Factory fCombOperator) {
        this.fCombOperator = fCombOperator;
    }
    
    public CombVar[] variables(){
        return variables;
    }
    public CombVar var(int n){
        return variables[n];
    }
    public int size(){
        return variables.length;
    }
    public Object rnd(int n) {
        return variables[n].rnd(rnd);
    }
    @Override
    public void start() throws Exception {
        variables = build_var();
    }
    @Override
    public void services(LinkerApproaches link) throws Exception {
        super.services(link);
        link.add(fCombOperator);
    }
    @Override
    public CombCodification build_codif() throws Exception {
        return new CombCodification(this);
    }
//    public Solution build_sol(Codification codif) throws Exception{
//        return new Problem.Sol(build_obj(), codif);
//    }
//    public void fix_combination(CombCodification codif, Object[] values){
//        System.arraycopy(values, 0, codif.cromo, 0, codif.cromo.length);
//    }
//    public void fix_combination(Solution<?,?,CombCodification,?> sol, Object[] values){
//        fix_combination(sol.codif(), values);
//    }
}
