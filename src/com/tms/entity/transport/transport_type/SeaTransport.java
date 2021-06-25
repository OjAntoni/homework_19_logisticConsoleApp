package com.tms.entity.transport.transport_type;

public class SeaTransport extends TransportType{
    @Override
    public String getType() {
        return "sea";
    }

    @Override
    public int getTypeInt() {
        return 3;
    }
}
