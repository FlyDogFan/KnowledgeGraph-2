package TupleWeightForm;

import NameEntityRecognize.TrieDictionary;
import NameEntityRecognize.TrieNode;
import NameEntityRecognize.WordNetHelper;
import StaticConstant.NonChiSplit;

public class IdentityTuple {
//	//2016/11/29�Ķ�
//	String entity_start = "entity_start";
//	String entity_end = "entity_end";
//	
//	public int[] seg(String seq, String Entity)
//	{
//		//��ʼ��һ���ַ������������������Ĵ���
//	    StringBuffer segBuffer_derivative = new StringBuffer();
//	    
//	    //��ʵ��Եĵ�һ���͵ڶ���ʵ��ֱ𱣴�
//	    String FirstEntity = Entity.substring(0,Entity.indexOf(','));
//	    String SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
//	    
//	    int i;
//	    char c;
//	    //����Ӿ����ж�ȡ�ĵ���
//	    String word = "";
//	    //��һ���ڶ���ʵ��ֱ��ھ����е�λ��
//	    int FirstEntityLocate = -1, SecondEntityLocate = -1;//ʵ��������е�λ��
//	    
//	    System.out.println("ʶ����ӿ�ʼ��"+seq);
//	    
//	    for(i = 0; i < seq.length(); i++){
//	    	
//	    	 int start_index_first = -1,start_index_second = -1,end_index_first = -1, end_index_second = -1;
//			 //���Ҿ����г��ֵ�ʵ����
//			 start_index_first = seq.indexOf(entity_start);
//			 end_index_first = seq.indexOf(entity_end);
//			   
//			 start_index_second = seq.indexOf(entity_start,end_index_first);
//			 end_index_second = seq.indexOf(entity_end,start_index_second);
//			 if(start_index_first != -1 && start_index_second != -1 && end_index_first != -1&& end_index_second != -1){
//			     for(int j = 0; )
//			 }
//	    	if(c == ' '||i == seq.length()-1||NonChiSplit.isCharSeperator(c)){
//		    	  //===============����������ƥ��Ĺ���
//	    	      String word_derivative = "";
//	    	      WordNetHelper getstem = new WordNetHelper();
//	    	      TrieNode pChild_derivative = null;
//	    	      boolean isLetter = false;      
//	    	      int isLetterJudge = 0;
//	    	      for(isLetterJudge = 0; isLetterJudge < word.length(); isLetterJudge++){
//    		    	  if(Character.isLetter(word.charAt(isLetterJudge))){
//    		    		  isLetter = true;
//    		    		  break;
//    		    	  }
//	    	      }
//	    	      
//	    	      if(isLetter == true){
//	    	         word_derivative = getstem.findStem(word);
//	    	      }else{
//	    	    	  word_derivative = word;
//	    	      }
//    			  //===�����ǰ�ڵ���ӽڵ�Ϊ�գ��������������һ���ҵ�һ����������ƥ�䣻�������ܸôʲ����ֵ�����(��Ϊ�ôʴӸ��ڵ����û�г���)
//	    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
//	    	    	  
//	    	    	  //��ǰ�ڵ�boudֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
//	    	    	  if(p_derivative.bound){
//	    	    		  String segBuffer = "";
//	    	    		  System.out.println("CAonimabi");
//	    	              
//	    	              if((segBuffer_derivative.toString()).charAt(segBuffer_derivative.length()-1) == ' '){
//	    	            	  segBuffer = segBuffer_derivative.toString().substring(0,segBuffer_derivative.length()-1);
//	    	              }else{
//	    	            	  segBuffer = segBuffer_derivative.toString();
//	    	              }
////	    	              System.out.println("word_derivative: "+word);
////	    	              System.out.println(segBuffer+"-"+FirstEntity);
////	    	              System.out.println(segBuffer+"-"+SecondEntity); 
//	    	    		  
//	    	    		 //��ǰ�ڵ���ӽڵ�Ϊ��,˵���Ӹ��ڵ㵽��ǰ�ڵ��Ѿ���һ�����ƥ�䣻���ǵ�ǰ�ڵ���ӽڵ�Ϊ��,������ʾ�ӽڵ�������Ĵ�û�г������ֵ����У��������½��ôʴӸ��ڵ㿪ʼƥ�䣬��ˣ�ָ�����һ���ʵĳ���
//	    	    		  i = i-word.length()-1;
//	    	    		 //�ھ�����ƥ�䵽��ʵ��һ����ʵ���
//	    	    		  if(segBuffer.equals(FirstEntity)){
//	    	    			  FirstEntityLocate = i;
//	    	    		  }
//	    	    		  if(segBuffer.equals(SecondEntity)){
//	    	    			  SecondEntityLocate = i;
//	    	    		  }
//	    	    	  }
//	    	    	  //��ǰ�ڵ�boundֵΪ�٣������κδ���
//	    	    	  else{
//	    	    	      ;
//	    	    	  }
//	    	    	  //�ô����ѯ�Ѿ���Ҷ�ӽڵ㣬���˵��ֵ����ĸ��ڵ㣬�´β��ҴӸ��ڵ㿪ʼƥ��
//	    	    	  p_derivative = dict.getRootDerivative();
//	    	    	  //����ַ���
//	    	    	  segBuffer_derivative = new StringBuffer();
//	    	      }
//	    	      else{
//	    	    	  //�Ѿ��ﵽ����ĩβ,�������´ʼ������
//	    	    	  if(i == seq.length() - 1){
//	    	    		  //��ǰ�ڵ�boundֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
//	    	    		  if(pChild_derivative.bound){
//		    	    		  //System.out.println("bound");
//		    	    		  segBuffer_derivative.append(word_derivative);
//		    	    		  
//		    	    		  System.out.println(segBuffer_derivative.toString()+"-"+FirstEntity);
//		    	              System.out.println(segBuffer_derivative.toString()+"-"+SecondEntity); 
//		    	              
//		    	    		  //�ھ�����ƥ�䵽��ʵ��һ����ʵ���
//		    	    		  if((segBuffer_derivative.toString()).equals(FirstEntity)){
//		    	    			  FirstEntityLocate = i;
//		    	    		  }
//		    	    		  if((segBuffer_derivative.toString()).equals(SecondEntity)){
//		    	    			  SecondEntityLocate = i;
//		    	    		  }
//		    	    	  }
//		    	    	  //����ַ���
//		    	    	  segBuffer_derivative = new StringBuffer();
//	    	    	  }
//	    	    	  else{
//	    	    		  segBuffer_derivative.append(word_derivative+" ");
//	    	    	  }
//	    	    	  p_derivative = pChild_derivative;
//	    	      }
//		    	      
//	    		  word = "";
//	    	}
//	    	else{
//	    		word += c; 
//	    	}
//	    	
//	    	//�ж�ʵ���Ƿ������ͬһ��������
//  	        if(FirstEntityLocate != -1 && SecondEntityLocate != -1){
//  	    	    break;  
//  	        }
//	    }
////	    System.out.println("FisrtEntityLocate: " + FirstEntityLocate);
////	    System.out.println("SecondEntityLocate: " + SecondEntityLocate);
//	    int locate[] = new int [3];
//	    locate[0] = FirstEntityLocate;
//	    locate[1] = SecondEntityLocate;
//	    return locate;
//	}
	//2016/11/29�Ķ�����
	
