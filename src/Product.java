public class Product {
    String ID;
    String description;
    String name;
    Double cost;
    public Product(){

    }
    public Product(String inID, String inName, String inDescription, Double inCost){
        ID= inID;
        name=inName;

        description=inDescription;
        cost= inCost;

    }

    public String getID() {
        return ID;
    }

    public Double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String toCSVDataRecord(){
        Vulture turkyVul = new Vulture();
        return turkyVul.padString(getID(),6)+", "+ turkyVul.padString(getName(),35)+", "+turkyVul.padString(getDescription(),75)+", "+getCost()+"\n";
    }
    public String getRecord(){

        return getID()+", "+ getName()+", "+getDescription()+", "+getCost();
    }

}
