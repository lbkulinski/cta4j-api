package app.cta4j.busroute.service;

import app.cta4j.api.CtaBusApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ArrivalService {
    private final CtaBusApi ctaBusApi;

    @Autowired
    public ArrivalService(CtaBusApi ctaBusApi) {
        this.ctaBusApi = ctaBusApi;
    }
}
