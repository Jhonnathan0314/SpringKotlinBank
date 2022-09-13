package com.example.bank.SpringBankKotlin.controllers

import com.example.bank.SpringBankKotlin.models.Client
import com.example.bank.SpringBankKotlin.repositories.ClientRepository
import com.example.bank.SpringBankKotlin.repositories.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RequestMapping(value = ["client"])
@RestController
class ClientController {
    //Declaracion de variables
    @Autowired
    private val clientRepository: ClientRepository? = null
    @Autowired
    private val roleRepository: RoleRepository? = null

    @GetMapping(value = [""])
    fun findAll(): List<Client>? {
        return clientRepository?.findAll()
    }

    @GetMapping(value = ["{id}"])
    fun findById(@PathVariable id: Int): Client? {
        return clientRepository?.findById(id)?.orElse(null)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["role/{idRole}"])
    fun create(@PathVariable idRole: Int, @RequestBody client: Client): Client? {
        val role = roleRepository?.findById(idRole)?.orElse(null)
        return if (client != null && role != null) {
            client.idRole = role
            clientRepository?.save(client)
        } else {
            null
        }
    }

    @PutMapping(value = ["{idClient}/role/{idRole}"])
    fun update(@PathVariable idClient: Int, @PathVariable idRole: Int, @RequestBody newClient: Client?): Client? {
        val client = clientRepository?.findById(idClient)?.orElse(null)
        val role = roleRepository?.findById(idRole)?.orElse(null)
        return if (client != null && newClient != null && role != null) {
            client.idClient = newClient.idClient
            client.name = newClient.name
            client.totalCash = newClient.totalCash
            client.idRole = role
            client.password = newClient.password
            clientRepository?.save(client)
        } else {
            null
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = ["{id}"])
    fun delete(@PathVariable id: Int) {
        val client = clientRepository?.findById(id)?.orElse(null)
        if (client != null) {
            clientRepository?.deleteById(id)
        }
    }
}