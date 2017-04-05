package FeatureExtract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableServer.AdapterActivator;

import StaticConstant.AdjustParameter;
import StaticConstant.ConstantParameter;
import StaticConstant.Distant;
import StaticConstant.POSMatrix;
import edu.stanford.nlp.io.EncodingPrintWriter.out;

public class ExtractWordFeature {
    
    
    static String entity_start = "[entity_start]";
    static String entity_end = "[entity_end]";
    
	//�õ�word����
	public void GetWordVector(String Sentence){
		int i,j;
		String word = "";
		char c;
		
		//�Ƿ���ϴ��������
		int flag = 0;
		
		//��������ӵ�������������
		FeatureStore featureStore = new FeatureStore();
		featureStore.sentence = Sentence;//���ӱ��浽������
		
		FeatureStore SentenceFeatureVec = new FeatureStore();//�����Ӻ;��ӵ������������浽Feature���еľ�̬������
		
		//�õ�������ÿ��ʵ���λ��
	    int FirstEntityStartLocate = -1;
	    int FirstEntityEndLocate = -1;
	    
	    int SecondEntityStartLocate = -1;
	    int SecondEntityEndLocate = -1;
	    
	    int word_num = 0;
	    
	    for(j = 0; j < Sentence.length(); j++){
	    	c = Sentence.charAt(j);
	    	//������ַ�c�ǿո�����һ������
	    	if(c == ' '|| j == Sentence.length()-1){
	    		if(j == Sentence.length()-1){
	    			word += c;
	    		}
//	    		SentenceHash.put(word,word_num);
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
		//�õ�������ÿ�����ʵĴ�������pos����
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
//		    		}else if(word_num > FirstEntityStartLocate && word_num < FirstEntityEndLocate){//��һ��ʵ��
//		    			p1 = 0;
//		    			p2 = FirstEntityEndLocate - SecondEntityStartLocate;
		    		}else if(word_num > FirstEntityEndLocate && word_num < SecondEntityStartLocate){//��һ��ʵ��͵ڶ���ʵ��֮��ĵ���
						p1 = word_num - FirstEntityEndLocate;
						p2 = 0 - (SecondEntityStartLocate - word_num);
						flag = 1;
//					}else if(word_num > SecondEntityStartLocate && word_num < SecondEntityEndLocate){//�ڶ���ʵ��
//						p1 = FirstEntityEndLocate - SecondEntityStartLocate;
//						p2 = 0;
					}else if(word_num > SecondEntityEndLocate){//�ڶ���ʵ���ĵ���
						p1 = word_num - FirstEntityEndLocate;
						p2 = word_num - SecondEntityEndLocate;
						if(p2 <= AdjustParameter.SufWordNUm){//������ڵڶ���ʵ�����������֮��
							flag = 1;
						}
					}
		    		
		    		//System.out.println("word:"+ word + ":"+"distant:"+p1+":"+p2);
		    		
		    	    //�õ����ʵ�����
		    		if(flag == 1){//������㹹��������������
			    		if(ConstantParameter.WordVecHash.containsKey(word)){//��������ڴ������д���
	
	//		    			System.out.println("word:"+word);
	//		    			System.out.println(ConstantParameter.WordVecHash.get(word));
			    			ArrayList<Float> word_vec = new ArrayList<>();
			    			for(int k = 0; k < ConstantParameter.WordVecHash.get(word).size(); k++){//�õ����ʵĴ�����
		    				     word_vec.add(ConstantParameter.WordVecHash.get(word).get(k));
		    			    }
			    			//word_vec = ConstantParameter.WordVecHash.get(word);
			    			
	//		    			System.out.println("����������WordVecHash��"+ConstantParameter.WordVecHash.get(word).size());
	//		    			System.out.println("���������ȣ�"+word_vec.size());
			    			//System.out.println("WordVecHash:"+ word_vec);
			                
			    		
			    			
			    			word_vec = LinkDistantVec(word_vec,p1,p2);//���ú���ƴ��dis����
			    			word_vec = LinkPosVec(word_vec,word);//���ú���ƴ��pos����
			    			
			    			
			    			//System.out.println("WordVecHash:"+ word_vec);
			    			
			    			//System.out.println("���������ȣ�"+word_vec.size());
			    			//System.out.println("word_vec:"+ word_vec);
			    			//System.out.println("featureStore.vector+word_vec:"+ featureStore.vector.);
			    			if(featureStore.vector.size() == 0){
			    				for(int t = 0; t < word_vec.size(); t++){
			    				    featureStore.vector.add(word_vec.get(t));
			    				}
			    				//System.out.println("featureStore.vector+word_vec:"+ featureStore.vector);
			    			}else{
			    				for(int t = 0; t < featureStore.vector.size(); t++){
			    					featureStore.vector.set(t,featureStore.vector.get(t)+word_vec.get(t));
			    				}
			    			}
			    		}
		    		}
	    		}
	    		
	    		word_num ++;
	    		word = "";
	    	}else{
	    		word += c; 
	    	} 
	    }
		//System.out.println("featureStore.sentence:"+ featureStore.sentence);
		//System.out.println(featureStore.vector.size());
		//System.out.println("featureStore.vector:"+ featureStore.vector);
		
//		ClusterSentence clusterSentence = new ClusterSentence();//���þ��Ӿ��ຯ�����о���
//		clusterSentence.SentenceCluster(featureStore);
		if(featureStore.vector.size() != 0)
		   SentenceFeatureVec.SentenceVector.add(featureStore);//�������������ľ��ӱ��浽��̬����SentenceVector��
		
    }
	
	//ƴ�ӵ��ʺ�ʵ��λ�õ�����
	public ArrayList<Float> LinkDistantVec(ArrayList<Float> word_vec, int p1, int p2){
		
		int locate1 = -1, locate2 = -1;
		if(ConstantParameter.DisHash.containsKey(String.valueOf(p1))){//�õ���һ��p1
			locate1 = ConstantParameter.DisHash.get(String.valueOf(p1));
		}else{
			locate1 = 0;
		}
		
		//System.out.println("locate1:"+locate1);
		for(int i = 0; i < Distant.row; i++){
			word_vec.add(Distant.DisMatrix[i][locate1]);
			//System.out.println("Distant.DisMatrix:"+Distant.DisMatrix[i][locate1]);
		}
		
		
		if(ConstantParameter.DisHash.containsKey(String.valueOf(p2))){//�õ��ڶ���p2
			locate2 = ConstantParameter.DisHash.get(String.valueOf(p2));
		}else{
			locate2 = 0;
		}
		
		//System.out.println("locate2:"+locate2);
		for(int i = 0; i < Distant.row; i++){
			word_vec.add(Distant.DisMatrix[i][locate2]);
			//System.out.println("Distant.DisMatrix:"+Distant.DisMatrix[i][locate2]);
		}
		
		return word_vec;
	}
	
	//ƴ��pos����
	public ArrayList<Float> LinkPosVec(ArrayList<Float> word_vec,String word){
		WordTagger wordTagger = new WordTagger();//�õ����ʵĶ�Ӧ������0-1�����е�λ��
		try{
		    int locate = wordTagger.WordPOS(word);
		    
		    //System.out.println("locate:"+locate);
		    
		    for(int i = 0; i < POSMatrix.row; i++){
		    	word_vec.add(POSMatrix.PosMatrix[i][locate]);
		    	
		    	//System.out.println(POSMatrix.PosMatrix[i][locate]);
		    }
		}catch(Exception e){
		    System.out.println(e);
		}
		return word_vec;
	}
	
}
