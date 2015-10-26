//
// by Paulo Collares
//
package StringFinder;

public class StringFinderApplication {

   /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {

        //
        // Define... o conjunto de caracteres válido para uma cadeia de caracteres(string)...
        //
        AlgoritmoGenetico.setCaracteres("!,.:;?áÁãÃâÂõÕôÔóÓéêíÉÊQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuúiopasdfghjklçzxcvbnm1234567890 ");
        //
        // Define... a solução (string) desejada...
        //
        AlgoritmoGenetico.setSolucao("Vou COLOCAR uma cadeia que é mais complicada pois contém números 01293943948");
        //
        // Taxa de recombinação (ou crossover)... 0.00 (0%) a 1.00 (100%)
        //
        AlgoritmoGenetico.setTaxaDeCrossover(0.01);
        //
        // Taxa de mutação (ou mutation)... 0.00 (0%) a 1.00 (100%)
        //
        AlgoritmoGenetico.setTaxaDeMutacao(0.01);
        //
        // Vai haver ELITISMO? (ou seja: o indivíduo com maior APTIDÃO será preservado para a próxima geração? 
        //
        boolean eltismo = true;
        //
        // Define... o tamanho da população, o número máximo de gerações... 
        //
        int tamPop         = 10000;
        int numMaxGeracoes = 20000;
        //
        // A partir do tamanho da cadeia de caracteres SOLUÇÃO, define qual é o número de genes 
        // do indivíduo, ou seja, do cromossomo
        //
        int numGenes = AlgoritmoGenetico.getSolucao().length();

        //
        // Cria, de forma aleatória, a primeira população (chamada de geração "0"), 
        // definindo que no momento não há solução para o problema...
        // e começam as "gerações" de indivíduos
        //
        Populacao populacao = new Populacao(numGenes, tamPop);
        boolean temSolucao  = false;
        int     geracao     = 0;

        System.out.println("Iniciando... Aptidão da solução: "+ AlgoritmoGenetico.getSolucao().length());
        
        //
        // O critério de parada é o número de gerações... quando atingir o número máximo permitido, o algoritmo
        // genético irá parar.
        //
        while (!temSolucao && geracao < numMaxGeracoes) {
            geracao++;

            //
            // Cria uma "nova geração" de indivíduos...
            //
            populacao = AlgoritmoGenetico.novaGeracao(populacao, eltismo);

            System.out.println("Geracao " + geracao + " | Aptidao: " + populacao.getIndividuo(0).getAptidao() + " | Melhor: " + populacao.getIndividuo(0).getGenes());
            
            //
            // Se algum dos indivíduos da geração atual é a solução para o problema, então o 
            // algoritmo genético para.
            //
            temSolucao = populacao.solucaoAtingida(AlgoritmoGenetico.getSolucao());
        }

        if (geracao == numMaxGeracoes) {
            System.out.println("Numero Maximo de Geracoes | " + populacao.getIndividuo(0).getGenes() + " " + populacao.getIndividuo(0).getAptidao());
        }

        if (temSolucao) {
            System.out.println("Encontrado resultado na geracao " + geracao + " | " + populacao.getIndividuo(0).getGenes() + " (Aptidão: " + populacao.getIndividuo(0).getAptidao() + ")");
        }
    }
   
}
