package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */
public class GeneticAlgorithm {

    private static final double CROSSOVER_RATE = 0.5;
    private static final double MUTATION_RATE = 0.015;
    private static final double WILDCARD_RATE = 0.03;
    private static final int TOURNAMENT_SIZE = 2;
    private static final boolean ELITISM = true;


    public static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.size());

        for (int i = 0; i < population.size(); i++) {
            Individual individualOne = tournamentSelection(population);
            Individual individualTwo = tournamentSelection(population);
            Individual newIndividual = crossover(individualOne, individualTwo);
            newPopulation.saveIndividual(i, newIndividual);
        }
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        if (ELITISM) {
            Individual elite = new Individual(population.getFittest());
            int worstIndex = newPopulation.getLeastFittestIndex();
            newPopulation.saveIndividual(worstIndex, elite);
        }
        return newPopulation;
    }

    private static Individual crossover(Individual individualOne, Individual individualTwo) {
        Individual child = new Individual();
        for (int i = 0; i < individualOne.size(); i++) {
            if (Math.random() <= CROSSOVER_RATE) {
                child.setGene(i, individualOne.getGene(i));
            } else {
                child.setGene(i, individualTwo.getGene(i));
            }
        }
        return child;
    }

    private static void mutate(Individual individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                if (Math.random() <= WILDCARD_RATE) {
                    byte gene = 2;
                    individual.setGene(i, gene);
                } else {
                    if (individual.getGene(i) == 1) {
                        byte gene = 0;
                        individual.setGene(i, gene);
                    } else {
                        byte gene = 1;
                        individual.setGene(i, gene);
                    }
                }
            }
        }
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }

        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
