package MatchWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GetTerm {
	
	  //��ƥ�䵽�ĵ���д���ļ���
	  public static void WriteIntoFile(String glaucoma){
		  //д���ļ�
		   BufferedWriter bw = null;
			try{
				
			    FileWriter fileWriter=new FileWriter("E:/�����ļ�/��̳���/ʵ���Ҵ���/֪ʶͼ��/GlaucomaConceptTerm.txt",true);
			    bw = new BufferedWriter(fileWriter);
			    bw.write(glaucoma);
			    bw.newLine();  
			
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
			    try {
			        bw.close();
			    }catch (IOException e) {
			        e.printStackTrace();
			    }
			}
	  }
	  //ƥ����˵�������ĵ����޹صĸ���
//	  public static boolean MatchWord(String concept, String MatchGlaucoma){
//		  int i;
//		  int SpaceFirst = 0,SpaceSecond = -1;
//		  String Tempconcept = "";
//		  for(i = 0; i < MatchGlaucoma.length(); i++){
//			  SpaceFirst = SpaceSecond+1;
//			  if(i == (MatchGlaucoma.length()-1))
//				  SpaceSecond = i;
//			  else
//			      SpaceSecond = MatchGlaucoma.indexOf(" ",SpaceFirst);
//			  //System.out.println("SpaceFirst: "+SpaceFirst +" "+"SpaceSecond: " + SpaceSecond);
//			  if(SpaceSecond != -1){
//			      Tempconcept = MatchGlaucoma.substring(SpaceFirst,SpaceSecond+1);
//			      Tempconcept.replaceAll(" ", "");
//			      System.out.println("Tempconcept: "+Tempconcept);
//			  }
//			  
//			  if(concept.equals(Tempconcept)){
//				  return true;
//			  }
//		  }
//		  return false;
//	  }
	  
	  public static void GetGlaucomaParameters(String glaucoma){
		  // System.out.println("glaucoma: "+glaucoma);
		   //��ȡ������
		   String concept = ""; //����۸���
		   int SpaceFirst = glaucoma.indexOf("\t");
		   int SpaceSecond = glaucoma.indexOf("\t",SpaceFirst+1);
		   //System.out.println(SpaceFirst+" "+SpaceSecond);
		   concept = glaucoma.substring(SpaceSecond+1,glaucoma.indexOf("\t",SpaceSecond+1));
		   int bracket = concept.lastIndexOf('(');
		   if(bracket != -1)
		      concept = concept.substring(0,bracket-1);
		   System.out.println("concept: "+concept);
		   
		   int i = 0,i_first = 0;
		   for(i = 0; i < concept.length(); i++){
			   if(concept.charAt(i) == ' '){
				   System.out.println(i_first+" to "+ i+ ":"+ concept.substring(i_first,i));
				   
				   WriteIntoFile(concept.substring(i_first,i));
				   i_first = i+1;
			   }else if(i == concept.length()-1){
				   System.out.println(i_first+" to "+ i+ ":"+ concept.substring(i_first,i+1));
				   WriteIntoFile(concept.substring(i_first,i+1));
			   }
			   
		   }
		   
		   //��ȡEGSEnglish
//		   try { 
//	            Scanner in = new Scanner(new File("E:/�����ļ�/��̳���/ʵ���Ҵ���/֪ʶͼ��/EGSEnglish.txt"),"UTF-8"); 
//               
//	            int l = 0;
//	            boolean judge = false;
//	            //��ȡEGSEnglish.txt
//	            while (in.hasNextLine()) {
//	                String MatchGlaucoma = in.nextLine();
//	                System.out.println("MatchGlaucoma: "+MatchGlaucoma);
//	                if(!MatchGlaucoma.isEmpty()){
//	                	judge = MatchWord(concept,MatchGlaucoma);
//	                	if(judge == true){
//	                		System.out.println("ƥ��ɹ�: " + glaucoma);
//	                		WriteIntoFile(glaucoma);
//	                		break;
//	                	}
//	                }
//	                
//
//	            } 
//	            in.close();
//	        } catch (FileNotFoundException e) { 
//	            e.printStackTrace(); 
//	        } 
		   
		   

			
			
	  }
	  
	  //�������������ݳ�������
//	  public static void main(String[] args){
//		    try { 
//	            Scanner in = new Scanner(new File("E:/�����ļ�/��̳���/ʵ���Ҵ���/֪ʶͼ��/Concepts.txt"),"UTF-8"); 
//                
//	            int l = 0; 
//	            //��ȡConcepts.txt
//	            while (in.hasNextLine()) {
//	                String glaucoma = in.nextLine();
//	                l++;
//	                if(l != 1)
//	                    GetGlaucomaParameters(glaucoma);
////	                if(l == 9){
////	                	break;
////	                }
//	            } 
//	            in.close();
//	        } catch (FileNotFoundException e) { 
//	            e.printStackTrace(); 
//	        } 
//
//    }
}
