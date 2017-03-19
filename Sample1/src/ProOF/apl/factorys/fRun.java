/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.factorys;

import ProOF.apl.sample1.method.BranchAndBound;
import ProOF.com.language.Run;
import ProOF.com.language.Factory;
import ProOF.apl.sample1.method.GeneticAlgorithm;
import ProOF.apl.sample1.method.RandomAlgorithm;
import ProOF.apl.sample1.method.CombinatorialAlgorithm;
import ProOF.apl.sample1.method.CombinatorialAlgorithm_v1;
import ProOF.apl.sample1.method.CombinatorialAlgorithm_v2;
import ProOF.apl.sample1.method.GreedyAlgorithm;
import ProOF.apl.sample1.method.NSGAII;
import ProOF.apl.sample1.method.SimulatedAnnealing;

/**
 *
 * @author marcio
 */
public class fRun extends Factory<Run>{
    public static final fRun obj = new fRun();
    private fRun(){}
    
    @Override
    public String name() {
        return "Run";
    }
    @Override
    public Run build(int index) {
        switch(index){
            case 0: return new RandomAlgorithm(fProblem.obj, fStop.obj);
            case 1: return new SimulatedAnnealing(fProblem.obj, fStop.obj, fTemp.obj);
            case 2: return new GeneticAlgorithm(fProblem.obj, fStop.obj);
            case 3: return new NSGAII(fProblem.obj, fStop.obj);
            case 4: return new BranchAndBound(fBranchProblem.obj, fStop.obj);
            case 5: return new GreedyAlgorithm(fBranchProblem.obj, fStop.obj);
            case 6: return new CombinatorialAlgorithm(fProblem.obj, fStop.obj);
            case 7: return new CombinatorialAlgorithm(fProblem.obj, fStop.obj);
            case 8: return new CombinatorialAlgorithm_v1(fProblem.obj, fStop.obj);
            case 9: return new CombinatorialAlgorithm_v2(fProblem.obj, fStop.obj);
        }
        return null;
    }
}
