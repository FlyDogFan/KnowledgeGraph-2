package SentenceParser;

import java.awt.geom.FlatteningPathIterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GetNewEntity {
	 static String entity_start = "[entity_start]";
     static String entity_end = "[entity_end]";
     public void FindNewEntity(ArrayList<String> SentenceList){
    	 //����stanford-parser���о��ӽ���
    	 SentenceParser sentenceParser = new SentenceParser();
    	 char c;
    	 String word = "",word2 = "";
    	 int FirstEntityStartLocate = -1, FirstEntityEndLocate = -1, SecondEntityStartLocate = -1, SecondEntityEndLocate = -1,space = -1;
    	 String FirstEntity = "", SecondEntity = ""; 
    	 String[] TempEntity = new String[]{"",""};
    	 int i = 0, flag = 0;
    	 
    	 //��һ������ҵ����ڵ�ʵ�壬���Ժϲ�
    	 for(i = SentenceList.size()-1; i >= 0; ){//�Ӿ�β�����ײ��ұ�ע��ʵ��
    		 word = SentenceList.get(i);
    		 if(word.equals(entity_end)){//�ҵ���һ��ʵ���ǽ����ı�־
                 for(int j = i - 1; j >= 0; j--){
                	 word2 = SentenceList.get(j);
                	 if(word2.equals(entity_start)){//�ҵ�һ��ʵ�忪ʼ�ı�־
                		 FirstEntity = SubString(SentenceList,j+1,i-1);//����ʵ���ݴ浽������
                		 if(j-1 > 0){//���û�е����ף�����Ž��в�ѯ���ҵ�ʵ��ǰ���ڵ�һ�����ʣ�����һ��ʵ��
                			 word = SentenceList.get(j-1);
                			 if(word.equals(entity_end)){//���ʵ��ǰ�����ڵ���һ��ʵ��
                				 for(int t = j-2; t >= 0; t--){
                					 word2 = SentenceList.get(t);
                					 if(word2.equals(entity_start)){//�ҵ���ǰʵ��Ŀ�ʼ���
                						 SecondEntity = SubString(SentenceList,t+1,j-2);
                						 TempEntity[0] = SecondEntity;
                						 TempEntity[1] = FirstEntity;
                						 if(sentenceParser.GetSentenceParser(TempEntity)){
                							 SentenceList.set(j-1, "");//��SecondEntity��������ʿ�
                                         	 SentenceList.set(j,"");//FirstEntity��ʼ����ÿ�
                                         	 i = t-1;
                                         	 //flag = 1;
                                         	 break;
                						 }else{
                							 i = j-1;
                							 //flag = 1;
                                         	 break;
                						 }
                					 }
                				 }
                				 break;
                			 }else{
                				 i = j-1;
                				 break;
                			 }
                		 }else{//�Ѿ������׵�һ��ʵ�������ʼ����ҵ��Ժ��Ѿ�������
                			 i = j-1;
                			 break;
                		 }
                	 }
                 }
    		 }else{
    			 i--;
    		 }
    	 }
    	 
    	 //�ڶ���������ҵ�ʵ��ǰ�ĵ�һ�����ʣ����ܷ�ϲ�
//    	 for(i = SentenceList.size()-1; i >= 0; ){//�Ӿ�β�����ײ��ұ�ע��ʵ��
//    		 word = SentenceList.get(i);
//    		 if(word.equals(entity_end)){//�ҵ���һ��ʵ���ǽ����ı�־
//                 for(int j = i - 1; j >= 0; j--){
//                	 word2 = SentenceList.get(j);
//                	 if(word2.equals(entity_start)){//�ҵ�һ��ʵ�忪ʼ�ı�־
//                		 FirstEntity = SubString(SentenceList,j+1,i-1);//����ʵ���ݴ浽������
//                		 if(j-1 > 0){//���û�е����ף�����Ž��в�ѯ���ҵ�ʵ��ǰ���ڵ�һ�����ʣ�����һ��ʵ��
//                			 word = SentenceList.get(j-1);
//                			 if(!word.equals(entity_end)){//���ʵ��ǰ�����ڵ���һ����ʵ�嵥��
//                                TempEntity[0] = word; 
//                                TempEntity[1] = FirstEntity;
//                                if(sentenceParser.GetSentenceParser(TempEntity)){//����ж�����ʵ��
//                                	SentenceList.set(j-1, entity_start);//����������λ����ʵ�忪ʼ��Ǵ���
//                                	SentenceList.set(j,word);//��ʵ�忪ʼ�������λ���õ��ʴ���
//                                	i = j-2;
//                                	//flag = 1;
//                                	break;
//                                }else{
//                                	i = j-2;
//                                	//flag = 1;
//                                	break;
//                                }
//                			 }
//                			 else{//���ʵ��ǰ�����ڵ���һ��ʵ��
////                				 for(int t = j-2; t >= 0; t--){
////                					 word2 = SentenceList.get(t);
////                					 if(word2.equals(entity_start)){//�ҵ���ǰʵ��Ŀ�ʼ���
////                						 SecondEntity = SubString(SentenceList,t+1,j-2);
////                						 TempEntity[0] = SecondEntity;
////                						 TempEntity[1] = FirstEntity;
////                						 if(sentenceParser.GetSentenceParser(TempEntity)){
////                							 SentenceList.set(j-1, "");//��SecondEntity��������ʿ�
////                                         	 SentenceList.set(j,"");//FirstEntity��ʼ����ÿ�
////                                         	 i = t-1;
////                                         	 //flag = 1;
////                                         	 break;
////                						 }else{
////                							 i = j-1;
////                							 //flag = 1;
////                                         	 break;
////                						 }
////                					 }
////                				 }
//                				 i = j-1;
//                				 break;
//                			 }
//                		 }else{//�Ѿ������׵�һ��ʵ�������ʼ����ҵ��Ժ��Ѿ�������
//                			 i = j-1;
//                			 break;
//                		 }
//                	 }
//                 }
//    		 }else{
//    			 i--;
//    		 }
//    	 }
    	 
    	 String MergeEntitySentence = "";
    	 int EntityNum= 0; //ʵ�����
    	 
    	 //���ϲ�ʵ���ľ��ӱ���
    	 for(int j = 0; j < SentenceList.size()-1; j++){
    		 if(!"".equals(SentenceList.get(j)))
    		    MergeEntitySentence += SentenceList.get(j) + " ";
    		 if(SentenceList.get(j).equals(entity_start)){
    			 EntityNum ++;
    		 }
    	 }
    	 MergeEntitySentence += SentenceList.get(SentenceList.size()-1);
    	// System.out.println("MergeEntitySentence:"+MergeEntitySentence);
    	 if(EntityNum == 2){
	           WriteSentenceIntoFile(MergeEntitySentence);
	     }else{
	    		DivideSentence(MergeEntitySentence,EntityNum-1);
	     }
            
     }
     
     //2017/2/7���־���
	  public void DivideSentence(String sentence, int i_label){
	    	 int i = 0;
	    	 String divide_sentence = "";
	    	 
	    	 int first_entity_start = 0, first_entity_end = 0;
	    	 int second_entity_start = 0, second_entity_end = 0;
	    	 int third_entity_start = 0, third_entity_end = 0;
	    	 int forth_entity_start = 0, forth_entity_end = 0;
	    	 while(i_label > 0){
	    		 first_entity_start = sentence.indexOf(entity_start,i);
	    		 first_entity_end = sentence.indexOf(entity_end,first_entity_start + entity_start.length()); 
	    		 
	    		 second_entity_start = sentence.indexOf(entity_start,first_entity_end + entity_end.length());
	    		 second_entity_end = sentence.indexOf(entity_end,second_entity_start + entity_start.length());
	    		 
	    		 third_entity_start = sentence.indexOf(entity_start,second_entity_end + entity_end.length());
	    		 third_entity_end = sentence.indexOf(entity_end,third_entity_start + entity_start.length());
	    		 
	             if(third_entity_start > 0){
	    		    divide_sentence = sentence.substring(i,third_entity_start-1);
	    		    WriteSentenceIntoFile(divide_sentence);
	             }else{
	            	divide_sentence = sentence.substring(i,sentence.length());
	            	WriteSentenceIntoFile(divide_sentence);
	             }
	    		 i = first_entity_end + entity_end.length() + 1;
	    		 i_label --;
	    	 }
	}
	//2017/2/7���־��ӽ���
     
     //��ƥ�䵽�ĵ�����д���ļ���
	  public static void WriteSentenceIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
			    fileWriter = new FileWriter("E:/�����ļ�/��̳���/disease_symptom_entity_recognize/recognize_entity_coprus/sentence_label.txt",true);
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
     
     public String SubString(ArrayList<String> SentenceList,int start,int end){
    	 String TempEntity = "";
    	 for(int i = start; i < end; i++){
    		 TempEntity += SentenceList.get(i) + " ";
    	 }
    	 TempEntity += SentenceList.get(end);
    	 
    	 return TempEntity;
     }
}
