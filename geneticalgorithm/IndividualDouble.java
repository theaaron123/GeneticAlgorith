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
                if ((i + 1) % 7 == 0) {
                    double gene = Math.round(Math.random());
                    genes[i][j] = gene;
                } else {
                    double gene = Math.random();
                    genes[i][j] = gene;
                }
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

    public void setGene(int index, double[] gene) {
        this.genes[index] = gene;
    }

    public int size() {
        return genes.length;
    }

    public int getFitness() {
        return FitnessCalculator.calculateIndividualFitnessDouble(this);
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

}