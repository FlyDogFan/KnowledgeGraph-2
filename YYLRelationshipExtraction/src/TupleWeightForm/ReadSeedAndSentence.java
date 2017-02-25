package TupleWeightForm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import ExtractEntity.TempEntityStore;

import java.util.Scanner;

import PatternForm.GroupTuple;
import PatternForm.GroupTupleStore;
import PatternForm.MatchTuple;
import StaticConstant.AdjustParameter;
import StaticConstant.NonChiSplit;


public class ReadSeedAndSentence {
	 //û�ʵ�����Ҫ����ǰ�к�׺
	 HashMap<String, Integer>   prefix = new HashMap<>();//ǰ׺��ÿ�����ʶ�Ӧ��Ƶ��
     HashMap<String, Integer>   middle = new HashMap<>();//��׺��ÿ�����ʶ�Ӧ��Ƶ��
     HashMap<String, Integer>   suffix = new HashMap<>();//��׺��ÿ�����ʶ�Ӧ��Ƶ��
     int prefix_total_word = 0;
     int middle_total_word = 0;
     int suffix_total_word = 0;
	 //��������ʵ���ϵ��
	 TempEntityStore SeedList = new TempEntityStore();
	 
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
	 
	  //������������2��ʱ�򱣴�һ���ֿ������
	 public void WriteIntoFile2(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("E:/�����ļ�/��̳���/YYLRelationshipExtraction/CandidateEntityTuple2.txt",true);
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
	 
     //��ȡ�ı��е����ӹ�ϵ��
	 public void GetSeed(String SeedFileName, String SentenceFileName){
		  try{
			 //��ȡ���ӶԵ��ڴ�
		     Scanner EntityPairs = new Scanner(new File(SeedFileName),"UTF-8");
		     while (EntityPairs.hasNextLine()){
		    	 String Entity = EntityPairs.nextLine();
		    	 SeedList.entity_pairs.add(Entity);
		     }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  //��ȡ���ӱ��浽�ڴ�
		  System.out.println("���ӶԸ���"+SeedList.entity_pairs.size());
		  ReadSentence(SentenceFileName);
	 }
	 
	 //��ȡ���ӵ��ڴ�
	 public void ReadSentence(String FileName){
		 //�����ȡ�ľ���
		 TuplesStore text_sentence = new TuplesStore();
		
//		 int i;
//		 try{
//		     Scanner reader = new Scanner(new File(FileName),"UTF-8");
//		     while (reader.hasNextLine()){
//		    	 String Sentence = reader.nextLine();
//		         //�����Ѿ���ȡ���ı����ӣ�Ϊ����׼��
//             	 text_sentence.putSentence(Sentence);
//		     }
//
//		 }catch(Exception e){
//			 e.printStackTrace();
//		 }
		 
		 try{
		     //Scanner reader = new Scanner(new File(FileName),"UTF-8");
		     int i = 0;
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
                	 //System.out.println(Sentence); 
                	 //�����Ѿ���ȡ���ı����ӣ�Ϊ����׼��
                	 text_sentence.putSentence(Sentence); 
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
		 
		 GetSentence();
	 }
	 
     //��ȡ�ڴ��еľ��ӣ�����ȡ��������������Ԫ��
	 public void GetSentence(){
	   	 String Sentence = "";
	   	 TuplesStore text_sentence = new TuplesStore();
         int i,j;
         int iterator_flag = 0;
         //������е�������ȡʵ���ģ��
		 while(true){
			 //������ȡ�����ж�����
			 int judge_start = SeedList.entity_pairs.size();
			 //��������
			 iterator_flag ++;
			 //2017/2/12�޸ģ�ÿ�ε������汾�ε�����������Ԫ��
			 TuplesStore TheTupleStore = new TuplesStore();
			 //ÿ�ε���ʱ����ʼ����ȡԪ�����
			 ExtractTuple extract_tuple = new ExtractTuple();
			 //2017/2/12�޸Ľ���
			 
			 
			 //������ȡ��ʼ
		   	 for(i = 0; i < SeedList.entity_pairs.size();i++){
		   		 for(j = 0; j < text_sentence.Text_sentence.size(); j++){
		   		    Sentence = text_sentence.Text_sentence.get(j);
		   			ArrayList<HashMap<String, Integer>> TempList = new ArrayList();
		      		
//		   			ExtractTuple extract_tuple = new ExtractTuple();
		      		TempList = extract_tuple.ExtractTuples(Sentence,SeedList.entity_pairs.get(i).toString(),TheTupleStore);
		//          		System.exit(0);
		      		
		      		String word = "";
		      		int word_number = 0;
		      	
		      		if(TempList.size() > 0){
		          		//ͳ��ǰ׺�ĵ��ʸ���
		          		Iterator it = TempList.get(0).entrySet().iterator();  
		          		while(it.hasNext()){  
		          		     Entry entry = (Entry)it.next();  
		          		     word = (String)entry.getKey(); //����������Ӧ�ļ�  
		          		     word_number = (int)entry.getValue(); // ����������Ӧ��ֵ 
		          		     
		          		     //���ǰ׺�Ѿ������õ��ʣ������ӵ��ʵ�����Ӧ��Ƶ��
		          		     if(prefix.containsKey(word)){
		          		    	 prefix.put(word, prefix.get(word) + word_number);
		          		     }else{//��������Ӹõ��ʺ�����ǰ׺�е�Ƶ��
		          		    	 prefix.put(word,word_number);
		          		     }
		          		     //System.out.println(entry.getValue());
		          		     prefix_total_word += word_number; 
		          		     
		//              		     System.out.println("prefix");
		//              		     System.out.println(word);
		//              		     System.out.println(prefix.get(word));
		          		}  
		          		
		          		
		          		//ͳ����׺�ĵ��ʸ���
		          		it = TempList.get(2).entrySet().iterator();
		          		while(it.hasNext()){  
		          		     Entry entry = (Entry)it.next();  
		          		     word = (String)entry.getKey(); //����������Ӧ�ļ�  
		          		     word_number = (int)entry.getValue(); // ����������Ӧ��ֵ 
		          		     
		          		     //���ǰ׺�Ѿ������õ��ʣ������ӵ��ʵ�����Ӧ��Ƶ��
		          		     if(middle.containsKey(word)){
		          		    	 middle.put(word, middle.get(word) + word_number);
		          		     }else{//��������Ӹõ��ʺ�����ǰ׺�е�Ƶ��
		          		    	 middle.put(word,word_number);
		          		     }
		          		     //System.out.println(entry.getValue()); 
		          		     middle_total_word += word_number;
		          		     
		//              		     System.out.println("middle");
		//              		     System.out.println(word);
		//              		     System.out.println(middle.get(word));
		          		}
		          		
		          		//ͳ�ƺ�׺�ĵ��ʸ���
		          		it = TempList.get(1).entrySet().iterator();
		          		while(it.hasNext()){  
		          		     Entry entry = (Entry)it.next();  
		          		     word = (String)entry.getKey(); //����������Ӧ�ļ�  
		          		     word_number = (int)entry.getValue(); // ����������Ӧ��ֵ 
		          		     
		          		     //���ǰ׺�Ѿ������õ��ʣ������ӵ��ʵ�����Ӧ��Ƶ��
		          		     if(suffix.containsKey(word)){
		          		    	 suffix.put(word, suffix.get(word) + word_number);
		          		     }else{//��������Ӹõ��ʺ�����ǰ׺�е�Ƶ��
		          		    	 suffix.put(word,word_number);
		          		     }
		          		     //System.out.println(entry.getValue());
		          		     suffix_total_word += word_number;
		          		     
		//              		     System.out.println("suffix");
		//              		     System.out.println(word);
		//              		     System.out.println(suffix.get(word));
		          		}
		   		    }
		   	    }
		   	 
		   	 }
		   	 //������ȡ����
//		   	System.out.println("ReadSeed: "+prefix_total_word);
//		    System.out.println("ReadSeed: "+prefix);
//		    System.out.println("ReadSeed: "+middle_total_word);
//		    System.out.println("ReadSeed: "+middle);
//		    System.out.println("ReadSeed: "+suffix_total_word);
//		    System.out.println("ReadSeed: "+suffix);
//		    System.out.println("ReadSeed: "+TheTupleStore.Tuples);
		    
		    CalculateTupleWeight(TheTupleStore);
		    
		    //2017/2/12�޸ģ�ÿ�ε�����������ǰ�к�׺Ƶ�����г�ʼ��
		    prefix = new HashMap<>();//ǰ׺��ÿ�����ʶ�Ӧ��Ƶ��
		    middle = new HashMap<>();//��׺��ÿ�����ʶ�Ӧ��Ƶ��
		    suffix = new HashMap<>();//��׺��ÿ�����ʶ�Ӧ��Ƶ��
		    prefix_total_word = 0;
		    middle_total_word = 0;
		    suffix_total_word = 0;
		    //2017/2/12�޸Ľ���
		    
		    //������ȡ�����ж�����
			int judge_end = SeedList.entity_pairs.size();
			System.out.println("�ڼ��ε�����"+iterator_flag);
			
			//��������������2��ʱ���ȱ���һ�Σ����������ô��
            if(iterator_flag == 2){
            	TempEntityStore entityStore2 = new TempEntityStore();
    			Iterator it2 = entityStore2.temp_entity.entrySet().iterator();
            	while(it2.hasNext()){
					 Entry entry = (Entry)it2.next();
					
					 String Entity = (String)entry.getKey();
					 //System.out.println(Entity);
					 
					 WriteIntoFile2(Entity);
					 
					 
					 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
					 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
					// System.out.println(GroupFiveTupleCollection);
					 
					 for(j = 0; j < GroupFiveTupleCollection.size(); j++){
						ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
						String test_string = "";//��Ȩ�ص�Ԫ���ϵ
						String test_relationship = "";//��ʵ���ϵ
						//System.out.println("=====");
						test_string +="{";
						for(int t = 0; t < test.size(); t++){
							test_string += "<"+test.get(t).word+","+test.get(t).weight+">"+",";
							//System.out.println(test.get(t).word);
							//System.out.println(test.get(t).weight);
						}
						test_string +="}";
						WriteIntoFile2(test_string);
						
						test_relationship += "{<"+Entity.substring(0,Entity.indexOf(","))+">,<";
						for(int t = AdjustParameter.PreWordNum; t < test.size() - AdjustParameter.SufWordNUm; t++){
							test_relationship += test.get(t).word + " ";
						}
						test_relationship += ">,<" + Entity.substring(Entity.indexOf(",")+1,Entity.length()) + ">";
						WriteIntoFile2(test_relationship);
					 }
			    }
            	System.out.println("������������2��ʱ�򣬱��������");
		    }
            //��������������2��ʱ�򱣴����
			
			//�������������ʵ���û�����ӣ���������� 
			if(judge_start == judge_end){
				//��������
				
			    System.out.println("����������"+iterator_flag);
			    
			    
				TempEntityStore entityStore = new TempEntityStore();
				
//				for(int k = 0; k < entityStore.entity_pairs.size(); k++){
//					System.out.println(entityStore.entity_pairs.get(k));
//				}
				
				Iterator it = entityStore.temp_entity.entrySet().iterator();
				
                
				while(it.hasNext()){
						 Entry entry = (Entry)it.next();
						
						 String Entity = (String)entry.getKey();
						 //System.out.println(Entity);
						 
						 WriteIntoFile(Entity);
						 
						 
						 ArrayList<ArrayList> GroupFiveTupleCollection = new ArrayList<>();
						 GroupFiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
						// System.out.println(GroupFiveTupleCollection);
						 
						 for(j = 0; j < GroupFiveTupleCollection.size(); j++){
							ArrayList<WeightTuples> test = GroupFiveTupleCollection.get(j);
							String test_string = "";//��Ȩ�ص�Ԫ���ϵ
							String test_relationship = "";//��ʵ���ϵ
							//System.out.println("=====");
							test_string +="{";
							for(int t = 0; t < test.size(); t++){
								test_string += "<"+test.get(t).word+","+test.get(t).weight+">"+",";
								//System.out.println(test.get(t).word);
								//System.out.println(test.get(t).weight);
							}
							test_string +="}";
							WriteIntoFile(test_string);
							
							test_relationship += "{<"+Entity.substring(0,Entity.indexOf(","))+">,<";
							for(int t = AdjustParameter.PreWordNum; t < test.size() - AdjustParameter.SufWordNUm; t++){
								test_relationship += test.get(t).word + " ";
							}
							test_relationship += ">,<" + Entity.substring(Entity.indexOf(",")+1,Entity.length()) + ">";
							WriteIntoFile(test_relationship);
						 }
					   
					   //
				}
				System.out.println("extract entity finish");
				break;
			}
		}
	 }
	 
	 //����Ԫ���Ȩ��
	 public  void CalculateTupleWeight(TuplesStore tuplesStore){
		 int i,j;
		 //ʵ���
		 String Entity = "";
		 Iterator it = tuplesStore.Tuples.entrySet().iterator();
		 TuplesStore get_tuple_weight = new TuplesStore();
		 while(it.hasNext()){
			 Entry entry = (Entry)it.next();
			 //����������Ӧ�ļ�  
			 Entity = (String)entry.getKey(); 
			 String FirstEntity = "", SecondEntity = "";
			 FirstEntity = Entity.substring(0,Entity.indexOf(','));
			 SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
			 
			// System.out.println(Entity);
  		     
			 //���������Ӧ��value
			 ArrayList<ArrayList> FiveTupleCollection = new ArrayList<>();
			 FiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
			 
			// System.out.println(FiveTupleCollection);
			
			 //�𲽽�������Ԫ�飬����Ȩ��ֵ�ü���
			 ArrayList<String> FiveTuple = new ArrayList<>();
			 
			 for(i = 0; i < FiveTupleCollection.size(); i++){
			     FiveTuple = FiveTupleCollection.get(i);
			     
			     //System.out.println(FiveTuple); 
			     //����ÿ�����ʵ�Ȩ��
			     //ǰ׺Ȩ�ؼ���  
			     ArrayList<WeightTuples> weight_tuples = new ArrayList<>();
			     ArrayList<String> weight_tuples_test = new ArrayList<>();
			     
			     for(j = 0; j <= AdjustParameter.PreWordNum-1; j++){
			    	 WeightTuples word_weight = new WeightTuples();
			         word_weight.word = FiveTuple.get(j);
			         
			         //System.out.println(word_weight.word);
			         
			         if(prefix.get(word_weight.word) == null)
			        	 word_weight.weight = 0.0;
			         else
			             word_weight.weight = AdjustParameter.PreFactor*(prefix.get(word_weight.word).doubleValue()/(double)prefix_total_word);
			         
			         weight_tuples.add(word_weight);
			     }
			    
//			     System.out.println(weight_tuples.get(0).word);
			     
			     //��׺Ȩ�ؼ���
			     for(j = AdjustParameter.PreWordNum+1; j < FiveTuple.size()-AdjustParameter.SufWordNUm-1; j++){
			    	 WeightTuples word_weight = new WeightTuples();
			    	 word_weight.word = FiveTuple.get(j);
//			    	 System.out.println(word_weight.word);
			    	 
			    	 if(middle.get(word_weight.word) == null)
			    		 word_weight.weight = 0.0;
			    	 else
			             word_weight.weight = AdjustParameter.MidFactor*(middle.get(word_weight.word).doubleValue()/(double)middle_total_word);
			         weight_tuples.add(word_weight);
			         
			     }
			     
			     //��׺Ȩ�ؼ���
			     for(j = FiveTuple.size()-AdjustParameter.SufWordNUm; j <= FiveTuple.size()-1; j++){
			    	 WeightTuples word_weight = new WeightTuples();
			    	 word_weight.word = FiveTuple.get(j);
			    	 
//			    	 System.out.println(word_weight.word);
			    	 
			    	 if(suffix.get(word_weight.word) == null)
			    		 word_weight.weight = 0.0;
			    	 else
			    		 word_weight.weight = AdjustParameter.SuffFactor*(suffix.get(word_weight.word).doubleValue()/(double)suffix_total_word);
			    	 weight_tuples.add(word_weight);
			    	 
//			    	 System.out.println(word_weight.weight);
//			    	 
//			    	 System.out.println("caonima"+weight_tuples.get(0).word);
//			         System.out.println("caonima"+weight_tuples.get(0).weight);
			     }
			     
			     //�����￼���Ƿ�����ϴε���ʱʹ�õ�ģ�嵽ʵ�����
			     
			     //���������ɵ�Ȩ����Ԫ��
			     ArrayList<ArrayList> Tuple = new ArrayList<>();
	    		 Tuple.add(weight_tuples);
	    		 
	    		 
	    		 
//	    		 for(int t = 0; t < weight_tuples.size(); t++){
//	    		    System.out.println(weight_tuples.get(t).word);
//	    		    System.out.println(weight_tuples.get(t).weight);
//	    		 }
//	    		 System.out.println(Tuple);
			     
	    		 
	    		 
	    		 if(get_tuple_weight.getweightKey(Entity) != null){
	    			 get_tuple_weight.getweightKey(Entity).add(weight_tuples);			    	 
			     }else{
			    	 get_tuple_weight.putWeightTuples(Entity,Tuple);
			     }
			 }
			 
		 }
		 
		 GroupTuple group_tuple = new GroupTuple(); 
		 
		 
		// System.out.println("Ȩ������");
		 
		 it = get_tuple_weight.weight.entrySet().iterator();
		 while(it.hasNext()){
			 Entry entry = (Entry)it.next();
			
			 Entity = (String)entry.getKey();
			 //System.out.println(Entity);
			 
			 ArrayList<ArrayList> FiveTupleCollection = new ArrayList<>();
			 FiveTupleCollection = (ArrayList<ArrayList>) entry.getValue();
			 //System.out.println(FiveTupleCollection);
			 
			 //2016/11/29�Ķ�
			 //if(Entity.equals("arthur Conan Doyle,adventure of sherlock holme")){
			 //2016/11/29�Ķ�����
		     group_tuple.GetGroupTuple(Entity,FiveTupleCollection);
			 //2016/11/29�Ķ�
		     //}
			 //2016/11/29�Ķ�����
		 }
	     
		
		 
	 }
		   
}
