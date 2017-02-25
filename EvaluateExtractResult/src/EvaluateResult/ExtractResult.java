package EvaluateResult;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ExtractResult {
	 int EntityPairsNum = 100;
	 //��ȡ�ı��еľ���
	 public void ReadSentence(String FileName, int random){
		 try{
		     //Scanner reader = new Scanner(new File(FileName),"UTF-8");
		     int i = 0,t = 0;
		     Reader reader = null;
             //��ȡtxt�ļ��е�����
             System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");  
             // һ�ζ�һ���ַ�  
             reader = new InputStreamReader(new FileInputStream(FileName));  
             String Sentence = ""; 
             int tempchar = -1;  
             while ((tempchar = reader.read()) != -1) {  
                 // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�  
                 if((char) tempchar == '\n'){
                	 i++;
                	 Sentence = Sentence.substring(0,Sentence.length()-1);
                	 if(Sentence.indexOf("{",0) < 0){
                		 t++;
                	 }
                	 if(t >= random){
                		 GetRandomResult(Sentence);
                	 }
                	 if(t - random == EntityPairsNum){
                		 System.out.println("���˶��ٸ�ʵ��ԣ�" + t);
                		 break;
                	 }
                	 Sentence = "";
	              }else{
	                  Sentence = Sentence + ((char) tempchar);
	              } 
             }  
             reader.close();
             System.out.println("�ܹ����˶����У�"+i); 
            // System.exit(0);

		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 //���ͳ�Ƴ��������ɸ�����
     public void GetRandomResult(String Sentence){
    	 if(Sentence.indexOf("{",0) >= 0){
    		 if(Sentence.indexOf("}",Sentence.length()-1) < 0){
    			 WriteRelationIntoFile(Sentence);
    		 }
    	 }else{
    		 WriteEntityIntoFile(Sentence);
    	 }
     }
     
     //��ʵ��Ա��浽�ı���
   	 public void WriteEntityIntoFile(String glaucoma){
   		    //д���ļ�
   		    BufferedWriter bw = null;
   		    FileWriter fileWriter=null;
   			try{
   				fileWriter = new FileWriter("E:/�����ļ�/��̳���/EvaluateExtractResult/entity.txt",true);
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
   			WriteRelationIntoFile(glaucoma);
   	  }
   	 
   	 //��ʵ����ϵд���ı�
   	 public void WriteRelationIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("E:/�����ļ�/��̳���/EvaluateExtractResult/relation.txt",true);
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
}
