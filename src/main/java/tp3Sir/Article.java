package tp3Sir;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.*;

@Entity("article")
public class Article {

    @Id
    private ObjectId id_art;
    private String name;
    private int stars;


    @Reference
    private List<Person> buyers;

    public Article(){
        buyers  = new ArrayList<Person>();


    }
    public ObjectId getId_art() {
        return id_art;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }
    public void setId_art(ObjectId id_art) {
        this.id_art = id_art;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Person> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Person> buyers) {
        this.buyers = buyers;
    }

    public void addBuyers(Person p){
        this.buyers.add(p);
    }

}
