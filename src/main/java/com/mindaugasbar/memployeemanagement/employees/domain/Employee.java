package com.mindaugasbar.memployeemanagement.employees.domain;

import com.mindaugasbar.memployeemanagement.authorization.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    protected enum Role {ADMINISTRATOR, MANAGER, EMPLOYEE}
    protected enum Gender {MALE, FEMALE}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @Min(10)
    @Max(120)
    @NotNull
    private Integer age;
    @Min(0)
    @Max(100)
    @NotNull
    @Column(name = "working_hours")
    private Integer workingHours;
    @NotNull
    @Column(name = "started_working_date")
    private LocalDate startedWorkingDate = LocalDate.now();
    @NotNull
    private Gender gender;
    private  boolean enabled = true;

    @NotNull
    @OneToOne(mappedBy = "employee")
    private User user;

    @OneToMany(mappedBy = "assignedToEmployee")
    @NotNull
    private List<Task> tasksAssigned = new ArrayList<>();

    @NotNull
    private int phone;

    @NotNull
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Task> getTasksToBeDone() {
        return tasksAssigned;
    }

    public void setTasksToBeDone(List<Task> tasksToBeDone) {
        this.tasksAssigned = tasksToBeDone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public LocalDate getStartedWorkingDate() {
        return startedWorkingDate;
    }

    public void setStartedWorkingDate(LocalDate startedWorkingDate) {
        this.startedWorkingDate = startedWorkingDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
