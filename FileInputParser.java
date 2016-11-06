import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aaron on 21/10/16.
 */
public class FileInputParser {

    public List<String> condition = new ArrayList<>();
    public List<String> answers = new ArrayList<>();

    public List<String> getAnswers() {
        return answers;
    }

    public List<String> getConditions() {

        return condition;
    }

    public boolean ParseInput(File file) {
        try {
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
                    condition.add(parts[0]);
                    answers.add(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
