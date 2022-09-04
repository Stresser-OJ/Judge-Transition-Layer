package com.chosanglyul.jtl.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository

interface FileRepository : R2dbcRepository<File, Long>
