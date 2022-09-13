package com.example.bank.SpringBankKotlin.repositories

import com.example.bank.SpringBankKotlin.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
}