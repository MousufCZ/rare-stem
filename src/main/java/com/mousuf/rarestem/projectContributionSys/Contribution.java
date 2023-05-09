package com.mousuf.rarestem.projectContributionSys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contribution {
    private String projectName;
    private String projectDesc;
    private String projectOwner;
    private String email;
}
