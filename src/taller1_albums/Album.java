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
public class Album {
    
//    Classe Album que crea objectes de tipus album
    
    private int num;
    private int any;
    private String titol;
    private String artista;
    private String tipus;
    private String cal;
    private int edicions;
    
    public Album(){
        num = 0;
        any = 0;
        titol = "";
        artista = "";
        tipus = "";
        cal = "";
        edicions = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public int getEdicions() {
        return edicions;
    }

    public void setEdicions(int edicions) {
        this.edicions = edicions;
    }


    @Override
    public String toString() {
        return "Album{" + "num= " + num + ", any= " + any + ", impres= " + edicions +  ", titol= " + titol + ", artista= " + artista + ", tipus= " + tipus + ", estrelles= " + cal + '}';
    }

    
    
}
