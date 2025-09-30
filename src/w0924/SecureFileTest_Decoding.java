package w0924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SecureFileTest_Decoding {
    public static void main(String[] args) {
        final String PATH = "D:/Java_Project/FileTest/secure1.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            int num = 1;

            while ((line = br.readLine()) != null) {
                StringBuilder decoded = new StringBuilder();

                for (int i = 0; i < line.length(); i++) {
                    int code = line.charAt(i);
                    code -= 100;
                    decoded.append((char)code);
                }

                System.out.println(num + " Message Decoded: " + decoded.toString());
                num++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + PATH);
        }
    }
}
