package com.fup.accoffe.models

class EstateInfoModel {
    //planting
    var p_year: String? = null
    var p_albedo: Double? = null
    var p_promedio_inso: Double? = null
    var p_capa_admos: Double? = null
    var p_densidad_aire: Double? = null
    var p_vel_viento: Double? = null
    var p_duracion_cult: Double? = null
    var p_evaporacion_poten: Double? = null
    var p_coefi_cult: Double? = null
    var p_energia_libre_gibbs: Double? = null
    var p_aspec_hidrico: Double? = null
    var p_agua: Double? = null
    var p_promedio_altura: Double? = null
    var p_densidad: Double? = null
    var p_transpiracion: Double? = null
    var p_perdida_suelo: Double? = null
    var p_cont_materia_orga: Double? = null
    var p_ferti_nitro: Double? = null
    var p_ferti_fosfato: Double? = null
    var p_ferti_potacio: Double? = null
    var p_urea: Double? = null
    var p_cal: Double? = null
    var p_materia_orga: Double? = null
    var p_semillas: Double? = null
    var p_maq_man: Double? = null
    var p_pest_fung: Double? = null
    var total_p: Double? = null
    var area_finca: Double? = null

    //planting
    var dos_anios_p_f1: Double? = null
    var dos_anios_p_f2: Double? = null
    var dos_anios_p_f3: Double? = null
    var dos_anios_p_f4: Double? = null
    var dos_anios_p_f5: Double? = null
    var dos_anios_p_f6: Double? = null
    var dos_anios_p_f7: Double? = null
    var dos_anios_p_f8: Double? = null
    var dos_anios_p_f9: Double? = null
    var dos_anios_p_f10: Double? = null
    var dos_anios_p_f11: Double? = null
    var dos_anios_p_f12: Double? = null
    var dos_anios_p_f13: Double? = null
    var dos_anios_p_f14: Double? = null
    var dos_anios_p_f15: Double? = null
    var dos_anios_p_f16: Double? = null
    var anio_f1_p: Double? = null
    var anio_f2_p: Double? = null
    var anio_f3_p: Double? = null
    var anio_f4_p: Double? = null
    var anio_f5_p: Double? = null
    var anio_f6_p: Double? = null
    var total_f1: Double? = null
    var total_f2: Double? = null
    var total_f3: Double? = null
    var total_f4: Double? = null
    var total_f5: Double? = null
    var total_f6: Double? = null
    var total_f7: Double? = null
    var total_f8: Double? = null
    var total_f9: Double? = null
    var total_f10: Double? = null
    var total_f11: Double? = null
    var total_f12: Double? = null
    var total_f13: Double? = null
    var total_f14: Double? = null
    var total_f15: Double? = null
    var total_f16: Double? = null
    var transformidad: Double? = null
    var transformidad_f2: Double? = null
    var transformidad_f3: Double? = null
    var transformidad_f4: Double? = null
    var transformidad_f5: Double? = null
    var transformidad_f6: Double? = null
    var transformidad_f7: Double? = null
    var transformidad_f8: Double? = null
    var transformidad_f9: Double? = null
    var transformidad_f10: Double? = null
    var transformidad_f11: Double? = null
    var transformidad_f12: Double? = null
    var transformidad_f13: Double? = null
    var transformidad_f14: Double? = null
    var transformidad_f15: Double? = null
    var transformidad_f16: Double? = null

    //harvest
    var h_year: String? = null
    var h_maq_man: Double? = null
    var h_combustible: Double? = null
    var h_transport: Double? = null
    var l_Pjornal_recole: Double? = null

    var total_c: Double? = null
    var transformidad_f17: Double? = null
    var transformidad_f18: Double? = null
    var transformidad_f19: Double? = null
    var transformidad_c20: Double? = null
    var l_siembra_mante: Double? = null
    var anio_analizado_c1: Double? = null

