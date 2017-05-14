import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by aelKashef on 5/13/2017.
 */
public class MainController {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("inputMDFMessages_ipo_day.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                 System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }


}
