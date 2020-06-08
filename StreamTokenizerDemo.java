import java.io.*;

public class StreamTokenizerDemo {

   public static void main(String[] args) {

      String text = 
        "room 0 wall d0 wall wall \n"+
        "room 1 d0 wall d1 wall\n"+
        "room 2 wall wall wall d1\n"+
        "door d0 0 1 close\n"+
        "door d1 1 2 open\n" ;
      try {
        
         Reader strReader = new StringReader(text);
         StreamTokenizer st = new StreamTokenizer(strReader);

         // set letters o- t as word chars
         //st.wordChars('d', 'd');
         
         // print the stream tokens
         boolean eof = false;
         do {

            int token = st.nextToken();
             System.out.println("token="+token);
            switch (token) {
               case StreamTokenizer.TT_EOF:
                  System.out.println("End of File encountered.");
                  eof = true;
                  break;
               case StreamTokenizer.TT_EOL:
                  System.out.println("---");
                  break;
               case StreamTokenizer.TT_WORD:
                  System.out.println("Word: " + st.sval);
                  break;
               case StreamTokenizer.TT_NUMBER:
                  System.out.println("Number: " + st.nval);
                  break;
               default:
                   //StreamTokenizer.
                  System.out.println((char) token + " encountered. ");
                  if (token == '!') {
                     eof = true;
                  }
            }


         } while (!eof);


      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}