import java.util.ArrayList;
import java.util.Scanner;

public class KeywordExtraction {

   /**
   * Javadoc example
   * @param parameter1 the first parameter
   * @return what is returned from this method
   */
  // TODO: Create 1-2 meaningful ArrayList 
private ArrayList<String> Article1;
private ArrayList<String> Article2;
private ArrayList<String> Article3;
private ArrayList<String> stopWords; 

/**
   * This returns Article1 as a public ArrayList
   * @return returns Article1 as a public ArrayList
   */
  public ArrayList<String> getArticle1() {
    return Article1;
  }
  /**
   * This returns Article2 as a public ArrayList
   * @return returns Article2 as a public ArrayList
   */
  public ArrayList<String> getArticle2() {
    return Article2;
  }
  /**
   * This returns Article3 as a public ArrayList
   * @return returns Article3 as a public ArrayList
   */
  public ArrayList<String> getArticle3() {
    return Article3;
  }

  /**
   * This creates ArrayLists from articles from the text file
   * @param parameter1 the first parameter
   * @return returns the text from the .txt files as an arrayList
   */
  
  // TODO: assigned initial values for your instance variables
  public KeywordExtraction() {
    Article1 = createwordList("Article1.txt");
    Article2 = createwordList("Article2.txt");
    Article3 = createwordList("Article3.txt");
    stopWords = createwordList("stopWords.txt");
    
    
  }
public ArrayList<String> createwordList(String fileName){
  TextProcessor tp = new TextProcessor(fileName);
  
  return tp.textToWords();
}
 /**
   * This iterates through an article and finds the most common word that is not a stopWord
   * @param ArticleName is the article that the user wants to find the most common word from
   * @return returns the most common topic/word aswell as how many times it showed up 
   */
public String findMostCommonTopic(ArrayList<String> ArticleName ){
  //create a variable for the max times a word shows up, and a variable for the word that shows up the most
  int maxWordCount  = 0;
  String mostCommonWord = "";
  //iterates through the article to compare the word with the rest of the article
  for(int i = 0; i< ArticleName.size(); i++){
    String currWord = ArticleName.get(i);
    int currWordCount = 0;
    //checks if currWord is not equal to any of the stopWords 
    if(isNotStopWord(currWord)){
      //iterates through the article making the current word the word we compare the currWord too
       for(int j = 0; j< ArticleName.size(); j++){
         String compareWord = ArticleName.get(j);
         //checks the if currWord is equal to the compareWord whilst ingnoring the capitilization.
         if(currWord.equalsIgnoreCase(compareWord)){
           currWordCount++; 
       
         }
       }
    }
    
         if(currWordCount >= maxWordCount){
           maxWordCount = currWordCount;
           mostCommonWord = currWord;
         }
    
  }
  return mostCommonWord + " Shows up: "+ maxWordCount + " times";
}

  
/**
   * This iterates through an article and finds the least common word that is not a stopWord
   * @param ArticleName is the article that the user wants to find the least common word from
   * @return returns the least common topics aswell as how many times it showed up 
   */
public String findLeastCommonTopic(ArrayList<String> ArticleName ){
  //create a variable for the least times a word shows up, and a variable for the word that shows up the most
 
  int leastWordCount  = 9999;
  String leastCommonWord = "";
  //iterates through the article to compare the word with the rest of the article
  for(int i = 0; i< ArticleName.size(); i++){
    String currWord = removePunctuation(ArticleName.get(i));
    int currWordCount = 0;
    //checks if currWord is not equal to any of the stopWords 
    if(isNotStopWord(currWord)){
      //iterates through the article making the current word the word we compare the currWord too
       for(int j = 0; j< ArticleName.size(); j++){
         String compareWord = ArticleName.get(j);
         //checks the if currWord is equal to the compareWord whilst ingnoring the capitilization.
         if(removePunctuation(currWord).equalsIgnoreCase(removePunctuation(compareWord))){
           currWordCount++; 
         
         }
       }
    }
    
         if(currWordCount <= leastWordCount && isNotStopWord(currWord)){
           leastWordCount = currWordCount;
           leastCommonWord = currWord;
         }
    
  }
  return leastCommonWord + " Shows up: "+ leastWordCount + " times";
}
 /**
   * This removes the punctuation from the Articles  
   * @param word is the word that we are comparing the stop words to and should return false if the word is a stop word
   * @return returns true if the word is equal to a stop Word, and returns false if the word is not a stopWord 
   */
public String removePunctuation(String word) {
    int lastIndex = word.length() - 1;
    int periodIndex = word.indexOf(".");

    if (periodIndex != -1 && periodIndex == lastIndex) {
        // Return the string without the period
        return word.substring(0, periodIndex);
    }

    // If there's no period at the end, return the original string
    return word;
}


  
 /**
   * This evaluates if the word is equal to a stopWord 
   * @param word is the word that we are comparing the stop words to and should return false if the word is a stop word
   * @return returns true if the word is equal to a stop Word, and returns false if the word is not a stopWord 
   */
public boolean isNotStopWord(String word) {
//goes through the stop words arrayList 
    for (int i = 0; i < stopWords.size(); i++) {
      String stopWord = stopWords.get(i);
      //if the word is found to be equal to a stop word it returns false and true otherwise
      if (word.equalsIgnoreCase(stopWord)) {
        return false;
      }
    }
    return true;
  }


/**
   * This asks the user promts about if they want to see the msot or least common words in different Articles
   *  
   */
  
public void prompt() {
     // Create a Scanner object to read user input from the console
    Scanner input = new Scanner(System.in);
 KeywordExtraction nlp = new KeywordExtraction();
    /*
     Prompt were the user can choose between finding the most or least used word.
     After your chose the user has an option to select an article (1, 2, or 3)
      */
   
    System.out.println("Would you like to see the most or least used word in each article");
    
  String type = input.nextLine();

    System.out.println("Which article would you like to see 1, 2, or 3?");
    String articleNumber = input.nextLine();
    /*
     If and else statement to see if they want to see most or least.
     And generates appropriate response to get what user would like
       */
   
    if (type.equals("most")) {
      if(articleNumber.equals("1")){
        String a = nlp.findMostCommonTopic(nlp.getArticle1());
    System.out.println(a);
      }else if (articleNumber.equals("2")){
        String b = nlp.findMostCommonTopic(nlp.getArticle2());
    System.out.println(b);
      }else if(articleNumber.equals("3")){
        String c = nlp.findMostCommonTopic(nlp.getArticle3());
    System.out.println(c);
      }
    
    } else if (type.equals("least")) {
if(articleNumber.equals("1")){
        String d = nlp.findLeastCommonTopic(nlp.getArticle1());
    System.out.println(d);
      }else if (articleNumber.equals("2")){
        String e = nlp.findLeastCommonTopic(nlp.getArticle2());
    System.out.println(e);
      }else if(articleNumber.equals("3")){
        String f = nlp.findLeastCommonTopic(nlp.getArticle3());
    System.out.println(f);
      }


      
    } else {
      // Handles input other than most of least
      System.out.println("Sorry I didnt understand it.");
    }
  // Message to end of
    System.out.println("Goodbye!");
   // Closes the scanner, to free the system resources
    input.close();
   
  }



}