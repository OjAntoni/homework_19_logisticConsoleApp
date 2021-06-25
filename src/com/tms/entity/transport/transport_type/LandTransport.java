package com.tms.entity.transport.transport_type;

public class LandTransport extends TransportType{
    @Override
    public String getType() {
        return "land";
    }

    @Override
    public int getTypeInt() {
        return 1;
    }
}
