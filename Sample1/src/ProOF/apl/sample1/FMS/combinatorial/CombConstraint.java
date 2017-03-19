/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marci
 * @param <T>
 */
public abstract class CombConstraint<T> {
    private final CombCodification codif;
    private final CombVar<T>[] var;
    public final int last_id;
    public CombConstraint(CombCodification codif) throws Exception {
        this.codif = codif;
        List<CombVar<T>> list = new LinkedList();
        select_variables(list);
        this.var = list.toArray(new CombVar[list.size()]);
        int last = -1;
        for(int i=0; i<this.var.length; i++){
            last = Math.max(last, var[i].id);
        }
        last_id = last;
    }
    public T x(int i){
        return (T) codif.cromo[var[i].id];
    }
    public int size(){
        return var.length;
    }
    public CombVar<T>[] var() throws Exception{
        return var;
    }
    public abstract void select_variables(List<CombVar<T>> list) throws Exception;
    public abstract boolean feasible() throws Exception;
}
