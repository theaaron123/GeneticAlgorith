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
            populationFitness += calculateIndividualRuleFitness(population.getIndividual(i));
        }
        return populationFitness / population.size();
    }

    static int calculateIndividualRuleFitness(Individual individual) {
        FileInputParser fileInput = new FileInputParser();
        fileInput.parseInput("data1.txt");
        int individualFitness = 0;

        Rules rule = new Rules(individual);
        for (int j = 0; j < rule.rules.length; j++) {
            for (int x = 0; x < fileInput.condition.size(); x++) {
                if (Arrays.equals(rule.rules[j], fileInput.condition.get(x))) {
                    if (rule.answers[j][0] == fileInput.answers.get(x)[0]) {
                        individualFitness++;
                        break;
                    }
                }
            }
        }
        return individualFitness;
    }
}