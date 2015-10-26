
package StringFinder;
//
//--------------------------------------------------------------------------------------------------------------------
// Classe....: População
// Objetivo..: Representa toda a população de indivíduos do algoritmo genético, com determinado número especificado
//             de indivíduos participantes.                                                
//--------------------------------------------------------------------------------------------------------------------
//
public class Populacao {
   
    private Individuo[] populacao;
    private int         tamanhoPopulacao;

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: Populacao (construtor da classe)
    // Objetivo..: Cria uma populacao com o tamanho especificado em paramTamanhoPopulacao e cujos individuos possuem 
    //             seus  genes aleatoriamente definidos.                                  
    //--------------------------------------------------------------------------------------------------------------------
    //
    public Populacao(int numGenes, int paramTamanhoPopulacao) {
        
        this.tamanhoPopulacao  = paramTamanhoPopulacao;
        this.populacao         = new Individuo[paramTamanhoPopulacao];
        for (int i = 0; i < this.populacao.length; i++) {
            this.populacao[i] = new Individuo(numGenes);
        }
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: Populacao (construtor da classe)
    // Objetivo..: Cria uma populacao com o tamanho especificado em paramTamanhoPopulacao e cujos individuos possuem 
    //             seus  genes não definidos (não inicializados, null)                                  
    //--------------------------------------------------------------------------------------------------------------------
    //
    public Populacao(int paramTamanhoPopulacao) {
        this.tamanhoPopulacao = paramTamanhoPopulacao;
        this.populacao        = new Individuo[paramTamanhoPopulacao];
        for (int i = 0; i < this.populacao.length; i++) {
            this.populacao[i] = null;
        }
    }
    //
    // Gets and Sets...
    //
    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    public Individuo getIndividuo(int pos) {
        return populacao[pos];
    }
    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: setIndividuo
    // Objetivo..: Insere o paramIndividuo recebido como parametro numa determinada posição na população.                                            
    //--------------------------------------------------------------------------------------------------------------------
    //
    public void setIndividuo(Individuo paramIndividuo, int paramPosicaoPopulacao) {
        this.populacao[paramPosicaoPopulacao] = paramIndividuo;
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: setIndividuo
    // Objetivo..: Insere o paramIndividuo recebido como parametro na próxima posição disponível na população.                                            
    //--------------------------------------------------------------------------------------------------------------------
    //
    public void setIndividuo(Individuo paramIndividuo) {
        for (int i = 0; i < this.populacao.length; i++) {
            if (this.populacao[i] == null) {
                this.populacao[i] = paramIndividuo;
                return;
            }
        }
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: solucaoAtingida
    // Objetivo..: Verifica se algum indivíduo da população atual já é a solução para o problema. Se for, retorna TRUE
    //             Do contrário, retorna FALSE.
    //--------------------------------------------------------------------------------------------------------------------
    //
    public boolean solucaoAtingida(String solucao) {
        Individuo i = null;
        for (int j = 0; j < this.populacao.length; j++) {
            if (this.populacao[j].getGenes().equals(solucao)) {
                i  = this.populacao[j];
                break;
            }
        }
        if (i == null) {
           return false;
        }
        else {
           return true;
        }
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: ordenaPopulacao  
    // Objetivo..: Ordena, em ordem decrescente, a populacao de acordo com o valor da função de aptidão de cada indi-
    //             víduo. Portanto, os indivíduos "mais aptos" estarão nas posições 0, 1, 2, ... até que ocorra o 
    //            "desempate" no valor da função de aptidão.
    //
    // Observação: Utiliza o "método da bolha" (bubble sort) para realizar a ordenação da população.
    //--------------------------------------------------------------------------------------------------------------------
    //
    public void ordenaPopulacao() {       
        boolean trocou = true;
        while (trocou) {
            trocou = false;
            for (int i = 0; i < this.populacao.length - 1; i++) {
                if (this.populacao[i].getAptidao() < this.populacao[i + 1].getAptidao()) {
                    Individuo temp = this.populacao[i];
                    this.populacao[i] = this.populacao[i + 1];
                    this.populacao[i + 1] = temp;
                    trocou = true;
                }
            }
        }
    }

    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: calculaTamanhoPopulacao 
    // Objetivo..: Calcula o tamanho, ou seja, o número de indivíduos presentes numa populacao.                        
    //--------------------------------------------------------------------------------------------------------------------
    //
    public int calculaTamanhoPopulacao() {
        int num = 0;
        for (int i = 0; i < this.populacao.length; i++) {
            if (this.populacao[i] != null) {
                num++;
            }
        }
        return num;
    }
   
}
