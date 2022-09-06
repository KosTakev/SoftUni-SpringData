import entity.City;
import entity.User;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("softuni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        var sofia = new City();
//        sofia.setName("Sofia");
//
//        var pesho = new User();
//        pesho.setUsername("Pesho");
//        pesho.setPassword("1234");
//        pesho.setCity(sofia);
//
//        entityManager.persist(pesho);
        City city = entityManager.find(City.class, 1);
        entityManager.remove(city);

        entityManager.getTransaction().commit();
    }
}
