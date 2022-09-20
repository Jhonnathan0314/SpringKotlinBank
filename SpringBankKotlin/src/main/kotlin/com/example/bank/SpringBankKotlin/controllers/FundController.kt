package com.example.bank.SpringBankKotlin.controllers

import com.example.bank.SpringBankKotlin.models.Fund
import com.example.bank.SpringBankKotlin.models.Client
import com.example.bank.SpringBankKotlin.repositories.ClientRepository
import com.example.bank.SpringBankKotlin.repositories.FundRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RequestMapping(value = ["fund"])
@RestController
class FundController {
    //Declaracion de variables
    @Autowired
    private val fundRepository: FundRepository? = null

    @Autowired
    private val clientRepository: ClientRepository? = null

    @GetMapping(value = [""])
    fun findAll(): List<Fund?>? {
        return fundRepository!!.findAll()
    }

    @GetMapping(value = ["{id}"])
    fun findById(@PathVariable id: Int): Fund? {
        return fundRepository!!.findById(id).orElse(null)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/client/{idClient}"])
    fun create(@RequestBody fund: Fund?, @PathVariable idClient: Int): Fund? {
        val client: Client = clientRepository!!.findById(idClient).orElse(null)
        return if (fund != null && client != null) {
            fund.idClient = client
            fundRepository?.save(fund)
        } else {
            null
        }
    }

    @PutMapping(value = ["{idFund}/client/{idClient}"])
    fun update(@PathVariable idFund: Int, @PathVariable idClient: Int, @RequestBody newFund: Fund?): Fund? {
        val fund = fundRepository?.findById(idFund)?.orElse(null)
        val client: Client = clientRepository!!.findById(idClient).orElse(null)
        return if (fund != null && newFund != null) {
            fund.idFund = newFund.idFund
            fund.name = newFund.name
            fund.minInvest = newFund.minInvest
            fund.totalCash = newFund.totalCash
            fund.idClient = client
            fundRepository?.save(fund)
        } else {
            null
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = ["{id}"])
    fun delete(@PathVariable id: Int) {
        val fund = fundRepository?.findById(id)?.orElse(null)
        if (fund != null) {
            fundRepository?.deleteById(id)
        }
    }
}