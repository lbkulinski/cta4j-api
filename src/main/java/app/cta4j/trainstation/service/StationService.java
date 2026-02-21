package app.cta4j.trainstation.service;

import app.cta4j.trainstation.dto.StationDto;
import app.cta4j.trainstation.mapper.StationMapper;
import app.cta4j.trainstation.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class StationService {
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Autowired
    public StationService(
        StationRepository stationRepository,
        StationMapper stationMapper
    ) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }

    public List<StationDto> getStations() {
        return this.stationRepository.findAll()
                                     .stream()
                                     .map(this.stationMapper::toDomain)
                                     .toList();
    }
}
