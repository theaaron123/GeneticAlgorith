package geneticalgorithm;

/**
 * Created by aaron on 20/11/16.
 */

public class PopulationDouble {

    IndividualDouble[] individuals;

    public PopulationDouble(int populationSize) {
        individuals = new IndividualDouble[populationSize];
        for (int i = 0; i < individuals.length; i++) {
            IndividualDouble newIndividual = new IndividualDouble();
            newIndividual.initialiseIndividual();
            saveIndividual(i, newIndividual);
        }
    }

    public IndividualDouble getIndividual(int index) {
        return individuals[index];
    }

    public int getPopulationFitness() {
        return FitnessCalculator.calculateRuleFitnessDouble(this);
    }

    public void evalFitness() {
        for (int i = 0; i < size(); i++) {
            individuals[i].fitness = individuals[i].evalFitness();
        }
    }

    public IndividualDouble getFittest() {
        IndividualDouble fittest = new IndividualDouble();
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
        IndividualDouble individual = individuals[0];
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

    public void saveIndividual(int index, IndividualDouble individual) {

        individuals[index] = new IndividualDouble(individual);
    }
}
