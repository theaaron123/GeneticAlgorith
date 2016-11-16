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
        int populationFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            populationFitness += calculateIndividualRuleFitness(population.getIndividual(i));
        }
        return populationFitness / population.size();
    }

    static int calculateIndividualRuleFitness(Individual individual) {
        FileInputParser fileInput = new FileInputParser();
        fileInput.parseInput("data2.txt");
        int individualFitness = 0;

        Rules rule = new Rules(individual);
        for (int x = 0; x < fileInput.condition.size(); x++) {
        for (int j = 0; j < rule.rules.length; j++) {
            if (Arrays.equals(rule.rules[j], fileInput.condition.get(x)) | wildCardCheck(rule.rules[j], fileInput.condition.get(x))) {
                    if (rule.answers[j][0] == fileInput.answers.get(x)[0]) {
                        if (rule.answers[j][0] == 2) {
                            System.out.println("ANSWER = 2!");
                        }
                        individualFitness++;
                    }
                break;
                }
            }
        }
        return individualFitness;
    }

    private static boolean wildCardCheck(byte[] rule, byte[] dataRule) {
        int matches = 0;
        for (int i = 0; i < rule.length; i++) {
            for (int j = 0; j < dataRule.length; j++) {
                if (rule[i] == dataRule[j] || rule[i] == 2) {
                    matches++;
                } else {
                    matches = 0;
                    break;
                }
            }
            if (matches == rule.length) {
                return true;
            }
        }
        return false;
    }
}
