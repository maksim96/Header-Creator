package max;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CHeaderGenerator {

    public static void main(String[] args) {

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
            String line = "Hallo hallo(ydsuf fsd_54, asd asd) {";

            String javaIdentifier = "\\p{Alpha}(\\w)*(\\*)*";
            String whiteSpace = "\\s*";
            String declaration = javaIdentifier + "\\s+" + javaIdentifier;

            if (Pattern.matches(declaration +
                    whiteSpace + "\\(((" + whiteSpace +
                    declaration + whiteSpace + ")?" + "|"
                    + whiteSpace + declaration + whiteSpace
                    + "(," + whiteSpace + declaration + whiteSpace + ")*)" +
                    "\\)" + whiteSpace + "\\{", line)) {
                if (!line.contains("main")) {
                    int pos = line.indexOf("{");
                    line = line.substring(0, pos);
                    line += ";";
                    writer.println(line);
                }
            }

            writer.println("#endif");
            writer.close();
        }

    }
}
