package com.example.bank.SpringBankKotlin.models

import javax.persistence.*

@Entity
@Table(name = "invest")
class Invest {
    //Declaracion de variables
    @Id
    var idInvest: Int
    var cashInvest: Int

    @OneToOne
    @JoinColumn(name = "id_client")
    var idClient: Client

    @OneToOne
    @JoinColumn(name = "id_fund")
    var idFund: Fund

    //Metodos constructores
    constructor(idInvest: Int, cashInvest: Int, idClient: Client, idFund: Fund) {
        this.idInvest = idInvest
        this.cashInvest = cashInvest
        this.idClient = idClient
        this.idFund = idFund
    }
}