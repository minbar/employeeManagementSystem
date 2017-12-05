package com.mindaugasbar.memployeemanagement.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "temployee")
public class Employee {
    protected enum Role {ADMINISTRATOR, MANAGER, EMPLOYEE}
    protected enum Gender {MALE, FEMALE}

    @Id
    @GeneratedValue
    private int Id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Age")
    @Min(10)
    @Max(100)
    private int age;

    @Min(0)
    @Max(80)
    @Column(name = "WorkingHours")
    private int workingHours;

    @Column(name = "StartedWorking")
    private Date startedWorkingDate;

    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "Earned vacation days")
    private int earnedVacationDays = 0;

    private  boolean enabled = true;

    @Length(min = 5, max = 15)
    private String username;

    @Length(min = 10, max = 25)
    private String password;

    @OneToMany
    private List<Task> tasksToBeDone = new ArrayList<>();

    @OneToMany
    private List<Task> tasksDone = new ArrayList<>();

    private Role role;

    public List<Task> getTasksToBeDone() {
        return tasksToBeDone;
    }

    public void setTasksToBeDone(List<Task> tasksToBeDone) {
        this.tasksToBeDone = tasksToBeDone;
    }

    public List<Task> getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(List<Task> tasksDone) {
        this.tasksDone = tasksDone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public Date getStartedWorkingDate() {
        return startedWorkingDate;
    }

    public void setStartedWorkingDate(Date startedWorkingDate) {
        this.startedWorkingDate = startedWorkingDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getEarnedVacationDays() {
        return earnedVacationDays;
    }

    public void setEarnedVacationDays(int earnedVacationDays) {
        this.earnedVacationDays = earnedVacationDays;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
