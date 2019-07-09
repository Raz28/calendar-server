package com.gsv28rus.calendar.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant
import java.time.LocalDateTime

data class PeriodEvent(
        var startDate: Instant?,
        var endDate: Instant?
)