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

    static int calculateIndividualFitness(IndividualDouble individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size(); i++) {
            if (individual.getGene(i) == 1.0) {
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
                if (Arrays.equals(rule.rules[j], fileInput.condition.get(x)) || wildCardCheck(rule.rules[j], fileInput.condition.get(x))) {
                    if (rule.answers[j][0] == fileInput.answers.get(x)[0]) {
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
        for (int j = 0; j < dataRule.length; j++) {
            if (rule[j] == dataRule[j] || rule[j] == 2) {
                matches++;
            } else {
                matches = 0;
                break;
            }
            if (matches == rule.length) {
                return true;
            }
        }
        return false;
    }

    private static boolean wildCardCheck(double[] rule, double[] dataRule) {
        int matches = 0;
        for (int j = 0; j < dataRule.length; j++) {
            if (dataRule[j] >= rule[0] && dataRule[j] <= rule[1] || rule[j] == 2) {
                matches++;
            } else {
                matches = 0;
                break;
            }
            if (matches == rule.length) {
                return true;
            }
        }
        return false;
    }

    static int calculateIndividualFitnessDouble(IndividualDouble individual) {
        FileInputParser fileInputParser = new FileInputParser();
        fileInputParser.parseInputDouble("data3.txt");
        int individualFitness = 0;

        Rules rule = new Rules(individual);
        for (int x = 0; x < fileInputParser.answersDouble.size(); x++) {
            for (int j = 0; j < rule.rules.length; j++) {
                for (int k = 0; k < fileInputParser.conditionDouble.get(x).length; k++) {
                    for (int i = 0; i < rule.rulesDouble[j].length - 1; i++) {
                        if (rule.rulesDouble[j][i] < rule.rulesDouble[j][i + 1]) {
                            if (fileInputParser.conditionDouble.get(x)[k] >= rule.rulesDouble[j][i] && fileInputParser.conditionDouble.get(x)[k] <= rule.rulesDouble[j][i + 1]) { // || wildCardCheck(rule.rulesDouble[j][i], fileInputParser.conditionDouble.get(x))) {
                                if (rule.answersDouble[j][0] == fileInputParser.answersDouble.get(x)[0] && k == fileInputParser.conditionDouble.get(x).length - 1 && i == fileInputParser.conditionDouble.get(x).length) {
                                    individualFitness++;
                                }
                                break;
                            }
                        } else if (fileInputParser.conditionDouble.get(x)[k] >= rule.rulesDouble[j][i + 1] && fileInputParser.conditionDouble.get(x)[k] <= rule.rulesDouble[j][i]) { // || wildCardCheck(rule.rulesDouble[j][i], fileInputParser.conditionDouble.get(x))) {
                            if (rule.answersDouble[j][0] == fileInputParser.answersDouble.get(x)[0] && k == fileInputParser.conditionDouble.get(x).length - 1 && i == fileInputParser.conditionDouble.get(x).length) {
                                individualFitness++;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return individualFitness;
    }

    static int calculateRuleFitnessDouble(PopulationDouble population) {
        int populationFitness = 0;
        for (int i = 0; i < population.size(); i++) {
            populationFitness += calculateIndividualFitnessDouble(population.getIndividual(i));
        }
        return populationFitness / population.size();
    }
}
