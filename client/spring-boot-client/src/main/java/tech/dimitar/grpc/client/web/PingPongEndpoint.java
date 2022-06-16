package tech.dimitar.grpc.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.dimitar.grpc.client.service.GRPCClientService;

@RestController
public class PingPongEndpoint {
    GRPCClientService grpcClientService;

    @Autowired
    public PingPongEndpoint(GRPCClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @GetMapping("/ping")
    public String ping() {
        return grpcClientService.ping();
    }
}