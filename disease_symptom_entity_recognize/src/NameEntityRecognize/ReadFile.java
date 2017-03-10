package NameEntityRecognize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
	public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {
        	   
    		    //IndentitySentence indentitySentence = new IndentitySentence();
    		    PreProcessSentence preProcessSentence = new PreProcessSentence();
    		    //��ȡ�ļ����е��ļ�
                File file = new File(filepath);
                if (!file.isDirectory()) {
//                        System.out.println("�ļ�");
                        System.out.println("path=" + file.getPath());
                        System.out.println("absolutepath=" + file.getAbsolutePath());
                	 //���зֶ����ļ�
        		        String FileName = "";
        		        String Sentence = "";
        		        Reader reader = null;
//                        System.out.println("name=" + file.getName());
                        //��ȡtxt�ļ��е�����
                        FileName = file.getAbsolutePath();
                        System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");  
                        // һ�ζ�һ���ַ�  
                        reader = new InputStreamReader(new FileInputStream(FileName));  
                        int tempchar = -1;  
                        while ((tempchar = reader.read()) != -1) {  
                            // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�  
                            // ������������ַ��ֿ���ʾʱ���ỻ�����С�  
                            // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�  
                            if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {  
                            	//if(((char) tempchar) == '.') {
                            	if(NonChiSplit.isSentence((char)tempchar)){
                            		Sentence = Sentence + ((char) tempchar);
                            		preProcessSentence.CleanSentence(Sentence);
                            		//indentitySentence.GetSentence(Sentence);  
                            		Sentence = "";
                            	}
                            	else{
                            		Sentence = Sentence + ((char) tempchar);
                            	}
                            }
                            //2017/2/20�޸ģ��������������������ʻ�ϳ�һ�����ʵĴ���
                            else{
                            	if(Sentence.length() > 0){
                            	   if(Sentence.indexOf(" ",Sentence.length()-1) < 0)
                            	      Sentence = Sentence + ' ';
                            	}
                            }
                            //2017/2/20�޸Ľ���
                        }  
                        reader.close();
//                        System.out.println("All Finish!");  

                } else if (file.isDirectory()) {
                        System.out.println("�ļ���");
                        String[] filelist = file.list();
                        for (int i = 0; i < filelist.length; i++) {
                                File readfile = new File(filepath + "\\" + filelist[i]);
                                if (!readfile.isDirectory()) {
//                                        System.out.println("path=" + readfile.getPath());
                                       System.out.println("absolutepath="
                                                        + readfile.getAbsolutePath());
                                	    //���зֶ����ļ�
                        		        String FileName = "";
                        		        String Sentence = "";
                        		        Reader reader = null;
                                        //System.out.println("name=" + readfile.getName());
                                        //��ȡtxt�ļ��е�����
                                        FileName = readfile.getAbsolutePath();
                                        System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");  
                                        // һ�ζ�һ���ַ�  
                                        reader = new InputStreamReader(new FileInputStream(FileName));  
                                        int tempchar = -1;  
                                        while ((tempchar = reader.read()) != -1) {  
                                            // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�  
                                            // ������������ַ��ֿ���ʾʱ���ỻ�����С�  
                                            // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�  
                                            if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {  
                                            	//if(((char) tempchar) == '.') {
                                            	if(NonChiSplit.isSentence((char)tempchar)){
                                            		Sentence = Sentence + ((char) tempchar);
                                            		preProcessSentence.CleanSentence(Sentence);
                                            		//indentitySentence.GetSentence(Sentence);  
                                            		Sentence = "";
                                            	}
                                            	else{
                                            		Sentence = Sentence + ((char) tempchar);
                                            	}
                                            }  
                                            //2017/2/20�޸ģ��������������������ʻ�ϳ�һ�����ʵĴ���
                                            else{
                                            	if(Sentence.length() > 0){
                                            	   if(Sentence.indexOf(" ",Sentence.length()-1) < 0)
                                            	      Sentence = Sentence + ' ';
                                            	}
                                           }
                                           //2017/2/20�޸Ľ���
                                        }  
                                        reader.close();
//                                        System.out.println("All Finish!");

                                } else if (readfile.isDirectory()) {
                                        readfile(filepath + "\\" + filelist[i]);
                                }
                        }

                }

        } catch (FileNotFoundException e) {
                System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
   }
}
