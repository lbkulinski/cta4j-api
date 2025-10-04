package app.cta4j.busroute.service;

import app.cta4j.common.api.CtaBusApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DetourService {
    private final CtaBusApi ctaBusApi;

    @Autowired
    public DetourService(CtaBusApi ctaBusApi) {
        this.ctaBusApi = ctaBusApi;
    }
}
