package org.green.hr.model.request;

import lombok.Data;

@Data
public class QualificationSearch {

    private Short status = -1;
    private String keyword = "";

}
