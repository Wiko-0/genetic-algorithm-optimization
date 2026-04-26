import java.util.Random;
import java.util.function.BiFunction;

/*
Class implementing tournament selection of chromosomes.
 */
public class Tournament {
    private static final Random rand = new Random();
    private final Chromosome[] population;
    private final int populationSize;
    private final int groupSize;
    private final Chromosome[] newPopulation;
    private final Chromosome[] group;
    private final BiFunction<Double, Double, Double> function;

    Tournament(Chromosome[] population, int groupSize, BiFunction<Double, Double, Double> function) {
        this.population = population;
        this.populationSize = population.length;
        this.groupSize = groupSize;
        this.newPopulation = new Chromosome[this.populationSize];
        this.group = new Chromosome[groupSize];
        this.function = function;
    }

    /*
    Creates a new population, which is successively filled by the fittest individuals from tournament groups.
     */
    public void select() {
        for (int i = 0; i < populationSize; i++) {
            int[] populationIndexes = generatePopulationIndexes();
            for (int j = 0; j < groupSize; j++) {
                group[j] = population[populationIndexes[j]];
            }
            Chromosome best = findBestChromosomeFromGroup();
            try {
                newPopulation[i] = (Chromosome) best.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        System.arraycopy(newPopulation, 0, population, 0, populationSize);
    }

    /*
    Finds the fittest chromosome from the group.
     */
    private Chromosome findBestChromosomeFromGroup() {
        Chromosome best = null;
        double fMin = Double.POSITIVE_INFINITY;
        for (Chromosome chromosome : group) {
            double f = this.function.apply(chromosome.getX(), chromosome.getY());
            if (f < fMin) {
                fMin = f;
                best = chromosome;
            }
        }
        return best;
    }

    /*
    Generates indexes of individuals from the population, which are then used to create a tournament group.
     */
    private int[] generatePopulationIndexes() {
        return rand.ints(0, populationSize).distinct().limit(groupSize).toArray();
    }
}