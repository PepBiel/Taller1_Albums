/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_albums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author PepBiel
 */
public class InsercioDadesException {

//    Aquesta classe ens serveix per validar l'entrada per consola i per així no hi hagui errors
    public static class InsercioDadesExcepcio extends Exception {
    }

    public InsercioDadesException() {
    }

    public int totalAlbums(int i) {
        try {
            while ((i < 1) || (i > 100)) {
                throw new InsercioDadesExcepcio();
            }
        }catch (InsercioDadesExcepcio ide) {
            System.out.println("ERROR: Ha de ser un nombre entre 1 - 100");
            i = llegirNum("Torna a introduir el nombre: ");
        }
        return i;
    }

    public int vots(int i) {
        try {
            while ((i < 1) || (i > 10)) {
                throw new InsercioDadesExcepcio();
            }
        }catch (InsercioDadesExcepcio ide) {
            System.out.println("ERROR: Ha de ser un nombre entre 1 - 10");
            i = llegirNum("Torna a introduir la puntuació [1...10]: ");
        }

        return i;
    }

    public int opcio(int i){
        try {
            while ((i < 0) || (i > 5)) {
                throw new InsercioDadesExcepcio();
            }
        } catch (InsercioDadesExcepcio ide) {
            System.out.println("ERROR: Ha de ser un nombre entre 0 - 5");
            i = llegirNum("Torna a introduir l'opció: ");
        }

        return i;
    }

    private static int llegirNum(String msg) {
        int x = 0;
        try {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
            x = Integer.parseInt(s);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }
}
