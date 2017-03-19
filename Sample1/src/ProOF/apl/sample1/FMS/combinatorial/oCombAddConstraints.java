/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;


import ProOF.apl.sample1.FMS.combinatorial.CombCodification;
import ProOF.apl.sample1.FMS.combinatorial.CombConstraint;
import ProOF.apl.sample1.FMS.combinatorial.CombProblem;
import ProOF.opt.abst.problem.meta.codification.Operator;

/**
 *
 * @author marcio
 */
public abstract class oCombAddConstraints <
        Prob extends CombProblem, Codif extends CombCodification
> extends Operator {
    public abstract CombConstraint[] constraints(Prob prob, Codif ind) throws Exception;
}
