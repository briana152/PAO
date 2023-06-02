package servicii;

import exceptii.CustomException;
import java.util.Scanner;

public class GeneralService {
    private static final Scanner scanner = new Scanner(System.in);
    public static int readInt() throws CustomException {
        String line = scanner.next();
        if (line.matches("^\\d+$")) {
            return Integer.parseInt(line);
        } else {
            throw new CustomException("Invalid number!");
        }
    }
    public static int readIndex(int lenght) throws CustomException {
        String line = scanner.next();
        if (line.matches("^\\d+$") && Integer.parseInt(line) > 0 && Integer.parseInt(line) <= lenght) {
            return Integer.parseInt(line);
        } else {
            throw new CustomException("Invalid number!");
        }
    }
}
