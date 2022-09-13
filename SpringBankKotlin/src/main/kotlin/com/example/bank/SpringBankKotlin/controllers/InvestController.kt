package com.example.bank.SpringBankKotlin.controllers

import com.example.bank.SpringBankKotlin.models.Client
import com.example.bank.SpringBankKotlin.models.Fund
import com.example.bank.SpringBankKotlin.models.Invest
import com.example.bank.SpringBankKotlin.repositories.ClientRepository
import com.example.bank.SpringBankKotlin.repositories.FundRepository
import com.example.bank.SpringBankKotlin.repositories.InvestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RequestMapping(value = ["invest"])
@RestController
class InvestController {
    //Declaracion de variables
    @Autowired
    private val investRepository: InvestRepository? = null

    @Autowired
    private val clientRepository: ClientRepository? = null

    @Autowired
    private val fundRepository: FundRepository? = null

    @GetMapping(value = [""])
    fun findAll(): List<Invest?>? {
        return investRepository!!.findAll()
    }

    @GetMapping(value = ["{id}"])
    fun findById(@PathVariable id: Int): Invest? {
        return investRepository!!.findById(id).orElse(null)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["client/{idClient}/fund/{idFund}"])
    fun create(@PathVariable idClient: Int, @PathVariable idFund: Int, @RequestBody invest: Invest?): Invest? {
        val client = clientRepository?.findById(idClient)?.orElse(null)
        val fund = fundRepository?.findById(idFund)?.orElse(null)
        return if (invest != null && client != null && fund != null) {
            if(invest.cashInvest < fund.minInvest){
               null
            }
            updateClientFundCash(client, fund, invest)
            invest.idClient = client
            invest.idFund = fund
            investRepository?.save(invest)
        } else {
            null
        }
    }

    @PutMapping(value = ["{idInvest}/client/{idClient}/fund/{idFund}"])
    fun update(
        @PathVariable idInvest: Int,
        @PathVariable idClient: Int,
        @PathVariable idFund: Int,
        @RequestBody newInvest: Invest?
    ): Invest? {
        val invest = investRepository?.findById(idInvest)?.orElse(null)
        val newClient = clientRepository?.findById(idClient)?.orElse(null)
        val newFund = fundRepository?.findById(idFund)?.orElse(null)
        return if (invest != null && newInvest != null && newClient != null && newFund != null) {
            if(invest.cashInvest < newFund.minInvest){
                null
            }
            updateClientCash(newClient, invest, newInvest)
            updateFundCash(newFund, invest, newInvest)
            invest.idInvest = newInvest.idInvest
            invest.cashInvest = newInvest.cashInvest
            invest.idClient = newClient
            invest.idFund = newFund
            investRepository?.save(invest)
        } else {
            null
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = ["{id}"])
    fun delete(@PathVariable id: Int) {
        val invest = investRepository?.findById(id)?.orElse(null)
        if (invest != null) {
            substractClientFundCash(invest)
            investRepository?.deleteById(id)
        }
    }

    private fun updateClientFundCash(client: Client, fund: Fund, invest: Invest) {
        val newClientTotalCash: Int = client.totalCash + invest.cashInvest
        val newFundTotalCash = fund.totalCash + invest.cashInvest
        client.totalCash = newClientTotalCash
        fund.totalCash = newFundTotalCash
        clientRepository?.save<Client>(client)
        fundRepository?.save(fund)
    }

    private fun updateClientCash(newClient: Client, invest: Invest, newInvest: Invest) {
        val oldClient = clientRepository?.findById(invest.idClient.idClient)?.orElse(null)
        if (newClient.idClient === oldClient?.idClient) {
            val newClientTotalCash: Int = newClient.totalCash - invest.cashInvest + newInvest.cashInvest
            newClient.totalCash = newClientTotalCash
            clientRepository?.save(newClient)
        } else {
            val newOldClientTotalCash: Int = oldClient?.totalCash?.minus(invest.cashInvest)!!
            val newNewClientTotalCash: Int = newClient.totalCash + newInvest.cashInvest
            oldClient.totalCash = newOldClientTotalCash
            newClient.totalCash = newNewClientTotalCash
            clientRepository?.save(oldClient)
            clientRepository?.save(newClient)
        }
    }

    private fun updateFundCash(newFund: Fund, invest: Invest, newInvest: Invest) {
        val oldFund = fundRepository?.findById(invest.idFund.idFund)?.orElse(null)
        if (newFund.idFund == oldFund?.idFund) {
            val newFundTotalCash = newFund.totalCash - invest.cashInvest + newInvest.cashInvest
            newFund.totalCash = newFundTotalCash
            fundRepository?.save(newFund)
        } else {
            val newOldFundTotalCash = oldFund?.totalCash?.minus(invest.cashInvest)
            val newNewFundTotalCash = newFund.totalCash + newInvest.cashInvest
            oldFund?.totalCash = newOldFundTotalCash!!
            newFund.totalCash = newNewFundTotalCash
            fundRepository?.save(oldFund)
            fundRepository?.save(newFund)
        }
    }

    private fun substractClientFundCash(invest: Invest) {
        val client = clientRepository?.findById(invest.idClient.idClient)?.orElse(null)
        val fund = fundRepository?.findById(invest.idFund.idFund)?.orElse(null)
        val newClientTotalCash: Int = client?.totalCash?.minus(invest.cashInvest)!!
        val newFundTotalCash = fund?.totalCash?.minus(invest.cashInvest)
        client?.totalCash = newClientTotalCash
        fund?.totalCash = newFundTotalCash!!
        clientRepository?.save(client!!)
        fundRepository?.save(fund)
    }
}