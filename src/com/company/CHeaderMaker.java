

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CHeaderMaker {

    public static void main(String[] args) {
    	// write your code here

        String fileName = args[0];

        File input = new File(fileName + ".c");

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName + ".h", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println("#ifndef __" + fileName + "_H__");
        writer.println("#define __" + fileName + "_H__");
        writer.println();

        Scanner scanner = null;
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (Pattern.matches("\\p{Alpha}+\\s+\\p{Alpha}+\\s*\\(.*\\)\\s*\\{", line)) {
                if (!line.contains("main")) {
                    int pos = line.indexOf("{");
                    line = line.substring(0, pos);
                    line += ";";
                    writer.println(line);
                }
            }
        }

        writer.println("#endif");
        writer.close();


    }
}
