package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */
public class IndividualDouble {

    static int geneLength = 130;
    private double[] genes = new double[geneLength];
    protected int fitness = 0;

    IndividualDouble() {
    }

    IndividualDouble(IndividualDouble ind) {
        this.fitness = ind.fitness;
        this.genes = ind.genes;
    }

    public void initialiseIndividual() {
        for (int i = 0; i < size(); i++) {
            if ((i + 1) % 13 == 0) {
                if (Math.random() > 0.96) {
                    double gene = 2;
                    genes[i] = gene;
                }
                double gene = Math.round(Math.random());
                genes[i] = gene;
            } else {
                double gene = Math.random();
                genes[i] = gene;
            }
        }
    }

    public double getGene(int index) {
        return genes[index];
    }

    public double[] getGenes() {
        return this.genes;
    }


    public void setGenes(double[] genes) {
        this.genes = genes;
    }

    public void setGene(int index, double gene) {
        this.genes[index] = gene;
    }

    public int size() {
        return genes.length;
    }

    public int evalFitness() {
        return FitnessCalculator.calculateIndividualFitnessDouble(this);
    }

    public int getFitness() {
        return this.fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

}