package com.tms.entity.transport.transport_type;

public class AirTransport extends TransportType {
    @Override
    public String getType() {
        return "air";
    }

    @Override
    public int getTypeInt() {
        return 2;
    }
}
