package FattureFX;
import java.io.Serializable;
public class Fattura implements Serializable 
{
    private final int percIva=22;
    private Cliente destinatario;
    private Materiale materiale;
    private int qta;
    private double prezzo;
    
    public Fattura(Object cliente, Object materiale, int qta, double prezzo)
    {
        destinatario=(Cliente) cliente;
        this.materiale=(Materiale) materiale;
        this.qta=qta;
        this.prezzo=prezzo;
    }
    public String getMateriale()
    {
        return materiale.getDesc();
    }
    public int getQta()
    {
        return qta;
    }
    public double getPrezzo()
    {
        return prezzo;
    }
    public String getPartitaIva()
    {
        return destinatario.getPartitaIva();
    }
    public void addebita(Double p)
    {
        destinatario.paga(p);
    }
    public void decMat(int q)
    {
        materiale.utilizzo(q);
    }
}
