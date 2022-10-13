package com.syld.store;

import com.syld.store.dto.RoleDto;
import com.syld.store.services.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(RoleService roleService){
//		return runner -> {
//			roleService.save(new RoleDto(UUID.randomUUID().toString(),"role_user"));
//			roleService.save(new RoleDto(UUID.randomUUID().toString(),"role_admin"));
//		};
//	}

}
