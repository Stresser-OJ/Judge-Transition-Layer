package com.chosanglyul.jtl.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository

interface TestCaseRepository : R2dbcRepository<TestCase, Long>
