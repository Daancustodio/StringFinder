
package StringFinder;

import java.util.Random;
//
//--------------------------------------------------------------------------------------------------------------------
// Classe....: Individuo
// Objetivo..: Representa um único indivíduo da população, ou seja, um único cromossomo - uma string de caracteres, 
//             acompanhado do valor de função de aptidão (fitness function).
//--------------------------------------------------------------------------------------------------------------------
//
public class Individuo {
    private String strCromossomo   = "";
    private int    intAptidao      = 0;
    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: Individuo (construtor da classe)
    // Objetivo..: Cria um individuo com todos os seus genes aleatoriamente definidos.                                  
    //--------------------------------------------------------------------------------------------------------------------
    //
    public Individuo(int numGenes) {
        
        strCromossomo = "";
        Random r      = new Random();
        String caracteres = AlgoritmoGenetico.getCaracteres();
        
        for (int i = 0; i < numGenes; i++) {
            strCromossomo += caracteres.charAt(r.nextInt(caracteres.length()));
        }
        calcularAptidao();        
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: Individuo (construtor da classe)
    // Objetivo..: Cria um individuo o indivíduo da população, ou seja, um único cromossomo - uma string de caracteres, 
    //             acompanhado do valor de função de aptidão (fitness function).
    //--------------------------------------------------------------------------------------------------------------------
    //
    public Individuo(String genes) {  
       
        this.strCromossomo = genes;
        
        Random r = new Random();
        //
        // Se for realizar a operação de MUTAÇÃO, então o gene é criado de forma aleatória.
        //    Do contrário, o gene é criado a partir do parâmetro recebido: genes 
        //                                         
        if (r.nextDouble() <= AlgoritmoGenetico.getTaxaDeMutacao()) {
            String caracteres = AlgoritmoGenetico.getCaracteres();
            String geneNovo   = "";
            
            int posAleatoria  = r.nextInt(genes.length());
            
            for (int i = 0; i < genes.length(); i++) {
                if (i == posAleatoria){
                    geneNovo += caracteres.charAt(r.nextInt(caracteres.length()));
                }
                else{
                    geneNovo += genes.charAt(i);
                }
                
            }
            this.strCromossomo = geneNovo;   
        }
        calcularAptidao();
    }
    //
    // Gets and Sets...
    //
    public int getAptidao() {
        return intAptidao;
    }

    public String getGenes() {
        return strCromossomo;
    }
    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: calcularAptidao                           
    // Objetivo..: Calcula o valor da função de aptidão (fitness function) do indivíduo a partir da configuração atual
    //             de seus cromossomos.    
    //             A função de aptidão mede o número de caracteres coincidentes (iguais) entre o indivíduo e a solução 
    //             do problema, ou seja, a cadeia de caracteres que foi fornecida pelo usuário.
    //--------------------------------------------------------------------------------------------------------------------
    //
    private void calcularAptidao() {
        String solucao = AlgoritmoGenetico.getSolucao();
        for (int i = 0; i < solucao.length(); i++) {
            if (solucao.charAt(i) == strCromossomo.charAt(i)) {
                intAptidao++;
            }
        }
    }
   
}
