package Controllers;

import Models.Fields.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FieldController {
    private Field[] fields;
    private BufferedReader r;
    private int numOfLines;

        //Reads a CSV file with information about all the filelds, and put them in an array of Fields.
        public FieldController() throws IOException {
            String pathName = "fields.csv";
            Path path = Paths.get(pathName);
            numOfLines = (int)Files.lines(path).count() -1;
            String line;

            fields = new Field[numOfLines];
            //reads the file one line at a time, discard the first line.
            r = new BufferedReader(new FileReader(pathName));
            r.readLine();
            while ((line = r.readLine()) != null){
                String[] values = line.split(",");
                String fieldName = values[0];
                int pos = Integer.parseInt(values[1]);
                String fieldType = values[2];
                int price;
                // depending on which kind if field it is, makes a child class and puts them in the array of fields.
                switch (fieldType) {
                    case " start":
                        fields[pos] = new Start(fieldName);
                        break;
                    case " street":
                        price = Integer.parseInt(values[3]);
                        fields[pos] = new DeedField(fieldName, price, Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]));
                        break;
                    case " chance":
                        fields[pos] = new ChanceField(fieldName);
                        break;
                    case " tax":
                        price = Integer.parseInt(values[3]);
                            fields[pos] = new TaxField(fieldName,price,fieldName);
                            break;
                    case " ferry":
                        price = Integer.parseInt(values[3]);
                        fields[pos] = new FerryField(fieldName, price, Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]));
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
                        price = Integer.parseInt(values[3]);
                        fields[pos] = new BreweryField(fieldName, price, Integer.parseInt(values[5]), Integer.parseInt(values[6]));
                        break;
                    case " refugee":
                        fields[pos] = new FreeParking(fieldName);
                        break;
                }
            }
        }
// returns the array of fields
    public Field[] getFields() {
        return fields;
    }

    //returns the price of a field by a specific possession
    public int getFieldPrice(int i){
            int rent;
            Field currentField = fields[i];
            switch (currentField.getFieldType()){
                case "Brewery":
                    BreweryField bf = (BreweryField)fields[i];
                    rent = bf.getPrice();
                    break;
                case "Deed":
                    DeedField df = (DeedField) fields[i];
                    rent = df.getPrice();
                    break;
                case "Ferry":
                    FerryField ff = (FerryField) fields[i];
                    rent = ff.getPrice();
                    break;
                default:
                    rent = 0;
                    break;
            }
            return rent;
    }

    //returns the rent of a field by a specific possession
    public int getFieldRent(int i){
        int rent;
        Field currentField = fields[i];
        switch (currentField.getFieldType()){
            case "Brewery":
                BreweryField bf = (BreweryField)fields[i];
                rent = bf.getRent0();
                break;
            case "Deed":
                DeedField df = (DeedField) fields[i];
                rent = df.getRent0();
                break;
            case "Ferry":
                FerryField ff = (FerryField) fields[i];
                rent = ff.getRent0();
                break;
            default:
                rent = 0;
                break;
        }
        return rent;
    }

    //returns the name of a field by a specific possession
    public String getFieldTitle(int i){
        String title;
        Field currentField = fields[i];
        switch (currentField.getFieldType()){
            case "Brewery":
                BreweryField bf = (BreweryField)fields[i];
                title = bf.getFieldName();
                break;
            case "Deed":
                DeedField df = (DeedField) fields[i];
                title = df.getFieldName();
                break;
            case "Ferry":
                FerryField ff = (FerryField) fields[i];
                title = ff.getFieldName();
                break;
            default:
                title = "";
                break;
        }
        return title;
    }

    //returns the type of the field that the player has just landed on.
    public String GetCurrentFiledType(PlayerController pc){
        int pos = pc.getCurrentPlayer().getPos();
        Field currentField = fields[pos];
        String s = currentField.getFieldType();
        return s;
    }
}