	public int[] seg(String seq,TrieDictionary dict,String Entity)
	{
	    //��ʼ��һ���ַ������������������Ĵ���
	    StringBuffer segBuffer_derivative = new StringBuffer();
	    //�õ������ֵ�ĸ��ڵ�
	    TrieNode p_derivative = dict.getRootDerivative();
	    
	    //��ʵ��Եĵ�һ���͵ڶ���ʵ��ֱ𱣴�
	    String FirstEntity = Entity.substring(0,Entity.indexOf(','));
	    String SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
	    
	    int i;
	    char c;
	    //����Ӿ����ж�ȡ�ĵ���
	    String word = "";
	    //��һ���ڶ���ʵ��ֱ��ھ����е�λ��
	    int FirstEntityLocate = -1, SecondEntityLocate = -1;//ʵ��������е�λ��
	    
	    System.out.println("ʶ����ӿ�ʼ��"+seq);
	    
	    for(i = 0; i < seq.length(); i++){
	    	c = seq.charAt(i);
	    	if(c == ' '||i == seq.length()-1||NonChiSplit.isCharSeperator(c)){
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
	    	      }else{
	    	    	  word_derivative = word;
	    	      }
    			  //===�����ǰ�ڵ���ӽڵ�Ϊ�գ��������������һ���ҵ�һ����������ƥ�䣻�������ܸôʲ����ֵ�����(��Ϊ�ôʴӸ��ڵ����û�г���)
	    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
	    	    	  
	    	    	  //��ǰ�ڵ�boudֵΪ�棬��ʾ��ǰ�ڵ�һֱ׷�ݵ����ڵ�Ϊһ�����ƥ��Ĵ���
	    	    	  if(p_derivative.bound){
	    	    		  String segBuffer = "";
	    	    		  System.out.println("CAonimabi");
	    	              
	    	              if((segBuffer_derivative.toString()).charAt(segBuffer_derivative.length()-1) == ' '){
	    	            	  segBuffer = segBuffer_derivative.toString().substring(0,segBuffer_derivative.length()-1);
	    	              }else{
	    	            	  segBuffer = segBuffer_derivative.toString();
	    	              }
//	    	              System.out.println("word_derivative: "+word);
//	    	              System.out.println(segBuffer+"-"+FirstEntity);
//	    	              System.out.println(segBuffer+"-"+SecondEntity); 
	    	    		  
	    	    		 //��ǰ�ڵ���ӽڵ�Ϊ��,˵���Ӹ��ڵ㵽��ǰ�ڵ��Ѿ���һ�����ƥ�䣻���ǵ�ǰ�ڵ���ӽڵ�Ϊ��,������ʾ�ӽڵ�������Ĵ�û�г������ֵ����У��������½��ôʴӸ��ڵ㿪ʼƥ�䣬��ˣ�ָ�����һ���ʵĳ���
	    	    		  i = i-word.length()-1;
	    	    		 //�ھ�����ƥ�䵽��ʵ��һ����ʵ���
	    	    		  if(segBuffer.equals(FirstEntity)){
	    	    			  FirstEntityLocate = i;
	    	    		  }
	    	    		  if(segBuffer.equals(SecondEntity)){
	    	    			  SecondEntityLocate = i;
	    	    		  }
	    	    	  }
	    	    	  //��ǰ�ڵ�boundֵΪ�٣������κδ���
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
		    	    		  segBuffer_derivative.append(word_derivative);
		    	    		  
		    	    		  System.out.println(segBuffer_derivative.toString()+"-"+FirstEntity);
		    	              System.out.println(segBuffer_derivative.toString()+"-"+SecondEntity); 
		    	              
		    	    		  //�ھ�����ƥ�䵽��ʵ��һ����ʵ���
		    	    		  if((segBuffer_derivative.toString()).equals(FirstEntity)){
		    	    			  FirstEntityLocate = i;
		    	    		  }
		    	    		  if((segBuffer_derivative.toString()).equals(SecondEntity)){
		    	    			  SecondEntityLocate = i;
		    	    		  }
		    	    	  }
		    	    	  //����ַ���
		    	    	  segBuffer_derivative = new StringBuffer();
	    	    	  }
	    	    	  else{
	    	    		  segBuffer_derivative.append(word_derivative+" ");
	    	    	  }
	    	    	  p_derivative = pChild_derivative;
	    	      }
		    	      
	    		  word = "";
	    	}
	    	else{
	    		word += c; 
	    	}
	    	
	    	//�ж�ʵ���Ƿ������ͬһ��������
  	        if(FirstEntityLocate != -1 && SecondEntityLocate != -1){
  	    	    break;  
  	        }
	    }
//	    System.out.println("FisrtEntityLocate: " + FirstEntityLocate);
//	    System.out.println("SecondEntityLocate: " + SecondEntityLocate);
	    int locate[] = new int [3];
	    locate[0] = FirstEntityLocate;
	    locate[1] = SecondEntityLocate;
	    return locate;
	 }
}
