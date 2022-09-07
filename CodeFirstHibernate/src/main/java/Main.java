

import entity.Batch;
import entity.Shampoo;
import entity.ShampooLabel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        EntityManager em = Persistence
                .createEntityManagerFactory("softuni")
                .createEntityManager();



        em.getTransaction().begin();

        Batch batch = new Batch();
        batch.setCreationOn(new Date());

        ShampooLabel label = new ShampooLabel();
        label.setName("Jojova");

        em.persist(batch);

        Shampoo s1 = new Shampoo();
        s1.setWeigh(200);
        s1.setBatch(batch);

        Shampoo s2 = new Shampoo();
        s2.setWeigh(300);
        s2.setBatch(batch);

        Shampoo s3 = new Shampoo();
        s3.setWeigh(400);
        s3.setBatch(batch);

        em.persist(s1);
        em.persist(s2);
        em.persist(s3);

        em.getTransaction().commit();
    }
}
