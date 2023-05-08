package com.mousuf.rarestem.dataset.gene;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Gene {
    private String gene_id;
    private String gene_type;
    private String name;
    private String ncbi_mRna_id;
    private String id_protein_ncbi;
    private String position_c_mrna_start;
    private String position_c_mrna_end;//
    private String position_c_cds_end;
    private String position_g_mrna_start;//
    private String position_g_mrna_end;//
}
