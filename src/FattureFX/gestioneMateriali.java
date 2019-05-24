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
public class gestioneMateriali 
{
    public static void aggiungiMateriale()
    {      
        AnchorPane root=new AnchorPane();
        Label descrizioneL=new Label("Inserisci descrizione");
        descrizioneL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label quantitaL=new Label("Inserisci quantita'");
        quantitaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label prezzoL=new Label("Inserisci prezzo");
        prezzoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField descrizione = new TextField();
        descrizione.setBorder(Border.EMPTY);
        descrizione.setLayoutX(100); descrizione.setLayoutY(200);                                        
        descrizione.setPrefSize(190,30);                                                          
        descrizione.setStyle(roundTextFieldGreen);
        TextField quantita = new TextField();
        quantita.setBorder(Border.EMPTY);
        quantita.setLayoutX(100); quantita.setLayoutY(400);                                       
        quantita.setPrefSize(190,30);                                                          
        quantita.setStyle(roundTextFieldGreen);
        TextField prezzo = new TextField();
        prezzo.setBorder(Border.EMPTY);
        prezzo.setLayoutX(500); prezzo.setLayoutY(200);                                    
        prezzo.setPrefSize(190,30);                                                      
        prezzo.setStyle(roundTextFieldGreen);
        Button conf=new Button("Conferma");
        conf.setLayoutX(750);  conf.setLayoutY(600);                                     
        conf.setPrefSize(80,40);                                                 
        conf.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                                   
        ind.setPrefSize(80,40);                                                   
        ind.setStyle(FattureFX.roundRedBut);                
        descrizioneL.setLayoutX(descrizione.getLayoutX());  descrizioneL.setLayoutY(descrizione.getLayoutY()-25);     
        prezzoL.setLayoutX(prezzo.getLayoutX());  prezzoL.setLayoutY(prezzo.getLayoutY()-25);  
        quantitaL.setLayoutX(quantita.getLayoutX());  quantitaL.setLayoutY(quantita.getLayoutY()-25); 
        Label avvisoDesc=new Label("DESCRIZONE GIA' UTILIZZATA!"); 
        avvisoDesc.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoDesc.setLayoutX(descrizione.getLayoutX());   avvisoDesc.setLayoutY(descrizione.getLayoutY()+40); 
        avvisoDesc.setVisible(false);
        Label avvisoPrezzoNotDouble=new Label("PREZZO NON E' UN NUMERO!"); 
        avvisoPrezzoNotDouble.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoPrezzoNotDouble.setLayoutX(prezzo.getLayoutX());   avvisoPrezzoNotDouble.setLayoutY(prezzo.getLayoutY()+40); 
        avvisoPrezzoNotDouble.setVisible(false); 
        Label avvisoQtaNotInt=new Label("QUANTITA' NON E' UN INTERO!"); 
        avvisoQtaNotInt.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoQtaNotInt.setLayoutX(quantita.getLayoutX());   avvisoQtaNotInt.setLayoutY(quantita.getLayoutY()+40); 
        avvisoQtaNotInt.setVisible(false); 
        Label avvisoPrezzo=new Label("PREZZO MINORE DI 0!"); 
        avvisoPrezzo.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoPrezzo.setLayoutX(prezzo.getLayoutX());   avvisoPrezzo.setLayoutY(prezzo.getLayoutY()+40); 
        avvisoPrezzo.setVisible(false); 
        Label avvisoQta=new Label("QUANTITA' MINORE DI 0!"); 
        avvisoQta.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoQta.setLayoutX(quantita.getLayoutX());   avvisoQta.setLayoutY(quantita.getLayoutY()+40); 
        avvisoQta.setVisible(false);
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,descrizione,descrizioneL,prezzo,quantitaL,quantita,prezzoL,conf,ind,avvisoQtaNotInt,avvisoPrezzoNotDouble,avvisoDesc,avvisoQta,avvisoPrezzo);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        conf.setOnAction(e ->
        {
           
            if(descrizione.getText().equals(""))
                descrizione.setStyle(FattureFX.roundTextFieldRed);
            else
                descrizione.setStyle(roundTextFieldGreen);
            if(quantita.getText().equals(""))
                quantita.setStyle(FattureFX.roundTextFieldRed);
            else
            {
                    quantita.setStyle(roundTextFieldGreen);              
            }   
            boolean controllo3=true;
            avvisoDesc.setVisible(false);
            for(int i=0;i<FattureFX.materiale.size()&&controllo3;i++)
                    if(descrizione.getText().toUpperCase().equals(((Materiale)FattureFX.materiale.get(i)).getDesc()))
                    {
                        controllo3=false;
                        avvisoDesc.setVisible(true);
                        descrizione.setStyle(FattureFX.roundTextFieldRed);
                    }     
            boolean controllo=true, controllo2=true;
            Double prezzoD=0.0;  
            int quantitaD=0;
            if(!quantita.getText().equals(""))
            {
                    try
                {
                    controllo=true;
                    avvisoQtaNotInt.setVisible(false); 
                    quantitaD=Integer.parseInt(quantita.getText());     
                }
                catch(NumberFormatException g)
                {
                    System.out.print(g);    
                    quantita.setStyle(FattureFX.roundTextFieldRed);
                    avvisoQtaNotInt.setVisible(true); 
                    controllo=false;
                } 
            }
            else
                quantita.setStyle(FattureFX.roundTextFieldRed);
            if(!prezzo.getText().equals(""))
            {
                try
                {
                    controllo2=true;
                    avvisoPrezzoNotDouble.setVisible(false); 
                    prezzoD=Double.parseDouble(prezzo.getText());

                }
                catch(NumberFormatException g)
                {
                    System.out.print(g);    
                    prezzo.setStyle(FattureFX.roundTextFieldRed);
                    avvisoPrezzoNotDouble.setVisible(true); 
                    controllo2=false;
                } 
            }
            else
                prezzo.setStyle(FattureFX.roundTextFieldRed);
            if(quantitaD<0)
            {
                quantita.setStyle(FattureFX.roundTextFieldRed);
                avvisoQta.setVisible(true); 
            }
            else
            {
                avvisoQta.setVisible(false); 
                quantita.setStyle(FattureFX.roundTextFieldGreen);
            }
            if(prezzoD<0)
            {
                prezzo.setStyle(FattureFX.roundTextFieldRed);
                avvisoPrezzo.setVisible(true); 
            }
            else
            {
                avvisoPrezzo.setVisible(false); 
                prezzo.setStyle(FattureFX.roundTextFieldGreen);
            }
            if(!descrizione.getText().equals("")&&!quantita.getText().equals("")&&!prezzo.getText().equals("")&&prezzoD>0&&quantitaD>=0&&controllo&&controllo2&&controllo3) //controllo che si possa tornare indietro solo se i campi sono pieni
                {
                    FattureFX.materiale.add(new Materiale(descrizione.getText(),quantitaD,prezzoD));  //passo i dati al costruttore della classe Clienti
                    FattureFX.salvatoMat=false;
                    FattureFX.tornaIniziale(); //torno alla schermata iniziale
                }
            
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();
    }
    public static void eliminaMateriale()
    {
        AnchorPane root=new AnchorPane();
        Label delL=new Label("Inserisci descrizione materiale da cancellare");
        delL.setStyle("-fx-font: 18 arial; -fx-text-fill: WHITE;");
        TextField del = new TextField();
        del.setBorder(Border.EMPTY);
        del.setLayoutX(300); del.setLayoutY(280);                                        
        del.setPrefSize(360,30);                                                          
        Button conf=new Button("Conferma");
        conf.setLayoutX(380);  conf.setLayoutY(350);                                    
        conf.setPrefSize(80,40);                                                          
        Button ind=new Button("Indietro");
        ind.setLayoutX(480);  ind.setLayoutY(350);                                         
        ind.setPrefSize(80,40);                                                           
        delL.setLayoutX(del.getLayoutX());  delL.setLayoutY(del.getLayoutY()-25);      
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,del,delL,conf,ind);    //aggiungo all'AnchorPane
        Scene delCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(delCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        del.setStyle(roundTextFieldGreen); 
        conf.setStyle(FattureFX.roundRedBut);
        ind.setStyle(FattureFX.roundRedBut);
        conf.setOnAction(e ->
        {
            if(!del.getText().equals(""))
            {
                String desc;
                FattureFX.indiceDel=-1;
                boolean controllo=false;
                desc=del.getText().toUpperCase();
                for(int i=0;i<FattureFX.materiale.size()&&!controllo;i++)
                    if(desc.equals(((Materiale)FattureFX.materiale.get(i)).getDesc()))
                    {
                        FattureFX.indiceDel=i;
                        controllo=true;
                    }  
                if(FattureFX.indiceDel==-1)  
                    del.setStyle(FattureFX.roundTextFieldRed);   
                else                  
                    alertDelWindow(((Materiale)FattureFX.materiale.get( FattureFX.indiceDel)).getDesc(),FattureFX.indiceDel);
                    
                if(del.getText().equals(""))
                    del.setStyle(FattureFX.roundTextFieldRed);
                else
                    del.setStyle(roundTextFieldGreen);                    
            } 
            else
                del.setStyle(FattureFX.roundTextFieldRed);
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();    
    }
    public static void alertDelWindow(String nome, int i)
    {
        Button conf=new Button("Conferma");
        Button ann=new Button("Annulla");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        HBox hbox = new HBox();
        Text t=new Text("Sei sicuro di voler cancellare il materiale "+nome+" dal magazzino?");
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
            FattureFX.materiale.remove(i);
            FattureFX.tornaIniziale();    
            FattureFX.salvatoMat=false;
            dialogStage.close(); //torno alla schermata iniziale  
        });  
    }
    public static void modificaAttributi()
    {
        AnchorPane root=new AnchorPane();
        Label modL=new Label("Inserisci descrizione materiale da modificare");
        modL.setStyle("-fx-font: 18 arial; -fx-text-fill: WHITE;");
        TextField mod = new TextField();
        mod.setBorder(Border.EMPTY);
        mod.setLayoutX(300); mod.setLayoutY(280);                                        
        mod.setPrefSize(360,30);                                                          
        Button conf=new Button("Conferma");
        conf.setLayoutX(380);  conf.setLayoutY(350);                                    
        conf.setPrefSize(80,40);                                                          
        Button ind=new Button("Indietro");
        ind.setLayoutX(480);  ind.setLayoutY(350);                                         
        ind.setPrefSize(80,40);                                                           
        modL.setLayoutX(mod.getLayoutX());  modL.setLayoutY(mod.getLayoutY()-25);      
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,mod,modL,conf,ind);    //aggiungo all'AnchorPane
        Scene delCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(delCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        mod.setStyle(roundTextFieldGreen); 
        conf.setStyle(FattureFX.roundRedBut);
        ind.setStyle(FattureFX.roundRedBut);
        conf.setOnAction(e ->
        {
            if(!mod.getText().equals(""))
            {
                String desc;
                FattureFX.indiceDel=-1;
                boolean controllo=false;
                desc=mod.getText().toUpperCase();
                for(int i=0;i<FattureFX.materiale.size()&&!controllo;i++)
                    if(desc.equals(((Materiale)FattureFX.materiale.get(i)).getDesc()))
                    {
                        FattureFX.indiceDel=i;
                        controllo=true;
                    }  
                if(FattureFX.indiceDel==-1)  
                    mod.setStyle(FattureFX.roundTextFieldRed);   
                else                  
                    modifica(FattureFX.indiceDel);
                    
                if(mod.getText().equals(""))
                    mod.setStyle(FattureFX.roundTextFieldRed);
                else
                    mod.setStyle(roundTextFieldGreen);                    
            } 
            else
                mod.setStyle(FattureFX.roundTextFieldRed);
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();    
    }
    public static void modifica(int i)
    {
        AnchorPane root=new AnchorPane();
        String descBuff=((Materiale)FattureFX.materiale.get(i)).getDesc(), quantitaBuff=""+((Materiale)FattureFX.materiale.get(i)).getQuantita(), prezzoBuff=""+((Materiale)FattureFX.materiale.get(i)).getPrezzo();
        Label descL=new Label("Inserisci descrizione");
        descL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label quantitaL=new Label("Inserisci quantita'");
        quantitaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label prezzoL=new Label("Inserisci prezzo");
        prezzoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField desc = new TextField();
        desc.setBorder(Border.EMPTY);
        desc.setLayoutX(100); desc.setLayoutY(200);                                        
        desc.setPrefSize(190,30);                                                         
        desc.setStyle(roundTextFieldGreen);
        desc.setText(descBuff);
        TextField quantita = new TextField();
        quantita.setBorder(Border.EMPTY);
        quantita.setLayoutX(100); quantita.setLayoutY(400);                                    
        quantita.setPrefSize(190,30);                                             
        quantita.setStyle(roundTextFieldGreen);
        quantita.setText(quantitaBuff);
        TextField prezzo = new TextField();
        prezzo.setBorder(Border.EMPTY);
        prezzo.setLayoutX(500); prezzo.setLayoutY(200);             
        prezzo.setPrefSize(190,30);                                  
        prezzo.setStyle(roundTextFieldGreen);
        prezzo.setText(prezzoBuff);
        Button conf=new Button("Conferma");
        conf.setLayoutX(750);  conf.setLayoutY(600);                                       
        conf.setPrefSize(80,40);                                           
        conf.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                              
        ind.setPrefSize(80,40);                                            
        ind.setStyle(FattureFX.roundRedBut);                
        descL.setLayoutX(desc.getLayoutX());  descL.setLayoutY(desc.getLayoutY()-25);      
        prezzoL.setLayoutX(prezzo.getLayoutX());  prezzoL.setLayoutY(prezzo.getLayoutY()-25); 
        quantitaL.setLayoutX(quantita.getLayoutX());  quantitaL.setLayoutY(quantita.getLayoutY()-25);    
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,desc,descL,prezzo,quantitaL,quantita,prezzoL,conf,ind);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        conf.setOnAction(e ->
        {
            if(desc.getText().equals(""))
                desc.setStyle(FattureFX.roundTextFieldRed);
            else
                desc.setStyle(roundTextFieldGreen);
            if(quantita.getText().equals(""))
                quantita.setStyle(FattureFX.roundTextFieldRed);
            else       
                quantita.setStyle(roundTextFieldGreen);           
            if(prezzo.getText().equals(""))
                prezzo.setStyle(FattureFX.roundTextFieldRed);
            else
                prezzo.setStyle(roundTextFieldGreen);
            if(!desc.getText().equals(descBuff))
                ((Materiale)FattureFX.materiale.get(i)).setDesc(desc.getText());
            boolean controllo=true, controllo2=true;
            try
            {
                controllo=true;
                int quantitaD;
                quantitaD=Integer.parseInt(quantita.getText()); 
                if(!quantita.getText().equals(quantitaBuff))
                    ((Materiale)FattureFX.materiale.get(i)).setQuantita(quantitaD);
            }
            catch(NumberFormatException g)
            {
                controllo=false;
                System.out.print(g);    
                quantita.setStyle(FattureFX.roundTextFieldRed);
            } 
            try
            {
                controllo2=true;
                Double prezzoD;
                prezzoD=Double.parseDouble(prezzo.getText());   
                if(!prezzo.getText().equals(prezzoBuff))
                    ((Materiale)FattureFX.materiale.get(i)).setPrezzo(prezzoD);
            }
            catch(NumberFormatException g)
            {
                controllo2=false;
                System.out.print(g);    
                prezzo.setStyle(FattureFX.roundTextFieldRed);
            } 
            if(!desc.getText().equals("")&&!quantita.getText().equals("")&&!prezzo.getText().equals("")&&controllo&&controllo2) //controllo che si possa tornare indietro solo se i campi sono pieni
                FattureFX.tornaIniziale(); //torno alla schermata iniziale
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla home
        });
        FattureFX.theStage.show();
        FattureFX.salvatoMat=false;
    }
    public static void listaMateriali()
    {
        ScrollPane root=new ScrollPane();
        GridPane grid=new GridPane();
        grid.setVgap(10); 
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1=new ColumnConstraints(); col1.setPercentWidth(45);
        ColumnConstraints col2=new ColumnConstraints(); col2.setPercentWidth(35);
        ColumnConstraints col3=new ColumnConstraints(); col3.setPercentWidth(21);
        ColumnConstraints col4=new ColumnConstraints(); col4.setPercentWidth(6);
        ColumnConstraints col5=new ColumnConstraints(); col5.setPercentWidth(6);
        ColumnConstraints col6=new ColumnConstraints(); col6.setPercentWidth(6);
        grid.getColumnConstraints().addAll(col1,col2,col3,col4,col5,col6);
        Label desc=new Label("Descrizione");
        desc.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(desc,0,0);
        Label prezzo=new Label("Prezzo");
        prezzo.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(prezzo,1,0);
        Label quantita=new Label("Quantita'");
        quantita.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(quantita,2,0);
        ArrayList<Label> descriz = new ArrayList();
        ArrayList<Label> prezzi = new ArrayList();
        ArrayList<Label> quanti = new ArrayList();
        ArrayList<Button> aggiungi = new ArrayList();
        ArrayList<Button> modifica = new ArrayList();
        ArrayList<Button> cancella = new ArrayList();
        for(int i=0;i<FattureFX.materiale.size();i++)
        {
            descriz.add(new Label(((Materiale)FattureFX.materiale.get(i)).getDesc()));
            prezzi.add(new Label(""+((Materiale)FattureFX.materiale.get(i)).getPrezzo()));           
            quanti.add(new Label(""+((Materiale)FattureFX.materiale.get(i)).getQuantita()));
            modifica.add(new Button("",new ImageView(FattureFX.matita)));
            cancella.add(new Button("",new ImageView(FattureFX.cestino)));
            aggiungi.add(new Button("",new ImageView(FattureFX.addQuant)));
            if(((Materiale)FattureFX.materiale.get(i)).getQuantita()==0)
            {
                quanti.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                descriz.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                prezzi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
            }
            else
            {
                quanti.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                descriz.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                prezzi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            }
            grid.setConstraints(descriz.get(i),0,i+1);
            grid.setConstraints(prezzi.get(i),1,i+1);
            grid.setConstraints(quanti.get(i),2,i+1);
            grid.setConstraints(aggiungi.get(i),3,i+1);
            grid.setConstraints(modifica.get(i),4,i+1);
            grid.setConstraints(cancella.get(i),5,i+1);
            grid.getChildren().addAll(descriz.get(i),prezzi.get(i),quanti.get(i),modifica.get(i),cancella.get(i),aggiungi.get(i));
        }
        for(int k=0;k<modifica.size();k++)
        {
            int j=k;
            aggiungi.get(k).setOnAction(e ->
            {
                addQta(j);   
            });
            modifica.get(k).setOnAction(e ->
            {
                modifica(j);   
            });
            cancella.get(k).setOnAction(e ->
            {
                alertDelWindow(((Materiale)FattureFX.materiale.get(j)).getDesc(),j);
            });
        }
        grid.getChildren().addAll(desc,prezzo,quantita);     
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
    public static void addQta(int i)
    {
        Button conf=new Button("Conferma");
        Button ann=new Button("Annulla");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        HBox hbox = new HBox();
        TextField t=new TextField("Inserisci quantita' arrivata in magazzino");
        //t.setFill(Color.WHITE);
        t.setStyle(FattureFX.roundTextFieldGreen);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(25));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(t,conf,ann);
        hbox.setStyle("-fx-background: rgb(45,45,45);");
        dialogStage.setTitle("AggiungiQuantita'");
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
            try
            {
                ((Materiale) FattureFX.materiale.get(i)).addQuant(Integer.parseInt(t.getText()));
                FattureFX.tornaIniziale();    
                FattureFX.salvatoMat=false;
                dialogStage.close();  //torno alla schermata iniziale 
            }
            catch(NumberFormatException g)
            {
                System.out.println(g); 
                t.setStyle(FattureFX.roundTextFieldRed); 
            } 
        });  
    }
}
