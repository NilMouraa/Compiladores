package AnalisadorLexico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalisadorLexico {

    LinkedHashMap<Integer, ArrayList<analisadorlexico.token>> tokens;
    HashMap<String, String> lexemas;
    

    public AnalisadorLexico(String path) throws IOException {
        tokens = new LinkedHashMap<>();
        lexemas = new HashMap<>();
        
        //analisadorlexico.token objToken = new analisadorlexico.token();
//############### LEXEMAS DA LINGUAGEM #################
//Aritmeticos
        lexemas.put("+", "sum");
        lexemas.put("-", "sub");
        lexemas.put("*", "mult");
        lexemas.put("x", "mult");
        lexemas.put("/", "div");
        lexemas.put(":", "div");
        lexemas.put(".", ".");
        lexemas.put(",", ".");
//Comparativos
        lexemas.put(">", "gt");
        lexemas.put(">=", "gte");
        lexemas.put("=>", "gte");
        lexemas.put("<", "lt");
        lexemas.put("=<", "lte");
        lexemas.put("<=", "lte");
        lexemas.put("==", "eq");
        lexemas.put("!=", "neq");
//Gerais
        lexemas.put("=", "atrib");
        lexemas.put("int", "int,");
        lexemas.put("float", "float,");
        lexemas.put("str", "str,");
        lexemas.put("var", "id,");
//Palavras-chave
//Condicionais
        lexemas.put("se", "cond");
        lexemas.put("então", "initcond");
        lexemas.put("senão", "altcond");
        lexemas.put("fim-se", "endcond");
//Loops
        lexemas.put("para", "forloop");
        lexemas.put("de", "rng1forloop");
        lexemas.put("até", "rng2forloop");
        lexemas.put("faça", "initforloop");
        lexemas.put("fim-para", "endforloop");
        lexemas.put("enquanto", "whileloop");
        lexemas.put("fim-enquanto", "endwhileloop");
//##############################################################################
    
    
    
    }
    
    private void Analisar() throws IOException{
        boolean coment=false;
        int line=1;
        BufferedReader reader = null;
        String linha = "";
        
        try {
            reader = carrega("C:/Users/Aluno/Documents/prog.txt" /*pegar argumento args*/);
        } catch (IOException ex) {
            Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        while (reader.ready()) {
            
            ArrayList<analisadorlexico.token> tokenLine = new ArrayList<>();
            linha = reader.readLine();
            
            for (int i = 0; i < linha.length(); i++) {

                //System.out.println(c);
                char c = linha.charAt(i);
                char c1;
                if((i<linha.length()) -1)
                    c1 = linha.charAt(i+1);
                
                if (c == '#') {
                    coment = true;
                } else {
                    coment = false;
                }
                if (coment == false) {
                    if (c == '=') {
                        String retorno = lexemas.get("=");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                    else if (c == '+') {
                        String retorno = lexemas.get("+");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                    else if (c == '-') {
                        String retorno = lexemas.get("-");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                    else if ((c+""+c1).equals("<=") ) {
                        String retorno = lexemas.get("<=");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                     else if ((c+""+c1).equals("=<")) {
                        String retorno = lexemas.get("=<");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                    else if ( (c+""+c1).equals(">=") ) {
                        String retorno = lexemas.get(">=");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                     else if ((c+""+c1).equals("=>")) {
                        String retorno = lexemas.get("=>");
                        tokenLine.add(new analisadorlexico.token(retorno,""));
                    }
                    
                }

            }
            tokens.put(line, tokenLine);
            line++; 
        }
        imprimeResul();
    }
    
    private void imprimeResul(){
        for (Map.Entry<Integer, ArrayList<analisadorlexico.token>> entry : tokens.entrySet()) {
            Integer integer = entry.getKey();
            ArrayList<analisadorlexico.token> arrayList = entry.getValue();
            System.out.print(integer);
            for (analisadorlexico.token object : arrayList) {
                System.out.print(" "+object.toString());
                
            }
            System.out.println("");
            
        }
    }

    private AnalisadorLexico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private BufferedReader carrega(String path) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), "Cp1252"));
        return reader;
    }

    private void salva(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path + ".lex"), "Cp1252"));
    //Serializa o linkedhashmap de tokens e salva
    }

    public static void main(String[] args) throws IOException {
        AnalisadorLexico a = new AnalisadorLexico("");
        a.Analisar();
        
    }
    
    
}
