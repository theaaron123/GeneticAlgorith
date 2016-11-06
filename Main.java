public class Main {

    public static void main(String[] args) {

        Population population = new Population(50);

        int iterations = 50;
        int currenIteration = 0;

        for (int i = 0; i < iterations; i++) {
            currenIteration++;
            System.out.println("Iterations: " + currenIteration + " Fittest: " + population.getFittest().getFitness() + " Mean: " + population.getPopulationFitness());
            population = GeneticAlgorithm.evolvePopulation(population);
        }
    }
}
