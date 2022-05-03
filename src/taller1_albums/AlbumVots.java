/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_albums;

/**
 *
 * @author PepBiel
 */
public class AlbumVots {
    
//    Classe AlbumVots que crea objectes de tipus album incluint els vots que s'han generat o s'han afegit

    private int num;
    private int any;
    private String titol;
    private String artista;
    private String tipus;
    private String cal;
    private int edicions;
    private int vot;
    
    public AlbumVots(){
        num = 0;
        any = 0;
        titol = "";
        artista = "";
        tipus = "";
        cal = "";
        edicions = 0;
        vot = 0;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public void setEdicions(int edicions) {
        this.edicions = edicions;
    }

    public void setVot(int vot) {
        this.vot = vot;
    }

    @Override
    public String toString() {
        return "AlbumVots{" + "num= " + num + ", any= " + any + ", impresions= " + edicions + ", vots= " + vot + ", titol= " + titol + "\n" +
                ", artista= " + artista + ", tipus= " + tipus + ", puntuació= " + cal + '}';
    }
    
    
}