    //preprocesing
    var b_num_costales: Double? = null
    var b_promed_electrico: Double? = null
    var b_val_infra: Double? = null
    var b_year: String? = null
    var b_val_maq: Double? = null
    var b_gf_Benef: Double? = null
    var b_dolar: Double? = null

    var total: Double? = null
    var flujo1: Double? = null
    var flujo2: Double? = null
    var flujo3: Double? = null
    var flujo4: Double? = null
    var transformidad_t1: Double? = null
    var transformidad_t2: Double? = null
    var transformidad_t5: Double? = null
    var t1: Double? = null
    var t2: Double? = null
    var t3: Double? = null
    var t4: Double? = null
    var t5: Double? = null

    //drying
    var d_year: String? = null
    var d_produccion: Double? = null
    var d_dias_secado: Double? = null
    var d_k: Double? = null
    var d_patio_secado: Double? = null
    var d_val_maq: Double? = null
    var d_gf_Benef: Double? = null
    var d_combustible: Double? = null
    var d_val_infra: Double? = null
    var d_promedio_electrico: Double? = null
    var flujo_anual_s1: Double? = null
    var flujo_anual_s2: Double? = null
    var flujo_anual_s3: Double? = null
    var flujo_anual_s4: Double? = null
    var flujo_anual_s5: Double? = null
    var flujo_anual_s6: Double? = null
    var flujo_anual_s7: Double? = null
    var flujo_anual_s8: Double? = null
    var flujo_anual_s9: Double? = null
    var flujo_anual_s30: Double? = null
    var transformidad_s3: Double? = null
    var transformidad_s4: Double? = null
    var transformidad_s5: Double? = null
    var transformidad_s6: Double? = null
    var transformidad_s7: Double? = null
    var transformidad_s8: Double? = null
    var transformidad_s9: Double? = null
    var transformidad_s30: Double? = null
    var total_s: Double? = null


