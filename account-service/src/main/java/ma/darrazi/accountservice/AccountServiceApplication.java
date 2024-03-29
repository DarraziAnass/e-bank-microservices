package ma.darrazi.accountservice;

import ma.darrazi.accountservice.clients.CustomerRestClient;
import ma.darrazi.accountservice.entities.BankAccount;
import ma.darrazi.accountservice.enums.AccountType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import ma.darrazi.accountservice.repository.BankAccountRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.allCustomers().forEach(c ->{

                List<BankAccount> bankAccountList = List.of(
                        BankAccount.builder()
                                .accountId(UUID.randomUUID().toString())
                                .type(AccountType.CURRENT_ACCOUNT)
                                .balance(47376 * Math.random())
                                .customerId(c.getId())
                                .customer(c) // not working dunno?!!
                                .currency("MAD")
                                .createAt(LocalDate.now())
                                .build(),
                        BankAccount.builder()
                                .accountId(UUID.randomUUID().toString())
                                .type(AccountType.SAVING_ACCOUNT)
                                .balance(47376 * Math.random())
                                .customerId(c.getId())
                                .customer(c)
                                .currency("MAD")
                                .createAt(LocalDate.now())
                                .build()
                );
                accountRepository.saveAll(bankAccountList);


            });
        };
    }

}
