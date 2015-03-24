package Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Util {
	
	public static Date stringParaDate(String dateString){
		
		try{
			
			if(dateString.trim().length() != 10) return null; // Data deve estar completa
			
			if(!VerificaData(dateString)) return null; // Verifica se a data é válida. Usa o verificaData com estrada string
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
			
			format.setLenient(false);
			
			java.sql.Date data = new java.sql.Date(format.parse(dateString).getTime());   
			
			System.out.println("###### DATA GERADA FOI: " + data);
			

			return  data;
			
		}catch (ParseException parseException) {
			System.out.println("Erro ao converter data");
			parseException.printStackTrace();
			return null;
		}
			

	}
	
    //Verifica se Data é válida. Testa se o ano é bissexto
	// Fonte - http://www.guj.com.br/java/126029-resolvido---verificar-se-e-uma-data-valida
	// Parâmetro modificado para Date
    public static boolean VerificaData(Date data)
    {
    	
    	// Converte para String primeiro
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	String Dt = df.format(data);

    	System.out.println("### DATA EM STRING: " + Dt);
    	
    	
      //variaveis que recebem o valor
      Integer Dia,Mes;
      Integer Ano; 
      //retorno padrão
    
      //Se a data estiver completa
      if(Dt.trim().length()==10)
        {
         //quebra a string
         Dia = Integer.parseInt(Dt.substring(0,2));
         Mes = Integer.parseInt(Dt.substring(3,5));
         Ano = Integer.parseInt(Dt.substring(6,10));
         
         //verifica variaveis
        if(
            ( (Mes.equals(1) || Mes.equals(3) || Mes.equals(5) || Mes.equals(7) || Mes.equals(8) || Mes.equals(10) || Mes.equals(12)) && (Dia>=1 && Dia <=31))
            ||
            ( (Mes.equals(4) || Mes.equals(6) || Mes.equals(9) || Mes.equals(11)) && (Dia>=1 && Dia <=30))
            ||
            ( (Mes.equals(2)) && (AnoBissexto(Ano)) && (Dia>=1 && Dia <=29))
            ||
            ( (Mes.equals(2)) && !(AnoBissexto(Ano)) && (Dia>=1 && Dia <=28))
          )
            {
              return true;
            } 
        else{  return false;}
        }      
      else
      {  return false;}
     
    }
    public static boolean AnoBissexto (int ano) 
     {   
         return ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0;   
     }
    
  //Verifica se Data é válida. Testa se o ano é bissexto
  	// Fonte - http://www.guj.com.br/java/126029-resolvido---verificar-se-e-uma-data-valida
      public static boolean VerificaData(String Dt)
      {
        //variaveis que recebem o valor
        Integer Dia,Mes;
        Integer Ano; 
        //retorno padrão
      
        //Se a data estiver completa
        if(Dt.trim().length()==10)
          {
        	//quebra a string
            Dia = Integer.parseInt(Dt.substring(0,2));
            Mes = Integer.parseInt(Dt.substring(3,5));
            Ano = Integer.parseInt(Dt.substring(6,10));
           //verifica variaveis
          if(
              ( (Mes.equals(1) || Mes.equals(3) || Mes.equals(5) || Mes.equals(7) || Mes.equals(8) || Mes.equals(10) || Mes.equals(12)) && (Dia>=1 && Dia <=31))
              ||
              ( (Mes.equals(4) || Mes.equals(6) || Mes.equals(9) || Mes.equals(11)) && (Dia>=1 && Dia <=30))
              ||
              ( (Mes.equals(2)) && (AnoBissexto(Ano)) && (Dia>=1 && Dia <=29))
              ||
              ( (Mes.equals(2)) && !(AnoBissexto(Ano)) && (Dia>=1 && Dia <=28))
            )
              {
                return true;
              } 
          else{  return false;}
          }      
        else
        {  return false;}
       
      }

}
