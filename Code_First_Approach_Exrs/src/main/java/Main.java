import entity.Product;
import entity.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {


        EntityManager em = Persistence
                .createEntityManagerFactory("test")
                .createEntityManager();

        em.getTransaction().begin();



        em.getTransaction().commit();
    }
}
