
import structures.AssociativeArray;

//import java.lang.reflect.Constructor;

/* 
 * This is a class that represents a single category of items in the AAC, which stores the name of the category,
 * and the mapping between the image location and the text that should be spoken.
 * 
 * @author: Catie Baker
 * @author: Candice Lu
 */

public class AACCategory {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
    /*
     * The name of the category
     */
    String name;
    /*
     * Items in the category
     */
    AssociativeArray<String, String> items;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
    /* 
     * Creates a new empty category with the given name
    */
    public AACCategory(String name) {
        this.name = name;
        this.items = new AssociativeArray<String, String>();
    } // AACCategory(name)
    
  // +----------------+----------------------------------------------
  // | Methods |
  // +----------------+
    /*
     * Adds the mapping of the imageLoc to the text to the category.
     */
    public void addItem​(String imageLoc, String text){
        try {
            this.items.set(imageLoc, text);
        } catch (Exception e){}
    } // addItem(imageLoc, text)

    /*
     * Returns the name of the category
     */
    public String getCategory(){
        return this.name;
    } // getCategory()

    /* 
     * Returns the text associated with the given image loc in this category
     */
    public String getText​(String imageLoc){
        try {
            return this.items.get(imageLoc);
        } catch (Exception e){}
        return "";
    } // getText(imageLoc)

    /* 
     * Determines if the provided images is stored in the category, true if it is in the category, false otherwise
     */
    public boolean hasImage​(String imageLoc){
        try {
            this.items.get(imageLoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    } // hasImage(imageLoc)

    /* 
     * Returns an array of all the images in the category
     */
    public String[] getImages(){
        String[] images = new String[this.items.size()];
        images = this.items.getKeys();
        /*
        for (int i = 0; i < this.items.size(); i++){
            images[i] = this.items.getPairs()[i].getKey();
        }
        */
        return images;
    } // getImages()

} // class AACCategory
