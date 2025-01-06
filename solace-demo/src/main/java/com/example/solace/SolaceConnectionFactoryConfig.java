package com.example.solace;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolaceConnectionFactoryConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        SolConnectionFactory factory = SolJmsUtility.createConnectionFactory();
        factory.setHost("${solace.jms.host}");
        factory.setVPN("${solace.jms.msgVpn}");
        factory.setUsername("${solace.jms.clientUsername}");
        factory.setPassword("${solace.jms.clientPassword}");
        return factory; // This will now work with jakarta.jms
    }
}

