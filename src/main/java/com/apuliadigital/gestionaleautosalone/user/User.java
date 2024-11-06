package com.apuliadigital.gestionaleautosalone.user;

import com.apuliadigital.gestionaleautosalone.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "users", schema = "gestionale_autosalone")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnoreProperties({"department", "user"})
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created", nullable = false)
    private Instant created;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated")
    private Instant updated;

    @Column(name = "deleted")
    private Instant deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Byte getIsActive() {
        return isActive;
    }

    public boolean enabled() {
        return isActive != null && isActive == 1 && deleted == null;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public Instant getDeleted() {
        return deleted;
    }

    public void setDeleted(Instant deleted) {
        this.deleted = deleted;
    }

}