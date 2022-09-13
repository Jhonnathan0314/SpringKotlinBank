package com.example.bank.SpringBankKotlin.controllers

import com.example.bank.SpringBankKotlin.models.Role
import com.example.bank.SpringBankKotlin.repositories.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@CrossOrigin("*")
@RequestMapping(value = ["role"])
@RestController
class RoleController {
    //Declaracion de variables
    @Autowired
    val roleRepository: RoleRepository? = null

    @GetMapping(value = [""])
    fun findAll(): List<Role>? {
        return roleRepository?.findAll()
    }

    @GetMapping(value = ["{id}"])
    fun findById(@PathVariable id: Int): Role? {
        return roleRepository?.findById(id)?.orElse(null)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = [""])
    fun create(@RequestBody role: Role): Role? {
        return if (role != null) {
            roleRepository?.save(role)
        } else {
            null
        }
    }

    @PutMapping(value = ["{id}"])
    fun update(@PathVariable id: Int, @RequestBody newRole: Role): Role? {
        val role = roleRepository?.findById(id)?.orElse(null)
        return if (role != null && newRole != null) {
            role.idRole = newRole.idRole
            role.name = newRole.name
            roleRepository?.save(role)
        } else {
            null
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = ["{id}"])
    fun delete(@PathVariable id: Int) {
        val role = roleRepository?.findById(id)?.orElse(null)
        if (role != null) roleRepository?.deleteById(id)
    }
}