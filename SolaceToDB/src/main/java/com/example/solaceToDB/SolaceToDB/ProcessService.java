package com.example.solaceToDB.SolaceToDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.Blob;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import javax.sql.rowset.serial.SerialBlob;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.v;

@Slf4j
@Service
public class ProcessService {

    @Autowired
    SolaceToDBRepository solaceToDBRepository;

    public void processMessage(Exchange exchange){

        log.info("Data received from solace", v("source","solace"));
        try{
            Map<String,Object> header = exchange.getIn().getHeaders();
            String jsonInput = exchange.getIn().getBody(String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            InputRQ inputRQ = objectMapper.readValue(jsonInput, InputRQ.class);
            AiroplaneEventData airoplaneEventData = new AiroplaneEventData();
            airoplaneEventData.setId(inputRQ.getRequestId());
//            byte[] compressHeader = header.toString().getBytes();
//            Blob headerProp=new Blob(compressHeader);
            airoplaneEventData.setHeaderProperties(header.toString());
            airoplaneEventData.setRawJson(jsonInput);
            airoplaneEventData.setSrcTimeStamp(inputRQ.getLastUpdated().toString());
            solaceToDBRepository.save(airoplaneEventData);
        } catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
