package EvaluateResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GetConceptID {
	 public HashMap<String, String> conceptIDSet = new HashMap<>();

     //��ȡ�����
     public HashMap<String, String> ReadConcept(){
    	 try { 
	            Scanner in = new Scanner(new File("E:/�����ļ�/��̳���/UseSnomedEvaluateResult/Concepts.txt"),"UTF-8"); 
             
	            int l = 0; 
	            //��ȡConcepts.txt
	            while (in.hasNextLine()) {
	                String glaucoma = in.nextLine();
	                l++;
	                if(l != 1)
	                    GetGlaucomaParameters(glaucoma);
//	                l++;
//	                if(l == 9){
//	                	break;
//	                }
	            } 
	            in.close();
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } 
    	 System.out.println("concept��ȡ����");
    	 return conceptIDSet;
     }
     
     public void GetGlaucomaParameters(String glaucoma){
//		   System.out.println("glaucoma: "+glaucoma);
		   //��ȡ������
		   String concept = ""; //����۸���
		   String conceptID = "";//����ID
		   int SpaceFirst = glaucoma.indexOf("\t");
		   int SpaceSecond = glaucoma.indexOf("\t",SpaceFirst+1);
		   conceptID = glaucoma.substring(0,SpaceFirst);
		   //System.out.println(SpaceFirst+" "+SpaceSecond);
		   concept = glaucoma.substring(SpaceSecond+1,glaucoma.indexOf("\t",SpaceSecond+1));
		   //���˵������е�ʵ���ǩ
		   int bracket = concept.lastIndexOf('(');
		   if(bracket != -1){
			  //if(concept.substring(bracket+1,concept.length()-1).equals("disorder") || concept.substring(bracket+1,concept.length()-1).equals("finding")){
		         concept = concept.substring(0,bracket-1);
//		         System.out.println("concept: "+concept);
		         //���˵�����С�����ĵ��ʣ���Ϊ��Щ��һ��û��ʲô׼ȷ����
		         //if(concept.length() > 3){
		         GetSterm(concept, conceptID);
		         //}
		            //WriteIntoFile(concept);
			  //}
		   }

	  }
     
      public void GetSterm(String Sentence,String conceptID){
    	    int i;
		    char c;
		    String word = "";
		    String word_derivative = "";
		    WordNetHelper getstem = new WordNetHelper();
		    //��ʼ��һ���ַ������������������Ĵ���
		    StringBuffer segBuffer_derivative = new StringBuffer();
		    
		    for(i = 0; i < Sentence.length(); i++){
		    	c = Sentence.charAt(i);
		    	if(c == ' '||i == Sentence.length()-1){
		    		 if(c == ' '){
			    		  word_derivative = getstem.findStem(word);
			    		  segBuffer_derivative.append(word_derivative+" ");
		    		 }else if(i == Sentence.length()-1){
		    			  word += c;
		    			  word_derivative = getstem.findStem(word);
		    			  segBuffer_derivative.append(word_derivative);
		    			  conceptIDSet.put(conceptID,segBuffer_derivative.toString());
//		    			  System.out.println(segBuffer_derivative);
//		    			  System.out.println(conceptID);
				    	  segBuffer_derivative = new StringBuffer();
		    		 }
		    		 word = "";
		    		 
		    	}else{
		    		word += c; 
		    	}
		    }	  
      }
}
