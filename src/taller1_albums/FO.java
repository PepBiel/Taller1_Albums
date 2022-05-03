/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_albums;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author PepBiel
 */
public class FO {

    private int num;
    private int any;
    private String titol;
    private String artista;
    private String tipus;
    private String cal;
    private int edicions;
    private FileReader fr = null;
    private BufferedReader br;
    private String lin;
    private int nAlbums;

//    Constructor que li passam el nom del fitxer de text
    public FO(String nom) throws FileNotFoundException {
        fr = new FileReader(nom);
        br = new BufferedReader(fr);
        num = 0;
        any = 0;
        titol = "";
        artista = "";
        tipus = "";
        cal = "";
        edicions = 0;
        nAlbums = 0;
    }

//    Mètode que en serveix per llegir linia per linia el fitxer de text on hi ha els albums
//    Hi ha 7 linies i per aquest motiu feim un switch  de 7 opcions
    public Album llegirAlb() {
        Album alb = new Album();
        int nLin = 0;
        int vot = 0;
        FitxerAlbums fa = new FitxerAlbums("Albums.dat");
        try {
            while (nLin != 7) {
                lin = br.readLine();
                nLin++;
                switch (nLin) {
                    case 1:
                        alb.setNum(Integer.parseInt(lin));
                        break;
                    case 2:
                        alb.setAny(Integer.parseInt(lin));
                        break;
                    case 3:
                        alb.setTitol(lin);
                        break;
                    case 4:
                        alb.setArtista(lin);
                        break;
                    case 5:
                        alb.setTipus(lin);
                        break;
                    case 6:
                        alb.setCal(lin);
                        break;
                    case 7:
                        alb.setEdicions(Integer.parseInt(lin));
                        nAlbums++;
                        break;
                }
            }
            nLin = 0;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        fa.escriureAlbums(alb, vot, (nAlbums - 1)); //escriu els albums llegits a l'objecte Album
        return alb;
    }

//    Mètode per tancar el fitxer
    public void tancar() throws IOException {
        fr.close();
    }
    
//    Mètode per saber si queden albums per llegir
    public boolean quedenAlb() throws IOException{
        if(nAlbums !=100){
            return true;
        }
        else{
            return false;
        }
    }

}
