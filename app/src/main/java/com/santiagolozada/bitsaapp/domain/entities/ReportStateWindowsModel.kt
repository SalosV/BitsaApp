package com.santiagolozada.bitsaapp.domain.entities

data class ReportStateWindowsModel(
    var numOpenWindows: Int = 0,
    var numClosedWindow: Int = 0,
    var openRightWings: Int = 0,
    var openLeftWings: Int = 0,
)
