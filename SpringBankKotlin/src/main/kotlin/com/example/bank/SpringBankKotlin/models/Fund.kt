package com.example.bank.SpringBankKotlin.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "fund")
class Fund {
    //Declaracion de variables
    @Id
    var idFund: Int
    var name: String
    var minInvest: Int
    var totalCash: Int

    @OneToOne
    @JoinColumn(name = "id_client")
    var idClient: Client

    //METODOS CONSTRUCTORES
    constructor(idFund: Int, name: String, minInvest: Int, totalCash: Int, idClient: Client) {
        this.idFund = idFund
        this.name = name
        this.minInvest = minInvest
        this.totalCash = totalCash
        this.idClient = idClient
    }
}