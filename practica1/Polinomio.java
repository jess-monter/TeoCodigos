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
public class Polinomio {
    int[] coef;
    int grado;
    //prim es el campo en el que estan los coeficientes 
    int prim;
    //para crear el polinomio 0 
    public Polinomio(int prim){
        this.coef = new int[1];
        int grado = -1;
        this.prim = prim;
    }
    //para crear un polinomio constante
    public Polinomio(int cons, int prim){
        this.coef = new int[1];
        coef[0] = cons;
        this.grado = 0;
        this.prim = prim;
    }
    //crear un polinomio a partir de un arreglo 
    public Polinomio(int[] coef,int p){
        this.coef = coef;
        for (int i = 0; i < coef.length; i++) {
           this.coef[i] = UtilMod.modulo(coef[i],p);
        }
        int i = coef.length -1;
        //ajusta el grado 
        while(0 <= i && this.coef[i] == 0){
            i--;
        }
        this.grado = i;
        this.prim = p;
    }
   
    public Polinomio suma(Polinomio pl2){
        Polinomio aux = new Polinomio(new int[Integer.max(this.grado, pl2.grado) + 1],prim);
        aux.grado = Integer.max(this.grado, pl2.grado);
        for (int i = 0; i <= this.grado; i++) {
           aux.coef[i] += this.coef[i];
        }
        for (int i = 0; i <= pl2.grado; i++) {
           aux.coef[i] = UtilMod.modulo((aux.coef[i] + pl2.coef[i]),prim); 
        }
        //ajusta el grado 
        while(aux.grado >= 0 && aux.coef[aux.grado] == 0){
            aux.grado--;
        }
       return aux;
    }
     public Polinomio resta(Polinomio pl2){
        Polinomio aux = new Polinomio(new int[Integer.max(this.grado, pl2.grado) + 1],prim);
        aux.grado = Integer.max(this.grado, pl2.grado);
        for (int i = 0; i <= this.grado; i++) {
           aux.coef[i] += this.coef[i];
        }
        for (int i = 0; i <= pl2.grado; i++) {
           aux.coef[i] = UtilMod.modulo((aux.coef[i] - pl2.coef[i]),prim); 
        }
        //ajustar el grado 
        while(aux.grado >= 0 && aux.coef[aux.grado] == 0){
            aux.grado--;
        }
       return aux;
    }
     
    public Polinomio multEsc(int esc){
        for (int i = 0; i <= this.grado; i++) {
            this.coef[i] = UtilMod.modulo(this.coef[i]*esc,this.prim);
        }
        return this;
    }

    //de la misma manera qeu con la suma 
    //el polinomio que llama es el que se queda con el resdultado de la 
    //multiplicacion 
    public Polinomio multiplicacion(Polinomio p2){
        int[] nuevo = new int[this.grado + p2.grado + 1];
        for (int i = 0; i <= this.grado; i++) {
            for (int j = 0; j <= p2.grado; j++) {
                nuevo[i+j] = (nuevo[i+j] + coef[i]*p2.coef[j])%prim;
            }
        }
        return new Polinomio(nuevo,prim);
    }
    @Override
    public String toString(){
        String ret = "";
        for (int i = 0; i <= this.grado; i++) {
            if(coef[i] != 0){
               ret = coef[i] + "x^" + i + " + "+ ret;
            }
        }
        return ret;
    }
    @Override
    public boolean equals(Object o){
        boolean flag = false;
        if(o.getClass() == this.getClass() && 
           this.grado == ((Polinomio)o).grado && 
            this.prim == ((Polinomio)o).prim){
            int i = 0;
            //son iguales solo si tienen el mismo grado y los mismos coeficientes
            while(i <= grado && UtilMod.eqMod(coef[i],((Polinomio)o).coef[i],prim)){
                i++;
            }
            if(i-1 == grado){
                flag = true;
            }
        }
        return flag;
     }
    
    //utilizado unicamente para la division 
    public Polinomio multMon(int coef,int grad){ 
        int[] nuevo = new int[this.grado + grad + 1];
        for (int i = 0; i <= this.grado; i++) {
            nuevo[i + grad] = (coef*this.coef[i])%prim;
        }
        return new Polinomio(nuevo,prim);
    }
    //regresa un par (cociente,resto)
    public Par<Polinomio,Polinomio> division(Polinomio p1){
        if(p1.grado > this.grado){
            Polinomio r = new Polinomio(new int[this.grado+1],this.prim);
            r = this.suma(r);
            return new Par(new Polinomio(this.prim),r);
        }else{
        Polinomio r = new Polinomio(new int[this.grado+1],this.prim);
        r = this.suma(r);
        //ten cuidado con este arreglo 
        int[] coc = new int[this.grado - p1.grado + 1];
        int grad,coe;
        int inv = UtilMod.inverso(p1.coef[p1.grado], this.prim);
        while(p1.grado <= r.grado){
            coe = (r.coef[r.grado]*inv)%prim;
            grad = r.grado - p1.grado;
            coc[grad]= coe;
            r = r.resta(p1.multMon(coe, grad));
        }
        return new Par(new Polinomio(coc,this.prim),r);
        }
    }
    
    //regresa el mcd de este con el polinomio b ademas 
    //los polinomios con los que se crea la combinacion lineal
    public  Terna<Polinomio,Polinomio,Polinomio> algExtEu(Polinomio b) {
        int[] conCero = {0};
        int[] conUno = {1};
        Polinomio t,oldt,s,olds,r,oldr;
        s = new Polinomio(conCero,this.prim) ;
        olds = new Polinomio(conUno,this.prim);
        t = new Polinomio(conUno,this.prim);
        oldt = new Polinomio(conCero,this.prim);
        r = b; 
        oldr = this;
        Polinomio q;
        Polinomio temp;
        Polinomio cero = new Polinomio(conCero,this.prim);
        while(!(r.equals(cero))){
            q = (oldr.division(r)).pmr;
            System.out.println("la division es: ");
            System.out.println(q);
            temp = r;
            System.out.println("el residuo es: ");
          
            r = oldr.resta(q.multiplicacion(r));
            System.out.println(r);
            oldr = temp ;
            
            temp = s;
            s = olds.resta(q.multiplicacion(s));
            olds = temp;
            
            temp = t;
            t = oldt.resta(q.multiplicacion(t));
            oldt = temp;
          
        }
       return new Terna(olds,oldt,oldr);
    }
    //evaluacion del polinomio en x 
    public int evalHorner(int x){
        int res = 0; 
        for(int i = grado ; i >= 0 ; i--){
            res = coef[i] + x*res;
        }
        return UtilMod.modulo(res, prim);
    }
}
