package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */
public class Individual {

    static int geneLength = 60;
    private byte[] genes = new byte[geneLength];
    private int fitness = 0;

    Individual() {
    }

    Individual(Individual ind) {
        this.fitness = ind.fitness;
        this.genes = ind.genes;
    }

    public void initialiseIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public int size() {
        return genes.length;
    }

    public int getFitness() {
        return FitnessCalculator.calculateIndividualRuleFitness(this);
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
