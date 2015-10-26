
package StringFinder;

import java.util.Random;
//
//--------------------------------------------------------------------------------------------------------------------
// Classe....: AlgoritmoGenetico
// Objetivo..: Representa "todo" o algoritmo genético (AG) para a resolução do problema.                         
//--------------------------------------------------------------------------------------------------------------------
//
public class AlgoritmoGenetico {
   
    private static String solucao;
    private static double taxaDeCrossover;
    private static double taxaDeMutacao;
    private static String caracteres;
    //
    //--------------------------------------------------------------------------------------------------------------------
    // Metodo....: novaGeracao                               
    // Objetivo..: Calcula o valor da função de aptidão (fitness function) do indivíduo a partir da configuração atual
    //             de seus cromossomos.    
    //             A função de aptidão mede o número de caracteres coincidentes (iguais) entre o indivíduo e a solução 
    //             do problema, ou seja, a cadeia de caracteres que foi fornecida pelo usuário.
    //--------------------------------------------------------------------------------------------------------------------
    //  
    public static Populacao novaGeracao(Populacao populacao, boolean elitismo) {
        Random r = new Random();
        
        //
        // A "nova" população será, sempre, do mesmo tamanho da "antiga" população, ou que é chamado de 
        // "Steady Generation"
        //
        Populacao novaPopulacao = new Populacao(populacao.getTamanhoPopulacao());

        //
        // Se o ELITISMO tiver sendo utilizando, então o indivíduo com MAIOR APTIDÃO será mantido na "nova" geração...
        // Lembre-se: após ordenação da população, o indivíduo com maior aptidão encontra-se na posição "0" (zero) 
        //            da população (é claro que pode haver "empate" e haver outros nas posições 1, 2, ...)
        //
        if (elitismo) {
            novaPopulacao.setIndividuo(populacao.getIndividuo(0));
        }
        //
        // Insere novos indivíduos na "nova" população, até atingir o tamanho máximo permitido
        //
        while (novaPopulacao.calculaTamanhoPopulacao() < novaPopulacao.getTamanhoPopulacao()) {
            //
            // Seleciona os dois pais (progenitores) por torneio...
            //   cria os dois filhos (descendentes), verificando se há, ou não, "recombinação" (crossover)
           //
            Individuo[] pais   = selecaoTorneio(populacao);
            Individuo[] filhos = new Individuo[2];
            //
            // Faz um "sorteio" e verifica a taxa de crossover foi atingida... 
            //    se sim, realiza o crossover, 
            //    se não, mantém os pais selecionados para a próxima geração (
            //
            if (r.nextDouble() <= taxaDeCrossover) {
                filhos    = crossover(pais[1], pais[0]);
            } 
            else {
                filhos[0] = new Individuo(pais[0].getGenes());
                filhos[1] = new Individuo(pais[1].getGenes());
            }
            //  
            // Adiciona os filhos na "nova" geração...
            //
            novaPopulacao.setIndividuo(filhos[0]);
            novaPopulacao.setIndividuo(filhos[1]);
        }
        //
        // Ordena a nova população, fazendo com o os indivíduos de MAIOR APTIDÃO ocupem as posições 
        // iniciais (0, 1, 2, ...) até que haja desempate.
        //
        novaPopulacao.ordenaPopulacao();
        return (novaPopulacao);
    }

    public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {
        Random r = new Random();

        //sorteia o ponto de corte
        int pontoCorte1 = r.nextInt((individuo1.getGenes().length()/2) -2) + 1;
        int pontoCorte2 = r.nextInt((individuo1.getGenes().length()/2) -2) + individuo1.getGenes().length()/2;

        Individuo[] filhos = new Individuo[2];

        //pega os genes dos pais
        String genePai1 = individuo1.getGenes();
        String genePai2 = individuo2.getGenes();

        String geneFilho1;
        String geneFilho2;

        //realiza o corte, 
        geneFilho1 = genePai1.substring(0, pontoCorte1);
        geneFilho1 += genePai2.substring(pontoCorte1, pontoCorte2);
        geneFilho1 += genePai1.substring(pontoCorte2, genePai1.length());
        
        geneFilho2 = genePai2.substring(0, pontoCorte1);
        geneFilho2 += genePai1.substring(pontoCorte1, pontoCorte2);
        geneFilho2 += genePai2.substring(pontoCorte2, genePai2.length());

        //cria o novo indivíduo com os genes dos pais
        filhos[0] = new Individuo(geneFilho1);
        filhos[1] = new Individuo(geneFilho2);

        return filhos;
    }

    public static Individuo[] selecaoTorneio(Populacao populacao) {
        Random r = new Random();
        Populacao populacaoIntermediaria = new Populacao(3);

        //seleciona 3 indivíduos aleatóriamente na população
        populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamanhoPopulacao())));
        populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamanhoPopulacao())));
        populacaoIntermediaria.setIndividuo(populacao.getIndividuo(r.nextInt(populacao.getTamanhoPopulacao())));

        //ordena a população
        populacaoIntermediaria.ordenaPopulacao();

        Individuo[] pais = new Individuo[2];

        //seleciona os 2 melhores deste população
        pais[0] = populacaoIntermediaria.getIndividuo(0);
        pais[1] = populacaoIntermediaria.getIndividuo(1);

        return pais;
    }

    public static String getSolucao() {
        return solucao;
    }

    public static void setSolucao(String solucao) {
        AlgoritmoGenetico.solucao = solucao;
    }

    public static double getTaxaDeCrossover() {
        return taxaDeCrossover;
    }

    public static void setTaxaDeCrossover(double taxaDeCrossover) {
        AlgoritmoGenetico.taxaDeCrossover = taxaDeCrossover;
    }

    public static double getTaxaDeMutacao() {
        return taxaDeMutacao;
    }

    public static void setTaxaDeMutacao(double taxaDeMutacao) {
        AlgoritmoGenetico.taxaDeMutacao = taxaDeMutacao;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        AlgoritmoGenetico.caracteres = caracteres;
    }

   
}
