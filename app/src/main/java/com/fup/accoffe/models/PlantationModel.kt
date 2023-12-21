package com.fup.accoffe.models

data class PlantationModel(
    val id:String? = "",
    val p_capa_admos: Double? = 0.0,
    val p_densidad_aire:Double? = 0.0,
    val p_promedio_altura:Double? = 0.0,
    val p_evaporacion_poten: Double? = 0.0,
    val estateId: String? = "",
    val p_cont_materia_orga:Double? = 0.0,
    val p_aspec_hidrico: Double? = 0.0,
    val p_vel_viento: Double? = 0.0,
    val l_siembra_mante: Double? = 0.0,
    val p_maq_man: Double? = 0.0,
    val p_ferti_potacio: Double? = 0.0,
    val p_coefi_cult: Double? = 0.0,
    val p_pest_fung: Double? = 0.0,
    val p_materia_orga: Double? = 0.0,
    val p_agua: Double? = 0.0,
    val p_cal: Double? = 0.0,
    val p_energia_libre_gibbs: Double? = 0.0,
    val p_promedio_inso: Double? = 0.0,
    val p_albedo: Double? = 0.0,
    val p_semillas: Double? = 0.0,
    val p_ferti_nitro: Double? = 0.0,
    val p_urea: Double? = 0.0,
    val p_ferti_fosfato: Double? = 0.0,
    val area_finca: Double? = 0.0,
    val p_transpiracion: Double? = 0.0,
    val p_duracion_cult: Double? = 0.0,
    val p_year: String? = "",
    val p_densidad: Double? = 0.0,
    val p_perdida_suelo: Double? = 0.0
)