import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import structures.AssociativeArray;
import structures.KVPair;

/*
* This is a class that keeps track of the complete set of AAC mappings. 
* It stores the mapping of the images on the home page to the AACCategories, while 
* keeping track of the current category that is being displayed.
* 
* @author: Catie Baker
* @author: Candice Lu
*/

public class AACMappings {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /*
   * The current category we are in 
   */
    AACCategory curCategory;
    /*
     * All categories that are being displayed on the homepage
     */
    AssociativeArray<String,AACCategory> categories;
    //AACCategory categories;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
    public AACMappings(String filename){
        try {
            Scanner scanner = new Scanner(new File(filename));
            this.categories = new AssociativeArray<String,AACCategory>();
            //this.categories = new AACCategory("Home");

            while (scanner.hasNextLine()){
                String cur = scanner.nextLine();
                if (cur.charAt(0) != '>'){
                    String[] parts = cur.split(" ");
                    AACCategory newCat = new AACCategory(parts[1]);
                    //this.category = newCat;
                    this.curCategory = newCat;
                    this.categories.set(parts[0], newCat);
                    //this.category.set(parts[0]);
                } else {
                    String[] parts = cur.replaceAll(">", "").split(".png ");
                    String loc = parts[0] + ".png";
                    this.curCategory.addItem​(loc, parts[1]);
                }
            } 
            this.curCategory = null;
            scanner.close();
        } catch (Exception e){}
    } // AACMappings(filename)

  // +----------------+----------------------------------------------
  // | Methods |
  // +----------------+
    /*
     * If currently in home screen: create a new category with the given image and name.
     * If currently in another category: add the image and text to speak to the currently shown category
    */
    public void add​(String imageLoc, String text){
        try {
            if (this.curCategory == null) {
                this.categories.set(imageLoc, new AACCategory(text));
            } else {
                this.curCategory.addItem​(imageLoc, text);
            }
        } catch (Exception e){}
    } // add(imageLoc, text)	
   
    /*
     *Gets the current category
    */
    public String getCurrentCategory(){
        if (this.curCategory != null) {
            return this.curCategory.name;
        } 
        return "";
    } // getCurrentCategory()

    /*
     *Provides an array of all the images in the current category
     */
    public String[]	getImageLocs() {
        try {
            if (this.getCurrentCategory().equals("")) {
                String[] images = new String[this.categories.size()];
                images = this.categories.getKeys();
                /*
                for (int i = 0; i < this.categories.size(); i++){
                    images[i] = this.categories.getPairs()[i].getKey(); 
                }
                */
                return images;
            } else {
                return this.curCategory.getImages();
            }
        } catch(Exception e){}
        return new String[1];
    } // getImageLocs()

    /*
    * If at home screen: return the name of the category associated with that image 
    * and update the current category to that category 
    * If in a category: return the text to be spoken that is associated with that image.
    */
    public String getText​(String imageLoc){
        try {
            // if currently in homescreen
            if (this.curCategory == null) {
                //this.curCategory = home.;
                this.curCategory = this.categories.get(imageLoc);
                return this.curCategory.getCategory();
            // if not in homescreen
            } else {
                return this.curCategory.getText​(imageLoc);
            }
        } catch (Exception e){
            return "";
        }
    } // getText(imageLoc)

    /* 
     * Determines if the image represents a category or text to speak
    */
    public boolean isCategory​(String imageLoc) {
        try{
            this.categories.get(imageLoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    } // isCategory(imageLoc)

    /* 
     * Resets the current category of the AAC back to the default category
    */
    public void reset()	{
        this.curCategory = null;
    } // reset()

    /* 
     * Writes the ACC mappings stored to a file.
    */
    public void writeToFile​(String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            KVPair<String, AACCategory>[] cats = this.categories.getPairs();
            for (int i = 0; i < this.categories.size(); i++){
                writer.write(cats[i].getKey() + " " + cats[i].getValue().getCategory());
                writer.newLine();
                String[] keys = this.categories.getKeys();
                for (int j = 0; j < cats[i].getValue().items.size(); j++){
                    //AACCategory cat = cats[i].getValue();
                    writer.write(">" + keys[j] + " " + this.categories.get(keys[j]));
                    writer.newLine();
                }
                //cat.items.getPairs()[j].getKey();
                //cat.items.getPairs()[j].getValue()
            }
            writer.close();
        } catch (Exception e){
            return;
        }
    } // writeToFile(filename)

} // class AACMappings
