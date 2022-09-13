package com.example.bank.SpringBankKotlin.repositories

import com.example.bank.SpringBankKotlin.models.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
interface ClientRepository : JpaRepository<Client, Int> {
}