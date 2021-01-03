package store;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class HbmStore implements Store {

    private static final Store INSTANCE = new HbmStore();
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public static Store getInstance() {
        return INSTANCE;
    }

    public <T> T wrapperOne(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void wrapperTwo(BiConsumer<Session, Car> function, Car arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    private void wrapperFour(BiConsumer<Session, CarsUsers> function, CarsUsers arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    private void wrapperThree(BiConsumer<Session, User> function, User arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addCar(Car car) {
        this.wrapperTwo(Session::save, car);
    }

    @Override
    public void addCarsUsers(CarsUsers carsUsers) {
        this.wrapperFour(Session::save, carsUsers);
    }

    @Override
    public void addBrand(Brand brand) {
        this.wrapperOne(session -> session.save(brand));
    }

    @Override
    public void addModel(Model model) {
        this.wrapperOne(session -> session.save(model));
    }

    @Override
    public void addTransmission(Transmission transmission) {
        this.wrapperOne(session -> session.save(transmission));
    }

    @Override
    public void addEngine(Engine engine) {
       this.wrapperOne(session -> session.save(engine));
    }

    @Override
    public void delete(Car car) {
        wrapperTwo(Session::delete, car);
    }

    @Override
    public void update(Car car) {
        wrapperTwo(Session::update, car);
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) this.wrapperOne(
                session -> session.createQuery("From User where email=:email")
                        .setString("email", email)
                        .uniqueResult()
        );
    }

    @Override
    public void createUser(User user) {
        this.wrapperOne(session -> session.save(user));
    }

    @Override
    public List<Model> findAllModels() {
        return this.wrapperOne(
                session -> session.createQuery("from Model").list()
        );
    }

    @Override
    public List<Brand> findAllBrands() {
        return this.wrapperOne(
                session -> session.createQuery("from Brand").list()
        );
    }

    @Override
    public List<Car> findAllCars() {
        return this.wrapperOne(
                session -> session.createQuery("from Car c ").list());
    }

    @Override
    public List<CarsUsers> findAllCarsUsers() {
        return this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand").list());
    }

    @Override
    public void updateUser(User user) {
        this.wrapperThree(Session::update, user);
    }

}
