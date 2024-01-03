package com.fup.accoffe.models

data class HarvestModel(
    val id:String?="",
    val h_year: String?="",
    val h_maq_man: Double?=0.0,
    val h_combustible: Double?=0.0,
    val h_transport: Double?=0.0,
    val l_Pjornal_recole: Double?=0.0,
    val estateId: String?=""
)
