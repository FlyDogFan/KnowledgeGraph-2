package ReadShortText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import FeatureExtract.ExtractWordFeature;

public class ReadFile {
	public void ReadSentence(String FileName,int flag){
		 try{
		     //Scanner reader = new Scanner(new File(FileName),"UTF-8");
		     int i = 0;
		     Reader reader = null;
	         //��ȡtxt�ļ��е�����
	         System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");  
	         
	         // IndentitySentence indentitySentence = new IndentitySentence();
	         DictionaryTable dictionaryTable = new DictionaryTable();
	         //��ȡ������������
	         ExtractWordFeature extractWordFeature = new ExtractWordFeature();
	         // һ�ζ�һ���ַ�  
	         reader = new InputStreamReader(new FileInputStream(FileName));  
	         String Sentence = ""; 
	         int tempchar = -1;  
	         while ((tempchar = reader.read()) != -1) {  
	             // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�  
	             if((char) tempchar == '\n'){
	            	 Sentence = Sentence.substring(0,Sentence.length()-1);
	            	 //System.out.println(Sentence);
	            	 if(flag == 3){//��ȡ��sentence_lable����
	            		 //2017.3.24�޸�ȥ��һ��û�й�ϵ��ʵ�����
//	            		 if(i == 12314)
//	            			 break;
	            		 //2017.3.24�޸Ľ���
	            		 extractWordFeature.GetWordVector(Sentence);
	            	 }else{
	            	     dictionaryTable.GetTable(Sentence,i,flag);
	            	 }
	            	 i++;
	            	 //indentitySentence.GetSentence(Sentence);
	            	 Sentence = "";
	              }else{
	                  Sentence = Sentence + ((char) tempchar);
	              } 
	         }  
	         reader.close();
	         System.out.println(i); 
	        // System.exit(0);
	
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
}
