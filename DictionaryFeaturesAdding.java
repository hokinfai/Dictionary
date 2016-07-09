package Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DictionaryFeaturesAdding {
	StringBuilder result = new StringBuilder();
	List<String> dictionary = new ArrayList<String>();
	List<String> training = new ArrayList<String>();

	public DictionaryFeaturesAdding(String dictionaryPath, String Path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		BufferedReader br1 = new BufferedReader(new FileReader(Path));
		try {
			String line = br.readLine();
			while (line != null) {
				if (line != null)
					dictionary.add(line);
				line = br.readLine();
			}
			String line1 = br1.readLine();
			while (line1 != null) {
				if (line1 != null)
					training.add(line1);
				line1 = br1.readLine();
			}
		} finally {
			br.close();
			br1.close();
			dictionary.removeAll(Arrays.asList(null, ""));
		}
		merge();
	}

	public void merge() throws IOException {
		int size;
		if (dictionary.size() > training.size())
			size = training.size();
		else
			size = dictionary.size();
		for (int i = 0; i < size; i++) {
			System.out.println(dictionary.get(i));
			String[] dicSp = dictionary.get(i).split("\t");
			String dicAnno = dicSp[6];
			result.append(training.get(i) + "\t" + dicAnno + "\n");
		}
	}

	public void toPrint(String path) throws IOException {
		File file = new File(path);
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(result.toString());
		writer.flush();
		writer.close();
		System.out.println(result);
	}

	public static void main(String args[]) throws IOException {
		String dictionaryTestingPath = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/DictionaryFeatures(Testing)/NaEnDic.txt";
		final String testingPath = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/Testing Data/TestingData(dictionary).txt";
		String dictionaryPath = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/DictionaryFeatures(Training)/NaEnDic.txt";
		final String trainingPath = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/Training Data/Dictionary Features/TrainingData(dictionary).txt";

		new DictionaryFeaturesAdding(dictionaryTestingPath, testingPath).toPrint(testingPath);
		new DictionaryFeaturesAdding(dictionaryPath, trainingPath).toPrint(trainingPath);
	}
}