package org.rmitestex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "accounts_table" )
public class Account {
    @Id
    private Integer id;
    @Column(name = "ammount")
    private Long ammount;

    public Account(Integer id, Long amount) {
        this.id=id;
        this.ammount=amount;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAmmount() {
        return ammount;
    }

    public void setAmmount(Long ammouunt) {
        this.ammount = ammouunt;
    }



}
