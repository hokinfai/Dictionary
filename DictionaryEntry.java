package Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryEntry {
	public static void main(String args[]) throws IOException {
		String name = "NaturalEnvironment";
		String dictionaryPath = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/Dictionary/MachineLearning Features/"
				+ name + ".txt";
		StringBuilder output = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		try {
			String line = br.readLine();
			while (line != null) {
				if (line.endsWith("y")) {
					output.append(line + "\t" + name + "\n");
					output.append(line.substring(0, line.length() - 1) + "ies\t" + name + "\n");
				} else if (line.endsWith("ss") || line.endsWith("h") || line.endsWith("x")) {
					output.append(line + "\t" + name + "\n");
					output.append(line + "es\t" + name + "\n");
				} else if (line.endsWith("s") && (!line.substring(line.length() - 2, line.length() - 1).equals('s'))) {
					output.append(line + "\t" + name + "\n");
				} else {
					output.append(line + "\t" + name + "\n");
					output.append(line + "s\t" + name + "\n");
				}

				line = br.readLine();
			}
		} finally {
			br.close();
			File file = new File(
					"/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/Dictionary/MachineLearning Features/"
							+ name + ".txt");
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);
			// Writes the content to the file
			writer.write(output.toString());
			writer.flush();
			writer.close();
		}

	}
}
