package com.chosanglyul.jtl.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository

interface SourceRepository : R2dbcRepository<Source, Long>
