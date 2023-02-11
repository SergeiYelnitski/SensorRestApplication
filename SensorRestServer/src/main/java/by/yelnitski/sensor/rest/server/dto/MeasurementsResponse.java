package by.yelnitski.sensor.rest.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;
}

