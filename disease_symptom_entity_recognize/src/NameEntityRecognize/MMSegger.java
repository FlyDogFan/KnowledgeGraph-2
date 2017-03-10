package NameEntityRecognize;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import SentenceParser.GetSentenceList;

public class MMSegger 
{
      String entity_start = "[entity_start] ";
      String entity_end = " [entity_end]";
	 
//	  //��ƥ�䵽�ĵ���д���ļ���
//	  public static void WriteIntoFile(String glaucoma,int flag){
//		    //д���ļ�
//		    BufferedWriter bw = null;
//		    FileWriter fileWriter=null;
//			try{
//				if(flag == 1)
//					fileWriter = new FileWriter("E:/�����ļ�/��̳���/disease_symptom_entity_recognize/term.txt",true);
//				else if(flag == 2)
//				    fileWriter = new FileWriter("E:/�����ļ�/��̳���/disease_symptom_entity_recognize/term_derivative.txt",true);
//				bw = new BufferedWriter(fileWriter);
//			    bw.write(glaucoma);
//			    bw.newLine();  
//			
//			}catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//			    try {
//			        bw.close();
//			    }catch (IOException e) {
//			        e.printStackTrace();
//			    }
//			}
//	  }	
//	  
//	  //��ƥ�䵽�ĵ�����д���ļ���
//	  public static void WriteSentenceIntoFile(String glaucoma){
//		    //д���ļ�
//		    BufferedWriter bw = null;
//		    FileWriter fileWriter=null;
//			try{
//			    fileWriter = new FileWriter("E:/�����ļ�/��̳���/disease_symptom_entity_recognize/recognize_entity_coprus/sentence_label.txt",true);
//				bw = new BufferedWriter(fileWriter);
////				System.out.println(glaucoma);
//			    bw.write(glaucoma);
//			    bw.newLine();  
//			
//			}catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//			    try {
//			        bw.close();
//			    }catch (IOException e) {
//			        e.printStackTrace();
//			    }
//			}
//	  }	
	  

