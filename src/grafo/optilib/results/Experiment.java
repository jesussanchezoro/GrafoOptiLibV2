package grafo.optilib.results;

import grafo.optilib.metaheuristics.Algorithm;
import grafo.optilib.structure.Instance;
import grafo.optilib.structure.InstanceFactory;
import grafo.optilib.structure.Solution;
import grafo.optilib.tools.RandomManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Experiment<I extends Instance, IF extends InstanceFactory<I>, S extends Solution> {

	private final long SEED = 1309;

	private final Algorithm<I,S>[] algorithms;
	private final IF factory;
	
	public Experiment(Algorithm<I,S>[] algorithms, IF factory) {
		this.algorithms = algorithms;
		this.factory = factory;
	}
	
	private List<String> readFilesInFolder(String folder, String[] extensions) {
		List<String> files = null;
		try {
			files = Files.list(Paths.get(folder))
					.map(String::valueOf)
					.filter(path -> {
						for (String ext : extensions) {
							if (path.substring(Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"))+1).startsWith(".")) {
								return false;
							}
							if (path.endsWith(ext) || path.matches(ext)) {
								return true;
							}
						}
						return false;
					})
					.sorted()
					.collect(Collectors.toList());
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return files;
	}
	
	private void launchAlgorithm(Algorithm<I, S> algorithm, String inputFolder, String outputFile, String[] extensions) {
		List<String> files = readFilesInFolder(inputFolder, extensions);
		if (files == null) {
			System.err.println("The folder "+inputFolder+" does not contain any file matching the given extensions");
			System.exit(-1);
		}
		List<Result> results = new ArrayList<>();
		System.out.println("ALGORITHM: "+algorithm.toString());
		for (String file : files) {
			RandomManager.setSeed(SEED);
			I instance = factory.readInstance(file);
            Result res = algorithm.execute(instance);
            results.add(res);
		}
		TableCreator.createTable(outputFile, results);
	}

	public void launch(String instanceDir, String[] extensions) {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH)+1;
		int year = cal.get(Calendar.YEAR);

		String date = String.format("%04d-%02d-%02d", year, month, day);

		String outDir = "./experiments/"+date;
		File outDirCreator = new File(outDir);
		outDirCreator.mkdirs();

		for (Algorithm<I,S> algorithm : algorithms) {
			String outputFile = outDir + "/" + algorithm.toString() + ".csv";
			launchAlgorithm(algorithm, instanceDir, outputFile, extensions);
		}
	}

}
