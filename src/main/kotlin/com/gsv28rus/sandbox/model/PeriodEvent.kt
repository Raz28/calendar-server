package com.gsv28rus.sandbox.model

import java.time.Instant

data class PeriodEvent(
        var startDate: Instant,
        var endDate: Instant
)