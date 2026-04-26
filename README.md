# Genetic Algorithm for Function Optimization

This project is a Java implementation of a **Genetic Algorithm (GA)** used for finding the global minimum of complex mathematical functions. It was developed as part of a university course on Artificial Intelligence Tools.

##  Features
- **Binary Encoding:** Real numbers are encoded into bitstrings with custom precision.
- **Genetic Operators:**
  - **Selection:** Tournament selection (size 3).
  - **Crossover:** Single-point crossover.
  - **Mutation:** Bit-flip mutation.
- **Test Functions:** Includes popular optimization benchmarks: Bukin N.6, Matyas, Lévi N.13, Himmelblau, Three-hump camel, and Easom.
- **Data Export:** Results are automatically saved to `.txt` files for further analysis (e.g., in Excel or Python).

##  Project Structure
- `Main.java`: Entry point that runs experiments for various population sizes.
- `GeneticAlgorithm.java`: Core logic of the evolution process.
- `Chromosome.java`: Representation of an individual with binary-to-decimal decoding.
- `Functions.java`: Definitions of mathematical objective functions and their constraints.
- `Tournament.java`: Implementation of the tournament selection mechanism.

##  Experiments
The project investigates how **population size** (ranging from 10 to 1000) affects the convergence of the algorithm over 50,000 evaluations. To ensure statistical significance, each configuration is repeated 50 times.

##  How to Run Using an IDE (IntelliJ / Eclipse)

1. Create a new Java Project.  
2. Copy all `.java` files into the `src` folder.  
3. Locate `Main.java`, right-click, and select **Run 'Main.main()'**.  

---

## Understanding the Output

Once the program starts, it performs a large-scale experiment based on the following parameters:

- **Total Evaluations:** 50,000 per run  
- **Population Sizes:** Tests are conducted for sizes `10, 20, 50, 100, 200, 500, 1000`  
- **Runs:** Each experiment is repeated 50 times to ensure statistical reliability  

---

## Output Files

The algorithm saves results into the project's root directory using the naming convention:
{functionName}{populationSize}{runNumber}.txt

**Example:**
bukin_100_25.txt

Contains the fitness values for the 25th run of the Bukin N.6 function optimization with a population of 100.

---

> ⚠️ **Storage Note:**  
> Running the full suite will generate **2,100 text files**  
> (6 functions × 7 population sizes × 50 runs).  
> Ensure you have enough disk space.
