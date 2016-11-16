package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */

public class Population {

    Individual[] individuals;

    public Population(int populationSize) {
        individuals = new Individual[populationSize];
        for (int i = 0; i < individuals.length; i++) {
            Individual newIndividual = new Individual();
            newIndividual.initialiseIndividual();
            saveIndividual(i, newIndividual);
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public int getPopulationFitness() {
        return FitnessCalculator.calculateRuleFitness(this);
    }

    public Individual getFittest() {
        Individual fittest = new Individual();
        fittest.initialiseIndividual();
        for (int i = 0; i < individuals.length; i++) {
            if (fittest.getFitness() < getIndividual(i).getFitness()) {
                fittest.setFitness(getIndividual(i).getFitness());
                fittest.setGenes(getIndividual(i).getGenes());
            }
        }
        return fittest;
    }

    public int getLeastFittestIndex() {
        Individual individual = individuals[0];
        int worstIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individual.getFitness() < getIndividual(i).getFitness()) {
                individual = getIndividual(i);
                worstIndex = i;
            }
        }
        return worstIndex;
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual individual) {
        individuals[index] = individual;
    }
}
