package ElizabethMod.tools;

import java.io.*;
import java.util.ArrayList;

public abstract class DataParser {

    //Bless Kobting for this code

    protected File dataFile;
    protected ArrayList<Integer> invalidLines;

    public DataParser(File dataFile) {
        this.dataFile = dataFile;
        invalidLines = new ArrayList<>();
        init();
    }

    private void init() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.dataFile))){
            String command;
            int currentLine = 1;

            while ((command = bufferedReader.readLine()) != null) {
                try {
                    //Trim and replace to protect from multiple spaces before, after, and between commands
                    parseCommand(command.trim().replaceAll("\\s+", " "), currentLine);
                } catch (InvalidCommandException e) {
                    e.printStackTrace();
                    invalidLines.add(currentLine);
                }
                currentLine++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file: " + this.dataFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getInvalidLines(){
        return this.invalidLines;
    }

    protected abstract void parseCommand(String command, int currentLine) throws InvalidCommandException;
}