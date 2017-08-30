/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numeros;

/**
 *
 * @author nieves
 */
public class Terna<T1,T2,T3> extends Par<T1,T2>{
    T3 tcr; 

    public Terna(T1 pmr, T2 snd, T3 tcr) {
        super(pmr, snd);
        this.tcr = tcr;
    }
    
}