	  public void seg(String seq,TrieDictionary dict,int[] SentenceIndex)
	  {
		//��ʼ��һ���ַ�������  
	    StringBuffer segBuffer = new StringBuffer();
	    //��ʼ��һ���ַ������������������Ĵ���
	    StringBuffer segBuffer_derivative = new StringBuffer();
	    
	    //2016/11/29���뿪ʼ
	    StringBuffer seqBuffer = new StringBuffer(seq);
	   // StringBuffer sentenceBuffer = new StringBuffer(seq);
	    //2016/11/29�������
	    
	    int[] GlaucomaFlag = new int[2];//�ж�������Ƿ���snomed�е�Ӣ�Ĵ���
	    GlaucomaFlag[0] = 0;
	    GlaucomaFlag[1] = 0;
	    //�õ��ֵ�ĸ��ڵ�
	    TrieNode p = dict.getRoot();
	    //�õ������ֵ�ĸ��ڵ�
	    TrieNode p_derivative = dict.getRootDerivative();
	    
	    //����õ�����list�б�
	    GetSentenceList getSentenceList = new GetSentenceList();
	    
	    
	    int i;
	    int i_label = 0;
	    char c;
	    String word = "";
	    //System.out.println("CleanSentence:"+seq);
	    for(i = 0; i < seq.length(); i++){
	    	c = seq.charAt(i);
	    	if(c == ' '||i == seq.length()-1){
	    		if(word.length() > 0){
	    			  if(i == seq.length()-1){
	    				  word += c;
	    			  }
//	    			  TrieNode pChild = null;
		    		  //System.out.println("word:"+(TrieNode)p.childs.get(word));
	    			  //===�����ǰ�ڵ���ӽڵ�Ϊ�գ��������������һ���ҵ�һ����������ƥ�䣻�������ܸôʲ����ֵ�����(��Ϊ�ôʴӸ��ڵ����û�г���)
//		    	      if((pChild = (TrieNode)p.childs.get(word)) == null) {
//		    	    	  //��ǰ�ڵ�boudֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
//		    	    	  if(p.bound){
//		    	    		  //System.out.println("bound");
//		    	    		 // segBuffer.append("-");
//		    	    		  GlaucomaFlag[0] = 1;
//		    	    		  WriteIntoFile(new String(String.valueOf(SentenceIndex[0])+" "+segBuffer),1);
//		    	    		  //��ǰ�ڵ���ӽڵ�Ϊ�գ�������ʾ�ô�û�г������ֵ����У��������½��ôʴӸ��ڵ㿪ʼƥ�䣬��ˣ�ָ�����һ���ʵĳ���
//		    	    		//  i = i-word.length()-1;
//		    	    	  }
//		    	    	  //��ǰ�ڵ�boundֵΪ�٣������κδ���
//		    	    	  else{
//		    	    	      ;
//		    	    	  }
//		    	    	  //�ô����ѯ�Ѿ���Ҷ�ӽڵ㣬���˵��ֵ����ĸ��ڵ㣬�´β��ҴӸ��ڵ㿪ʼƥ��
//		    	    	  p = dict.getRoot();
//		    	    	  //����ַ���
//		    	    	  segBuffer = new StringBuffer();
//		    	      }
//		    	      else{
//		    	    	  //�Ѿ��ﵽ����ĩβ,�������´ʼ������
//		    	    	  if(i == seq.length() - 1){
//		    	    		  //��ǰ�ڵ�boundֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
//		    	    		  if(pChild.bound){
//			    	    		  //System.out.println("bound");
//			    	    		  segBuffer.append(word);
//			    	    		  GlaucomaFlag[0] = 1;
//			    	    		  WriteIntoFile(new String(String.valueOf(SentenceIndex[0])+" "+segBuffer),1);
//			    	    		  
//			    	    	  }
//			    	    	  //����ַ���
//			    	    	  segBuffer = new StringBuffer();
//		    	    	  }
//		    	    	  else{
//		    	    		  segBuffer.append(word+" ");
//		    	    	  }
//		    	    	  p = pChild;
//		    	      }
		    	      
		    	      //===============����������ƥ��Ĺ���
		    	      String word_derivative = "";
		    	      WordNetHelper getstem = new WordNetHelper();
		    	      TrieNode pChild_derivative = null;
		    	      boolean isLetter = false;
		    	      int isLetterJudge = 0;
		    	      for(isLetterJudge = 0; isLetterJudge < word.length(); isLetterJudge++){
		    		    	 if(Character.isLetter(word.charAt(isLetterJudge))){
		    		    		 isLetter = true;
		    		    		 break;
		    		    	 }
		    	      }
		    	      
		    	      if(isLetter == true){
		    	         word_derivative = getstem.findStem(word);
		    	      }
		    	      word_derivative = word_derivative.toLowerCase();//�����д�дת����Сд��ĸ
		    	      
//		    	      System.out.println("word:"+word);
//		    	      System.out.println("word_derivative:"+word_derivative);
		    	      
		    		  //System.out.println("word:"+(TrieNode)p.childs.get(word));
	    			  //===�����ǰ�ڵ���ӽڵ�Ϊ�գ��������������һ���ҵ�һ����������ƥ�䣻�������ܸôʲ����ֵ�����(��Ϊ�ôʴӸ��ڵ����û�г���)
		    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
		    	    	  //��ǰ�ڵ�boudֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
		    	    	  if(p_derivative.bound){
		    	    		  //System.out.println("bound");
		    	    		 // segBuffer.append("-");
//		    	    		  GlaucomaFlag[1] =  1;
//		    	    		  WriteIntoFile(new String(String.valueOf(SentenceIndex[1])+" "+segBuffer_derivative),2);
//		    	    		  
//		    	    		  System.out.println("segBuffer_derivative1:" + segBuffer_derivative);
		    	    		  
		    	    		  //2016/11/29�������
//		    	    		  if(i-segBuffer_derivative.length() - word.length()+(entity_end.length()+entity_start.length())*i_label < 0)
//		    	    			  seqBuffer.insert(0, entity_start);
//		    	    		  else
//		    	    		  System.out.println("i:"+i);
//		    	    		  System.out.println("segBuffer_derivative:"+segBuffer_derivative+":"+segBuffer_derivative.length());
//		    	    		  System.out.println("word:"+word+":"+word.length());
//		    	    		  System.out.println("i_label:"+i_label);
//		    	    		  System.out.println("����λ��:"+(i-segBuffer_derivative.length() - word.length()+(entity_end.length()+entity_start.length())*i_label));
		    	    		  
		    	    		  if(i == seq.length()-1){//������˾���ĩβ
		    	    			  //2017/3/10�������
			    	    		  seqBuffer = seqBuffer.insert(i-segBuffer_derivative.length() + 1 - word.length() +(entity_end.length()+entity_start.length())*i_label,entity_start);
			    	    		  seqBuffer = seqBuffer.insert(i+(entity_end.length()+entity_start.length())*i_label+entity_start.length() - word.length(), entity_end);
			    	    		  i_label++;
			    	    		  //2017/3/10������ݽ���
		    	    		  }else{//���û��
		    	    		      seqBuffer = seqBuffer.insert(i-segBuffer_derivative.length() - word.length()+(entity_end.length()+entity_start.length())*i_label,entity_start);
		    	    		      seqBuffer = seqBuffer.insert(i+(entity_end.length()+entity_start.length())*i_label+entity_start.length() - word.length()-1, entity_end);
		    	    		      i_label++;
		    	    		  }
		    	    		  //2016/11/29������ݽ���
		    	    		  
		    	    		  //��ǰ�ڵ���ӽڵ�Ϊ�գ�������ʾ�ô�û�г������ֵ����У��������½��ôʴӸ��ڵ㿪ʼƥ�䣬��ˣ�ָ�����һ���ʵĳ���
		    	    		  i = i-word.length()-1;
		    	    	  }
		    	    	  //��ǰ�ڵ�boundֵΪ�٣�˵���ô��鲻���ֵ���
		    	    	  else{
		    	    		  ;
		    	    	  }
		    	    	  //�ô����ѯ�Ѿ���Ҷ�ӽڵ㣬���˵��ֵ����ĸ��ڵ㣬�´β��ҴӸ��ڵ㿪ʼƥ��
		    	    	  p_derivative = dict.getRootDerivative();
		    	    	  //����ַ���
		    	    	  segBuffer_derivative = new StringBuffer();
		    	      }
		    	      else{
		    	    	  //�Ѿ��ﵽ����ĩβ,�������´ʼ������
		    	    	  if(i == seq.length() - 1){
		    	    		  //��ǰ�ڵ�boundֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
		    	    		  if(pChild_derivative.bound){
			    	    		  //System.out.println("bound");
			    	    		  segBuffer_derivative.append(word);
//			    	    		  System.out.println("segBuffer_derivative2:" + segBuffer_derivative);
//			    	    		  GlaucomaFlag[1] =  1;
//			    	    		  WriteIntoFile(new String(String.valueOf(SentenceIndex[1])+" "+segBuffer_derivative),2);
			    	    		  
			    	    		  //2016/11/29�������
			    	    		  seqBuffer = seqBuffer.insert(i-segBuffer_derivative.length() + 1 +(entity_end.length()+entity_start.length())*i_label,entity_start);
			    	    		  seqBuffer = seqBuffer.insert(i+(entity_end.length()+entity_start.length())*i_label+entity_start.length() + 1, entity_end);
			    	    		  i_label++;
			    	    		  //2016/11/29������ݽ���
			    	    		  
			    	    	  }
			    	    	  //����ַ���
			    	    	  segBuffer_derivative = new StringBuffer();
		    	    	  }
		    	    	  else{
		    	    		  segBuffer_derivative.append(word+" ");
		    	    		 // System.out.println("segBuffer_derivative3:" + segBuffer_derivative);
		    	    	  }
		    	    	  p_derivative = pChild_derivative;
		    	      }
		    	      
	    		}
	    		word = "";
	    	}
	    	else{
	    		word += c; 
	    	}
	    }
	    
