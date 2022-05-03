/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_albums;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author PepBiel
 */
public class Taller1_Albums {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InsercioDadesException.InsercioDadesExcepcio {
        boolean sortir = false;
        int opcio = 0;
        FitxerAlbums fa;
        InsercioDadesException ide = new InsercioDadesException();
        FO fo;
        while ((!sortir) && (opcio >= 0) && (opcio <= 5)) {
            menu();
            opcio = llegirNum("\n\n\tInserir opció: ");
            opcio = ide.opcio(opcio);
            while ((opcio < 0) || (opcio > 5)) {
                opcio = ide.opcio(opcio);
            }
            switch (opcio) {

//              En aquesta opció, mentres hi hagui albums al fitxer de text, es crearà l'objocte i s'imprimirà
                case 1:
                    fo = new FO("Albums.txt");
                    Album a = new Album();
                    while (fo.quedenAlb()) {
                        a = fo.llegirAlb();
                        System.out.println(a);
                    }
                    fo.tancar();
                    break;

//              Imprimeix els albums del fitxer d'Acces Aleatori
                case 2:
                    fa = new FitxerAlbums("Albums.dat");
                    fa.imprimirAlbums();
                    break;

//                Es demana que s'imtroduesqui el num del disc i la puntuació i després fa una cerca de l'album dins el fitxer
//                d'accés aleatori i es posa el vot inserit
                case 3:
                    fa = new FitxerAlbums("Albums.dat");
                    int vot2;
                    int numDisc;
                    numDisc = llegirNum("Nombre de disc que vols votar [1...100]: ");
                    numDisc = ide.totalAlbums(numDisc);
                    while ((numDisc < 1) || (numDisc > 100)) {
                        numDisc = ide.totalAlbums(numDisc);
                    }
                    
                    vot2 = llegirNum("Puntuació [1...10]: ");
                    vot2 = ide.vots(vot2);
                    while ((vot2 < 1) || (vot2 > 10)) {
                        vot2 = ide.vots(vot2);
                    }
                    fa.introduirVot(vot2, (long) numDisc);
                    break;

//              Es demana un nombre de votacions que es vol generar, i es creen votacionns aleatories de 10 albums
//              amb puntuacions de l'1 al 10
                case 4:
                    fa = new FitxerAlbums("Albums.dat");
                    int numVots;
                    numVots = llegirNum("Nombre de votacions que vols generar [1...100]: ");
                    numVots = ide.totalAlbums(numVots);
                    while ((numVots < 1) || (numVots > 100)) {
                        numDisc = ide.totalAlbums(numVots);;
                    }
                    numVots = numVots;
                    fa.votsAleatoris(numVots);
                    fa.imprimirVotsAleatoris(numVots);
                    break;

//              Es fa una cerca de l'album més votat dins el ditxer d'accés aleatori
                case 5:
                    fa = new FitxerAlbums("Albums.dat");
                    fa.mesVots();
                    break;

//              Si es 0 surt
                case 0:
                    sortir = true;
                    break;
            }

        }
    }

    private static void menu() {
        System.out.println("\n\nGESTIÓ DELS DISCS QUE S'HAURIEN D'ESCOLTAR");
        System.out.println("\n\t1. Veure el contingut del fitxer de text i inicialitza fitxer de vots");
        System.out.println("\t2. veure el contingut del fitxer de vots d'accès aleatori");
        System.out.println("\t3. Un vot manual");
        System.out.println("\t4. Simula vots aleatoris");
        System.out.println("\t5. Calcula el millor àlbum");
        System.out.println("\t0. Sortir");
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
