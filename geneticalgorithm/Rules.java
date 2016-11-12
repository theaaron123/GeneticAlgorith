/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

/**
 * @author a29-baker
 */
public class Rules {


    private static final int RULE_LENGTH = 5;
    private static final int ANSWER_LENGTH = 1;

    Individual individual = new Individual();
    byte[][] rules = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];
    byte[][] answers = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];

    public Rules(Individual individual) {
        this.individual = individual;
        splitGenes();
    }

    public byte[][] getRules() {
        return rules;
    }

    public byte[][] getAnswers() {
        return answers;
    }

    private void splitGenes() {
        for (int i = 0; i < individual.size() / (RULE_LENGTH + ANSWER_LENGTH); i++) {
            byte[] rule = new byte[RULE_LENGTH];
            for (int j = 0; j < (RULE_LENGTH + ANSWER_LENGTH); j++) {
                if (j < (RULE_LENGTH - ANSWER_LENGTH)) {
                    rule[j] = individual.getGene(j);

                } else {
                    byte[] answer = new byte[ANSWER_LENGTH];
                    answer[0] = individual.getGene(j);
                    answers[i] = answer;
                }
            }
            rules[i] = rule;
        }
    }
}
