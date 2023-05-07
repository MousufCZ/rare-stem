package com.mousuf.rarestem.projContributionSys.getContrib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetContrib {
    private String tc_projectName;
    private String tc_projectDesc;
    private String tc_projectOwner;
    private String tc_projectURL;
}