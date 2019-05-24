package FattureFX;
import java.io.Serializable;
public class Materiale implements Serializable
{
    private String descrizione;
    private int quantita;
    private double prezzo;
    
    public Materiale(String s, int q, double f)
    {
        descrizione=s.toUpperCase();
        quantita=q;
        prezzo=f;
    }
    public String getDesc()
    {
        return descrizione;
    }
    public int getQuantita()
    {
        return quantita;
    }
    public double getPrezzo()
    {
        return prezzo;
    }
    public void setQuantita(int q)
    {
        quantita=q;
    }
    public void setPrezzo(double f)
    {
        prezzo=f;
    }
    public void setDesc(String s)
    {
        descrizione=s;
    }
    public void utilizzo(int qta)
    {
        quantita-=qta;
    }
    public void addQuant(int qta)
    {
        quantita+=qta;
    }
}
