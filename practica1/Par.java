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
public class Par<T1,T2> {
    T1 pmr; 
    T2 snd;

    public Par(T1 pmr, T2 snd) {
        this.pmr = pmr;
        this.snd = snd;
    }
    

    public T1 getPmr() {
        return pmr;
    }

    public void setPmr(T1 pmr) {
        this.pmr = pmr;
    }

    public T2 getSnd() {
        return snd;
    }

    public void setSnd(T2 snd) {
        this.snd = snd;
    }
    
           
}
