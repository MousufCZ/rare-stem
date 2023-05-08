package com.mousuf.rarestem.dataset.col1a2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class COL1A2 {
    private Integer variants_on_genome_id;
    private Integer allele;
    private Integer effect_id;
    private Integer position_g_start;
    private Integer position_g_end;
    private String average_frequence; // Mixed
    private String VariantOnGenome_DBID;
    private String VariantOnGenome_DNA;
}
