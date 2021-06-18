import entity.PersonEntity;

import javax.persistence.*;

//coding help taken from ttuckett and IntelliJ Idea Community (Dalia Abo Sheasha)

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            /*PersonEntity dan = new PersonEntity();
            dan.setId(3);
            dan.setFirstName("Dan");
            dan.setLastName ("Bird");
            entityManager.persist(dan);*/

            Query countPeople = entityManager.createNativeQuery("SELECT COUNT(*) from person");
            System.out.println("There are " + countPeople.getSingleResult() + " people in the database.");


            transaction.commit();
        }
        finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
