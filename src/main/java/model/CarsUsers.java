package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars_users")
public class CarsUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createdTime;

    @NotNull
    private Boolean soldStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public CarsUsers() {

    }

    public CarsUsers(Date createdTime, Boolean soldStatus) {
        this.createdTime = createdTime;
        this.soldStatus = soldStatus;
    }

    public CarsUsers(Date createdTime, Boolean soldStatus, User user, Car car) {
        this.createdTime = createdTime;
        this.soldStatus = soldStatus;
        this.user = user;
        this.car = car;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(Boolean status) {
        this.soldStatus = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
