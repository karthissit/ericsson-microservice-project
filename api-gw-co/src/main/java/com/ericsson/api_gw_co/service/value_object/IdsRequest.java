package com.ericsson.api_gw_co.service.value_object;

import java.util.List;

public class IdsRequest {
    private List<Long> ids;

    public IdsRequest(List<Long> ids) {
        this.ids = ids;
    }

    // Getters and setters
    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}

