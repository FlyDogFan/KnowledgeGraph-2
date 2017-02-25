package ExtractEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import PatternForm.GroupTupleStore;
import StaticConstant.AdjustParameter;
import StaticConstant.NonChiSplit;
import TupleWeightForm.TuplesStore;
import TupleWeightForm.WeightTuples;

public class ExtractCandidateEntity {
	
	 String entity_start = "[entity_start]";
	 String entity_end = "[entity_end]";
	 //����ȡ�ĺ�ѡʵ�屣�浽�ı���
	 public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("E:/�����ļ�/��̳���/YYLRelationshipExtraction/CandidateEntityTuple.txt",true);
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
	 
	 //��ȡ��ѡʵ��
     public void GetCandidateEntity(GroupTupleStore group_pattern){
		   // GroupTupleStore group_pattern = new GroupTupleStore();
		   TuplesStore text_sentence = new TuplesStore();
		   //System.out.println(text_sentence.Text_sentence);
		  
		   Iterator it = group_pattern.temp_pattern.entrySet().iterator();
		   //Iterator it = group_pattern.entrySet().iterator();
		   String Entity = "";
		   
		   TempEntityStore entityStore = new TempEntityStore();
		   
		   //System.out.println(text_sentence.Text_sentence.size());
		   
		   //���ı��г�ȡʵ���
		   while(it.hasNext()){
			   Entry entry = (Entry)it.next();
				
			   Entity = (String)entry.getKey();
			  // System.out.println("����ģ���ʵ��ԣ�"+Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));
				 
			   ArrayList<ArrayList> group_pattern_collection = new ArrayList<>();
			   group_pattern_collection = (ArrayList<ArrayList>) entry.getValue();
			   
			   
			   for(int j = 0; j < group_pattern_collection.size(); j++){
				   
				   ArrayList<WeightTuples> pattern = group_pattern_collection.get(j);
				   //System.out.println("ÿ��ʵ��Զ�Ӧ��ģ�壺"+ pattern);
				   ArrayList<ArrayList> temp_tuple = new ArrayList<>();
				   //��Ҫ����ɶ���һ��ʵ���ʵ�������ģ����б���
				   //��Ԫ����ַ�����ʽ
                   String string_pattern = "";
                   for(int g = 0; g < pattern.size(); g++){
                      string_pattern += "<"+pattern.get(g).word+","+pattern.get(g).weight+">"+",";
       			   }
                   string_pattern +="}";
       			   //��Ԫ���ַ�����ʽ����
                   if(Entity.indexOf(AdjustParameter.RecognizeFlag) > 0){
					   if(entityStore.getPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))) != null){
						   if(!entityStore.string_tuple.contains(string_pattern)){
	                           entityStore.getPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))).add(pattern);	
	                           entityStore.string_tuple.add(string_pattern);
						   }
	          		   }else{
	          			   
	          			   temp_tuple.add(pattern);
	          		       entityStore.putPattern(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)),temp_tuple);
	          		       entityStore.string_tuple.add(string_pattern);
	          		       //2017/2/13�޸Ŀ�ʼ
	          		       if(!entityStore.entity_pairs.contains(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag))))
	          		          entityStore.entity_pairs.add(Entity.substring(0, Entity.indexOf(AdjustParameter.RecognizeFlag)));//���µ�ʵ�屣�浽������
	          		      //2017/2/13�޸Ľ���
	          		   }
                   }
				   
				   for(int i = 0; i < text_sentence.Text_sentence.size(); i++){
					   String sentence = text_sentence.Text_sentence.get(i);
					   
					   //���Ҿ����г��ֵ�ʵ����
					   int FirstEntityLocateStart = -1, FirstEntityLocateEnd = -1, SecondEntityLocateStart = -1, SecondEntityLocateEnd = -1;//ʵ��������е�λ��
					   FirstEntityLocateStart =sentence.indexOf(entity_start);
					   FirstEntityLocateEnd = sentence.indexOf(entity_end);
					    
					   SecondEntityLocateStart = sentence.indexOf(entity_start,FirstEntityLocateEnd);
					   SecondEntityLocateEnd = sentence.indexOf(entity_end,SecondEntityLocateStart);
					   
					   if(FirstEntityLocateStart != -1 && FirstEntityLocateEnd != -1 && SecondEntityLocateStart != -1&& SecondEntityLocateEnd != -1){
						   String temp_prefix = "";
						   String temp_middle = "";
						   String temp_suffix = "";
						   
						   //�õ�pattern��ǰ׺����,ǰ׺ƥ��
						   String prefix = "";		   
						   int flag = 0;
						   for(int t = 0; t < AdjustParameter.PreWordNum-1; t++){
							   if(!"".equals(pattern.get(t).word)){ 
								   prefix += pattern.get(t).word;
								   prefix += " ";
							   }
						   }
						   prefix += pattern.get(AdjustParameter.PreWordNum-1).word;
						   
						   //�õ�������ǰ׺�ĵ���
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
				    	    	else if(Character.isLetter(sentence.charAt(i_first))){
				    	    		break;
				    	    	}
				    	    	i_first --;
				    	   }
				    	   //���浥��
				    	   for(; i_first >= 0; i_first--){
				    		   	c = sentence.charAt(i_first);
				    		   	
				    		    if(word_number == AdjustParameter.PreWordNum)
				    		   		break;
				    		   	//�ҵ�һ������
				    		   	if(c == ' '||i_first == 0||NonChiSplit.isCharSeperator(c)){
				    		   		String word_reverse = "";
				    		   		if(i_first == 0)
				    		   			word.append(c);
				    		   		//��������ĸת�����򣬵õ����ʱ����ڱ�����
				    		   		word_reverse = word.reverse().toString();
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
				    		int k = 0;
			    			for(k = preWord.size()-1; k > 0; k--){
			    				temp_prefix += preWord.get(k) + " ";
			    			}
			    			if(!preWord.isEmpty())
						       temp_prefix += preWord.get(0);
						    
						    //���ģ���е�ǰ׺�;����е�ǰ׺��ƥ�䣬������Ĳ���ô�ȽϽ���
						    if(!temp_prefix.equals(prefix)){
						    	continue;
						    }
						   
						    //�õ�pattern����׺���ʣ���׺ƥ��
						    String middle = "";
						    for(int t = AdjustParameter.PreWordNum; t < pattern.size()-AdjustParameter.SufWordNUm; t++){
							   if(!"".equals(pattern.get(t).word)){
							      middle += pattern.get(t).word + " ";
							   }
						    }
						    if(middle.length() != 0)
						       middle = middle.substring(0,middle.length()-1);
						   
						    //�õ���������׺����
						    word = new StringBuffer();
				    	    int i_end = 0;
				    	    i_end =  SecondEntityLocateStart - 1;
					        while(true){
					    		if(i_end < 0){
					    			break;
					    		}
					    		else if(!Character.isLetter(sentence.charAt(i_end))){
					    	    	break;
					    	    }
					    	    i_end --;
					        }
				    	    for(i_first = FirstEntityLocateEnd + entity_end.length() + 1; i_first <= i_end; i_first++){
				    		   	c = sentence.charAt(i_first);
				    		   	
				    		   	//�ҵ�һ������
				    		   	if(c == ' '||i_first == i_end||NonChiSplit.isCharSeperator(c)){
				    		   		String word_middle = "";
				    		   		word_middle = word.toString();
				    		   		word = new StringBuffer();
				    		   		
				    		   		if(!"".equals(word_middle))
				    		   		    temp_middle += word_middle + " ";
				    		   		
				    		   	}
				    		   	else{
				    		   		word.append(c);
				    		   	}
				    	   }
				    	   if(temp_middle.length() != 0)
						      temp_middle = temp_middle.substring(0,temp_middle.length()-1);
						  
						   //���ģ���е���׺�;����е���׺��ƥ�䣬������Ĳ���������
						   if(!temp_middle.equals(middle)){
							   continue;
						   }
						   
						   
						   //�õ�pattern�ĺ�׺���ʣ���׺ƥ��
						   String suffix = "";
						   for(int t = pattern.size()-AdjustParameter.SufWordNUm; t < pattern.size() - 1; t++){
							   if(!"".equals(pattern.get(t).word)){
								   suffix += pattern.get(t).word;
								   suffix += " ";
							   }   
						   }
						   suffix += pattern.get(pattern.size()-1).word;
						   //�õ������еĺ�׺����
						   word = new StringBuffer();
				    	   word_number = 0;
				    	   for(i_first = SecondEntityLocateEnd + entity_end.length() + 1; i_first < sentence.length(); i_first++){
				    		   	c = sentence.charAt(i_first);
				    		   	
				    		   	if(word_number == AdjustParameter.SufWordNUm)
				    		   		break;
				    		   	
				    		   	//�ҵ�һ������
				    		   	if(c == ' '||i_first == (sentence.length()-1)||NonChiSplit.isCharSeperator(c)){   		
				    		   		
				    		   		String word_suffix = "";
				    		   		word_suffix = word.toString();
				    		   		word = new StringBuffer();
				    		   		word_number += 1;
				    		   		
				    		   		//SuffixWords += word_suffix + " ";
				    		   		if(!"".equals(word_suffix)){
				    		   		   temp_suffix += word_suffix + " ";
				    		   		}
				    		   	}
				    		   	else{
				    		   		word.append(c);
				    		   	}
				    		}
				    	    if(temp_suffix.length() != 0)
				    		   temp_suffix = temp_suffix.substring(0,temp_suffix.length()-1);
				    		
				    		//���ģ���еĺ�׺�;����еĺ�׺��ƥ�䣬������Ĳ���������
							if(!temp_suffix.equals(suffix)){
								continue;
							}

							if(prefix.equals(temp_prefix) && middle.equals(temp_middle) && suffix.equals(temp_suffix)){
	                             ArrayList<ArrayList> tuple = new ArrayList<>();
	                             String NewEntity = "";
	                             NewEntity = sentence.substring(FirstEntityLocateStart + entity_start.length() + 1,FirstEntityLocateEnd - 1) +"," + sentence.substring(SecondEntityLocateStart + entity_start.length() + 1,SecondEntityLocateEnd - 1);
//	                             System.out.println("������ʵ�壺"+NewEntity);
//	                             System.out.println("������ʵ���ģ�壺"+pattern);
	                             //��Ԫ����ַ�����ʽ
	                             String pattern_string = "";
                                 for(int g = 0; g < pattern.size(); g++){
                                     pattern_string += "<"+pattern.get(g).word+","+pattern.get(g).weight+">"+",";
                     			 }
                     			 pattern_string +="}";
                     			 //��Ԫ���ַ�����ʽ����	 
	                             if(entityStore.getPattern(NewEntity) != null){
	                            	if(!entityStore.string_tuple.contains(pattern_string)){
	                            		
	                            		entityStore.getPattern(NewEntity).add(pattern);
	                            		entityStore.string_tuple.add(pattern_string);
	                            	}
	                    		 }else{
	                    		     tuple.add(pattern);
	                    		     entityStore.putPattern(NewEntity,tuple);
	                    		     //Ԫ����ַ�����ʽ
	                    		     entityStore.string_tuple.add(pattern_string);
	                    		     //Ԫ���ַ�����ʽ��
	                    		     //2017/2/12�޸Ŀ�ʼ
	   	                             if(!entityStore.entity_pairs.contains(NewEntity))
	                    		        entityStore.entity_pairs.add(NewEntity);//���µ�ʵ�屣�浽������
	                    		     //2017/2/12�޸Ľ���
	                    		 }
							}
							
							FirstEntityLocateStart = -1;
							FirstEntityLocateEnd = -1;
							    
							SecondEntityLocateStart = -1;
							SecondEntityLocateEnd = -1;
					   } 
					  
					   
				   }
			   }
		   }
		   
		   //��������
//		   System.out.println("extract entity finish");
//		   it = entityStore.temp_entity.entrySet().iterator();
//		   while(it.hasNext()){
//				 Entry entry = (Entry)it.next();
//				
//				 Entity = (String)entry.getKey();
//				 System.out.println(Entity);
//				 
//				 WriteIntoFile(Entity);
//				 
//				 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
//				 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
//				 System.out.println(GroupFiveTupleCollection);
//				 
//				 
//				 
//				 for(int j = 0; j < GroupFiveTupleCollection.size(); j++){
//					ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
//					String test_string = "";
//					System.out.println("=====");
//					test_string +="{";
//					for(int t = 0; t < test.size(); t++){
//						test_string += "<"+test.get(t).word+","+test.get(t).weight+">"+",";
//						System.out.println(test.get(t).word);
//						System.out.println(test.get(t).weight);
//					}
//					test_string +="}";
//					WriteIntoFile(test_string);
//				 }
//			   
//			   //
//		  }
	 }
}
