package com.fup.accoffe.models

data class DryingModel(
    val id:String?="",
    val d_year: String? = "",
    val d_produccion: Double? = 0.0,
    val d_dias_secado: Double? = 0.0,
    val d_k: Double? = 0.0,
    val d_patio_secado: Double? = 0.0,
    val d_val_maq: Double? =  0.0,
    val d_gf_Benef: Double? =0.0,
    val d_combustible: Double? = 0.0,
    val d_val_infra: Double? = 0.0,
    val d_promedio_electrico: Double? = 0.0,
    val l_Pdolar: Double? = 0.0,
    val p_enegia_lib: Double? = 0.0,
    val p_densidad2: Double? = 0.0,
    val p_viento: Double? = 0.0,
    val p_admosfra: Double? = 0.0,
    val p_densidadA: Double? = 0.0,
    val p_insolation: Double? = 0.0,
    val estateId:String?=""
)
