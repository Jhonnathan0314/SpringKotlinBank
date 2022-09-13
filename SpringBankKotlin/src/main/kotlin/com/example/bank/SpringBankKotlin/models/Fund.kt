package com.example.bank.SpringBankKotlin.models

import javax.persistence.Entity
import javax.persistence.Id
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

    //METODOS CONSTRUCTORES
    constructor(idFund: Int, name: String, minInvest: Int, totalCash: Int) {
        this.idFund = idFund
        this.name = name
        this.minInvest = minInvest
        this.totalCash = totalCash
    }
}