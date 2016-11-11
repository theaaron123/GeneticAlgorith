package geneticalgorithm;

import java.util.Arrays;

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

    static int calculateRuleFitness(Population population) {
        FileInputParser fileInput = new FileInputParser();
        fileInput.parseInput("data1.txt");
        int populationFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            Rules rule = new Rules(population.individuals[i]);
            for (int j = 0; j < rule.rules.length; j++) {
                // for (int x = 0; x < rule.rules[j].length; x++) {
                for (int x = 0; x < fileInput.condition.size(); x++) {
                    if (Arrays.equals(rule.rules[j], fileInput.condition.get(x))) {
                        if (rule.answers[j][0] == fileInput.answers.get(j)[0]) {
                            populationFitness++;
                            break;
                        }
                    }
                    // }
                }
            }
        }
        return populationFitness / population.size();
    }
}
