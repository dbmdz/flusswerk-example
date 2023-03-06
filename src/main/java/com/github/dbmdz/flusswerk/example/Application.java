package com.github.dbmdz.flusswerk.example;

import com.github.dbmdz.flusswerk.framework.EnableFlusswerk;
import com.github.dbmdz.flusswerk.framework.FlusswerkApplication;
import com.github.dbmdz.flusswerk.framework.engine.Engine;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFlusswerk
public class Application extends FlusswerkApplication {

  public Application(Engine engine) {
    super(Optional.ofNullable(engine));
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
