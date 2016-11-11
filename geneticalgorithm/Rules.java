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

    Individual individual = new Individual();
    byte[][] rules = new byte[individual.size() / 6][];
    byte[][] answers = new byte[individual.size() / 6][];

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
        for (int i = 0; i < individual.size() / 6; i++) {
            byte[] rule = new byte[5];
            for (int j = 0; j < 6; j++) {
                if (j < 4) {
                    rule[j] = individual.getGene(j);

                } else {
                    byte[] answer = new byte[1];
                    answer[0] = individual.getGene(j);
                    answers[i] = answer;
                }
            }
            rules[i] = rule;
        }
    }
}
