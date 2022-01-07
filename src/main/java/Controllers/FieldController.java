package Controllers;

import Models.Fields.*;

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

        public FieldController() throws IOException {
            String pathName = "fields.csv";
            Path path = Paths.get(pathName);
            numOfLines = (int)Files.lines(path).count();
            String line;

            fields = new Field[numOfLines-1];
            r = new BufferedReader(new FileReader(pathName));
            r.readLine();
            while ((line = r.readLine()) != null){
                String fieldName = line.split(",")[0];
                int pos = Integer.parseInt(line.split(",")[1]);
                String fieldType = line.split(",")[2];
                switch (fieldType) {
                    case " start":
                        fields[pos] = new Start(fieldName);
                        break;
                    case " street":
                        fields[pos] = new DeedField(fieldName);
                        break;
                    case " chance":
                        fields[pos] = new ChanceField(fieldName);
                        break;
                    case " tax":
                        fields[pos] = new TaxField(fieldName);
                        break;
                    case " ferry":
                        fields[pos] = new FerryField(fieldName);
                        break;
                    case " jail":
                        if (pos == 10)
                            fields[pos] = new Jail(fieldName);
                        else if (pos == 30)
                            fields[pos] = new GoToJail(fieldName);
                        else {
                            IOException e = new IOException("Jail wrong pos in csv file");
                            throw e;
                        }
                        break;
                    case " brewery":
                        fields[pos] = new BreweryField(fieldName);
                        break;
                    case " refugee":
                        fields[pos] = new FreeParking(fieldName);
                        break;

                }

            }
            for (Field field : fields) {
                System.out.println(field.getFieldName());

            }
        }
}
