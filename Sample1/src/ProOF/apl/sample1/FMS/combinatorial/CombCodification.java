/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;

import ProOF.com.Stream.StreamPrinter;
import ProOF.opt.abst.problem.meta.codification.Codification;

/**
 *
 * @author marcio
 */
public class CombCodification extends Codification<CombProblem, CombCodification> {
    public final Object[] cromo;
    public CombCodification(CombProblem prob) throws Exception{
        this.cromo = new Object[prob.size()];
    }
    @Override
    public void copy(CombProblem prob, CombCodification source) throws Exception {
        System.arraycopy(source.cromo, 0, this.cromo, 0, this.cromo.length);
    }
    @Override
    public CombCodification build(CombProblem prob) throws Exception {
        return new CombCodification(prob);
    }
    
    @Override
    public void printer(CombProblem prob, StreamPrinter stream) throws Exception {
        super.printer(prob, stream); //To change body of generated methods, choose Tools | Templates.
        for(int n=0; n<cromo.length; n++){
            stream.printString("x"+n, " %3s", cromo[n].toString());
        }
    }
    
}
