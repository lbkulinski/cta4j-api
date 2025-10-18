package app.cta4j.bus.service;

import com.cta4j.client.BusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class VehicleService {
    private final BusClient busClient;

    @Autowired
    public VehicleService(BusClient busClient) {
        this.busClient = busClient;
    }
}
