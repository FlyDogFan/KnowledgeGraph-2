package NameEntityRecognize;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PreProcessSentence {
    public void CleanSentence(String Sentence){
    	//��ʼ��һ���ַ������� ,���洦���������ŵ�
	    String CleanSentence = "";
	    IndentitySentence indentitySentence = new IndentitySentence();
	    int i;
	    char c;
	    //���浥��
	    String word = "";
	    for(i = 0; i < Sentence.length(); i++){
	    	c = Sentence.charAt(i);
	    	//������ַ�c�ǿո��Ǿ��ӵ����һ���ַ������һ���ַ��Ǳ����ţ���������һ�������ţ�����һ������
	    	if(c == ' '||i == Sentence.length()-1||NonChiSplit.isCharSeperator(c)){
	    		if(word.length() > 0){
		    		if(c == ' '){
		    			CleanSentence += word + " ";//���õ��ʱ��浽������
		    		}else if(NonChiSplit.isCharSeperator(c)){
		    			if(i == Sentence.length()-1)
		    			    CleanSentence += word;
		    			else
		    				CleanSentence += word+ " ";
		    		}else if(i == Sentence.length()-1){
		    			if(!NonChiSplit.isCharSeperator(c)){
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
	    //����������Ӻ�Ľ��
	    WriteSentenceIntoFile(CleanSentence);
	    
	    indentitySentence.GetSentence(CleanSentence);
    }
    
     //��ƥ�䵽�ĵ�����д���ļ���
	  public static void WriteSentenceIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
			    fileWriter = new FileWriter("E:/�����ļ�/��̳���/disease_symptom_entity_recognize/CleanSentence.txt",true);
				bw = new BufferedWriter(fileWriter);
//				System.out.println(glaucoma);
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
