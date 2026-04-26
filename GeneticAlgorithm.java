import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.function.BiFunction;

/*
Class implementing the genetic algorithm.
 */
public class GeneticAlgorithm {
    private static final Random rand = new Random();
    private final Chromosome[] population;
    private final BiFunction<Double, Double, Double> function;
    private final double[] evaluations;
    private final int accuracy;
    private final double crossProbability;
    private final double mutationProbability;
    private final String fileName;
    private final Tournament tournament;

    GeneticAlgorithm(int populationSize,
                     int tournamentGroupSize,
                     int numberOfEvaluations,
                     BiFunction<Double, Double, Double> function,
                     double xMax,
                     double xMin,
                     double yMax,
                     double yMin,
                     int accuracy,
                     double crossProbability,
                     double mutationProbability,
                     String fileName) {
        this.population = generatePopulation(populationSize, xMax, xMin, yMax, yMin, accuracy);
        this.function = function;
        this.evaluations = new double[numberOfEvaluations];
        this.accuracy = accuracy;
        this.crossProbability = crossProbability;
        this.mutationProbability = mutationProbability;
        this.fileName = fileName;
        this.tournament = new Tournament(population, tournamentGroupSize, function);
    }

    /*
    Generates a population according to the provided parameters.
     */
    private static Chromosome[] generatePopulation(int populationSize,
                                                   double xMax,
                                                   double xMin,
                                                   double yMax,
                                                   double yMin,
                                                   int accuracy) {
        Chromosome[] population = new Chromosome[populationSize];
        for (int i = 0; i < population.length; i++) {
            double x = xMin + (xMax-xMin)*rand.nextDouble();
            double y = yMin + (yMax-yMin)*rand.nextDouble();
            population[i] = new Chromosome(x, y, xMax, xMin, yMax, yMin, accuracy);
        }
        return population;
    }

    /*
    The only public method of the class, optimizes the function.
    Performs a specified number of function evaluations and carries out selection, mutation, and crossover of chromosomes in the population.
     */
    public void optimize() {
        for (int i = 0; i < evaluations.length; i++) {
            double x = population[i%population.length].getX();
            double y = population[i%population.length].getY();
            evaluations[i] = this.function.apply(x, y);

            if (i != 0 && i % population.length == 0) {
                selectChromosomes();
                mutateChromosomes();
                crossChromosomes();
            }
        }
        writeEvaluationsToFile();
    }

    /*
    Performs selection of chromosomes using the tournament method.
     */
    private void selectChromosomes() {
        tournament.select();
    }

    /*
    Mutates chromosomes present in the population.
     */
    private void mutateChromosomes() {
        for (Chromosome ch : population) {
            for (int i = 0; i < ch.getLength(); i++) {
                if (rand.nextDouble() < mutationProbability) {
                    ch.mutate(i);
                    ch.fix();
                }
            }
        }
    }

    /*
    Crosses chromosomes present in the population.
     */
    private void crossChromosomes() {
        boolean isSecond = false;
        Chromosome first = null;
        for (Chromosome chromosome : population) {
            if (rand.nextDouble() < crossProbability) {
                if (isSecond) {
                    int position = rand.nextInt(1, population.length-1);
                    Chromosome.cross(first, chromosome, position);
                    first.fix();
                    chromosome.fix();
                    isSecond = false;
                } else {
                    first = chromosome;
                    isSecond = true;
                }
            }
        }
    }

    /*
    Saves the evaluation results to a text file.
     */
    private void writeEvaluationsToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            for (double evaluation : evaluations) {
                writer.append(String.format("%." + accuracy + "f\n", evaluation));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to a file.");
        }
    }
}