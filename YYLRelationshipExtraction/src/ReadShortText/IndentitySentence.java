package ReadShortText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class IndentitySentence {
	
	//����������ŵľ���д���ļ�
	public static void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("D:/YYLSoftware/Program/YYLRelationshipExtraction/CleanSentence.txt",true);
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

	public static void GetSentence(String Sentence){
		//��ʼ��һ���ַ������� ,���洦���������ŵ�
	    String CleanSentence = "";
	    int i;
	    char c;
	    //���浥��
	    String word = "";
	    for(i = 0; i < Sentence.length(); i++){
	    	c = Sentence.charAt(i);
	    	//������ַ�c�ǿո��Ǿ��ӵ����һ���ַ������һ���ַ��Ǳ����ţ���������һ�������ţ�����һ������
	    	if(c == ' '||i == Sentence.length()-1||StaticConstant.NonChiSplit.isCharSeperator(c)){
	    		if(word.length() > 0){
		    		if(c == ' '){
		    			CleanSentence += word + " ";//���õ��ʱ��浽������
		    		}else if(StaticConstant.NonChiSplit.isCharSeperator(c)){
		    			if(i == Sentence.length()-1)
		    			    CleanSentence += word;
		    			else
		    				CleanSentence += word+ " ";
		    		}else if(i == Sentence.length()-1){
		    			if(!StaticConstant.NonChiSplit.isCharSeperator(c)){
		    				word += c;
		    			}
		    			CleanSentence += word;
		    		}
	    		}
	    		word = "";
	    	}else{
	    		word += c; 
	    	} 
	    }
	    WriteIntoFile(CleanSentence);
	}
	
}
