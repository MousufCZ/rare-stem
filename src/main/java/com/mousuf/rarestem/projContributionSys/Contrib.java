package com.mousuf.rarestem.projContributionSys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
public class Contrib {
    private String projectName;
    private String projectDesc;
    private String projectOwner;
    private String email;
}
