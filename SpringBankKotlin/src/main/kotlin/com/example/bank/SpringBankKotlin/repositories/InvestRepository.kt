package com.example.bank.SpringBankKotlin.repositories

import com.example.bank.SpringBankKotlin.models.Invest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvestRepository : JpaRepository<Invest, Int> {
}