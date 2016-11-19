package geneticalgorithm;

/**
 * Created by aaron on 05/11/16.
 */
public class IndividualDouble {

    static int geneLength = 35;
    private double[][] genes = new double[geneLength][2];
    private int fitness = 0;

    IndividualDouble() {
    }

    IndividualDouble(IndividualDouble ind) {
        this.fitness = ind.fitness;
        this.genes = ind.genes;
    }

    public void initialiseIndividual() {
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < genes[i].length; j++) {
                double gene = Math.random();
                genes[i][j] = gene;
            }
        }
    }

    public double[] getGene(int index) {
        return genes[index];
    }

    public double[][] getGenes() {
        return this.genes;
    }


    public void setGenes(double[][] genes) {
        this.genes = genes;
    }

    public int size() {
        return genes.length;
    }

    public int getFitness() {
        return 0;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

}