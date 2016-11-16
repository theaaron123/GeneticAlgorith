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

    private static final int RULE_LENGTH = 6;
    private static final int ANSWER_LENGTH = 1;

    Individual individual = new Individual();
    byte[][] rules = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];
    byte[][] answers = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];

    public Rules(Individual individual) {
        this.individual = individual;
        rules = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];
        answers = new byte[individual.size() / (RULE_LENGTH + ANSWER_LENGTH)][];
        splitGenes();
    }

    public byte[][] getRules() {
        return rules;
    }

    public byte[][] getAnswers() {
        return answers;
    }

    private void splitGenes() {
        int index = 0;
        for (int i = 0; i < individual.size() / (RULE_LENGTH + ANSWER_LENGTH); i++) {
            byte[] rule = new byte[RULE_LENGTH];
            for (int j = 0; j < (RULE_LENGTH + ANSWER_LENGTH); j++) {
                if (j <= (RULE_LENGTH - ANSWER_LENGTH)) {
                    rule[j] = individual.getGene(index);
                    index++;

                } else {
                    byte[] answer = new byte[ANSWER_LENGTH];
                    answer[0] = individual.getGene(index);
                    index++;
                    answers[i] = answer;
                }
            }
            rules[i] = rule;
        }
    }
}
