package uk.co.riversparrow.redair.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

public class ConfigFile {
	private String path;
	private File file;
	
	public ConfigFile(String configPath) {
		setPath(configPath);
		file = new File(configPath);
	}
	
	public boolean setPath(String newPath) {
		File fileAtPath = new File(newPath);
		if(fileAtPath.exists() && fileAtPath.isFile()) {
			path = newPath;
			file = fileAtPath;
			return true;
		}
		else {
			return false;
		}
	}
	public String getPath() {
		return path;
	}
	
	public File getFile() {
		return file;
	}
	
	public boolean exists() {
		return file.exists();
	}
	
	public HashMap<String, String> read() throws FileNotFoundException {
		HashMap<String, String> results = new HashMap<String, String>();
		Yaml yamlFile = new Yaml();
		InputStream stream = new FileInputStream(path);
		@SuppressWarnings("unchecked")
			List<String> lines = (List<String>) yamlFile.load(stream);
		for(String thisLine : lines) {
			if(thisLine.contains("=")) {
				String[] splits = thisLine.split("=");
				results.put(splits[0], splits[1]);
			} else {
				results.put(Integer.toString(results.size()), thisLine);
			}
		}
		return results;
	}
	
	public void create() throws IOException {
		file.createNewFile();
		String initialText;
		String newLine = System.getProperty("line.separator");
		initialText = "" + newLine;
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
		writer.println(initialText);
		writer.close();
	}
}
