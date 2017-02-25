package TupleWeightForm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import NameEntityRecognize.TrieDictionary;
import NameEntityRecognize.WordNetHelper;
import StaticConstant.AdjustParameter;
import StaticConstant.NonChiSplit;


public class ExtractTuple {
	  //static ArrayList<ArrayList> PatternTuples = new ArrayList<>();
	  String entity_start = "[entity_start]";
	  String entity_end = "[entity_end]";
	  
	  
	  //��ƥ�䵽�ĵ���д���ļ���
	  public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("E:/�����ļ�/��̳���/EnglishSegTest/SentenceTuple.txt",true);
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
	  
	  //��ȡԪ���ϵ
	  public  ArrayList<HashMap<String, Integer>> ExtractTuples(String Sentence,String Entity, TuplesStore tuplesStore){
		  
			String FirstEntity = "",SecondEntity = "";
			//��ʵ��Եĵ�һ���͵ڶ���ʵ��ֱ𱣴�
		    FirstEntity = Entity.substring(0,Entity.indexOf(','));
		    SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
		    
//		    System.out.println(FirstEntity);
//		    System.out.println(SecondEntity);
		    
			//����TrieDictionary�����ֵ���
//			NameEntityRecognize.TrieDictionary dict = null;
//	        dict = TrieDictionary.getInstance(Entity);
//	        
//	        //�����ֵ��������ʵ���ƥ�䵥ʵ��
//			IdentityTuple  mmsegger = new IdentityTuple();
//			int locate[] = new int [3];
//	        locate = mmsegger.seg(Sentence,dict,Entity);
//				
//	        //��һ���ڶ���ʵ��ֱ��ھ����е�λ��
		    //2017/2/8ʹ��ȫƥ��ķ�����ʶ��ʵ�壬�õ�Ԫ��
		    int FirstEntityLocateStart = -1, FirstEntityLocateEnd = -1, SecondEntityLocateStart = -1, SecondEntityLocateEnd = -1;//ʵ��������е�λ��
		    FirstEntityLocateStart = Sentence.indexOf(entity_start);
		    FirstEntityLocateEnd = Sentence.indexOf(entity_end);
		    
		    SecondEntityLocateStart = Sentence.indexOf(entity_start,FirstEntityLocateEnd);
		    SecondEntityLocateEnd = Sentence.indexOf(entity_end,SecondEntityLocateStart);
		    
		    ArrayList<HashMap<String, Integer>> TempList = new ArrayList();
	    	HashMap<String, Integer>   prefix = new HashMap<>();
	        HashMap<String, Integer>   middle = new HashMap<>();
	        HashMap<String, Integer>   suffix = new HashMap<>();
	        String middle_string = "";
	        
	        //�����ȡ��Ԫ��
	        ArrayList TempTuple = new ArrayList();
	        
		    //��������ʵ����������ַ���
	        String FirstEntityLocate = "", SecondEntityLocate = "";
	        
	        FirstEntityLocate = Sentence.substring(FirstEntityLocateStart + entity_start.length() + 1,FirstEntityLocateEnd - 1);
	        SecondEntityLocate = Sentence.substring(SecondEntityLocateStart + entity_start.length() + 1,SecondEntityLocateEnd - 1);
	        
	        //System.out.println("�����е�ʵ�壺" + FirstEntityLocate+ " " + SecondEntityLocate);
	        //System.out.println("�����е�ʵ��" + FirstEntity+ " " + SecondEntity);
	        
		    if(FirstEntityLocate.equals(FirstEntity) && SecondEntityLocate.equals(SecondEntity)){
		        
//			    	System.out.println(FirstEntityLocate+ " " + SecondEntityLocate);
//			    	System.out.println(Sentence);
		    	
		    		//�õ�ԭ�ĵ��ʵĴʸ�
//		    		WordNetHelper getstem = new WordNetHelper();
		    		//�õ�ǰ׺�ĵ���
		    		int i_first = 0;
		    		char c;
		    	    //����Ӿ����ж�ȡ�ĵ���
		    	    StringBuffer word = new StringBuffer();
		    	    //ǰ�к�׺���ʸ�������
		    	    int word_number = 0;
		    	    
		    	    //ǰ׺����ͳ��
		    	    ArrayList<String> preWord = new ArrayList<>();
		    	    i_first = FirstEntityLocateStart-1;
		    	    
		    	    //�������һ��ʵ��ǰ�����пո�
		    	    while(true){
		    	    	if(i_first < 0){
		    	    		break;
		    	    	}
		    	    	else if(Character.isLetter(Sentence.charAt(i_first))){
		    	    		break;
		    	    	}
		    	    	i_first --;
		    	    }
		    		for(; i_first >= 0; i_first--){
		    		   	c = Sentence.charAt(i_first);
		    		   	
//		    		   	boolean isLetter = false;
//			    	    int isLetterJudge = 0;
		    		   	
		    		    if(word_number == AdjustParameter.PreWordNum)
		    		   		break;
		    		   	//�ҵ�һ������
		    		   	if(c == ' '||i_first == 0||NonChiSplit.isCharSeperator(c)){
		    		   		String word_reverse = "";
		    		   		if(i_first == 0)
		    		   			word.append(c);
		    		   		//��������ĸת�����򣬵õ����ʱ����ڱ�����
		    		   		word_reverse = word.reverse().toString();
		    		   		
				    	    if(!prefix.containsKey(word_reverse)){//���ǰ׺��ϣ����û�а����õ���
		    		   			if(!"".equals(word_reverse))//�õ��ʲ�Ϊ��
		    		   		       prefix.put(word_reverse, 1);//���øõ��ʰ���String,int��ʽ��������,�õ�ǰ׺�иõ��ʵĸ���
		    		   		}
		    		   		else{//���򣬸õ�����֮ǰ��ǰ׺�г��ֹ����õ�������Ӧ��Ƶ����һ
		    		   			prefix.put(word_reverse,prefix.get(word_reverse)+1);
		    		   		}
		    		   		word = new StringBuffer();
		    		   		word_number += 1;
		    		   		
		    		   		//�ı����ﱣ���ǰ׺�����ǵ��Ŵ�ģ���������ȡ��ʱ����Ҫ����ȡ������ÿ��һ�������е�ǰ׺�������ݴ���preword�����У������������򱣴浽TempTuple�У�Ϊ����Ԫ���Ȩ����׼��
		    		   		if(!"".equals(word_reverse))
		    		   		   preWord.add(word_reverse);
		    		   	}
		    		   	else{
		    		   		word.append(c);
		    		   	}
		    		}
		    		
		    		//���򱣴�ǰ׺�Ĵ�
		    		int j = 0;
		    		//2016/12/13�Ķ�
		    		
	    			for(j = 0; j < AdjustParameter.PreWordNum - preWord.size(); j++){
	    				TempTuple.add("");
	    			}
	    			for(j = preWord.size()-1; j >= 0; j--){
	    				TempTuple.add(preWord.get(j));
	    			}
		    		//2016/12/13�Ķ�����
		    		
		    		//ǰ׺��������󣬱����һ��ʵ��
		    		TempTuple.add(FirstEntity);
		    		
		    		//��׺����ͳ��
		    		word = new StringBuffer();
		    		int i_end = 0;
		    		i_end =  SecondEntityLocateStart - 1;
			    	while(true){
			    		if(i_end < 0){
			    			break;
			    		}
			    		else if(!Character.isLetter(Sentence.charAt(i_end))){
			    	    	break;
			    	    }
			    	    i_end --;
			    	}
		    		for(i_first = FirstEntityLocateEnd + entity_end.length() + 1; i_first <= i_end; i_first++){
		    		   	c = Sentence.charAt(i_first);
		    		   	
		    		   	//�ҵ�һ������
		    		   	if(c == ' '||i_first == i_end||NonChiSplit.isCharSeperator(c)){
		    		   		String word_middle = "";
		    		   		word_middle = word.toString();
		    		   		
		    		   		if(!middle.containsKey(word_middle)){
		    		   			if(!"".equals(word_middle))
		    		   		       middle.put(word_middle, 1);
		    		   		}
		    		   		else{
		    		   			middle.put(word_middle,middle.get(word_middle)+1);
		    		   		}
		    		   		word = new StringBuffer();
		    		   		
		    		   		if(!"".equals(word_middle))
		    		   		    TempTuple.add(word_middle);
		    		   		
		    		   	}
		    		   	else{
		    		   		word.append(c);
		    		   	}
		    		}
		    		
		    		//��׺��������󱣴�ڶ���ʵ��
		    		TempTuple.add(SecondEntity);
		    		
		    		//��׺����ͳ��
		    		word = new StringBuffer();
		    		word_number = 0;
		    		ArrayList<String> suffWord = new ArrayList<>();
		    		for(i_first = SecondEntityLocateEnd + entity_end.length() + 1; i_first < Sentence.length(); i_first++){
		    		   	c = Sentence.charAt(i_first);
		    		   	
		    		   	if(word_number == AdjustParameter.SufWordNUm)
		    		   		break;
		    		   	
		    		   	//�ҵ�һ������
		    		   	if(c == ' '||i_first == (Sentence.length()-1)||NonChiSplit.isCharSeperator(c)){   		
		    		   		
		    		   		String word_suffix = "";
		    		   		word_suffix = word.toString();
		    		   		
		    		   		if(!suffix.containsKey(word_suffix)){
		    		   			if(!"".equals(word_suffix))
		    		   		      suffix.put(word_suffix, 1);
		    		   		}
		    		   		else{
		    		   			suffix.put(word_suffix,suffix.get(word_suffix)+1);
		    		   		}
		    		   		word = new StringBuffer();
		    		   		word_number += 1;
		    		   		
		    		   		//SuffixWords += word_suffix + " ";
		    		   		if(!"".equals(word_suffix))
		    		   		   suffWord.add(word_suffix);
		    		   	}
		    		   	else{
		    		   		word.append(c);
		    		   	}
		    		}
		    		
		    		//�����׺����
		    		//2016/12/13�Ķ�
		    		for(j = 0; j < suffWord.size(); j++){
		    			TempTuple.add(suffWord.get(j));
		    		}
		    		for(j = suffWord.size(); j < AdjustParameter.SufWordNUm; j++){
		    			TempTuple.add("");
		    		}
		    		//2016/12/13�Ķ�����
		    		
	    			TempList.add(prefix);
	    			TempList.add(suffix);
	    			TempList.add(middle);
	    			
//	    			System.out.println("TempTuple: " + TempTuple);
//	    			System.out.println("prefix: " + prefix);
//	    			System.out.println("middle: " + middle);
//	    			System.out.println("suffix: " + suffix);
	    			
	    			//������Ԫ��
	    			ArrayList<ArrayList> Tuple = new ArrayList<>();
	    			Tuple.add(TempTuple);
	    			if(tuplesStore.getKey(Entity) != null){
	    				tuplesStore.getKey(Entity).add(TempTuple);
	    			}else{
	    				tuplesStore.putTuples(Entity,Tuple);
	    			}
	    			
	    			//WriteIntoFile("1"+"$"+FirstEntity+"$"+SecondEntity+"$"+prefix+"$"+suffix+"$"+middle);
		    	
		    	//PatternTuples.add(TempList);
		    	
		    	//WriteIntoFile(Sentence);
		    	
		    }
		    return TempList;
	  }
}
