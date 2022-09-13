package com.example.bank.SpringBankKotlin.repositories

import com.example.bank.SpringBankKotlin.models.Fund
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FundRepository : JpaRepository<Fund, Int> {

}