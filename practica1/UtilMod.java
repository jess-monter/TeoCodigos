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
public class UtilMod {

    public static boolean eqMod(int n, int m, int p) {
        n = n % p;
        m = n % p;
        if (n < 0) {
            n = n + p;
        }
        if (m < 0) {
            m = m + p;
        }
        return n == m;
    }

    public static int inverso(int a, int n) {
        int t = 0;
        int nt = 1;
        int r = n;
        int nr = a;
        int q;
        int temp;
        while (nr != 0) {
            q = r / nr;
            temp = t;
            t = nt;
            nt = temp - q * nt;
            temp = r;
            r = nr;
            nr = temp - q * nr;
        }
        if (t < 0) {
            t = t + n;
        }
        return t;
    }
    //es necesario porque a veces el modulo da negativo 
    //entonces esta funcion es para que todos los coeficientes
    //aprezcan como positivos 
    public static int modulo(int n, int p) {
        n = n % p;
        if (n < 0) {
            n = n + p;
        }
        return n;
    }
    
}
