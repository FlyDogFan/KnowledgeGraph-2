package ReadEntityPairs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ExtractSeeds {
	String entity_start = "[entity_start]";
	String entity_end = "[entity_end]";
	
	//��ƥ�䵽�ĵ���д���ļ���
	public void WriteIntoFile(String glaucoma){
		    //д���ļ�
		    BufferedWriter bw = null;
		    FileWriter fileWriter=null;
			try{
				fileWriter = new FileWriter("E:/�����ļ�/��̳���/YYLRelationSeedExtraction/SeedRelation.txt",true);
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
	
	//��ȡ����ǩ�����е�ʵ��ԣ��õ�����
    public void ReadSentenceLabel(String FileName){
    	 HashMap<String, Integer>   EntityPairs = new HashMap<>();//ÿ��ʵ��Զ�Ӧ��Ƶ��
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
                	 //�ҵ������е�ʵ��λ��
	   		    	 int FirstEntityLocateStart = -1, FirstEntityLocateEnd = -1, SecondEntityLocateStart = -1, SecondEntityLocateEnd = -1;//ʵ��������е�λ��
	   				 FirstEntityLocateStart = Sentence.indexOf(entity_start);
	   				 FirstEntityLocateEnd = Sentence.indexOf(entity_end);
	   				    
	   				 SecondEntityLocateStart = Sentence.indexOf(entity_start,FirstEntityLocateEnd);
	   				 SecondEntityLocateEnd = Sentence.indexOf(entity_end,SecondEntityLocateStart);
	   				 
	   				 //��ȡ������ʵ��
	   			     String FirstEntity = "", SecondEntity = "";
	   			        
	   			     FirstEntity = Sentence.substring(FirstEntityLocateStart + entity_start.length() + 1,FirstEntityLocateEnd - 1);
	   			     SecondEntity = Sentence.substring(SecondEntityLocateStart + entity_start.length() + 1,SecondEntityLocateEnd - 1);
	   			     
	   			     //���ʵ����Ѿ����ڣ������ӵ��ʵ�����Ӧ��Ƶ��
	         		 if(EntityPairs.containsKey(FirstEntity+","+SecondEntity)){
	         		    	EntityPairs.put(FirstEntity+","+SecondEntity, EntityPairs.get(FirstEntity+","+SecondEntity) + 1);
	         		 }else{//��������Ӹ�ʵ��Ժ���Ƶ��
	         		    	EntityPairs.put(FirstEntity+","+SecondEntity,1);
	         		 }
	                 Sentence = "";
	              }else{
	                  Sentence = Sentence + ((char) tempchar);
	              } 
             }  
             reader.close();
             System.out.println(i); 

		 }catch(Exception e){
			 e.printStackTrace();
		 }
    	 
    	 //���ú�����ʵ��Խ�������
    	 SortEntityPairs(EntityPairs);
    }
    
    public void SortEntityPairs(HashMap<String, Integer>   EntityPairs){//����valueֵ�Ӹߵ�������
    	List<Map.Entry<String, Integer>> EntitySeed = new ArrayList<Map.Entry<String, Integer>>(EntityPairs.entrySet());
    	
    	//����
    	Collections.sort(EntitySeed, new Comparator<Map.Entry<String, Integer>>() {   
    	    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
    	        return (o2.getValue() - o1.getValue()); 
    	        //return (o1.getKey()).toString().compareTo(o2.getKey());
    	    }
    	}); 

    	//�����
    	for (int i = 0; i < EntitySeed.size(); i++) {
    	    String Entity = EntitySeed.get(i).toString();
    	    System.out.println(Entity);
    	    Entity = Entity.substring(0,Entity.indexOf("="));
    	    WriteIntoFile(Entity);
    	    if(i == 10){
    	    	break;
    	    }
    	    	
    	}
    }
}
