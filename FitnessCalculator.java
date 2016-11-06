/**
 * Created by aaron on 05/11/16.
 */
public class FitnessCalculator {

    static int calculateIndividualFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size(); i++) {
            if (individual.getGene(i) == 1) {
                fitness++;
            }
        }
        return fitness;
    }

    static int calculatePopulationFitness(Population population) {
        int populationFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            populationFitness += population.getIndividual(i).getFitness();
        }
        return populationFitness / population.size();
    }
}
