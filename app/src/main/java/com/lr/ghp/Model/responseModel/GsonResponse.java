package com.lr.ghp.Model.responseModel;

/**
 * Created by ghp on 7/9/2015.
 */
public class GsonResponse {
    private int EntityID;
    private String Context;
    private String PayOrder;

    public GsonResponse() {
    }

    public GsonResponse(int entityID, String context, String payOrder) {
        EntityID = entityID;
        Context = context;
        PayOrder = payOrder;
    }

    public int getEntityID() {
        return EntityID;
    }

    public String getContext() {
        return Context;
    }

    public String getPayOrder() {
        return PayOrder;
    }

    public void setEntityID(int entityID) {
        EntityID = entityID;
    }

    public void setContext(String context) {
        Context = context;
    }

    public void setPayOrder(String payOrder) {
        PayOrder = payOrder;
    }
}
