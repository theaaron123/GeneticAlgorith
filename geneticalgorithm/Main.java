package geneticalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        PopulationDouble population = new PopulationDouble(100);

        int iterations = 100;
        int currenIteration = 0;

        for (int i = 0; i < iterations; i++) {
            currenIteration++;
            System.out.println("Iterations: " + currenIteration + " Fittest: " + population.getFittest().getFitness() + " Mean: " + population.getPopulationFitness());
            try (PrintWriter out = new PrintWriter(new FileOutputStream(new File("results.csv"), true))) {
                out.println(currenIteration + "," + (population.getFittest().getFitness()) + "," + population.getPopulationFitness() + ",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            population = GeneticAlgorithm.evolvePopulation(population);
        }

        int testFitness = FitnessCalculator.calculateRuleFitnessDouble(population);

        System.out.println("TEST DATA = " + testFitness);
    }
}
