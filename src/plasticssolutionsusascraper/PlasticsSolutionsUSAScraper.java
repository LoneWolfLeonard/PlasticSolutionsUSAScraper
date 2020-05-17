package plasticssolutionsusascraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PlasticsSolutionsUSAScraper {

    public static void main(String[] args) throws IOException {
        
     String URL = "https://www.plasticssolutionsusa.com/collections/cleaning-and-maintenance/products/on-cycle-mold-cleaner";
     String holder = "";
     String holder2 = "";
     String holder3 = "";
     String holder4 = "";
     String holder5 = "";
     String holder6 = "";
     String holder7 = "";
     String holder8 = "";
     String[] OptionsArrayType = new String[99999];
     String[] OptionsArrayPrice = new String[99999];
     String[] PartNumberArray = new String[99999];
     int counter1 = 0;
     int counter2 = 0;
     int recounter1 = 0;
     int filenamecounter = 0;
     String GimmeAName = "";
      File file = new File("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\Counter.txt"); 
      File file2 = new File("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\UrlLoader.txt"); 

//Phase -1 Choose the url to scrape
BufferedReader br2 = new BufferedReader(new FileReader(file2));                      

String line2 = "";
			while ((line2 = br2.readLine()) != null) 
                        {
                            URL = line2;
			}	            
                       br2.close();
//Phase 0 Deciding The Name of the CSV File
// Read The Counter      
      BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) 
                        {
                                filenamecounter = Integer.parseInt(line);  
			}
                        br.close();
                     GimmeAName = ("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheCSV\\PSUSAScraper" + filenamecounter + ".csv");    
                     filenamecounter = filenamecounter + 1;
//Save The New Number
   FileWriter fw2 = new FileWriter("C:\\Users\\tremanleo\\Documents\\NetBeansProjects\\PlasticsSolutionsUSAScraperLauncher\\HoldTheFiles\\Counter.txt");
   PrintWriter out2 = new PrintWriter(fw2);  

   out2.print(filenamecounter);

   out2.flush(); 
   //Close the Print Writer
   out2.close();       
   //Close the File Writer
   fw2.close();      
   
//Launch The Document Writer
File f = new File(GimmeAName);
FileWriter fw = new FileWriter(f);
PrintWriter out = new PrintWriter(fw);    

//Scrape The URL
    Document doc = Jsoup.connect(URL).timeout(1000 * 1000).get();
     Elements options = doc.select("option");
     Elements options2 = doc.select("option");
     Elements titles = doc.select("title");  

     //Grabs the title of the page
     for (Element option : options) 
{     
        holder = option.outerHtml();
        holder2 = holder.replaceAll("option data-sku=", "");
        holder3 = holder2.replaceAll("option selected data-sku=", "");
        holder4 = holder3.replaceAll("<", "");
        holder5 = holder4.replaceAll("\"", "");
        holder6 = holder5.replaceAll("- ", "");
        holder7 = holder6.replaceAll("-", "");        
        holder8 = holder7.replaceAll(" value=", "-");
            
        String[] part = holder8.split("-");
        String HolderPart0 = part[0]; 
        String HolderPart1 = part[1];

   
        PartNumberArray[counter2] = HolderPart0;
        //System.out.println("Part Number: " + PartNumberArray[counter2]);
            counter2++;
}
     //Scrapes options on the page
     //Then it removed the last 4 characters " USD" from each option.
     //Next it replaces "-Gallon" with " Gallon"
     //After that it splits each option into 2 strings by the - tag
for (Element option : options) {
      holder = option.text();

         holder2 = holder.substring(0, holder.length() - 4);
         holder3 = holder2.replaceAll("-Galon", " Galon");
         holder4 = holder3.replaceAll(",", "");
         holder5 = holder4.replaceAll("Solvent- ", ""); 
         holder6 = holder5.replaceAll("-lb", " lb"); 
         holder7 = holder6.replaceAll("-oz", " oz"); 
         holder8 = holder7.replaceAll("-Gallon", " Gallon");
         System.out.println(holder8);
//At some point I need to figure out why the $ replacement doesn't work...
String[] parts = holder8.split("-");        
String Holder4Part0 = parts[0]; 
String Holder4Part1 = parts[1];

            OptionsArrayType[counter1] = Holder4Part0;
            OptionsArrayPrice[counter1] = Holder4Part1;
            //System.out.println("Price: " + OptionsArrayPrice[counter1]);
        
            counter1++;
        }
while (recounter1 != counter1)
{
out.print(PartNumberArray[recounter1] + ",");
out.print(OptionsArrayPrice[recounter1] + "," + "\n");  
recounter1++;    
}
   out.flush(); 
   //Close the Print Writer
   out.close();       
   //Close the File Writer
   fw.close();    
    }    
    }