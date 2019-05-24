package FattureFX;

import static FattureFX.FattureFX.clienti;
import static FattureFX.FattureFX.roundTextFieldGreen;
import static FattureFX.gestioneFatture.indiceMat;
import static FattureFX.gestioneFatture.prezzoD;
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

public class gestioneClienti 
{
    static int indiceFat=0;
    public static void aggiungiCliente()
    {      
        AnchorPane root=new AnchorPane();
        Label nomeL=new Label("Inserisci nome");
        nomeL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label pIvaL=new Label("Inserisci partita IVA");
        pIvaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label saldoL=new Label("Inserisci saldo cliente");
        saldoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField nome = new TextField();
        nome.setBorder(Border.EMPTY);
        nome.setLayoutX(100); nome.setLayoutY(200);                                        //posiziono textfield nome
        nome.setPrefSize(190,30);                                                          //modifico grandezza nome
        nome.setStyle(roundTextFieldGreen);
        TextField pIva = new TextField();
        pIva.setBorder(Border.EMPTY);
        pIva.setLayoutX(100); pIva.setLayoutY(400);                                        //posiziono textfield pIva
        pIva.setPrefSize(190,30);                                                          //modifico grandezza pIva
        pIva.setStyle(roundTextFieldGreen);
        TextField saldo = new TextField();
        saldo.setBorder(Border.EMPTY);
        saldo.setLayoutX(500); saldo.setLayoutY(200);                                      //posiziono textfield saldo
        saldo.setPrefSize(190,30);                                                         //modifico grandezza saldo
        saldo.setStyle(roundTextFieldGreen);
        Button conf=new Button("Conferma");
        conf.setLayoutX(750);  conf.setLayoutY(600);                                       //posiziono bottone conferma
        conf.setPrefSize(80,40);                                                           //modifico grandezza conferma
        conf.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                                         //posiziono bottone indietro
        ind.setPrefSize(80,40);                                                            //modifico grandezza indietro
        ind.setStyle(FattureFX.roundRedBut);                
        nomeL.setLayoutX(nome.getLayoutX());  nomeL.setLayoutY(nome.getLayoutY()-25);      //Posiziono label inserimento nome
        saldoL.setLayoutX(saldo.getLayoutX());  saldoL.setLayoutY(saldo.getLayoutY()-25);  //Posiziono label inserimento saldo
        pIvaL.setLayoutX(pIva.getLayoutX());  pIvaL.setLayoutY(pIva.getLayoutY()-25);      //Posiziono label inserimento pIva
        Label avvisoSaldo=new Label("SALDO NON E' UN NUMERO!"); 
        avvisoSaldo.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSaldo.setLayoutX(saldo.getLayoutX());   avvisoSaldo.setLayoutY(saldo.getLayoutY()+40); 
        avvisoSaldo.setVisible(false);
        Label avvisoSpaziPIVA=new Label("SPAZI NELLA PARTITA IVA!"); 
        avvisoSpaziPIVA.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSpaziPIVA.setLayoutX(pIva.getLayoutX());   avvisoSpaziPIVA.setLayoutY(pIva.getLayoutY()+40);
        avvisoSpaziPIVA.setVisible(false); 
        Label avvisoPIVANotNew=new Label("PARTITA IVA GIA' ASSEGNATA"); 
        avvisoPIVANotNew.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoPIVANotNew.setLayoutX(pIva.getLayoutX());   avvisoPIVANotNew.setLayoutY(pIva.getLayoutY()+40); 
        avvisoPIVANotNew.setVisible(false); 
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,nome,nomeL,saldo,pIvaL,pIva,saldoL,conf,ind,avvisoSaldo,avvisoSpaziPIVA,avvisoPIVANotNew);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        conf.setOnAction(e ->
        {
            try
            {
                boolean controllo=false;
                if(nome.getText().equals(""))
                    nome.setStyle(FattureFX.roundTextFieldRed);
                else
                    nome.setStyle(roundTextFieldGreen);
                if(pIva.getText().equals(""))
                    pIva.setStyle(FattureFX.roundTextFieldRed);
                else
                {
                    controllo=false;
                    if(pIva.getText().indexOf(' ')>=0)
                    {
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                        avvisoSpaziPIVA.setVisible(true);
                    }
                    else  
                    {
                        controllo=true;
                        avvisoSpaziPIVA.setVisible(false);
                        pIva.setStyle(roundTextFieldGreen);
                    }                
                }   
                boolean controllo2=true;
                avvisoPIVANotNew.setVisible(false);
                for(int i=0;i<FattureFX.clienti.size()&&controllo;i++)
                    if(pIva.getText().toUpperCase().equals(((Cliente)FattureFX.clienti.get(i)).getPartitaIva()))
                    {
                        controllo2=false;
                        avvisoPIVANotNew.setVisible(true);
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                    }              
                if(saldo.getText().equals(""))
                    saldo.setStyle(FattureFX.roundTextFieldRed);
                else
                    saldo.setStyle(roundTextFieldGreen);
                Double saldoD;                
                avvisoSaldo.setVisible(false);
                saldoD=Double.parseDouble(saldo.getText()); 
                if(!nome.getText().equals("")&&!pIva.getText().equals("")&&!saldo.getText().equals("")&&controllo&&controllo2) //controllo che si possa tornare indietro solo se i campi sono pieni
                {
                    FattureFX.clienti.add(new Cliente(nome.getText(),pIva.getText(),saldoD));  //passo i dati al costruttore della classe Clienti
                    FattureFX.controlloAddCli=true;
                    FattureFX.salvato=false;
                    FattureFX.tornaIniziale(); //torno alla schermata iniziale
                }
                    
            }
            catch(NumberFormatException g)
            {
                System.out.print(g);    
                saldo.setStyle(FattureFX.roundTextFieldRed);
                avvisoSaldo.setVisible(true);
            }      
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();
    }
    public static void listaSituazioniClienti()
    {
        ScrollPane root=new ScrollPane();
        GridPane grid=new GridPane();
        grid.setVgap(10); 
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1=new ColumnConstraints(); col1.setPercentWidth(45);
        ColumnConstraints col2=new ColumnConstraints(); col2.setPercentWidth(45);
        ColumnConstraints col3=new ColumnConstraints(); col3.setPercentWidth(21);
        ColumnConstraints col4=new ColumnConstraints(); col4.setPercentWidth(6);
        ColumnConstraints col5=new ColumnConstraints(); col5.setPercentWidth(6);
        ColumnConstraints col6=new ColumnConstraints(); col6.setPercentWidth(5);
        grid.getColumnConstraints().addAll(col1,col2,col3,col4,col5,col6);
        Label nome=new Label("Nome");
        nome.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(nome,0,0);
        Label pIva=new Label("Partita IVA");
        pIva.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(pIva,1,0);
        Label saldo=new Label("Saldo");
        saldo.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(saldo,2,0);
        ArrayList<Label> nomi = new ArrayList();
        ArrayList<Label> pIve = new ArrayList();
        ArrayList<Label> saldi = new ArrayList();
        ArrayList<Button> modifica = new ArrayList();
        ArrayList<Button> accredita = new ArrayList();
        ArrayList<Button> cancella = new ArrayList();
        for(int i=0;i<FattureFX.clienti.size();i++)
        {
            nomi.add(new Label(((Cliente)FattureFX.clienti.get(i)).getNome()));
            pIve.add(new Label(((Cliente)FattureFX.clienti.get(i)).getPartitaIva()));           
            saldi.add(new Label(""+((Cliente)FattureFX.clienti.get(i)).getSaldo()));
            modifica.add(new Button("",new ImageView(FattureFX.matita)));
            cancella.add(new Button("",new ImageView(FattureFX.cestino)));
            accredita.add(new Button("",new ImageView(FattureFX.moneta)));
            if(((Cliente)FattureFX.clienti.get(i)).getSaldo()<0)
            {
                saldi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                nomi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                pIve.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
            }
            else
            {
                saldi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                nomi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                pIve.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            }
            grid.setConstraints(nomi.get(i),0,i+1);
            grid.setConstraints(pIve.get(i),1,i+1);
            grid.setConstraints(saldi.get(i),2,i+1);
            grid.setConstraints(accredita.get(i),3,i+1);
            grid.setConstraints(modifica.get(i),4,i+1);
            grid.setConstraints(cancella.get(i),5,i+1);
            grid.getChildren().addAll(nomi.get(i),pIve.get(i),saldi.get(i),modifica.get(i),cancella.get(i),accredita.get(i));
        }
        for(int k=0;k<modifica.size();k++)
        {
            int j=k;
            accredita.get(k).setOnAction(e ->
            {
                accWindow(j);   
            });
            modifica.get(k).setOnAction(e ->
            {
                modificaLista(j);   
            });
            cancella.get(k).setOnAction(e ->
            {
                alertDelWindow(((Cliente)FattureFX.clienti.get(j)).getPartitaIva(), ((Cliente)FattureFX.clienti.get(j)).getNome(),j);
            });
        }
        grid.getChildren().addAll(nome,pIva,saldo);     
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
    public static void modificaLista(int i)
    {
        AnchorPane root=new AnchorPane();
        String nomeBuff=((Cliente)clienti.get(i)).getNome(), pIvaBuff=((Cliente)clienti.get(i)).getPartitaIva(), saldoBuff=""+((Cliente)clienti.get(i)).getSaldo();
        Label nomeL=new Label("Inserisci nome");
        nomeL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label pIvaL=new Label("Inserisci partita IVA");
        pIvaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label saldoL=new Label("Inserisci saldo cliente");
        saldoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField nome = new TextField();
        nome.setBorder(Border.EMPTY);
        nome.setLayoutX(100); nome.setLayoutY(200);                                        //posiziono textfield nome
        nome.setPrefSize(190,30);                                                          //modifico grandezza nome
        nome.setStyle(roundTextFieldGreen);
        nome.setText(nomeBuff);
        TextField pIva = new TextField();
        pIva.setBorder(Border.EMPTY);
        pIva.setLayoutX(100); pIva.setLayoutY(400);                                        //posiziono textfield pIva
        pIva.setPrefSize(190,30);                                                          //modifico grandezza pIva
        pIva.setStyle(roundTextFieldGreen);
        pIva.setText(pIvaBuff);
        TextField saldo = new TextField();
        saldo.setBorder(Border.EMPTY);
        saldo.setLayoutX(500); saldo.setLayoutY(200);                                      //posiziono textfield saldo
        saldo.setPrefSize(190,30);                                                         //modifico grandezza saldo
        saldo.setStyle(roundTextFieldGreen);
        saldo.setText(saldoBuff);
        Button conf=new Button("Conferma");
        conf.setLayoutX(750);  conf.setLayoutY(600);                                       //posiziono bottone conferma
        conf.setPrefSize(80,40);                                                           //modifico grandezza conferma
        conf.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                                         //posiziono bottone indietro
        ind.setPrefSize(80,40);                                                            //modifico grandezza indietro
        ind.setStyle(FattureFX.roundRedBut);                
        nomeL.setLayoutX(nome.getLayoutX());  nomeL.setLayoutY(nome.getLayoutY()-25);      //Posiziono label inserimento nome
        saldoL.setLayoutX(saldo.getLayoutX());  saldoL.setLayoutY(saldo.getLayoutY()-25);  //Posiziono label inserimento saldo
        pIvaL.setLayoutX(pIva.getLayoutX());  pIvaL.setLayoutY(pIva.getLayoutY()-25);      //Posiziono label inserimento pIva
        Label avvisoSaldo=new Label("SALDO NON E' UN NUMERO!"); 
        avvisoSaldo.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSaldo.setLayoutX(saldo.getLayoutX());   avvisoSaldo.setLayoutY(saldo.getLayoutY()+40); 
        avvisoSaldo.setVisible(false);
        Label avvisoSpaziPIVA=new Label("SPAZI NELLA PARTITA IVA!"); 
        avvisoSpaziPIVA.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSpaziPIVA.setLayoutX(pIva.getLayoutX());   avvisoSpaziPIVA.setLayoutY(pIva.getLayoutY()+40);
        avvisoSpaziPIVA.setVisible(false); 
        Label avvisoPIVANotNew=new Label("PARTITA IVA GIA' ASSEGNATA"); 
        avvisoPIVANotNew.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoPIVANotNew.setLayoutX(pIva.getLayoutX());   avvisoPIVANotNew.setLayoutY(pIva.getLayoutY()+40); 
        avvisoPIVANotNew.setVisible(false); 
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,nome,nomeL,saldo,pIvaL,pIva,saldoL,conf,ind,avvisoSaldo,avvisoSpaziPIVA,avvisoPIVANotNew);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        conf.setOnAction(e ->
        {
            try
            {
                boolean controllo=false;
                if(nome.getText().equals(""))
                    nome.setStyle(FattureFX.roundTextFieldRed);
                else
                    nome.setStyle(roundTextFieldGreen);
                if(pIva.getText().equals(""))
                    pIva.setStyle(FattureFX.roundTextFieldRed);
                else
                {
                    controllo=false;
                    if(pIva.getText().indexOf(' ')>=0)
                    {
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                        avvisoSpaziPIVA.setVisible(true);
                    }
                    else  
                    {
                        controllo=true;
                        avvisoSpaziPIVA.setVisible(false);
                        pIva.setStyle(roundTextFieldGreen);
                    }      
                }      
                boolean controllo2=true;
                avvisoPIVANotNew.setVisible(false);
                for(int g=0;g<FattureFX.clienti.size()&&controllo;g++)
                    if(pIva.getText().toUpperCase().equals(((Cliente)FattureFX.clienti.get(g)).getPartitaIva())&&!pIva.getText().equals(pIvaBuff))
                    {
                        controllo2=false;
                        avvisoPIVANotNew.setVisible(true);
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                    }    
                if(saldo.getText().equals(""))
                    saldo.setStyle(FattureFX.roundTextFieldRed);
                else
                    saldo.setStyle(roundTextFieldGreen);
                Double saldoD;    
                avvisoSaldo.setVisible(false);
                saldoD=Double.parseDouble(saldo.getText());
                if(!nome.getText().equals(nomeBuff))
                        ((Cliente)clienti.get(i)).setNome(nome.getText());
                if(!pIva.getText().equals(pIvaBuff)&&controllo&&controllo2)
                        ((Cliente)clienti.get(i)).setPartitaIva(pIva.getText());
                if(!saldo.getText().equals(saldoBuff))
                        ((Cliente)clienti.get(i)).setSaldo(saldoD);
                if(!nome.getText().equals("")&&!pIva.getText().equals("")&&!saldo.getText().equals("")&&controllo&&controllo2) //controllo che si possa tornare indietro solo se i campi sono pieni
                    FattureFX.tornaIniziale(); //torno alla schermata iniziale
            }
            catch(NumberFormatException g)
            {
                System.out.print(g);    
                saldo.setStyle(FattureFX.roundTextFieldRed);
                avvisoSaldo.setVisible(true);
            }      
        });
        ind.setOnAction(e ->
        {
            listaSituazioniClienti(); //torno alla lista
        });
        FattureFX.theStage.show();
        FattureFX.salvato=false;
    }
    public static void alertDelWindow(String pIva, String nome, int i)
    {
        Button conf=new Button("Conferma");
        Button ann=new Button("Annulla");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        HBox hbox = new HBox();
        Text t=new Text("Sei sicuro di voler cancellare il cliente "+nome+" con partita IVA: "+pIva+"?");
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
            FattureFX.clienti.remove(i);
            FattureFX.tornaIniziale();    
            FattureFX.salvato=false;
            dialogStage.close(); //torno alla schermata iniziale  
        });  
    }
    public static void togliCliente()
    {
        AnchorPane root=new AnchorPane();
        Label delL=new Label("Inserisci partita IVA del cliente da Cancellare");
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
                String pIva;
                FattureFX.indiceDel=-1;
                boolean controllo=false;
                pIva=del.getText().toUpperCase();
                for(int i=0;i<clienti.size()&&!controllo;i++)
                    if(pIva.equals(((Cliente)clienti.get(i)).getPartitaIva()))
                    {
                        FattureFX.indiceDel=i;
                        controllo=true;
                    }  
                if(FattureFX.indiceDel==-1)  
                    del.setStyle(FattureFX.roundTextFieldRed);   
                else                  
                    alertDelWindow(pIva, ((Cliente)clienti.get( FattureFX.indiceDel)).getNome(),FattureFX.indiceDel);
                    
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
        FattureFX.salvato=false;
        FattureFX.theStage.show();    
    }
    public static void accreditaSoldiCliente()
    {
        AnchorPane root=new AnchorPane();
        Label accL=new Label("Inserisci partita IVA del cliente al quale vuoi accreditare soldi");
        accL.setStyle("-fx-font: 15 arial; -fx-text-fill: WHITE;");
        TextField acc = new TextField();
        acc.setBorder(Border.EMPTY);
        acc.setLayoutX(300); acc.setLayoutY(280);                                        
        acc.setPrefSize(360,30);                                                          
        Button conf=new Button("Conferma");
        conf.setLayoutX(380);  conf.setLayoutY(350);                                    
        conf.setPrefSize(80,40);                                                          
        Button ind=new Button("Indietro");
        ind.setLayoutX(480);  ind.setLayoutY(350);                                         
        ind.setPrefSize(80,40);                                                           
        accL.setLayoutX(acc.getLayoutX());  accL.setLayoutY(acc.getLayoutY()-25);      
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,acc,accL,conf,ind);    //aggiungo all'AnchorPane
        Scene delCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(delCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        acc.setStyle(roundTextFieldGreen); 
        conf.setStyle(FattureFX.roundRedBut);
        ind.setStyle(FattureFX.roundRedBut);
        conf.setOnAction(e ->
        {
            if(!acc.getText().equals(""))
            {
                String pIva;
                FattureFX.indiceDel=-1;
                boolean controllo=false;
                pIva=acc.getText().toUpperCase();
                for(int i=0;i<clienti.size()&&!controllo;i++)
                    if(pIva.equals(((Cliente)clienti.get(i)).getPartitaIva()))
                    {
                        FattureFX.indiceDel=i;
                        controllo=true;
                    }  
                if(FattureFX.indiceDel==-1)  
                    acc.setStyle(FattureFX.roundTextFieldRed);   
                else                  
                   accWindow(FattureFX.indiceDel);     
            } 
            else
                acc.setStyle(FattureFX.roundTextFieldRed);
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();    
    }
    public static void accWindow(int i)
    {
        Button conf=new Button("Conferma");
        Button ann=new Button("Annulla");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        HBox hbox = new HBox();
        TextField t=new TextField("Inserisci somma da accreditare");
        //t.setFill(Color.WHITE);
        t.setStyle(FattureFX.roundTextFieldGreen);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(25));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(t,conf,ann);
        hbox.setStyle("-fx-background: rgb(45,45,45);");
        dialogStage.setTitle("AccreditaCash");
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
                ((Cliente) FattureFX.clienti.get(i)).addebita(Double.parseDouble(t.getText()));
                FattureFX.tornaIniziale();    
                FattureFX.salvato=false;
                dialogStage.close();  //torno alla schermata iniziale 
            }
            catch(NumberFormatException g)
            {
                System.out.println(g); 
                t.setStyle(FattureFX.roundTextFieldRed); 
            }
              
        });  
    }
    public static void listaProfEx()
    {
        ScrollPane root=new ScrollPane();
        GridPane grid=new GridPane();
        grid.setVgap(10); 
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1=new ColumnConstraints(); col1.setPercentWidth(45);
        ColumnConstraints col2=new ColumnConstraints(); col2.setPercentWidth(45);
        ColumnConstraints col3=new ColumnConstraints(); col3.setPercentWidth(21);
        ColumnConstraints col4=new ColumnConstraints(); col4.setPercentWidth(6);
        grid.getColumnConstraints().addAll(col1,col2,col3,col4);
        Label nome=new Label("Nome");
        nome.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(nome,0,0);
        Label pIva=new Label("Partita IVA");
        pIva.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(pIva,1,0);
        Label saldo=new Label("Saldo");
        saldo.setStyle("-fx-font: 30 arial ; -fx-text-fill: WHITE; -fx-font-weight: bold");
        grid.setConstraints(saldo,2,0);
        ArrayList<Label> nomi = new ArrayList();
        ArrayList<Label> pIve = new ArrayList();
        ArrayList<Label> saldi = new ArrayList();
        ArrayList<Button> seleziona = new ArrayList();
        for(int i=0;i<FattureFX.clienti.size();i++)
        {
            nomi.add(new Label(((Cliente)FattureFX.clienti.get(i)).getNome()));
            pIve.add(new Label(((Cliente)FattureFX.clienti.get(i)).getPartitaIva()));           
            saldi.add(new Label(""+((Cliente)FattureFX.clienti.get(i)).getSaldo()));
            seleziona.add(new Button("",new ImageView(FattureFX.seleziona)));
            if(((Cliente)FattureFX.clienti.get(i)).getSaldo()<0)
            {
                saldi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                nomi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
                pIve.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: RED;");
            }
            else
            {
                saldi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                nomi.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
                pIve.get(i).setStyle("-fx-font: 14 arial ; -fx-text-fill: WHITE;");
            }
            grid.setConstraints(nomi.get(i),0,i+1);
            grid.setConstraints(pIve.get(i),1,i+1);
            grid.setConstraints(saldi.get(i),2,i+1);
            grid.setConstraints(seleziona.get(i),3,i+1);
            grid.getChildren().addAll(nomi.get(i),pIve.get(i),saldi.get(i),seleziona.get(i));
        }
        for(int k=0;k<seleziona.size();k++)
        {
            indiceFat=k;
            seleziona.get(k).setOnAction(e ->
            {
                
                FattureFX.fatture.add(new Fattura(FattureFX.clienti.get(indiceFat),FattureFX.materiale.get(gestioneFatture.indiceMat),gestioneFatture.qta,gestioneFatture.prezzoD));
                ((Fattura)FattureFX.fatture.get((FattureFX.fatture.size()-1))).addebita(gestioneFatture.prezzoD);
                ((Fattura)FattureFX.fatture.get(FattureFX.fatture.size()-1)).decMat(gestioneFatture.qta);
                FattureFX.salvatoMat=false;
                FattureFX.salvatoFatt=false;
                FattureFX.tornaIniziale(); //torno alla schermata iniziale  
            });
        }
        grid.getChildren().addAll(nome,pIva,saldo);     
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
    public static void aggiungiClienteFatt()
    {      
        AnchorPane root=new AnchorPane();
        Label nomeL=new Label("Inserisci nome");
        nomeL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label pIvaL=new Label("Inserisci partita IVA");
        pIvaL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        Label saldoL=new Label("Inserisci saldo cliente");
        saldoL.setStyle("-fx-font: 20 arial; -fx-text-fill: WHITE;");
        TextField nome = new TextField();
        nome.setBorder(Border.EMPTY);
        nome.setLayoutX(100); nome.setLayoutY(200);                                        //posiziono textfield nome
        nome.setPrefSize(190,30);                                                          //modifico grandezza nome
        nome.setStyle(roundTextFieldGreen);
        TextField pIva = new TextField();
        pIva.setBorder(Border.EMPTY);
        pIva.setLayoutX(100); pIva.setLayoutY(400);                                        //posiziono textfield pIva
        pIva.setPrefSize(190,30);                                                          //modifico grandezza pIva
        pIva.setStyle(roundTextFieldGreen);
        TextField saldo = new TextField();
        saldo.setBorder(Border.EMPTY);
        saldo.setLayoutX(500); saldo.setLayoutY(200);                                      //posiziono textfield saldo
        saldo.setPrefSize(190,30);                                                         //modifico grandezza saldo
        saldo.setStyle(roundTextFieldGreen);
        Button conf=new Button("Conferma");
        conf.setLayoutX(750);  conf.setLayoutY(600);                                       //posiziono bottone conferma
        conf.setPrefSize(80,40);                                                           //modifico grandezza conferma
        conf.setStyle(FattureFX.roundRedBut); 
        Button ind=new Button("Indietro");
        ind.setLayoutX(850);  ind.setLayoutY(600);                                         //posiziono bottone indietro
        ind.setPrefSize(80,40);                                                            //modifico grandezza indietro
        ind.setStyle(FattureFX.roundRedBut);                
        nomeL.setLayoutX(nome.getLayoutX());  nomeL.setLayoutY(nome.getLayoutY()-25);      //Posiziono label inserimento nome
        saldoL.setLayoutX(saldo.getLayoutX());  saldoL.setLayoutY(saldo.getLayoutY()-25);  //Posiziono label inserimento saldo
        pIvaL.setLayoutX(pIva.getLayoutX());  pIvaL.setLayoutY(pIva.getLayoutY()-25);      //Posiziono label inserimento pIva
        Label avvisoSaldo=new Label("SALDO NON E' UN NUMERO!"); 
        avvisoSaldo.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSaldo.setLayoutX(saldo.getLayoutX());   avvisoSaldo.setLayoutY(saldo.getLayoutY()+40); 
        avvisoSaldo.setVisible(false);
        Label avvisoSpaziPIVA=new Label("SPAZI NELLA PARTITA IVA!"); 
        avvisoSpaziPIVA.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoSpaziPIVA.setLayoutX(pIva.getLayoutX());   avvisoSpaziPIVA.setLayoutY(pIva.getLayoutY()+40);
        avvisoSpaziPIVA.setVisible(false); 
        Label avvisoPIVANotNew=new Label("PARTITA IVA GIA' ASSEGNATA"); 
        avvisoPIVANotNew.setStyle("-fx-font: 15 arial; -fx-text-fill: RED;");
        avvisoPIVANotNew.setLayoutX(pIva.getLayoutX());   avvisoPIVANotNew.setLayoutY(pIva.getLayoutY()+40); 
        avvisoPIVANotNew.setVisible(false); 
        root.getChildren().addAll(FattureFX.backBase,FattureFX.menuBar,nome,nomeL,saldo,pIvaL,pIva,saldoL,conf,ind,avvisoSaldo,avvisoSpaziPIVA,avvisoPIVANotNew);    //aggiungo all'AnchorPane
        Scene addCli=new Scene(root,980,720);
        FattureFX.theStage.setScene(addCli);
        FattureFX.theStage.setMaxHeight(720);
        FattureFX.theStage.setMaxWidth(980);
        conf.setOnAction(e ->
        {
            try
            {
                boolean controllo=false;
                if(nome.getText().equals(""))
                    nome.setStyle(FattureFX.roundTextFieldRed);
                else
                    nome.setStyle(roundTextFieldGreen);
                if(pIva.getText().equals(""))
                    pIva.setStyle(FattureFX.roundTextFieldRed);
                else
                {
                    controllo=false;
                    if(pIva.getText().indexOf(' ')>=0)
                    {
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                        avvisoSpaziPIVA.setVisible(true);
                    }
                    else  
                    {
                        controllo=true;
                        avvisoSpaziPIVA.setVisible(false);
                        pIva.setStyle(roundTextFieldGreen);
                    }                 
                }   
                boolean controllo2=true;
                avvisoPIVANotNew.setVisible(false);
                for(int i=0;i<FattureFX.clienti.size()&&controllo;i++)
                    if(pIva.getText().toUpperCase().equals(((Cliente)FattureFX.clienti.get(i)).getPartitaIva()))
                    {
                        controllo2=false;
                        avvisoPIVANotNew.setVisible(true);
                        pIva.setStyle(FattureFX.roundTextFieldRed);
                    }                  
                if(saldo.getText().equals(""))
                    saldo.setStyle(FattureFX.roundTextFieldRed);
                else
                    saldo.setStyle(roundTextFieldGreen);
                Double saldoD;                
                avvisoSaldo.setVisible(false);
                saldoD=Double.parseDouble(saldo.getText()); 
                if(!nome.getText().equals("")&&!pIva.getText().equals("")&&!saldo.getText().equals("")&&controllo&&controllo2) //controllo che si possa tornare indietro solo se i campi sono pieni
                {
                    FattureFX.clienti.add(new Cliente(nome.getText(),pIva.getText(),saldoD));  //passo i dati al costruttore della classe Clienti
                    FattureFX.fatture.add(new Fattura(FattureFX.clienti.get(FattureFX.clienti.size()-1),FattureFX.materiale.get(gestioneFatture.indiceMat),gestioneFatture.qta,gestioneFatture.prezzoD));
                    ((Fattura)FattureFX.fatture.get((FattureFX.fatture.size()-1))).addebita(gestioneFatture.prezzoD);
                    ((Fattura)FattureFX.fatture.get(FattureFX.fatture.size()-1)).decMat(gestioneFatture.qta);
                    FattureFX.salvatoMat=false;
                    FattureFX.salvatoFatt=false;
                    FattureFX.salvato=false;
                    FattureFX.tornaIniziale(); //torno alla schermata iniziale
                } 
            }
            catch(NumberFormatException g)
            {
                System.out.print(g);    
                saldo.setStyle(FattureFX.roundTextFieldRed);
                avvisoSaldo.setVisible(true);
            }      
        });
        ind.setOnAction(e ->
        {
            FattureFX.tornaIniziale(); //torno alla schermata iniziale  
        });
        FattureFX.theStage.show();
    }
}
