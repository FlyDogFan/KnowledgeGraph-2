package NameEntityRecognize;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class IndentitySentence {
	//����TrieDictionary�����ֵ���
	public static TrieDictionary dict = null;
	public static MMSegger mmsegger = new MMSegger();
	public static int[] SentenceIndex = new int[2];//ָʾ�������
	//public static int SentenceIndexDerivative = 0;//ָʾ������ţ�����
	
	static {
	    dict = TrieDictionary.getInstance("E:/�����ļ�/��̳���/unify_entity_recognize/GlaucomaConcept.txt");
	}
	
	 //��ƥ�䵽�����д���ļ���
	 public static void WriteIntoFile(String glaucoma, String glaucoma2 ,int[] flag){
		    //д���ļ�
		    BufferedWriter bw1 = null;
		    BufferedWriter bw2 = null;
		    FileWriter fileWriter1 = null;
		    FileWriter fileWriter2 = null;
			try{
				if(flag[0] == 1){
					SentenceIndex[0] += 1;
			        fileWriter1 = new FileWriter("E:/�����ļ�/��̳���/unify_entity_recognize/sentence.txt",true);
			        bw1 = new BufferedWriter(fileWriter1);
					bw1.write(glaucoma);
					bw1.newLine(); 
				}
				
				if(flag[1] == 1){
					 SentenceIndex[1] += 1;
					 fileWriter2 = new FileWriter("E:/�����ļ�/��̳���/unify_entity_recognize/sentence_derivative.txt",true);
					 bw2 = new BufferedWriter(fileWriter2);
					 bw2.write(glaucoma2);
					 bw2.newLine();  
				}
			
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
			    try {
			    	if(flag[0] == 1)
			            bw1.close();
			    	if(flag[1] == 1)
			            bw2.close();
			    }catch (IOException e) {
			        e.printStackTrace();
			    }
			}
	}	
	
	public static void GetSentence(String Sentence){
		//System.out.println(Sentence);
		int[] flag  = mmsegger.seg(Sentence,dict,SentenceIndex);
		//System.out.println("flag[0]"+flag[0]);
		//System.out.println("flag[1]"+flag[1]);
		String Sentence1 = "";
		String Sentence2 = "";
		if(flag[0] == 1||flag[1] == 1){
			if(flag[0] == 1)
			   Sentence1 = String.valueOf(SentenceIndex[0]) + " " +Sentence;
			if(flag[1] == 1)
				Sentence2 = String.valueOf(SentenceIndex[1]) + " " +Sentence;
			WriteIntoFile(Sentence1, Sentence2 ,flag);
		}
		
		//System.out.println(mmsegger.seg(Sentence,dict));
	}
	
	
	//����������
	public static void main(String[] args){
		
		//���зֶ����ļ�
//		String FileName = "E:/�����ļ�/��̳���/unify_entity_recognize/character_new.txt";
//		String Sentence = "";
//		Reader reader = null;  
        try {
        	String filepath = "E:/�����ļ�/��̳���/unify_entity_recognize/medline coprus";
			ReadFile readFile = new ReadFile();
			if(readFile.readfile(filepath)){
				System.out.println("All finish");
			}
//            System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");  
//            // һ�ζ�һ���ַ�  
//            reader = new InputStreamReader(new FileInputStream(FileName));  
//            int tempchar = -1;  
//            while ((tempchar = reader.read()) != -1) {  
//                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�  
//                // ������������ַ��ֿ���ʾʱ���ỻ�����С�  
//                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�  
//                if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {  
//                	//if(((char) tempchar) == '.') {
//                	if(NonChiSplit.isSentence((char)tempchar)){
//                		Sentence = Sentence + ((char) tempchar);
//                		GetSentence(Sentence);  
//                		Sentence = "";
//                	}
//                	else{
//                		Sentence = Sentence + ((char) tempchar);
//                	}
//                }  
//            }  
//            reader.close();
//            System.out.println("All Finish!");  
        } catch (Exception e) {  
        	System.out.println("fail");
        	e.printStackTrace(); 
        }  
	}
}
