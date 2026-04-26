import java.util.function.BiFunction;

public class Main {
    public static final int NUMBER_OF_EVALUATIONS = 50000;
    public static final int ACCURACY = 3;
    public static final double CROSS_PROBABILITY = 0.6;
    public static final double MUTATION_PROBABILITY = 0.02;
    public static final int[] POPULATION_SIZES = {10, 20, 50, 100, 200, 500, 1000};
    public static final int NUMBER_OF_RUNS = 50;
    public static final int TOURNAMENT_GROUP_SIZE = 3;

    public static void main(String[] args) {
        optimizeFunctions();
    }

    /*
    Optimizes all examined functions.
     */
    private static void optimizeFunctions() {
        for (int populationSize : POPULATION_SIZES) {
            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::bukin,
                        populationSize,
                        Functions.BUKIN_X_MAX,
                        Functions.BUKIN_X_MIN,
                        Functions.BUKIN_Y_MAX,
                        Functions.BUKIN_Y_MIN,
                        generateResultFileName("bukin", populationSize, j)
                );
            }

            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::matyas,
                        populationSize,
                        Functions.MATYAS_X_MAX,
                        Functions.MATYAS_X_MIN,
                        Functions.MATYAS_Y_MAX,
                        Functions.MATYAS_Y_MIN,
                        generateResultFileName("matyas", populationSize, j)
                );
            }

            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::levi,
                        populationSize,
                        Functions.LEVI_X_MAX,
                        Functions.LEVI_X_MIN,
                        Functions.LEVI_Y_MAX,
                        Functions.LEVI_Y_MIN,
                        generateResultFileName("levi", populationSize, j)
                );
            }

            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::himmelblau,
                        populationSize,
                        Functions.HIMMELBLAU_X_MAX,
                        Functions.HIMMELBLAU_X_MIN,
                        Functions.HIMMELBLAU_Y_MAX,
                        Functions.HIMMELBLAU_Y_MIN,
                        generateResultFileName("himmelblau", populationSize, j)
                );
            }

            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::threeHumpCamel,
                        populationSize,
                        Functions.THREE_HUMP_CAMEL_X_MAX,
                        Functions.THREE_HUMP_CAMEL_X_MIN,
                        Functions.THREE_HUMP_CAMEL_Y_MAX,
                        Functions.THREE_HUMP_CAMEL_Y_MIN,
                        generateResultFileName("three_hump_camel", populationSize, j)
                );
            }

            for (int j = 1; j <= NUMBER_OF_RUNS; j++) {
                optimizeFunction(
                        Functions::easom,
                        populationSize,
                        Functions.EASOM_X_MAX,
                        Functions.EASOM_X_MIN,
                        Functions.EASOM_Y_MAX,
                        Functions.EASOM_Y_MIN,
                        generateResultFileName("easom", populationSize, j)
                );
            }
        }
    }

    /*
    Optimizes a single function.
    In addition to passing the optimized function to the method, it is necessary to pass the population size,
    function parameter constraints, and the name of the file where the function evaluation values will be saved.
     */
    private static void optimizeFunction(BiFunction<Double, Double, Double> function,
                                         int populationSize,
                                         double xMax,
                                         double xMin,
                                         double yMax,
                                         double yMin,
                                         String fileName) {
        new GeneticAlgorithm(
                populationSize,
                TOURNAMENT_GROUP_SIZE,
                NUMBER_OF_EVALUATIONS,
                function,
                xMax,
                xMin,
                yMax,
                yMin,
                ACCURACY,
                CROSS_PROBABILITY,
                MUTATION_PROBABILITY,
                fileName
        ).optimize();
    }

    /*
    Creates a filename in which the function evaluation values will be located.
     */
    private static String generateResultFileName(String functionName, int populationSize, int runNumber) {
        return String.format("%s_%d_%d.txt", functionName, populationSize, runNumber);
    }
}