    override fun toString(): String {
        return "EstateInfoModel(" +
                "p_year=$p_year, " +
                "p_albedo=$p_albedo, " +
                "p_promedio_inso=$p_promedio_inso, " +
                "p_capa_admos=$p_capa_admos, " +
                "p_densidad_aire=$p_densidad_aire, " +
                "p_vel_viento=$p_vel_viento, " +
                "p_duracion_cult=$p_duracion_cult, " +
                "p_evaporacion_poten=$p_evaporacion_poten, " +
                "p_coefi_cult=$p_coefi_cult, " +
                "p_energia_libre_gibbs=$p_energia_libre_gibbs, " +
                "p_aspec_hidrico=$p_aspec_hidrico, " +
                "p_agua=$p_agua, " +
                "p_promedio_altura=$p_promedio_altura, " +
                "p_densidad=$p_densidad, " +
                "p_transpiracion=$p_transpiracion, " +
                "p_perdida_suelo=$p_perdida_suelo, " +
                "p_cont_materia_orga=$p_cont_materia_orga, " +
                "p_ferti_nitro=$p_ferti_nitro, " +
                "p_ferti_fosfato=$p_ferti_fosfato, " +
                "p_ferti_potacio=$p_ferti_potacio, " +
                "p_urea=$p_urea, " +
                "p_cal=$p_cal, " +
                "p_materia_orga=$p_materia_orga, " +
                "p_semillas=$p_semillas, " +
                "p_maq_man=$p_maq_man, " +
                "p_pest_fung=$p_pest_fung, " +
                "total_p=$total_p, " +
                "area_finca=$area_finca, " +
                "dos_anios_p_f1=$dos_anios_p_f1, " +
                "dos_anios_p_f2=$dos_anios_p_f2, " +
                "dos_anios_p_f3=$dos_anios_p_f3, " +
                "dos_anios_p_f4=$dos_anios_p_f4, " +
                "dos_anios_p_f5=$dos_anios_p_f5, " +
                "dos_anios_p_f6=$dos_anios_p_f6, " +
                "dos_anios_p_f7=$dos_anios_p_f7, " +
                "dos_anios_p_f8=$dos_anios_p_f8, " +
                "dos_anios_p_f9=$dos_anios_p_f9, " +
                "dos_anios_p_f10=$dos_anios_p_f10, " +
                "dos_anios_p_f11=$dos_anios_p_f11, " +
                "dos_anios_p_f12=$dos_anios_p_f12, " +
                "dos_anios_p_f13=$dos_anios_p_f13, " +
                "dos_anios_p_f14=$dos_anios_p_f14, " +
                "dos_anios_p_f15=$dos_anios_p_f15, " +
                "dos_anios_p_f16=$dos_anios_p_f16, " +
                "anio_f1_p=$anio_f1_p, " +
                "anio_f2_p=$anio_f2_p, " +
                "anio_f3_p=$anio_f3_p, " +
                "anio_f4_p=$anio_f4_p, " +
                "anio_f5_p=$anio_f5_p, " +
                "anio_f6_p=$anio_f6_p, " +
                "total_f1=$total_f1, " +
                "total_f2=$total_f2, " +
                "total_f3=$total_f3, " +
                "total_f4=$total_f4, " +
                "total_f5=$total_f5, " +
                "total_f6=$total_f6, " +
                "total_f7=$total_f7, " +
                "total_f8=$total_f8, " +
                "total_f9=$total_f9, " +
                "total_f10=$total_f10, " +
                "total_f11=$total_f11, " +
                "total_f12=$total_f12, " +
                "total_f13=$total_f13, " +
                "total_f14=$total_f14, " +
                "total_f15=$total_f15, " +
                "total_f16=$total_f16, " +
                "transformidad=$transformidad, " +
                "transformidad_f2=$transformidad_f2, " +
                "transformidad_f3=$transformidad_f3, " +
                "transformidad_f4=$transformidad_f4, " +
                "transformidad_f5=$transformidad_f5, " +
                "transformidad_f6=$transformidad_f6, " +
                "transformidad_f7=$transformidad_f7, " +
                "transformidad_f8=$transformidad_f8, " +
                "transformidad_f9=$transformidad_f9, " +
                "transformidad_f10=$transformidad_f10, " +
                "transformidad_f11=$transformidad_f11, " +
                "transformidad_f12=$transformidad_f12, " +
                "transformidad_f13=$transformidad_f13, " +
                "transformidad_f14=$transformidad_f14, " +
                "transformidad_f15=$transformidad_f15, " +
                "transformidad_f16=$transformidad_f16, " +
                "l_siembra_mante=$l_siembra_mante" +

                //harvest
                "total_c=$total_c, " +
                "transformidad_f17=$transformidad_f17, " +
                "transformidad_f18=$transformidad_f18, " +
                "transformidad_f19=$transformidad_f19, " +
                "transformidad_f20=$transformidad_c20, " +
                "anio_analizado_c1=$anio_analizado_c1" +

                //preprocesing
                "total=$total, " +
                "t1=$t1, " +
                "t2=$t2, " +
                "t3=$t3, " +
                "t4$t4, " +
                "t5=$t5, " +
                "flujo1=$flujo1, " +
                "flujo2=$flujo2, " +
                "flujo3=$flujo3, " +
                "flujo4=$flujo4, " +
                "transformidad_t1=$transformidad_t1, " +
                "transformidad_t2=$transformidad_t2, " +
                "transformidad_t5=$transformidad_t5, " +

                //drying
                "flujo_anual_s1=$flujo_anual_s1, " +
                "flujo_anual_s2=$flujo_anual_s2, " +
                "flujo_anual_s3=$flujo_anual_s3, " +
                "flujo_anual_s4=$flujo_anual_s4, " +
                "flujo_anual_s5=$flujo_anual_s5, " +
                "flujo_anual_s6=$flujo_anual_s6, " +
                "flujo_anual_s7=$flujo_anual_s7, " +
                "flujo_anual_s8=$flujo_anual_s8, " +
                "flujo_anual_s9=$flujo_anual_s9, " +
                "flujo_anual_s30=$flujo_anual_s30, " +



                ")"
    }
}