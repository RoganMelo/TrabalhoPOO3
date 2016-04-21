package control;

import javax.swing.JProgressBar;

/**
 *
 * @author Diego e Rogan
 */
public class BarraProgresso {
    private static int cont_chassi = 0;
    private static int cont_carroceria = 0;
    private static int cont_roda = 0;
    private static int cont_motor = 0;
    private static int condicao_chassi = 0;
    private static int condicao_carroceria = 0;
    private static int condicao_roda = 0;
    private static int condicao_motor = 0;
    private static boolean produzir_chassi = false;
    private static boolean produzir_carroceria = false;
    private static boolean produzir_roda = false;
    private static boolean produzir_motor = false;
    private static boolean interromper = false;
    
    public static void barraChassi(JProgressBar jProgressBar1,int valor){
        
        
        if(condicao_chassi < valor){
            cont_chassi += 1;
            jProgressBar1.setValue(cont_chassi);
            if(cont_chassi == 5){
                cont_chassi = 0;
                condicao_chassi += 1;
            }  
        }
        
        if(condicao_chassi == valor - 1)
            condicao_chassi = 0;
        
    }
    
    public static void barraCarroceria(JProgressBar jProgressBar2,int valor){
        
        
        if(condicao_carroceria < valor){
            cont_carroceria += 1;
            jProgressBar2.setValue(cont_carroceria);
            if(cont_carroceria == 12){
                cont_carroceria = 0;
                condicao_carroceria += 1;
            }  
        }
        
        if(condicao_carroceria == valor - 1)
            condicao_carroceria = 0;
        
    }
    
    public static void barraRoda(JProgressBar jProgressBar3,int valor){
        
        if(condicao_roda < valor){
            cont_roda += 1;
            jProgressBar3.setValue(cont_roda);
            if(cont_roda == 3){
                cont_roda = 0;
                condicao_roda += 1;
            }  
        }
        
        if(condicao_roda == valor - 1)
            condicao_roda = 0;
    }
    
    public static void barraMotor(JProgressBar jProgressBar4,int valor){
              
        if(condicao_motor < valor){
            cont_motor += 1;
            jProgressBar4.setValue(cont_motor);
            if(cont_motor == 5){
                cont_motor = 0;
                condicao_motor += 1;
            }  
        }
        
        if(condicao_motor == valor - 1)
            condicao_motor = 0;
    }

    /**
     * @return the produzir_chassi
     */
    public static boolean isProduzir_chassi() {
        return produzir_chassi;
    }

    /**
     * @param aProduzir_chassi the produzir_chassi to set
     */
    public static void setProduzir_chassi(boolean aProduzir_chassi) {
        produzir_chassi = aProduzir_chassi;
    }

    /**
     * @return the produzir_carroceria
     */
    public static boolean isProduzir_carroceria() {
        return produzir_carroceria;
    }

    /**
     * @param aProduzir_carroceria the produzir_carroceria to set
     */
    public static void setProduzir_carroceria(boolean aProduzir_carroceria) {
        produzir_carroceria = aProduzir_carroceria;
    }

    /**
     * @return the produzir_roda
     */
    public static boolean isProduzir_roda() {
        return produzir_roda;
    }

    /**
     * @param aProduzir_roda the produzir_roda to set
     */
    public static void setProduzir_roda(boolean aProduzir_roda) {
        produzir_roda = aProduzir_roda;
    }

    /**
     * @return the produzir_motor
     */
    public static boolean isProduzir_motor() {
        return produzir_motor;
    }

    /**
     * @param aProduzir_motor the produzir_motor to set
     */
    public static void setProduzir_motor(boolean aProduzir_motor) {
        produzir_motor = aProduzir_motor;
    }
    
    /**
     * @return the interromper
     */
    public static boolean isInterromper() {
        return interromper;
    }

    /**
     * @param aInterromper the interromper to set
     */
    public static void setInterromper(boolean aInterromper) {
        interromper = aInterromper;
    }
}
