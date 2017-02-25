package MatchWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GetWord {
	
	  //将匹配到的单词写入文件中
	  public static void WriteIntoFile(String glaucoma){
		  //写入文件
		   BufferedWriter bw = null;
			try{
				
			    FileWriter fileWriter=new FileWriter("E:/北航文件/编程程序/实验室代码/知识图谱/GlaucomaConcept.txt",true);
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
	  //匹配过滤掉青光眼文档中无关的感念
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
		   System.out.println("glaucoma: "+glaucoma);
		   //读取出概念
		   String concept = ""; //青光眼概念
		   int SpaceFirst = glaucoma.indexOf("\t");
		   int SpaceSecond = glaucoma.indexOf("\t",SpaceFirst+1);
		   //System.out.println(SpaceFirst+" "+SpaceSecond);
		   concept = glaucoma.substring(SpaceSecond+1,glaucoma.indexOf("\t",SpaceSecond+1));
		   //过滤掉括号中的实体标签
		   int bracket = concept.lastIndexOf('(');
		   if(bracket != -1){
			  if(concept.substring(bracket+1,concept.length()-1).equals("disorder") || concept.substring(bracket+1,concept.length()-1).equals("finding")){
		         concept = concept.substring(0,bracket-1);
		         System.out.println("concept: "+concept);
		         //过滤掉长度小于三的单词，因为这些词一般没有什么准确意义
		         if(concept.length() > 3)
		            WriteIntoFile(concept);
			  }
		   }
		   
		   
		   //读取EGSEnglish
//		   try { 
//	            Scanner in = new Scanner(new File("E:/北航文件/编程程序/实验室代码/知识图谱/EGSEnglish.txt"),"UTF-8"); 
//               
//	            int l = 0;
//	            boolean judge = false;
//	            //读取EGSEnglish.txt
//	            while (in.hasNextLine()) {
//	                String MatchGlaucoma = in.nextLine();
//	                System.out.println("MatchGlaucoma: "+MatchGlaucoma);
//	                if(!MatchGlaucoma.isEmpty()){
//	                	judge = MatchWord(concept,MatchGlaucoma);
//	                	if(judge == true){
//	                		System.out.println("匹配成功: " + glaucoma);
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
		   
//		    WriteIntoFile(concept);

			
			
	  }
	  
	  //主函数，将数据初步处理
	  public static void main(String[] args){
		    try { 
	            Scanner in = new Scanner(new File("E:/北航文件/编程程序/实验室代码/知识图谱/Concepts.txt"),"UTF-8"); 
                
	            int l = 0; 
	            //读取Concepts.txt
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

    }
}
