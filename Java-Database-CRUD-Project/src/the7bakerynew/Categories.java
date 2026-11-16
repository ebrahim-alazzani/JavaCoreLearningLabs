package the7bakerynew;


/**
 *
 * @author ASUS
 */
public class Categories {
private int CategorieId;
    private String CategorieName;

    public Categories(int CategorieId, String CategorieName) {
        this.CategorieId = CategorieId;
        this.CategorieName = CategorieName;
    }

   

   
    public Categories() {
    }

    Categories(String category_1) {
                this.CategorieName = category_1;

    }

    public int getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(
            int CategorieId) {
        this.CategorieId = CategorieId;
    }

    public String getCategorieName() {
        return CategorieName;
    }

    public void setCategorieName(
            String CategorieName) {
        this.CategorieName = CategorieName;
    }
    

    @Override
    public String toString() {
        return "" + CategorieId + " " + CategorieName + ' ';
    }


}