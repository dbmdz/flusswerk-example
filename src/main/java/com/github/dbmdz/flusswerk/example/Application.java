package com.github.dbmdz.flusswerk.example;

import com.github.dbmdz.flusswerk.framework.EnableFlusswerk;
import com.github.dbmdz.flusswerk.framework.FlusswerkApplication;
import com.github.dbmdz.flusswerk.framework.engine.Engine;

@EnableFlusswerk
public class Application extends FlusswerkApplication {

  public Application(Engine engine) {
    super(engine);
  }

  public static void main(String[] args) {
    FlusswerkApplication.run(Application.class, args);
  }
}
