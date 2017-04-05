package TupleWeightForm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import FeatureExtract.FeatureStore;
import NameEntityRecognize.TrieDictionary;
import NameEntityRecognize.WordNetHelper;
import StaticConstant.AdjustParameter;
import StaticConstant.ConstantParameter;
import StaticConstant.NonChiSplit;


public class ExtractTuple {
	  String entity_start = "[entity_start]";
	  String entity_end = "[entity_end]";
	  
	  
	  //��ƥ�䵽�ĵ���д���ļ���
	  public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("D:/YYLSoftware/Program/YYLRelationshipExtraction/SentenceTuple.txt",true);
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
	  
	  //��ȡ��Ԫ����������Ԫ�������
	  public void ExtractTuples(int k,String Entity, TuplesStore tuplesStore){
		  
		    int i,j;
			String word = "";
			char c;
			
			//�Ƿ���ϴ��������
			int flag = 0;
			
			//������Ӻ���Ԫ������
			String Sentence = FeatureStore.SentenceVector.get(k).sentence;
			ArrayList<Float> five_tuple_vector = new ArrayList<>();
			for(int g = 0; g < FeatureStore.SentenceVector.get(k).vector.size(); g++)
			    five_tuple_vector.add(FeatureStore.SentenceVector.get(k).vector.get(g)); 
			
			//System.out.println("ExtractTuplesSentence:"+ Sentence);
			
			//����ʵ�����ʵ��ı���
			String FirstEntity = "",SecondEntity = "";
		    FirstEntity = Entity.substring(0,Entity.indexOf(','));
		    SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
		    
		    //System.out.println("ExtractTuplesEntity:"+ FirstEntity + ":" + SecondEntity);
			
			//�õ�������ÿ��ʵ���λ��
		    int FirstEntityStartLocate = -1;
		    int FirstEntityEndLocate = -1;
		    
		    int SecondEntityStartLocate = -1;
		    int SecondEntityEndLocate = -1;
		    
		    //����������ʵ��ı���
	        String SentenceFirstEntity = "", SentenceSecondEntity = "";
	       
	        FirstEntityStartLocate = Sentence.indexOf(entity_start);
	        FirstEntityEndLocate = Sentence.indexOf(entity_end);
	        SecondEntityStartLocate = Sentence.indexOf(entity_start,FirstEntityEndLocate);
	        SecondEntityEndLocate = Sentence.indexOf(entity_end,SecondEntityStartLocate);
	        if(FirstEntityStartLocate + entity_start.length() + 1 < FirstEntityEndLocate - 1 && SecondEntityStartLocate + entity_start.length() + 1< SecondEntityEndLocate - 1){
	           SentenceFirstEntity = Sentence.substring(FirstEntityStartLocate + entity_start.length() + 1,FirstEntityEndLocate - 1);
	           SentenceSecondEntity = Sentence.substring(SecondEntityStartLocate + entity_start.length() + 1,SecondEntityEndLocate - 1);
	        }
	        
	        //System.out.println("ExtractTuplesSentenceEntity:"+ SentenceFirstEntity + ":" + SentenceSecondEntity);
	        
	        //���³�ʼ��������ʵ��λ�ñ���
	        FirstEntityStartLocate = -1;
		    FirstEntityEndLocate = -1;
		    
		    SecondEntityStartLocate = -1;
		    SecondEntityEndLocate = -1;
		   
		    if(SentenceFirstEntity.equals(FirstEntity) && SentenceSecondEntity.equals(SecondEntity)){//��������е�ʵ��;����е�ʵ��ƥ��
				    int word_num = 0;
				    String five_tuple = "";//������Ԫ��
				    for(j = 0; j < Sentence.length(); j++){
				    	c = Sentence.charAt(j);
				    	//������ַ�c�ǿո�����һ������
				    	if(c == ' '|| j == Sentence.length()-1){
				    		if(j == Sentence.length()-1){
				    			word += c;
				    		}
		//		    		SentenceHash.put(word,word_num);
				    	    //����ʵ��λ��
				    		if(word.equals(entity_start)){
				    			if(FirstEntityStartLocate == -1){
				    				FirstEntityStartLocate = word_num;
				    			}else{
				    				SecondEntityStartLocate = word_num;
				    			}
				    		}else if(word.equals(entity_end)){
				    			if(FirstEntityEndLocate == -1){
				    				FirstEntityEndLocate = word_num;
				    			}else{
				    				SecondEntityEndLocate = word_num;
				    			}
				    		}
				    		word_num ++;
				    		word = "";
				    	}else{
				    		word += c; 
				    	} 
				    }
				    
				   // System.out.println("Sentence:"+Sentence);
				   // System.out.println("EntityLocate:"+ FirstEntityStartLocate + ":" + FirstEntityEndLocate + ":" + SecondEntityStartLocate + ":" + SecondEntityEndLocate);
				    
				    
				    word_num = 0;
				    word = "";
					//�õ�������ǰ�к�׺����
					for(j = 0; j < Sentence.length(); j++){
				    	c = Sentence.charAt(j);
				    	//������ַ�c�ǿո�����һ������
				    	if(c == ' '|| j == Sentence.length()-1){
				    		if(j == Sentence.length()-1){
				    			word += c;
				    		}
				    		int p1 = -1;//���ʵ���һ��ʵ��ľ���
				    	    int p2 = -1;//���ʵ��ڶ���ʵ��ľ���
				    	    flag = 0;
				    		if(!word.equals(entity_start) && !word.equals(entity_end)){//������ʲ���ʵ���־
					    		if(word_num < FirstEntityStartLocate){//��һ��ʵ��ǰ�ĵ���
					    			p1 = 0 - (FirstEntityStartLocate - word_num); 
					    			p2 = 0 - (SecondEntityStartLocate - word_num);
					    			if((0-p1) <= AdjustParameter.PreWordNum){//������ڵ�һ��ʵ��ǰ3������֮��
					    				flag = 1;
					    			}
//					    		}else if(word_num > FirstEntityStartLocate && word_num < FirstEntityEndLocate){//��һ��ʵ��
//					    			p1 = 0;
//					    			p2 = FirstEntityEndLocate - SecondEntityStartLocate;
//					    			flag = 2;
					    		}else if(word_num > FirstEntityEndLocate && word_num < SecondEntityStartLocate){//��һ��ʵ��͵ڶ���ʵ��֮��ĵ���
									p1 = word_num - FirstEntityEndLocate;
									p2 = 0 - (SecondEntityStartLocate - word_num);
									flag = 1;
//								}else if(word_num > SecondEntityStartLocate && word_num < SecondEntityEndLocate){//�ڶ���ʵ��
//									p1 = FirstEntityEndLocate - SecondEntityStartLocate;
//									p2 = 0;
//									flag = 2;
								}else if(word_num > SecondEntityEndLocate){//�ڶ���ʵ���ĵ���
									p1 = word_num - FirstEntityEndLocate;
									p2 = word_num - SecondEntityEndLocate;
									if(p2 <= AdjustParameter.SufWordNUm){//������ڵڶ���ʵ�����������֮��
										flag = 1;
									}
								}
					    		
					    		//System.out.println("word:"+ word + ":"+"distant:"+p1+":"+p2);
					    		
					    	    //�õ�ǰ�к�׺����
					    		if(flag == 1){
						    		five_tuple += word + " ";
//					    		}else if(flag == 2){
//					    			five_tuple += word + " ";
					    		}
				    		}else if(!word.equals(entity_start)){
				    		     five_tuple += "| "; 
				    		}
				    		word_num ++;
				    		word = "";
				    	}else{
				    		word += c; 
				    	} 
				    }
					//����ʵ��Ժ�����Ԫ����������Ԫ�����
					FeatureStore featureStore = new FeatureStore();
					featureStore.sentence = five_tuple;
					for(int g = 0; g < five_tuple_vector.size(); g++){
					     featureStore.vector.add(five_tuple_vector.get(g));
					}
				    
					//System.out.println("ExtractTuplesFeatureStore:"+ featureStore.sentence + ":" + featureStore.vector);
				
	    		    ArrayList<FeatureStore> Tuple = new ArrayList<>();
	    			Tuple.add(featureStore);
	    			if(tuplesStore.getKey(Entity) != null){
	    				tuplesStore.getKey(Entity).add(featureStore);
	    			}else{
	    				tuplesStore.putTuples(Entity,Tuple);
	    			}
		    }
	  }
}
