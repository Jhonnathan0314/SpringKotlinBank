package com.example.bank.SpringBankKotlin.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role")
class Role {
    //Declaracion de variables
    @Id
    var idRole: Int
    var name: String

    //Metodos constructores
    constructor(idRole: Int, name: String) {
        this.idRole = idRole
        this.name = name
    }
}