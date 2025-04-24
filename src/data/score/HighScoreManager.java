package data.score;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighScoreManager {
    private static final Logger LOGGER = Logger.getLogger(HighScoreManager.class.getName());
    private static final String FILE_NAME = "highscore.txt";

    /**
     * Loads the high score from file.
     *
     * @return the high score or 0 if not found
     */
    public static int load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (IOException | NumberFormatException e) {
            LOGGER.warning("No high score found");
            return 0;
        }
    }

    /**
     * Saves the high score to file.
     *
     * @param score the score to save
     */
    public static void save(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Integer.toString(score));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save high score", e);
        }
    }
}