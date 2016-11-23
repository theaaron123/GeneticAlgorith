package geneticalgorithm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by aaron on 05/11/16.
 */
public class GeneticAlgorithm {

    private static final double CROSSOVER_RATE = 0.85;
    private static final double MUTATION_RATE = 0.035;
    private static final int TOURNAMENT_SIZE = 2;
    private static final boolean ELITISM = true;

    public static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.size());

        for (int i = 0; i < population.size(); i++) {
            Individual individualOne = tournamentSelection(population);
            Individual individualTwo = tournamentSelection(population);
            Individual newIndividual = crossoverSinglePoint(individualOne, individualTwo);
            newPopulation.saveIndividual(i, newIndividual);
        }
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        if (ELITISM) {
            Individual elite = new Individual(population.getFittest());
            int worstIndex = newPopulation.getLeastFittestIndex();
            newPopulation.saveIndividual(worstIndex, elite);
        }
        return newPopulation;
    }

    public static PopulationDouble evolvePopulation(PopulationDouble population) {
        PopulationDouble newPopulation = new PopulationDouble(population.size());
        population.evalFitness();

        for (int i = 0; i < population.size(); i++) {
            IndividualDouble individualOne = tournamentSelection(population);
            IndividualDouble individualTwo = tournamentSelection(population);
            IndividualDouble newIndividual = crossoverSinglePoint(individualOne, individualTwo);
            newPopulation.saveIndividual(i, newIndividual);
        }
        for (int i = 0; i < newPopulation.size(); i++) {
            boxMutate(newPopulation.getIndividual(i));
        }

        if (ELITISM) {
            IndividualDouble elite = new IndividualDouble(population.getFittest());
            int worstIndex = newPopulation.getLeastFittestIndex();
            newPopulation.saveIndividual(worstIndex, elite);
        }
        return newPopulation;
    }

    private static Individual crossover(Individual individualOne, Individual individualTwo) {
        Individual child = new Individual();
        for (int i = 0; i < individualOne.size(); i++) {
            if (Math.random() <= CROSSOVER_RATE) {
                child.setGene(i, individualOne.getGene(i));
            } else {
                child.setGene(i, individualTwo.getGene(i));
            }
        }
        return child;
    }

    private static Individual crossoverSinglePoint(Individual individualOne, Individual individualTwo) {
        Individual child = new Individual();
        int crossOverPoint = (int) (Math.random() * individualOne.size());
        for (int i = 0; i < individualOne.size(); i++) {
            if (i <= crossOverPoint) {
                child.setGene(i, individualOne.getGene(i));
            } else {
                child.setGene(i, individualTwo.getGene(i));
            }
        }
        return child;
    }

    private static IndividualDouble crossoverSinglePoint(IndividualDouble individualOne, IndividualDouble individualTwo) {
        IndividualDouble child = new IndividualDouble();
        int crossOverPoint = (int) (Math.random() * individualOne.size());
        for (int i = 0; i < individualOne.size(); i++) {
            if (i <= crossOverPoint) {
                child.setGene(i, individualOne.getGene(i));
            } else {
                child.setGene(i, individualTwo.getGene(i));
            }
        }
        return child;
    }

    public static IndividualDouble crossoverBLXAlpha(IndividualDouble individualOne, IndividualDouble individualTwo) {
        IndividualDouble child = new IndividualDouble();
        int crossOverPoint = (int) (Math.random() * individualOne.size());
        if (Math.random() > 0) {
            for (int i = 0; i < individualOne.size(); i++) {
                if (i <= crossOverPoint) {
                    double gene1 = individualOne.getGene(i);
                    double gene2 = individualTwo.getGene(i);
                    Random r = new Random();
                    double childGene = 0;
                    if (gene1 < gene2) {
                        childGene = gene1 + (gene2 - gene1) * r.nextDouble();
                    } else {
                        childGene = gene2 + (gene1 - gene2) * r.nextDouble();
                    }
                    child.setGene(i, childGene);
                }
            }
        } else {
            return crossoverSinglePoint(individualOne, individualTwo);
        }
        return child;
    }

    private static void mutateSimple(Individual individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                if (individual.getGene(i) == 1) {
                    byte gene = 0;
                    individual.setGene(i, gene);
                } else {
                    byte gene = 1;
                    individual.setGene(i, gene);
                }
            }
        }
    }

    private static void mutate(Individual individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                if (i % 7 != 0) {
                    switch (individual.getGene(i)) {
                        case 1:
                            if (Math.random() >= 0.5) {
                                byte gene = 2;
                                individual.setGene(i, gene);
                            } else {
                                byte gene = 0;
                                individual.setGene(i, gene);
                            }
                            break;
                        case 0:
                            if (Math.random() >= 0.5) {
                                byte gene = 2;
                                individual.setGene(i, gene);
                            } else {
                                byte gene = 1;
                                individual.setGene(i, gene);
                            }
                            break;
                        case 2:
                            if (Math.random() >= 0.5) {
                                byte gene = 1;
                                individual.setGene(i, gene);
                            } else {
                                byte gene = 0;
                                individual.setGene(i, gene);
                            }
                            break;
                        default:
                            break;
                    }
                } else if (individual.getGene(i) == 1) {
                    byte gene = 0;
                    individual.setGene(i, gene);
                } else {
                    byte gene = 1;
                    individual.setGene(i, gene);
                }
            }
        }
    }

    private static void boxMutate(IndividualDouble individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                double gene1 = Math.random();
                double gene2 = Math.random();
                individual.setGene(i, gene1);
                individual.setGene(i, gene2);
            }
        }
    }

    private static void mutate(IndividualDouble individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                if ((i + 1) % 7 != 0) {
                    if (Math.random() >= 0.5) {
                        double d = individual.getGene(i) + Math.random() / 10;
                        if (Math.random() >= 0.5) {
                            if (d <= 1.0) {
                                double gene = d;
                                individual.setGene(i, gene);
                            } else {
                                double gene = 1;
                                individual.setGene(i, gene);
                            }
                        } else {
                            double gene = 2;
                            individual.setGene(i, gene);
                        }
                    } else {
                        double d = individual.getGene(i) - Math.random() / 10;
                        if (Math.random() >= 0.5) {
                            if (d < 0.0) {
                                double gene = d;
                                individual.setGene(i, gene);

                            } else {
                                double gene = 0;
                                individual.setGene(i, gene);
                            }
                        } else {
                            double gene = 2;
                            individual.setGene(i, gene);
                        }
                    }
                } else if (individual.getGene(i) == 0) {
                    double gene = 1;
                    individual.setGene(i, gene);

                } else {
                    double gene = 0;
                    individual.setGene(i, gene);

                }
            }
        }
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    private static IndividualDouble tournamentSelection(PopulationDouble pop) {
        PopulationDouble tournament = new PopulationDouble(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        IndividualDouble fittest = tournament.getFittest();
        return fittest;
    }
}
