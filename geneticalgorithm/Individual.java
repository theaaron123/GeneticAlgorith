package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */
public class Individual {

    static int geneLength = 70;
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
            if (Math.random() >= 0.96) {
                gene = 2;
            }
            genes[i] = gene;
        }
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public byte[] getGenes() {
        return this.genes;
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public void setGenes(byte[] genes) {
        this.genes = genes;
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
