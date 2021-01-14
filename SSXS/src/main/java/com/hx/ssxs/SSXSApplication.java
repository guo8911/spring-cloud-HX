package com.hx.ssxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jerome Guo
 */
@EnableFeignClients
@EnableTransactionManagement
@SpringBootApplication
public class SSXSApplication {
  public static void main(String[] args) {
    SpringApplication.run(SSXSApplication.class, args);
  }
}
