package FattureFX;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
public class FattureFX extends Application 
{
    static int indiceDel;
    static String roundRedBut="-fx-background-color: linear-gradient(#ff5400, #be1d00); -fx-background-radius: 30; -fx-background-insets: 0; -fx-text-fill: white;",roundTextFieldRed="-fx-focus-color: red; -fx-background-radius: 10 10 10 10;", roundTextFieldGreen="-fx-focus-color: green ; -fx-background-radius: 10 10 10 10;";
    static MenuBar menuBar=new MenuBar();
    static Stage theStage;
    static ImageView back;
    static ImageView backBase=new ImageView(new Image("file:BackBase.png"));
    static Image cestino=new Image("file:cestino.png"), matita=new Image("file:matita.png"), moneta=new Image("file:moneta.png"), seleziona=new Image("file:seleziona.png"), addQuant=new Image("file:addQuant.png");
    static Menu gClienti,gMateriali,gFatture;
    static boolean salvato=true, salvatoMat=true, salvatoFatt=true, controlloAddCli=false;
    static ArrayList<Object> clienti,materiale,fatture;
    static String dirclie="Clienti.dat" , dirmat="Materiali.dat",dirfatt="Fatture.dat";
    @Override
    public void start(Stage primaryStage) 
    {
        theStage=primaryStage;  //Creazione menu
        theStage.setResizable(false);
        primaryStage.setTitle("Fatturization");
        primaryStage.setScene(creaMenu());
        primaryStage.setMaxHeight(720);
        primaryStage.setMaxWidth(980);
        primaryStage.setResizable(false);
        salvaFunz(primaryStage); 
        primaryStage.show();
    }
    public static void main(String[] args) {
        clienti=new ArrayList();
        materiale=new ArrayList();
        fatture=new ArrayList();
        vettorizzazione();
        launch(args);
    }
    public static Scene creaMenu()
    {   
        AnchorPane root = new AnchorPane();
        gClienti=new Menu("GESTIONE CLIENTI");
        vettorizzaMenuClienti();
        gMateriali=new Menu("GESTIONE MATERIALI");
        vettorizzaMenuMateriali();
        gFatture=new Menu("GESTIONE FATTURE");
        vettorizzaMenuFatture();
        menuBar.getMenus().addAll(gClienti,gMateriali,gFatture);
        menuBar.setMinWidth(980);
        back=new ImageView(new Image("file:Fatturization.png"));
        root.getChildren().addAll(back,menuBar);
        Scene main=new Scene(root,980,720);
        return main;
        
    }
    public static void salvaFunz(Stage primaryStage)
    {
        primaryStage.setOnCloseRequest(e ->
        {
            if(!salvato)
            {
                salva();
                System.out.println("Clienti salvati!");
            }
            if(!salvatoMat)
            {
                salvaMat();
                System.out.println("Materiali salvati!");
            }
                
            if(!salvatoFatt)
            {
                salvaFatt();
                System.out.println("Fatture salvati!");
            }    
        });
    }
    public static void vettorizzaMenuClienti()
    {
       
        MenuItem addCli=new MenuItem("Aggiungi cliente");
        MenuItem delCli=new MenuItem("Eliminazione Cliente manuale");
        MenuItem addCash=new MenuItem("Accredita soldi cliente");
        MenuItem listCli=new MenuItem("Lista clienti");
        gClienti.getItems().addAll(addCli,delCli,addCash,listCli);
        addCli.setOnAction(e ->
        {
           gestioneClienti.aggiungiCliente();
        });
        listCli.setOnAction(e ->
        {
           gestioneClienti.listaSituazioniClienti();
        });
        delCli.setOnAction(e ->
        {
           gestioneClienti.togliCliente();
        });
        addCash.setOnAction(e ->
        {
           gestioneClienti.accreditaSoldiCliente();
        });
    }
    public static void vettorizzaMenuMateriali()
    {
        MenuItem addMat=new MenuItem("Aggiungi materiale");
        MenuItem delMat=new MenuItem("Elimina materiale");
        MenuItem mod=new MenuItem("Modifica attributi");
        MenuItem listMat=new MenuItem("Lista materiali");
        gMateriali.getItems().addAll(addMat,delMat,mod,listMat);
        addMat.setOnAction(e ->
        {
           gestioneMateriali.aggiungiMateriale();
        });
        delMat.setOnAction(e ->
        {
           gestioneMateriali.eliminaMateriale();
        });
        mod.setOnAction(e ->
        {
           gestioneMateriali.modificaAttributi();
        });
        listMat.setOnAction(e ->
        {
           gestioneMateriali.listaMateriali();
        });
    }
    public static void vettorizzaMenuFatture()
    {
        MenuItem addFat=new MenuItem("Emetti Fattura");
        MenuItem stampFat=new MenuItem("Lista Fatture");
        gFatture.getItems().addAll(addFat,stampFat);
        addFat.setOnAction(e ->
        {
           gestioneFatture.emettiFattura();
        });
        stampFat.setOnAction(e ->
        {
           gestioneFatture.listaFatture();
        }); 
    }

