package com.car_shop.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="user")
public class User extends AbstractEntity implements UserDetails
{
    @Column(name="userName")
    private String name;

    @Column(name="userEmail")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="userAddress")
    private String address;

    @Enumerated
    private Role role;

    private boolean enable;

    private boolean block;

    private String uuId;

    @ManyToMany
    @JoinTable(name = "users_cars",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "carId"))
    private Set<Car> cars = new HashSet<>();

    @OneToMany(mappedBy = "user")

    private Set<Billable> billable = new HashSet<>();

    public User() {}

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public User(String name, String email, String password, String address)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Email: " + email + ", Password: " + password +
                ", Address: " + address + ", Role: " + role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getUsername()
    {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return block;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Billable> getBillable() {
        return billable;
    }

    public void setBillable(Set<Billable> billable) {
        this.billable = billable;
    }
}
