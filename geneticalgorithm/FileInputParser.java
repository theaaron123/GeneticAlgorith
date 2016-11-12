package geneticalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aaron on 21/10/16.
 */
public class FileInputParser {

    public List<byte[]> condition = new ArrayList<>();
    public List<byte[]> answers = new ArrayList<>();

    public List<byte[]> getAnswers() {
        return answers;
    }

    public List<byte[]> getConditions() {
        return condition;
    }

    public boolean parseInput(String filePath) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            int iteration = 0;
            while (sc.hasNextLine()) {
                if (iteration == 0) {
                    String line = sc.nextLine();
                    iteration++;
                    // Skip header
                } else {

                    String line = sc.nextLine();
                    String[] parts = line.split(" ");
                    String[] conds = parts[0].split("(?!^)");
                    byte condition[] = new byte[5];
                    for (int i = 0; i < conds.length; i++) {
                        condition[i] = Byte.parseByte(conds[i]);
                    }
                    this.condition.add(condition);
                    byte[] answer = new byte[1];
                    answer[0] = Byte.parseByte(parts[1]);
                    answers.add(answer);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
