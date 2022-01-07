package Controllers;

import Models.Fields.Field;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FieldController {
    private Field[] fields;
    BufferedReader r;
    int numOfLines;
        public void FieldController() throws IOException {
            String pathName = "Fields.txt";
            Path path = Paths.get(pathName);
            numOfLines = (int)Files.lines(path).count();

            fields = new Field[numOfLines];
            r = new BufferedReader(new FileReader(pathName));

            while (r.) {
                String line = r.readLine();
                String fieldName = line.split(",")[0];
                String fieldType = line.split(",")[2];

                switch (fieldType) {
                    case "Start":
                        break;
                    case "street":
                        break;
                    case "chance":
                        break;
                    case "tax":
                        break;
                    case "ferry":
                        break;
                    case "Jail":
                        break;
                    case "brewery":
                        break;
                    case "refugee":
                        break;

                }

            }

        }
}
