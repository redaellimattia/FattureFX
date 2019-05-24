package FattureFX;
import java.io.*;

public class Console
{  
     /* Visualizza messaggio su video */
   public static void visualizzaMessaggio(String messaggio)
   {  System.out.print(messaggio + " ");
      System.out.flush();
   }

     /* Inserimento da tastiera di una stringa */
   public static String readLine()
   {  
      String str = "";
      InputStreamReader tastiera= new InputStreamReader(System.in);
      BufferedReader in = new BufferedReader(tastiera);
      //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      
      try { str = in.readLine(); } 
      catch (java.io.IOException e) { System.out.println("NON riesco a leggere! " + e);}   
      //str = str.trim();  
      //return str;
      return (str.trim());
   }      

     /*  Inserimento da tastiera di una stringa con visualizz. di messaggio */
   public static String readLine(String messaggio)
   {  visualizzaMessaggio(messaggio);
      return readLine();
   }

     /* Inserimento da tastiera di un char con visualizz. di messaggio */
   public static char readChar(String messaggio)
   { String s= new String(); 
     do
     { visualizzaMessaggio(messaggio);   
       s= readLine();
       if (s.length() != 1) System.out.println("Inserisci solo un carattere !");
     }while(s.length()!= 1); 
     return s.charAt(0); 
   }

     /*  Inserimento da tastiera in un intero con controllo dell'eccezione e 
         visualizzazione di messaggio */
   public static int readInt(String messaggio)
   {  while(true)
      {  visualizzaMessaggio(messaggio);
         try {  //return Integer.valueOf( readLine() ).intValue();
                return Integer.parseInt( readLine() );
             } 
         catch(NumberFormatException e)
             { System.out.println ("Non e' un intero. Inseriscilo ancora !"); }
      }
   }

     /*  Inserimento da tastiera in un Double con controllo dell'eccezione e 
         visualizzazione di messaggio */
   public static double readDouble(String messaggio)
   {  while(true)
      {  visualizzaMessaggio(messaggio);
         try { return Double.parseDouble(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un double. Inseriscilo ancora !");}
      }
   }
   
     /*  Inserimento da tastiera in un float con controllo dell'eccezione e 
         visualizzazione di messaggio */   
   public static float readFloat(String prompt)
   {  while(true)
      {  visualizzaMessaggio(prompt);
         try { return Float.parseFloat(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un float. Inseriscilo ancora !"); }
      }
   }   
   
   public static byte readByte(String prompt)
   {  while(true)
      {  visualizzaMessaggio(prompt);
         try { return Byte.parseByte(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un valore byte. Inseriscilo ancora !"); }
      }
   }   
   
   public static long readLong(String prompt)
   {  while(true)
      {  visualizzaMessaggio(prompt);
         try { return Long.parseLong(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un valore Long. Inseriscilo ancora !"); }
      }
   }
   
   public static short readShort(String prompt)
   {  while(true)
      {  visualizzaMessaggio(prompt);
         try { return Short.parseShort(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un valore short. Inseriscilo ancora !"); }
      }
   }
   
   public static boolean readBoolean(String prompt)
   {  while(true)
      {  visualizzaMessaggio(prompt);
         try { return Boolean.parseBoolean(readLine()); } 
         catch(NumberFormatException e)
             { System.out.println("Non e' un valore short. Inseriscilo ancora !"); }
      }
   }   
      
   
}
