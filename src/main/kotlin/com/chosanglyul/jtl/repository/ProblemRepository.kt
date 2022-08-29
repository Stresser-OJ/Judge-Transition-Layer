package com.chosanglyul.jtl.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface ProblemRepository : R2dbcRepository<Problem, Long> {
}
