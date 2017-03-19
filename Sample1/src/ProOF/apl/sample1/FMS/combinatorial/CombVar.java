/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProOF.apl.sample1.FMS.combinatorial;

import java.util.Random;

/**
 *
 * @author marci
 * @param <T>
 */
public final class CombVar <T>{
    private static int id_count = 0;
    public final int id;
    private final T[] possibilities;
    public CombVar(T... possibilities) {
        this.id = id_count++;
        this.possibilities = possibilities;
    }
    public int N(){
        return possibilities.length;
    }
    public T value(int n){
        return possibilities[n];
    }
    public T rnd(Random rnd){
        return possibilities[rnd.nextInt(possibilities.length)];
    }
    public T[] all(){
        return possibilities;
    }
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj) && ((CombVar)obj).id == id; //To change body of generated methods, choose Tools | Templates.
//    }
}
