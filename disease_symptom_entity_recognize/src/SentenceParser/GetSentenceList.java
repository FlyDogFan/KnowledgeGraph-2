package SentenceParser;

import java.util.ArrayList;

public class GetSentenceList {
     public void GetSentenceList(String Sentence){
    	//��ʼ��һ���ַ������� ,���洦���������ŵ�
  	    String CleanSentence = "";
  	    ArrayList<String> SentenceList = new ArrayList<>();
  	   
  	    int i;
  	    char c;
  	    //���浥��
  	    String word = "";
  	    for(i = 0; i < Sentence.length(); i++){
  	    	c = Sentence.charAt(i);
  	    	//������ַ�c�ǿո��Ǿ��ӵ����һ���ַ������һ���ַ��Ǳ����ţ�,����һ������
  	    	if(c == ' '||i == Sentence.length()-1){
  	    		if(word.length() > 0){
  		    		if(c == ' '){
  		    			SentenceList.add(word);
  		    		}else if(i == Sentence.length()-1){
  		    			word += c;
  		    			SentenceList.add(word);
  		    		}
  	    		}
  	    		word = "";
  	    	}else{
  	    		word += c; 
  	    	} 
  	    }
  	    
  	    //System.out.println("CleanSentence:"+CleanSentence);
  	    //���ú�����ɾ��ӵĽ�������ʵ�巢��
  	    GetNewEntity getNewEntity = new GetNewEntity();
  	    getNewEntity.FindNewEntity(SentenceList);
     }
}
