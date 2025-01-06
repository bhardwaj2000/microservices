package com.example.solaceToDB.SolaceToDB;

import com.solacesystems.jms.SolConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

import java.util.Hashtable;

@Slf4j
@Configuration
public class SolaceConfig {

    @Value("${solace.jms.host}")
    private String host;

    @Value("${solace.jms.clientUsername}")
    private String userName;
    @Value("${solace.jms.clientPassword}")
    private String password;
    @Value("${solace.jms.msgVpn}")
    private String vpn;
    @Value("${solace.jms.retry}")
    private String retry;

    @Bean("solace")
    public JmsComponent getJmsComponent(){
        SolConnectionFactory solConnectionFactory=null;
        try {
            solConnectionFactory = com.solacesystems.jms.SolJmsUtility.
                    createConnectionFactory(host,userName,password,vpn, new Hashtable<>(1));
            solConnectionFactory.setReconnectRetries(Integer.getInteger(retry));
        } catch (Exception e) {
            log.info("exception in connection ",e.getMessage());
        }
        solConnectionFactory.setDirectTransport(false);
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(solConnectionFactory);
        JmsConfiguration jmsConfiguration = new JmsConfiguration(cachingConnectionFactory);
        JmsComponent jmsComponent = new JmsComponent(jmsConfiguration);
        return jmsComponent;
    }
}