    public static void tornaIniziale()
    {
        AnchorPane rootMain=new AnchorPane();
        rootMain.getChildren().addAll(back,menuBar);
        theStage.setScene(new Scene(rootMain,980,720));
    }

    private static void salva() 
    {
        try
        {
            FileOutputStream f = new FileOutputStream(dirclie);
            ObjectOutputStream file = new ObjectOutputStream(f);
            for(int i=0;i<clienti.size();i++)
                file.writeObject(clienti.get(i));
            file.flush();
            f.close();
        }
        catch(IOException e){System.out.println("Eccezione: "+e);}
        System.out.println("FILE .DAT AGGIORNATO");
    }
    private static void salvaMat() 
    {
        try
        {
            FileOutputStream f = new FileOutputStream(dirmat);
            ObjectOutputStream file = new ObjectOutputStream(f);
            for(int i=0;i<materiale.size();i++)
                file.writeObject(materiale.get(i));
            file.flush();
            f.close();
        }
        catch(IOException e){System.out.println("Eccezione: "+e);}
        System.out.println("FILE .DAT AGGIORNATO");
    }
    private static void salvaFatt() 
    {
        try
        {
            FileOutputStream f = new FileOutputStream(dirfatt);
            ObjectOutputStream file = new ObjectOutputStream(f);
            for(int i=0;i<fatture.size();i++)
                file.writeObject(fatture.get(i));
            file.flush();
            f.close();
        }
        catch(IOException e){System.out.println("Eccezione: "+e);}
        System.out.println("FILE .DAT AGGIORNATO");
    }
    private static void vettorizzazione() 
    {
        int conta=0;
        try
        { 
            ObjectInputStream f = new ObjectInputStream(new FileInputStream(dirclie));
            while(true)
            { 
                try
                { 
                    clienti.add(f.readObject());
                    conta++;
                }
                catch(EOFException e) 
                { 
                    System.out.println("Caricati "+ conta + " oggetti nel vettore clienti");
                    break; 
                }
            }
        }
        catch (Exception e) {System.out.println("File Vuoto");}  
        conta=0;
        try
        { 
            ObjectInputStream f = new ObjectInputStream(new FileInputStream(dirmat));
            while(true)
            { 
                try
                { 
                    materiale.add(f.readObject());
                    conta++;
                }
                catch(EOFException e) 
                { 
                    System.out.println("Caricati "+ conta + " oggetti nel vettore materiali");
                    break; 
                }
            }
        }
        catch (Exception e) {System.out.println("File Vuoto");}  
        conta=0;
        try
        { 
            ObjectInputStream f = new ObjectInputStream(new FileInputStream(dirfatt));
            while(true)
            { 
                try
                { 
                    fatture.add(f.readObject());
                    conta++;
                }
                catch(EOFException e) 
                { 
                    System.out.println("Caricati "+ conta + " oggetti nel vettore fatture");
                    break; 
                }
            }
        }
        catch (Exception e) {System.out.println("File Vuoto");}  
    }
}
