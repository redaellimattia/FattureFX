package FattureFX;

import static FattureFX.FattureFX.roundTextFieldGreen;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class gestioneFatture 
{
    static int indiceMat=-1, qta;
    static Double prezzoD;
    public static void emettiFattura()
    {
        AnchorPane root=new AnchorPane();
        Label matL=new Label("Materiale");
        matL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label quantitaL=new Label("Quantita'");
        quantitaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label prezzoL=new Label("Prezzo");
        prezzoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField mat = new TextField();
        mat.setBorder(Border.EMPTY);
        mat.setLayoutX(150); mat.setLayoutY(200);                                    
        mat.setPrefSize(190,30);                                          
        mat.setStyle(roundTextFieldGreen);
        TextField prezzo = new TextField();
        prezzo.setBorder(Border.EMPTY);
        prezzo.setLayoutX(mat.getLayoutX()); prezzo.setLayoutY(300);           
        prezzo.setPrefSize(190,30);                                                      
        prezzo.setStyle(roundTextFieldGreen);
        prezzo.setEditable(false);
        TextField quantita = new TextField();
        quantita.setBorder(Border.EMPTY);
        quantita.setLayoutX(550); quantita.setLayoutY(200);                       
        quantita.setPrefSize(190,30);                                                         
        quantita.setStyle(roundTextFieldGreen);
        Button profEx=new Button("Fattura a Cliente gia' esistente");
        profEx.setLayoutX(prezzo.getLayoutX());  profEx.setLayoutY(prezzo.getLayoutY()+80);                            
        profEx.setPrefSize(190,60);               
        profEx.setStyle(FattureFX.roundRedBut);
        Button profNotEx=new Button("Fattura a nuovo Cliente");
        profNotEx.setLayoutX(quantita.getLayoutX());  profNotEx.setLayoutY(prezzo.getLayoutY()+80);                            
        profNotEx.setPrefSize(190,60);               
        profNotEx.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                  
        ind.setPrefSize(80,40);   
        ind.setStyle(FattureFX.roundRedBut);                
        matL.setLayoutX(mat.getLayoutX());  matL.setLayoutY(mat.getLayoutY()-25);   
        quantitaL.setLayoutX(quantita.getLayoutX());  quantitaL.setLayoutY(quantita.getLayoutY()-25);  
        prezzoL.setLayoutX(prezzo.getLayoutX());  prezzoL.setLayoutY(prezzo.getLayoutY()-25);    
        Label avvisoQta=new Label("QUANTITA SUPERIORE A QUELLA \nPRESENTE IN MAGAZZINO!"); //Avviso in caso si selezioni una quantitá superiore a quella presente in magazzino
        avvisoQta.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoQta.setLayoutX(quantita.getLayoutX());   avvisoQta.setLayoutY(quantita.getLayoutY()+40); 
        avvisoQta.setVisible(false); 
        Label avvisoMatEmpty=new Label("NESSUN MATERIALE IN MAGAZZINO!"); //Avviso se il vet materiale é vuoto
        avvisoMatEmpty.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoMatEmpty.setLayoutX(quantita.getLayoutX());   avvisoMatEmpty.setLayoutY(prezzo.getLayoutY()-5); 
        avvisoMatEmpty.setVisible(false);
        Label avvisoMatNotFound=new Label("MATERIALE NON TROVATO!"); //Avviso in caso di materiale non trovato
        avvisoMatNotFound.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoMatNotFound.setLayoutX(mat.getLayoutX());   avvisoMatNotFound.setLayoutY(mat.getLayoutY()+40); 
        avvisoMatNotFound.setVisible(false);
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,mat,matL,quantita,prezzoL,prezzo,quantitaL,profEx,profNotEx,ind,avvisoQta,avvisoMatEmpty,avvisoMatNotFound);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        profEx.setOnAction(e ->
        {
            if(controlliPrimaPag(mat,quantita,prezzo,avvisoQta,avvisoMatEmpty,avvisoMatNotFound)) //Eseguo i controlli sugli inserimenti della prima pag
                gestioneClienti.listaProfEx();
        });
        profNotEx.setOnAction(e ->
        {
            if(controlliPrimaPag(mat,quantita,prezzo,avvisoQta,avvisoMatEmpty,avvisoMatNotFound)) //Eseguo i controlli sugli inserimenti della prima pag
                gestioneClienti.aggiungiClienteFatt();
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();
    }
    private static boolean controlliPrimaPag(TextField mat, TextField quantita, TextField prezzo,Label avvisoQta,Label avvisoMatEmpty, Label avvisoMatNotFound)
    {
        prezzo.setText(""); //Svuoto il textfield del prezzo
        if(FattureFX.materiale.size()==0)
            avvisoMatEmpty.setVisible(true);
        else
            avvisoMatEmpty.setVisible(false);
        if(mat.getText().equals(""))
            mat.setStyle(FattureFX.roundTextFieldRed);
        else
            mat.setStyle(roundTextFieldGreen);
        if(quantita.getText().equals(""))
            quantita.setStyle(FattureFX.roundTextFieldRed);
        else
            quantita.setStyle(roundTextFieldGreen);
        boolean controlloMat=true;
        for(int i=0;i<FattureFX.materiale.size()&&controlloMat;i++)
        {
            if(mat.getText().toUpperCase().equals(((Materiale)FattureFX.materiale.get(i)).getDesc()))
            {
                indiceMat=i;
                controlloMat=false;
            }
        } 
        if(indiceMat==-1) //In caso non trovo il materiale lo segnalo
        {
            mat.setStyle(FattureFX.roundTextFieldRed); 
            avvisoMatNotFound.setVisible(true);
        }            
        else
        {
            mat.setStyle(roundTextFieldGreen); 
            avvisoMatNotFound.setVisible(false);
        }
               
        int quantitaI=0;
        try
        {              
            quantitaI=Integer.parseInt(quantita.getText());
        }
        catch(NumberFormatException g)
        {
            System.out.print(g);    
            quantita.setStyle(FattureFX.roundTextFieldRed);      
        }
        boolean controlloQta=true;
        if(quantitaI<1) //In caso si inserisca quantita<1 segnalo
        {
            quantita.setStyle(FattureFX.roundTextFieldRed);
            controlloQta=false;
            avvisoQta.setVisible(false);
        }
        else
        {
            if(quantitaI>((Materiale)FattureFX.materiale.get(indiceMat)).getQuantita()) //In caso si inserisca una quantitá maggiore di quella presente in magazzino segnalo
            {
                quantita.setStyle(FattureFX.roundTextFieldRed);
                controlloQta=false;
                avvisoQta.setVisible(true);
            }
            else
            {
                quantita.setStyle(roundTextFieldGreen);
                avvisoQta.setVisible(false);
            }      
            prezzo.setText(""+quantitaI*(((Materiale)(FattureFX.materiale.get(indiceMat))).getPrezzo())); //Stampo nella label il prezzo totale
        }
            
        
        qta=quantitaI;
        prezzoD=Double.parseDouble(prezzo.getText());
        if(!mat.getText().equals("")&&!quantita.getText().equals("")&&!controlloMat&&controlloQta)
            return true;
        else
            return false;
    }
    public static void listaFatture()
    {
        ScrollPane root=new ScrollPane();
        GridPane grid=new GridPane();
        grid.setVgap(10); 
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1=new ColumnConstraints(); col1.setPercentWidth(30);
        ColumnConstraints col2=new ColumnConstraints(); col2.setPercentWidth(30);
        ColumnConstraints col3=new ColumnConstraints(); col3.setPercentWidth(30);
        ColumnConstraints col4=new ColumnConstraints(); col4.setPercentWidth(30);
        ColumnConstraints col5=new ColumnConstraints(); col5.setPercentWidth(6);
        grid.getColumnConstraints().addAll(col1,col2,col3,col4,col5);
        Label matL=new Label("Materiale");
        matL.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(matL,0,0);
        Label QuantitaL=new Label("Quantita'");
        QuantitaL.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(QuantitaL,1,0);
        Label prezzoL=new Label("Prezzo");
        prezzoL.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(prezzoL,2,0);
        Label pIvaCliL=new Label("Partita IVA");
        pIvaCliL.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(pIvaCliL,3,0);
        ArrayList<Label> mat = new ArrayList();
        ArrayList<Label> qta = new ArrayList();
        ArrayList<Label> prezzi = new ArrayList();
        ArrayList<Label> pIve = new ArrayList();
        ArrayList<Button> cancella = new ArrayList();
        for(int i=0;i<FattureFX.fatture.size();i++)
        {
            mat.add(new Label(((Fattura)FattureFX.fatture.get(i)).getMateriale()));
            qta.add(new Label(""+((Fattura)FattureFX.fatture.get(i)).getQta()));           
            prezzi.add(new Label(""+((Fattura)FattureFX.fatture.get(i)).getPrezzo()));
            pIve.add(new Label(((Fattura)FattureFX.fatture.get(i)).getPartitaIva()));
            cancella.add(new Button("",new ImageView(FattureFX.cestino)));
            mat.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            qta.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            prezzi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            pIve.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            grid.setConstraints(mat.get(i),0,i+1);
            grid.setConstraints(qta.get(i),1,i+1);
            grid.setConstraints(prezzi.get(i),2,i+1);
            grid.setConstraints(pIve.get(i),3,i+1);
            grid.setConstraints(cancella.get(i),4,i+1);
            grid.getChildren().addAll(mat.get(i),qta.get(i),prezzi.get(i),pIve.get(i),cancella.get(i));
        }
        for(int k=0;k<cancella.size();k++)
        {
            int j=k;
            cancella.get(k).setOnAction(e ->
            {
                alertDelWindow(j); //Chiedo conferma prima di eliminare
            });
        }
        grid.getChildren().addAll(matL,QuantitaL,prezzoL,pIvaCliL);     
        VBox vb=new VBox();
        vb.getChildren().addAll(FattureFX.menuBar,grid);
        root.setContent(vb);
        root.setFitToWidth(true);
        root.setStyle("-fx-background: rgb(45,45,45);");
        Scene main=new Scene(root,980,720);
        FattureFX.theStage.setScene(main);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        FattureFX.theStage.show();
    }
    public static void alertDelWindow(int i)
    {
        Button conf=new Button("Conferma");
        Button ann=new Button("Annulla");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        HBox hbox = new HBox();
        Text t=new Text("Sei sicuro di voler cancellare la fattura intestata a "+(((Fattura)FattureFX.fatture.get(i)).getPartitaIva())+" con materiale: "+(((Fattura)FattureFX.fatture.get(i)).getMateriale())+"?");
        t.setFill(Color.WHITE);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(25));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(t,conf,ann);
        hbox.setStyle("-fx-background: rgb(45,45,45);");
        dialogStage.setTitle("Alert");
        dialogStage.setResizable(false);
        dialogStage.setScene(new Scene(hbox));
        dialogStage.show();
        ann.setStyle(FattureFX.roundRedBut);
        conf.setStyle(FattureFX.roundRedBut);
        ann.setOnAction(e ->
        {
            dialogStage.close(); //torno alla schermata iniziale  
        });
        conf.setOnAction(e ->
        {
            FattureFX.fatture.remove(i);
            FattureFX.tornaIniziale();    
            FattureFX.salvatoFatt=false;
            dialogStage.close(); //torno alla schermata iniziale  
        });  
    }
}

