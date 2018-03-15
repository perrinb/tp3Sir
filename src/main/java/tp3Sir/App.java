package tp3Sir;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import redis.clients.jedis.Jedis;

/**
 * TP3-SIR JPA MONGO
 * PERRIN Briac - RANNOU Nicolas
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException
    {
        App application = new App();
        application.mongoDb();
        application.redis1();
        application.redis2();
        try {
            application.redis3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        application.redis4();
    }

    public void mongoDb(){
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "test");

        Person nicolas = new Person();
        Person briac = new Person();
        nicolas.setName("Nicolas");
        briac.setName("Briac");

        Address addressN = new Address();
        addressN.setStreet("36 rue de baulieu");
        addressN.setCity("rennes");
        addressN.setPostCode("35700");
        addressN.setCountry("france");

        Address addressB = new Address();
        addressB.setStreet("3 rue du lac");
        addressB.setCity("rennes");
        addressB.setPostCode("35700");
        addressB.setCountry("france");

        Article a = new Article();
        a.setName("smartphone");
        a.setStars(10);

        Article a2 = new Article();
        a2.setName("computer");
        a2.setStars(15);

        a.addBuyers(nicolas);
        a2.addBuyers(briac);

        //set address
        nicolas.setAddress(addressN);
        briac.setAddress(addressB);
        // Save the POJO for persons do the same for articles and address
        ds.save(addressN);
        ds.save(addressB);
        ds.save(nicolas);
        ds.save(briac);
        ds.save(a);
        ds.save(a2);
        for (Person e : ds.find(Person.class))
            System.err.println(e);
    }

    public void redis1(){
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");

        System.err.println(value);
    }

    public void redis2(){
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.get("counter"));
        jedis.incr("counter");
        System.out.println(jedis.get("counter"));

    }

    public void redis3() throws InterruptedException {
        String cacheKey = "cachekey";
        Jedis jedis = new Jedis("localhost");
        // adding a new key
        jedis.set(cacheKey, "cached value");
        // setting the TTL in seconds
        jedis.expire(cacheKey, 15);
        // Getting the remaining ttl
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        Thread.sleep(1000);
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        // Getting the cache value
        System.out.println("Cached Value:" + jedis.get(cacheKey));

        // Wait for the TTL finishs
        Thread.sleep(15000);

        // trying to get the expired key
        System.out.println("Expired Key:" + jedis.get(cacheKey));

    }
    public void redis4() {
        String cacheKey = "languages";
        Jedis jedis = new Jedis("localhost");
        // Adding a set as value

        jedis.sadd(cacheKey, "Java");
        jedis.sadd(cacheKey, "C#");
        jedis.sadd(cacheKey, "Python");// SADD

        // Getting all values in the set: SMEMBERS
        System.out.println("Languages: " + jedis.smembers(cacheKey));
        // Adding new values
        jedis.sadd(cacheKey, "Java");
        jedis.sadd(cacheKey, "Ruby");
        // Getting the values... it doesn't allow duplicates
        System.out.println("Languages: " + jedis.smembers(cacheKey));
    }
}
