package com.fup.accoffe.models

data class PreProcessingModel(
    val id:String?="",
    val b_num_costales: Double? =0.0,
    val b_promed_electrico:Double? =0.0,
    val b_val_infra: Double? =0.0,
    val b_year: String? ="",
    val b_val_maq: Double? =0.0,
    val b_gf_Benef:Double? =0.0,
    val b_dolar:Double? =0.0,
    val estateId:String?=""
)
