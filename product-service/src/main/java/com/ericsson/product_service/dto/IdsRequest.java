package com.ericsson.product_service.dto;

import java.util.List;

public class IdsRequest {
    private List<Long> ids;

    public IdsRequest() {
    }

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
