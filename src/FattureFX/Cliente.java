package FattureFX;
import java.io.Serializable;
class Cliente implements Serializable
{
    private String nome;
    private String partitaIva;
    private double saldo;
    public Cliente(String nome,String pIva, Double saldo)
    {
        
        this.nome=nome.toUpperCase();
        partitaIva=pIva.toUpperCase();
        this.saldo=saldo;
    }
    public void addebita(double importo)
    {
        saldo+=importo;
    }
    public void paga(double importo)
    {
        saldo-=importo;
    }
    public String getNome()
    {
        return nome;
    }
    public String getPartitaIva()
    {
        return partitaIva;
    }
    public double getSaldo()
    {
        return saldo;
    }
    public void setNome(String nome)
    {
        this.nome=nome.toUpperCase();
    }
    public void setPartitaIva(String pIva)
    {
        partitaIva=pIva.toUpperCase();
    }
    public void setSaldo(Double saldo)
    {
        this.saldo=saldo;
    }
}
