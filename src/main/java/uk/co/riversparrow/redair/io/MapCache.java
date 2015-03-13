package uk.co.riversparrow.redair.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.bukkit.block.Block;

import uk.co.riversparrow.redair.BlockMap;
import uk.co.riversparrow.redair.Utils;

public class MapCache {
	private String path;
	private File file;
	private Utils utils;
	
	public MapCache(String filePath, Utils utilsInstance) throws IOException {
		path = filePath;
		file = new File(filePath);
		file.createNewFile();
		utils = utilsInstance;
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
	
	public void create() throws IOException {
		file.createNewFile();
	}
	
	public HashMap<Block, Block> read() throws FileFormatException, IOException {
		HashMap<Block, Block> results = new HashMap<Block, Block>();
		if(!file.exists()) {
			create();
		}
		Scanner reader = new Scanner(new FileReader(path));
		int index = 0;
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] maps = line.split("->");
			if(maps.length != 2) {
				reader.close();
				throw new FileFormatException("Incorrect formatting at line " + index);
			}
			BlockMap firstBlock;
			BlockMap secondBlock;
			try {
				firstBlock = new BlockMap(maps[0], utils);
				secondBlock = new BlockMap(maps[1], utils);
			} catch(IllegalArgumentException ex) {
				reader.close();
				throw new FileFormatException(ex.getMessage());
			}
			Block first = firstBlock.getBlock();
			Block second = secondBlock.getBlock();
			results.put(first, second);
			index++;
		}
		reader.close();
		return results;
	}
	
	public void addEntry(Block firstBlock, Block secondBlock) throws IOException {
		BlockMap firstMap = new BlockMap(firstBlock.getWorld(), firstBlock);
		BlockMap secondMap = new BlockMap(secondBlock.getWorld(), secondBlock);
		String toSave = firstMap.toString() + "->" + secondMap.toString();
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		writer.println(toSave);
		writer.close();
	}
	
	public void saveFromHashMap(HashMap<Block, Block> mappings) throws IOException {
		Set<Entry<Block, Block>> entries = mappings.entrySet();
		for(Entry<Block, Block> pair : entries) {
			Block firstBlock = pair.getKey();
			Block secondBlock = pair.getValue();
			BlockMap firstMap = new BlockMap(firstBlock.getWorld(), firstBlock);
			BlockMap secondMap = new BlockMap(secondBlock.getWorld(), secondBlock);
			String toSave = firstMap.toString() + "->" + secondMap.toString();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			writer.println(toSave);
			writer.close();
		}
	}
	
	public void removeEntry(Block firstBlock) throws IOException {
		BlockMap map = new BlockMap(firstBlock.getWorld(), firstBlock);
		String fileString = map.toString();
		LineNumberReader reader = new LineNumberReader(new FileReader("input.txt"));
		while(true) {
			int lineNumber = reader.getLineNumber();
			String line = reader.readLine();
			if(line == null) {
				break;
			}
			if(line.startsWith(fileString)) {
				removeNthLine(lineNumber);
			}
		}
		reader.close();
	}
	
	public void removeNthLine(int toRemove) throws IOException {
	    RandomAccessFile raf = new RandomAccessFile(path, "rw");

	    // Leave the n first lines unchanged.
	    for (int i = 0; i < toRemove; i++)
	        raf.readLine();

	    // Shift remaining lines upwards.
	    long writePos = raf.getFilePointer();
	    raf.readLine();
	    long readPos = raf.getFilePointer();

	    byte[] buf = new byte[1024];
	    int n;
	    while (-1 != (n = raf.read(buf))) {
	        raf.seek(writePos);
	        raf.write(buf, 0, n);
	        readPos += n;
	        writePos += n;
	        raf.seek(readPos);
	    }

	    raf.setLength(writePos);
	    raf.close();
	}
}
