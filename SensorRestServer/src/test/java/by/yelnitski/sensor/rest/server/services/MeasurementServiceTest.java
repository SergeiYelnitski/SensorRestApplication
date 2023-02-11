package by.yelnitski.sensor.rest.server.services;

import by.yelnitski.sensor.rest.server.models.Measurement;
import by.yelnitski.sensor.rest.server.models.Sensor;
import by.yelnitski.sensor.rest.server.repositories.MeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeasurementServiceTest {

  private MeasurementService measurementService;

  @Mock
  private MeasurementRepository measurementRepository;

  @Mock
  private SensorService sensorService;

  @BeforeEach
  public void setUp() {
    measurementService = new MeasurementService(measurementRepository, sensorService);
  }

  @Test
  public void testAddMeasurement() {
    Measurement measurement = new Measurement();
    Sensor sensor = new Sensor();
    sensor.setName("TestSensor");
    measurement.setSensor(sensor);
    measurement.setValue(23.0);

    when(sensorService.findByName("TestSensor")).thenReturn(Optional.of(sensor));

    measurementService.addMeasurement(measurement);

    assertNotNull(measurement.getMeasurementDateTime());
    assertEquals(sensor, measurement.getSensor());
  }
}
