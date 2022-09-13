package com.example.bank.SpringBankKotlin.models

import javax.persistence.*


@Entity
@Table(name = "client")
class Client {
    //Declaracion de variables
    @Id
    var idClient: Int
    var name: String
    var totalCash: Int

    @OneToOne
    @JoinColumn(name = "id_role")
    var idRole: Role
    var password: String

    //Metodos constructores
    constructor(idClient: Int, name: String, totalCash: Int, idRole: Role, password: String) {
        this.idClient = idClient
        this.name = name
        this.totalCash = totalCash
        this.idRole = idRole
        this.password = password
    }
}