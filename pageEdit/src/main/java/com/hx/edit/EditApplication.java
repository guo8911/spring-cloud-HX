package com.hx.edit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jerome Guo
 */
@EnableFeignClients
@SpringBootApplication
public class EditApplication {
  public static void main(String[] args) {
    SpringApplication.run(EditApplication.class, args);
  }
}
