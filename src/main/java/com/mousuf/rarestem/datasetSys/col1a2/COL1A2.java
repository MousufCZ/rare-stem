package com.mousuf.rarestem.datasetSys.col1a2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * These are all my code. Insipired by my own Team Project in Stage 2.
 * See City GitLab repository
 * */
@Getter
@Setter
@AllArgsConstructor
public class COL1A2 {
    private Integer variants_on_genome_id;
    private Integer chromosome;
    private Integer effect_id;
    private Integer position_g_start;
    private Integer position_g_end;
    private String average_frequency; // Mixed data
    private String variantOnGenome_DBID;
    private String variantOnGenome_DNA;
}
