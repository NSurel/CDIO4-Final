package Controllers;

import Models.Fields.*;
import Models.Player;

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
                String[] values = line.split(",");
                String fieldName = values[0];
                int pos = Integer.parseInt(values[1]);
                String fieldType = values[2];
                int price = Integer.parseInt(values[3]);
                switch (fieldType) {
                    case " start":
                        fields[pos] = new Start(fieldName);
                        break;
                    case " street":
                        fields[pos] = new DeedField(fieldName, price, Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]));
                        break;
                    case " chance":
                        fields[pos] = new ChanceField(fieldName);
                        break;
                    case " tax":
                            fields[pos] = new TaxField(fieldName,price,fieldName);
                    case " ferry":
                        fields[pos] = new FerryField(fieldName, price);
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
                        fields[pos] = new BreweryField(fieldName, price);
                        break;
                    case " refugee":
                        fields[pos] = new FreeParking(fieldName);
                        break;
                }
            }
        }

    public Field[] getFields() {
        return fields;
    }

    public void doFieldAction(PlayerController playerController)
    {
        Field currentField = fields[playerController.getCurrentPlayer().getPos()];

        if (currentField.getClass().equals(DeedField.class.getClass())){

        }
        else if (currentField.getClass().equals(BreweryField.class.getClass())){

        }
        else if (currentField.getClass().equals(ChanceField.class.getClass())){

        }
        else if (currentField.getClass().equals(FerryField.class.getClass())){

        }
        else if (currentField.getClass().equals(GoToJail.class.getClass())){

        }
        else if (currentField.getClass().equals(Jail.class.getClass())){

        }
        else if (currentField.getClass().equals(TaxField.class.getClass())){

        }
        else if (currentField.getClass().equals(Start.class.getClass())){

        }
        else if (currentField.getClass().equals(FreeParking.class.getClass())){

        }
        else {}


    }
}
