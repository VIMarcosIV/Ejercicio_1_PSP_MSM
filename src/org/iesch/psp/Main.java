package org.iesch.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ejercicio 1 - Abrir el fichero txt que hemos creado con NotePad++ via consola");

        try {
            Runtime.getRuntime().exec(new String[]{"c:\\windows\\notepad.exe", "D:\\Users\\dam2\\Documents\\ejercicio1pspmsm.txt"});
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Ejercicio 2 - Ejecutar con ayuda de ProcessBuilder 3 comandos ping");
        ProcessBuilder processBuilder1 = new ProcessBuilder();
        ProcessBuilder processBuilder2 = new ProcessBuilder();
        ProcessBuilder processBuilder3 = new ProcessBuilder();

        processBuilder1.command("cmd.exe", "/c", "ping -n 3 google.com");
        processBuilder2.command("cmd.exe", "/c", "ping -n 3 dam2chomon.org");
        processBuilder3.command("cmd.exe", "/c", "pring -n 3 iesch.org");

        ping(processBuilder1);
        ping(processBuilder2);
        ping(processBuilder3);

        System.out.println("Ejercicio 3 - Ejecutar un script con ayuda de un .bat");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("cmd /c start D:\\Users\\dam2\\Documents\\ejercicio1pspmsm.bat");
            InputStream is = p1.getInputStream();
            int i = 0;
            while( (i = is.read() ) != -1) {
                System.out.print((char)i);
            }
        } catch(IOException ioException) {
            System.out.println(ioException.getMessage() );
        }
    }

    private static void ping(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

