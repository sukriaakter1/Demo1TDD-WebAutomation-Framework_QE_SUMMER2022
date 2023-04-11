package config.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    static String relPath = "../TDD-WebAutomation-Framework_QE_SUMMER2022/DataTest/Sample.txt";

    public static void main(String[] args)  {

        readDataFile(relPath);

    }

    public static void readDataFile( String relPath){
        DataReader learnDataReader = new DataReader();
        // String filePath=learnDataReader.absPath;
        String filePath = relPath;

        // File Reader
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String data;

            while (       (       data = bufferedReader.readLine()   ) != null                 ) {
                System.out.println(data);
            }


        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found Exception : " + fileNotFoundException.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert bufferedReader != null;
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }

        }
    }

}
