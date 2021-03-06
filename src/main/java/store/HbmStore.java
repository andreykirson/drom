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

    private <T> void wrapper(BiConsumer<Session, T> function, T arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBrand(Brand brand) {
        this.wrapper(Session::update, brand);
    }

    @Override
    public void updateModel(Model model) {
        this.wrapper(Session::update, model);
    }

    @Override
    public void updateCarsUsers(CarsUsers carsUsers) {
        this.wrapper(Session::update, carsUsers);
    }


    @Override
    public void addCar(Car car) {
        this.wrapper(Session::save, car);
    }

    @Override
    public void addCarsUsers(CarsUsers carsUsers) {
        this.wrapper(Session::save, carsUsers);
    }

    @Override
    public void addBrand(Brand brand) {
        this.wrapper(Session::save, brand);
    }

    @Override
    public void addModel(Model model) {
        this.wrapper(Session::save, model);
    }

    @Override
    public void addTransmission(Transmission transmission) {
        this.wrapper(Session::save, transmission);
    }

    @Override
    public void updateTransmission(Transmission transmission) {
        this.wrapper(Session::update, transmission);
    }

    @Override
    public void addEngine(Engine engine) {
       this.wrapper(Session::save, engine);
    }

    @Override
    public void updateEngine(Engine engine) {
        this.wrapper(Session::update, engine);
    }

    @Override
    public void delete(Car car) {
        wrapper(Session::delete, car);
    }

    @Override
    public void deleteCarUser(CarsUsers carsUsers) {
        wrapper(Session::delete, carsUsers);
    }

    @Override
    public void update(Car car) {
        wrapper(Session::update, car);
    }

    @Override
    public void updateUser(User user) {
        this.wrapper(Session::update, user);
    }

    @Override
    public User findUserByEmail(String email) {
        return (User) this.wrapperOne(
                session -> session.createQuery("From User where email=:email")
                        .setString("email", email)
                        .uniqueResult()
        );
    }

    //    @Override
//    public CarsUsers findCarsUsersById(int id) {
//        String qry = "SELECT * FROM cars_users LEFT JOIN cars ON cars.id = cars_users.car_id"
//                + " LEFT JOIN users ON users.id = cars_users.user_id"
//                + " WHERE cars_users.id = " + id + " ";
//        return (CarsUsers) this.wrapperOne(
//                session -> session.createSQLQuery(qry).addEntity(CarsUsers.class).getSingleResult());
//    }

    @Override
    public CarsUsers findCarsUsersById(int id) {
        return (CarsUsers) this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand"
                        + " where cu.id = :id ").setInteger("id", id).getSingleResult());
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
    public List<CarsUsers> findCarsByUserId(int id) {
        return this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand"
                        + " where cu.user.id = :id ").setInteger("id", id).list());
    }

    @Override
    public List<CarsUsers> getAllByBrand(String brand) {
        return this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand"
                        + " where m.brand.name = :brand ").setString("brand", brand).list());

    }

   @Override
    public List<CarsUsers> getLastOfDay() {
        return this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand"
                        + " where cu.createdTime = current_date").list());
    }

    @Override
    public List<CarsUsers> getAllWithPhoto() {
        return this.wrapperOne(
                session -> session.createQuery("select distinct cu from CarsUsers cu "
                        + "join fetch cu.user "
                        + "join fetch cu.car c "
                        + "join fetch c.transmission "
                        + "join fetch c.engine "
                        + "join fetch c.model m "
                        + "join fetch m.brand"
                        + " where c.imagepath is not null").list());
    }
}
