package NameEntityRecognize;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;



public class TrieDictionary
{
	  //���������ʸ��ڵ�
	  private TrieNode root_derivative = new TrieNode();
	  
	  //����importDict���������ֵ�
	  private TrieDictionary(String fn) {
	    importDict(fn);
	  }
	  
	  //����TrieDictionary���͵ĺ�����ʵ����һ���ļ���
	  public static TrieDictionary getInstance(String Entity) {
		TrieDictionary dict = null;   
		System.out.println("��ʵ��Դ����ֵ���");
	    if (dict == null)
	      dict = new TrieDictionary(Entity);
	      
	    return dict;
	  }
	
	  //�����ֵ��ļ��е�����
	  private boolean importDict(String Entity)
	  
	  {
	      System.out.println("Entity:"+Entity);
	      String FirstEntity = Entity.substring(0,Entity.indexOf(','));
	      String SecondEntity = Entity.substring(Entity.indexOf(',')+1,Entity.length());
	      add(FirstEntity); 
	      add(SecondEntity);
	     
          System.out.println("Construct Dict Finish!");
	      return true;
	  }
	  
	  //�����ֵ���
	  public void add(String term)
	  {
	    TrieNode p_derivative = this.root_derivative;
	    int i;
	    String word = "";
	    String word_derivative = "";
	    WordNetHelper getstem = new WordNetHelper();
	    for (i = 0; i < term.length(); ++i) {
		      char c = term.charAt(i);
		      if(c == ' '||i == (term.length()-1)){
		    	 
		    	  //���c�ǿո����Ѿ��õ�һ��word�������ֵ����ķ��������ֵ����Ĺ���
		    	  if(c== ' '){
		    		  word_derivative = getstem.findStem(word);
		    		  TrieNode pChild_derivative = null;
		    	      
		    	      //��������ʲ����ڣ��������ʼ��뵽�������ֵ���
		    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
		    	          pChild_derivative = new TrieNode(word_derivative);
		    	          //System.out.println("pChild:" + pChild.key);
		    	          p_derivative.childs.put(word_derivative, pChild_derivative);
		    	      }
		    	      
		    	      //ָ��ָ���word
		    	      p_derivative = pChild_derivative;
		    	  }
		    	  
		    	  //����Ǹö�������һ����ĸ���������һ������
		    	  else if(i == (term.length()-1)){
		    		  word += c; 
		    		  word_derivative = getstem.findStem(word);
		    		  
		    		  TrieNode pChild_derivative = null;
		    	      
		    	      //��������ʲ����ڣ��������ʼ��뵽�������ֵ���
		    	      if((pChild_derivative = (TrieNode)p_derivative.childs.get(word_derivative)) == null) {
		    	          pChild_derivative = new TrieNode(word_derivative);
		    	          //System.out.println("pChild:" + pChild.key);
		    	          p_derivative.childs.put(word_derivative, pChild_derivative);
		    	      }
		    	      
		    	      p_derivative = pChild_derivative;
		    	  }
		    	  //System.out.println(word);
		    	  word = "";
		      }
		      else{
		    	  word += c; 
		      }
	    }
	    p_derivative.bound = true;
	   // System.out.println("pChild:" + p.key +" "+"p.bound:"+" "+p.bound);
	   // System.out.println("pChild_derivative:" + p_derivative.key +" "+"p_derivative.bound:"+" "+p_derivative.bound);
	  }

	  public TrieNode getRootDerivative()
	  {
		  return this.root_derivative;
	  }
}
