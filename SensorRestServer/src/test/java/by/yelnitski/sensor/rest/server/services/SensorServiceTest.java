package by.yelnitski.sensor.rest.server.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import by.yelnitski.sensor.rest.server.models.Sensor;
import by.yelnitski.sensor.rest.server.repositories.SensorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SensorServiceTest {

  @Mock
  private SensorRepository sensorRepository;

  private SensorService sensorService;

  @Before
  public void setup() {
    sensorService = new SensorService(sensorRepository);
  }

  @Test
  public void findByName_returnsSensor() {
    var sensor = new Sensor();
    sensor.setName("test sensor");
    when(sensorRepository.findByName("test sensor")).thenReturn(Optional.of(sensor));

    Optional<Sensor> testSensor = sensorService.findByName("test sensor");

    assertThat(testSensor).isPresent();
    assertThat(testSensor.get().getName()).isEqualTo("test sensor");
  }

  @Test
  public void findByName_returnsEmpty() {
    when(sensorRepository.findByName("test sensor")).thenReturn(Optional.empty());

    Optional<Sensor> sensor = sensorService.findByName("test sensor");

    assertThat(sensor).isNotPresent();
  }

  @Test
  public void register_savesSensor() {
    Sensor sensor = new Sensor();
    sensor.setName("test sensor");

    sensorService.register(sensor);

    org.mockito.Mockito.verify(sensorRepository).save(sensor);
  }
}