	    //2016/11/29
	    if(i_label >= 2){
	    	//2017/2/7��ʼ
//	    	System.out.println("i_label: " + i_label);
//	    	System.out.println(seqBuffer);
//	    	if(i_label == 2){
//	           WriteSentenceIntoFile(new String(seqBuffer));
//	    	}else{
//	    		DivideSentence(new String(seqBuffer),i_label-1);
//	    	}
	        //2017/2/7����
	    	
	    	//2017.3.9�޸�
	    	getSentenceList.GetSentenceList(new String(seqBuffer));
	    	//2017.3.9�޸Ľ���
	    }
	    //2016/11/29
		
	    // return GlaucomaFlag;
	  }
	  
//	  //2017/2/7���־���
//	  public void DivideSentence(String sentence, int i_label){
//	    	 int i = 0;
//	    	 String divide_sentence = "";
//	    	 
//	    	 int first_entity_start = 0, first_entity_end = 0;
//	    	 int second_entity_start = 0, second_entity_end = 0;
//	    	 int third_entity_start = 0, third_entity_end = 0;
//	    	 int forth_entity_start = 0, forth_entity_end = 0;
//	    	 while(i_label > 0){
//	    		 first_entity_start = sentence.indexOf(entity_start,i);
//	    		 first_entity_end = sentence.indexOf(entity_end,first_entity_start + entity_start.length()); 
//	    		 
//	    		 second_entity_start = sentence.indexOf(entity_start,first_entity_end + entity_end.length());
//	    		 second_entity_end = sentence.indexOf(entity_end,second_entity_start + entity_start.length());
//	    		 
//	    		 third_entity_start = sentence.indexOf(entity_start,second_entity_end + entity_end.length());
//	    		 third_entity_end = sentence.indexOf(entity_end,third_entity_start + entity_start.length());
//	    		 
//	             if(third_entity_start > 0){
//	    		    divide_sentence = sentence.substring(i,third_entity_start-1);
//	    		    WriteSentenceIntoFile(divide_sentence);
//	             }else{
//	            	divide_sentence = sentence.substring(i,sentence.length());
//	            	WriteSentenceIntoFile(divide_sentence);
//	             }
//	    		 i = first_entity_end + entity_end.length() + 1;
//	    		 i_label --;
//	    	 }
//	}
//	//2017/2/7���־��ӽ���
//  public static void main(String[] args) throws IOException
//  {
//	    MMSegger mmsegger = new MMSegger();
//	    System.out.println(mmsegger.seg("I buy the Snomed Product,you Glaucoma treat yout eyes rather than Glaucoma treat��"));
//  }
}
