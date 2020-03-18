package game;

import java.io.*;

/**
 * A class to store high score.
 * Can be serialized and stored in a serialized file so the object can be restored after exiting the program.
 */
public class HighScore implements Serializable {

    private int maxEatenFood;
    private int maxKilledEnemies;

    public HighScore(int maxEatenFood, int maxKilledEnemies) {
        this.maxEatenFood = maxEatenFood;
        this.maxKilledEnemies = maxKilledEnemies;
    }

    public int getMaxEatenFood() {
        return maxEatenFood;
    }

    public int getMaxKilledEnemies() {
        return maxKilledEnemies;
    }

    /**
     * Checks if the score in a round is better than the current high score that is stored on file.
     * The high score is deserialized from the file and it is checked if either number of eaten food or number of
     * killed enemies in the current round's score is better than the current high score and in that case the
     * high score is overridden.
     * The possible overridden high score is serialized to the file again.
     * @param newScore The HighScore object created after a round is completed
     */
    public static void newScore(HighScore newScore) {
        HighScore highScore = deserialize();
        if(newScore.maxEatenFood > highScore.maxEatenFood) {
            highScore.maxEatenFood = newScore.maxEatenFood;
        }
        if(newScore.maxKilledEnemies > highScore.maxKilledEnemies) {
            highScore.maxKilledEnemies = newScore.maxKilledEnemies;
        }
        serialize(highScore);
    }

    /**
     * Saves a high score object in a serialized file.
     * @param highScore the high score object to save on file
     */
    public static void serialize(HighScore highScore) {
        try (
                FileOutputStream fos = new FileOutputStream("highscore.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(highScore);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes a file to get the HighScore object saved on file.
     * @return HighScore object from file
     */
    public static HighScore deserialize() {
        try(
                FileInputStream fis = new FileInputStream("highscore.ser");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (HighScore) ois.readObject();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
