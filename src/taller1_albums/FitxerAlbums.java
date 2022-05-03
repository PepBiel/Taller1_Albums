/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_albums;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PepBiel
 */
public class FitxerAlbums {

    private File arxiu;
    private RandomAccessFile f;
//  4 bytes per cada enter i 2 bytes per cada caràcter
//  Enter (numero, vot, impresions i any) i 61 caràcters per nom àlbum, 29 per artista, 19 per tipus i 4 per puntuació
    private final long midaArticles = (4) + (4) + (61 * 2) + (29 * 2) + (4) + (4) + (19 * 2) + (4 * 2);
//  21 enters: numero de vot, numero d'àlbum i vot per a cada àlbum. Hi ha 10 vots
    private final long midaArticlesVots = (4) * (21);

//    Constructor que li passen el nom del fitxer
    public FitxerAlbums(String nom) {
        try {
            arxiu = new File(nom);
            f = new RandomAccessFile(arxiu, "rw");
        } catch (IOException exc) {
            System.out.println("ERORR: " + exc.getMessage());
        }
    }

//    Mètode que escriu al fitxer d'accés aleatori el numero, el vot, el titol, l'artista, l'any, les edicions, el tipus i puntuació
    public void escriureAlbums(Album a, int vot, int pos) {
        try {
            String titol = "";
            String artista = "";
            String tipus = "";
            String calidad = "";

            f.seek(pos * midaArticles);

            f.writeInt(a.getNum());
            f.writeInt(vot);

            titol = a.getTitol();

            if (titol.length() < 61) {
                for (int i = titol.length(); i < 61; i++) {
                    titol = titol + " ";
                }
            }

            artista = a.getArtista();

            if (artista.length() < 29) {
                for (int i = artista.length(); i < 29; i++) {
                    artista = artista + " ";
                }
            }
            f.writeChars(titol);
            f.writeChars(artista);
            f.writeInt(a.getAny());
            f.writeInt(a.getEdicions());

            tipus = a.getTipus();

            if (tipus.length() < 19) {
                for (int i = tipus.length(); i < 19; i++) {
                    tipus = tipus + " ";
                }
            }

            calidad = a.getCal();

            if (calidad.length() < 4) {
                for (int i = calidad.length(); i < 4; i++) {
                    calidad = calidad + " ";
                }
            }
            f.writeChars(tipus);
            f.writeChars(calidad);

            f.close();
        } catch (IOException exc) {
            System.out.println("ERORR: " + exc.getMessage());
        }

    }

//    Mètode que llegeix el fitxer que hem escrit abans, però sols mostra per pantalla el numero, el vot, el titol, l'artista i l'any de l'album
    public void imprimirAlbums() {
        int num;
        int vot;
        String titol = "";
        String artista = "";
        int any;
        long pos = 0;
        try {
            long numArt = 100;
            for (int i = 0; i < numArt; i++) {
                f.seek(pos * midaArticles);
                num = f.readInt();
                vot = f.readInt();
                for (int j = 0; j < 61; ++j) {
                    titol += f.readChar();
                }
                for (int j = 0; j < 29; ++j) {
                    artista += f.readChar();
                }
                any = f.readInt();
                System.out.println("# " + num + " vots: " + vot + " " + titol + " Artista: " + artista + " Any: " + any);

                titol = "";
                artista = "";
                pos++;
            }

            f.close();

        } catch (IOException ex) {
            Logger.getLogger(FitxerAlbums.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

//    Ens pasen el vot que hem d'introduir i la posició en que es troba l'album
//    Així que en col·locam a l'album lligat a la posició i sumam el nou vot amb el vot antterior i ho escrivim
    public void introduirVot(int vot, long pos) throws IOException {
        try {
            int num;
            int votAnt;
            String titol = "";
            String artista = "";
            f.seek(((pos - 1) * midaArticles) + 4);
            votAnt = f.readInt();
            f.seek(((pos - 1) * midaArticles) + 4);
            f.writeInt(votAnt + vot);
            f.seek((pos - 1) * midaArticles);
            num = f.readInt();
            vot = f.readInt();
            for (int j = 0; j < 61; ++j) {
                titol += f.readChar();
            }
            for (int j = 0; j < 29; ++j) {
                artista += f.readChar();
            }
            System.out.println("# " + num + " vots: " + vot + " per " + titol + " de " + artista);
            f.close();

        } catch (IOException ex) {
            Logger.getLogger(FitxerAlbums.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    Passam el numero de vots que hem de fer i cream vots aleatoris 
    public void votsAleatoris(int numVots) throws IOException {
        long r;
        for (int i = 0; i < numVots; i++) {
            f.seek((100 * midaArticles) + (i * midaArticlesVots));  //Ens col·locam a una posició nova per poder escriure sense llevar res
            f.writeInt(i + 1);
            for (int j = 1; j <= 10; j++) {
                r = (new Random().nextInt(100) + 1);
                f.writeInt((int) r);
                f.writeInt(j);
                sumarVots(j, r);
                f.seek((100 * midaArticles) + (i * midaArticlesVots) + (4) + (2 * j * 4));  //Afegim aquest nombres per col·locar-mos allà on haviem escrit lo derrer
            }
        }
    }

//    Sumam el vots aleatoris amb els vots anteriors i es queden els vots definitius
    public void sumarVots(int vot, long pos) {
        try {
            int votAnt;
            f.seek(((pos - 1) * midaArticles) + 4);
            votAnt = f.readInt();
            f.seek(((pos - 1) * midaArticles) + 4);
            f.writeInt(votAnt + vot);
        } catch (IOException ex) {
            Logger.getLogger(FitxerAlbums.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Imprimim el vots aleatoris generats anteriorment
    public void imprimirVotsAleatoris(int numVots) {
        long pos = 0;
        int num;
        int pos1;
        int vot1;
        int pos2;
        int vot2;
        int pos3;
        int vot3;
        int pos4;
        int vot4;
        int pos5;
        int vot5;
        int pos6;
        int vot6;
        int pos7;
        int vot7;
        int pos8;
        int vot8;
        int pos9;
        int vot9;
        int pos10;
        int vot10;
        try {
            for (int i = 1; i <= numVots; i++) {
                f.seek((100 * midaArticles) + (pos * midaArticlesVots));
                num = f.readInt();
                pos1 = f.readInt();
                vot1 = f.readInt();
                pos2 = f.readInt();
                vot2 = f.readInt();
                pos3 = f.readInt();
                vot3 = f.readInt();
                pos4 = f.readInt();
                vot4 = f.readInt();
                pos5 = f.readInt();
                vot5 = f.readInt();
                pos6 = f.readInt();
                vot6 = f.readInt();
                pos7 = f.readInt();
                vot7 = f.readInt();
                pos8 = f.readInt();
                vot8 = f.readInt();
                pos9 = f.readInt();
                vot9 = f.readInt();
                pos10 = f.readInt();
                vot10 = f.readInt();
                System.out.println("vot: " + num + " [#" + pos1 + " - " + vot1 + "] "
                        + "[#" + pos2 + " - " + vot2 + "] " + "[#" + pos3 + " - " + vot3 + "] "
                        + "[#" + pos4 + " - " + vot4 + "] " + "[#" + pos5 + " - " + vot5 + "] "
                        + "[#" + pos6 + " - " + vot6 + "] " + "[#" + pos7 + " - " + vot7 + "] "
                        + "[#" + pos8 + " - " + vot8 + "] " + "[#" + pos9 + " - " + vot9 + "] "
                        + "[#" + pos10 + " - " + vot10 + "] ");
                pos++;
            }

            f.close();

        } catch (IOException ex) {
            Logger.getLogger(FitxerAlbums.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Mètode que en diu l'album que te més vots
    public void mesVots() {
        try {
            AlbumVots av = new AlbumVots();
            long pos1 = 1;
            long pos2 = 2;
            int num;
            int vot;
            String titol = "";
            String artista = "";
            int any;
            int edicions;
            String tipus = "";
            String calidad = "";
            int vot1;
            int vot2;
            f.seek(((pos1 - 1) * midaArticles) + 4);
            vot1 = f.readInt();
            while (pos2 != 101) {
                f.seek(((pos2 - 1) * midaArticles) + 4);
                vot2 = f.readInt();
                if (vot1 < vot2) {
                    vot1 = vot2;
                    pos1 = pos2;
                    pos2++;
                } else {
                    pos2++;
                }
            }
            f.seek((pos1 - 1) * midaArticles);
            num = f.readInt();
            av.setNum(num);
            vot = f.readInt();
            av.setVot(vot);
            for (int j = 0; j < 61; ++j) {
                titol += f.readChar();
            }
            av.setTitol(titol);
            for (int j = 0; j < 29; ++j) {
                artista += f.readChar();
            }
            av.setArtista(artista);
            any = f.readInt();
            av.setAny(any);
            edicions = f.readInt();
            av.setEdicions(edicions);
            for (int j = 0; j < 19; ++j) {
                tipus += f.readChar();
            }
            av.setTipus(tipus);
            for (int j = 0; j < 4; ++j) {
                calidad += f.readChar();
            }
            av.setCal(calidad);
            System.out.println(av);

            f.close();

        } catch (IOException ex) {
            Logger.getLogger(FitxerAlbums.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Mètode que es pot cridar per tancar el fitxer
    public void tancar() throws IOException {
        f.close();
    }

}
