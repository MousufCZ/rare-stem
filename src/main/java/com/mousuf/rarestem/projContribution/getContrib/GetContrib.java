package com.mousuf.rarestem.projContribution.getContrib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetContrib {
    private String project_name;
    private String project_description;
    private String project_owner;
    private String project_url;
}